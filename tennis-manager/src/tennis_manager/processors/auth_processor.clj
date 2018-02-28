(ns tennis-manager.processors.auth-processor
  (:require [buddy.auth :refer [authenticated? throw-unauthorized]]
            [buddy.auth.accessrules :refer (success error)]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [clojure.string :as s]
            [ring.util.codec :as codec]
            [ring.util.response :as rr]
            [tennis-manager.content.page-layout :as layout]
            [tennis-manager.content.admin :as admin]
            [tennis-manager.data.auth-handler :as auth]
            [tennis-manager.data.auth-messages :as am]))


(defn unauthorized-handler
  [request metadata]
  (rr/response "Unauthorized request"))

(def auth-backend
  (session-backend {:unauthorized-handler unauthorized-handler}))

(defn any-access
  [request]
  (println "in any access ")
  true)

(defn authenticated-access
  [request]
  (let [session (:session request)
        session-id (:identity session)]
    (if-not (= (s/blank? session-id) true)
      (if (auth/session-valid? session-id)
        (do
          (println "authenticated-access session valid: " session-id)
          (auth/update-session-used-time session-id)
          true)
        (error am/SESSION_EXPIRED))
      (error am/NOT_AUTHENTICATED))))

(defn admin-access
  [request]
  (let [auth (authenticated-access request)]
    (if (= auth true)
      (let [session (:session request)
            session-id (:identity session)
            user (auth/get-user-from-session-id session-id)]
        (if (= (:user_type user) am/ADMIN_USER)
          true
          (error am/NOT_AUTHORIZED)))
      auth)))

(defn validate-user-credentials
  [request]
  (let [username (:username (:params request))
        password (:password (:params request))
        user (auth/get-user-with-password username password)]
    (if user
      (cond
        (> (:account_locked user) 0) (error am/LOGIN_LOCKED)
        (> (:account_disabled user) 0) (error am/LOGIN_DISABLED)
        (> (:force_password_change user) 0) (error am/CHG_PASSWORD)
        :else (error am/LOGIN_SUCCESS))
      (error am/LOGIN_FAILED))))

(defn authenticate-user-for-change-password
  "authenticate password for a change passord request"
  [request]
  (let [val (validate-user-credentials request)]
    (if (= (.get-value val) am/LOGIN_SUCCESS)
      (error am/CHG_PASSWORD)
      val)))

(defn on-error
  [request value]
  (let [errMsg (if-not (= (s/blank? value) true) value am/NOT_AUTHORIZED_MSG)]
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    (str "<h1 align='center' style='color:red'>" errMsg "</h1>")}))

(defn on-err-from-get
  [request value]
  (let [errMsg (if-not (= (s/blank? value) true) value am/NOT_AUTHORIZED_MSG)]
    {:status  200
     :headers {"Content-Type" "text/plain"}
     :body    (str {:status "failed" :status-code 400 :msg "User not logged in"})}))

(defn get-qs-parm
  "get a qs parm"
  [query-string elem]
  (if (= (s/blank? (val elem)) false)
    (conj query-string (str (subs (str (key elem)) 1) "=" (codec/url-encode (val elem))))
    query-string))

(defn get-redirect-url
  "create redirect URL from the error list hash map"
  [url-hash]
  (let [qs (s/join "&" (reduce #(get-qs-parm %1 %2) () (-> (dissoc url-hash :url :msg))))]
    (if (= (s/blank? qs) false)
      (str (:url url-hash) "?" qs)
      (:url url-hash))))

(defn logout-redirect
  "docstring"
  [request value]
  (let [error ((keyword value) am/authentication-error-list)
        session (:session request)
        redirect-url (get-redirect-url error)
        updated-session (dissoc session :identity)]
    (-> (rr/redirect redirect-url)
        (assoc :session updated-session))))

(defn login-redirect
  "redirect to next page depending on the outcome of the login attempt"
  [request value]
  (let [username (:username (:params request))]
    (auth/insert-login-attempt username value)
    (if (= value am/LOGIN_FAILED)
      (auth/lock-account? username))
    (if (or (= value am/LOGIN_SUCCESS) (and (= value am/LOGIN_LOCKED) (= (auth/account-unlocked? username) true)))
      (let [session (:session request)
            session-id (s/replace (str (java.util.UUID/randomUUID)) #"-" "")
            error ((keyword am/LOGIN_SUCCESS) am/authentication-error-list)
            updated-session (assoc session :identity session-id)
            redirect-url (get-redirect-url error)]
        (auth/update-user-last-login-time username)
        (auth/persist-session-id username session-id)
        (-> (rr/redirect redirect-url)
            (assoc :session updated-session)))
      (let [error ((keyword value) am/authentication-error-list)
            redirect-url (get-redirect-url (conj error {:username username}))]
        (rr/redirect redirect-url)))))

(defn password-counts
  "count new password characterstics"
  [ct-hash char]
  (cond
    (s/includes? "!@#$%&*_" (str char)) (update-in ct-hash [:special_chars] inc)
    (Character/isDigit char) (update-in ct-hash [:digits] inc)
    (Character/isUpperCase char) (update-in ct-hash [:upper] inc)
    (Character/isLowerCase char) (update-in ct-hash [:lower] inc)
    :else (update-in ct-hash [:none] inc)))

(comment "Wrote this function because I could not get regex to work :-(")
(defn validate-chg-password-input
  "Validates the input from change password.  This happens after validation of current password"
  [req-params]
  (let [new-password (:new_password req-params)
        confirm-password (:confirm_password req-params)
        username (:username req-params)
        pwd-map (reduce #(password-counts %1 %2) {:upper 0 :lower 0 :special_chars 0 :digits 0 :none 0} new-password)]
    (cond
      (< (count new-password) am/PASSWORD_MIN_LENGTH) am/CHG_PASSWORD_TOO_SHORT
      (not= (compare new-password confirm-password) 0) am/CHG_PASSWORDS_DO_NOT_MATCH
      (not (auth/new-password-different? username new-password)) am/CHG_PASSWORD_SAME
      (or (< (:upper pwd-map) 1)
          (< (:lower pwd-map) 2)
          (< (:special_chars pwd-map) 1)
          (< (:digits pwd-map) 1)) am/CHG_PASSWORD_COMPLEXITY_FAIL)))

(defn redirect-change-password
  "redirect on change password login error"
  [request error]
  (if (and (:session request) (auth/session-valid? (:identity (:session request))))
    (-> ((keyword error) am/authentication-error-list)
        (conj {:username (:username (:params request))})
        get-redirect-url
        rr/redirect
        (assoc :session (:identity (:session request)))))
  (-> ((keyword error) am/authentication-error-list)
      (conj {:username (:username (:params request))})
      get-redirect-url
      rr/redirect))

(defn change-password-redirect
  "redirect a change password request as appropriate"
  [request value]
  (cond
    (= value am/CHG_PASSWORD)
    (let [validation-error (validate-chg-password-input (:params request))]
      (if-not validation-error
        (do
          (auth/change-password (:username (:params request)) (:new_password (:params request)))
          (-> (redirect-change-password request am/CHG_PASSWORD_SUCCESS)
              (assoc :session (dissoc (:session request) :identity))))
        (-> ((keyword validation-error) am/authentication-error-list)
            (conj {:username (:username (:params request))})
            get-redirect-url
            rr/redirect)))
    (= value am/LOGIN_FAILED) (redirect-change-password request am/LOGIN_FAILED_CHG_PWD)
    :else (redirect-change-password request value)))

(defn user-validation-failed
  "Redirect if not authenticated"
  [request value]
  (if value
    (let [error ((keyword value) am/authentication-error-list)]
      (if (= value am/SESSION_EXPIRED)
        (let [user (auth/get-user-from-session-id (:identity (:session request)))]
          (rr/redirect (get-redirect-url (conj error {:username (:email user)})))))
      (rr/redirect (str (:url error))))))

(defn check-login-status
  "See if a user is already logged in.  It seems backwards but we want to fire the error handler if they are logged in so they get re-directed to the first landing page"
  [request]
  (if (= (authenticated-access request) true)
    (error am/LOGIN_SUCCESS)
    true))

(defn check-logout-status
  "See if a user is already logged in."
  [request]
  (if (= (authenticated-access request) true)
    (error am/LOGOUT_SUCCESS)
    true))

(defn redirect-to-main-page
  "redirects to the main landing page if a user requests the login page but is already logged in"
  [request value]
  (let [error ((keyword value) am/authentication-error-list)
        redirect-url (get-redirect-url error)]
    (rr/redirect redirect-url)))

(def rules [{:pattern        #"(^/login*)"
             :handler        check-login-status
             :on-error       redirect-to-main-page
             :request-method :get}
            {:pattern        #"(^/chgpassword*)|(^/availability-reply*)"
             :handler        any-access
             :request-method :get}
            {:pattern        #"(^/login$)"
             :handler        validate-user-credentials
             :on-error       login-redirect
             :request-method :post}
            {:pattern        #"(^/chgpassword$)"
             :handler        authenticate-user-for-change-password
             :on-error       change-password-redirect
             :request-method :post}
            {:pattern        #"(^/logout)"
             :handler        check-logout-status
             :on-error       logout-redirect
             :request-method :get}
            {:pattern        #"(^/mgr$)|(^/matches$)|(^/schedule$)|(^/roster$)"
             :handler        authenticated-access
             :on-error       user-validation-failed
             :request-method :get}
            {:pattern        #"(^/admin)"
             :handler        admin-access
             :on-error       user-validation-failed
             :request-method :get}
            {:pattern        #"(^/add-club$)|(^/add-season$)|(^/add-team$)|(^/load-schedule$)"
             :handler        admin-access
             :on-error       user-validation-failed
             :request-method :post}
            {:pattern        #"(^/testpagex)"
             :handler        admin-access
             :request-method :get}
            {:pattern        #"^/.*"
             :handler        authenticated-access
             :request-method :get
             :on-error       on-err-from-get}
            {:pattern  #"^/.*"
             :handler  authenticated-access
             :on-error user-validation-failed}])