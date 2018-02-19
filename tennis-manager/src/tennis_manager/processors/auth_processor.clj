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
            [tennis-manager.data.auth-handler :as auth]))

(def LOGIN_SUCCESS "0")
(def LOGOUT_SUCCESS "100")
(def CHG_PASSWORD_SUCCESS "200")
(def LOGIN_FAILED "1000")
(def LOGIN_LOCKED "1001")
(def LOGIN_DISABLED "1002")
(def CHG_PASSWORD "1003")
(def SESSION_EXPIRED "1004")
(def NOT_AUTHENTICATED "1005")
(def NOT_AUTHORIZED "1006")
(def LOGIN_FAILED_CHG_PWD "1007")
(def CHG_PASSWORD_FAILED "1008")

(def ADMIN_USER 1)
(def PASSWORD_MIN_LENGTH 8)

(def authentication-error-list {(keyword LOGIN_SUCCESS)        {:url "/mgr"}
                                (keyword LOGOUT_SUCCESS)       {:url "/login" :err "logoutsuccess" :msg "Logout successful"}
                                (keyword LOGIN_FAILED)         {:url "/login" :err "loginfailure" :msg "Login failed"}
                                (keyword LOGIN_LOCKED)         {:url "/login" :err "acctlocked" :msg "Account locked"}
                                (keyword LOGIN_DISABLED)       {:url "/login" :err "acctdisabled" :msg "Account disabled"}
                                (keyword CHG_PASSWORD)         {:url "/chgpassword" :err "chgpassword" :msg "Password change required"}
                                (keyword SESSION_EXPIRED)      {:url "/login" :err "sessionexpired" :msg "Login session timed out due to inactivity"}
                                (keyword NOT_AUTHENTICATED)    {:url "/login"}
                                (keyword NOT_AUTHORIZED)       {:url "/notauth"}
                                (keyword LOGIN_FAILED_CHG_PWD) {:url "/chgpassword" :err "chgpwdloginfail" :msg "Password invalid"}
                                (keyword CHG_PASSWORD_FAILED)  {:url "/chgpassword" :err "chgpasswordfailed"}
                                (keyword CHG_PASSWORD_SUCCESS) {:url "/login" :err "chgpasswordsuccess" :msg "Password changed"}})
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
      (do
        (if (auth/session-valid? session-id)
          (do
            (println "authenticated-access session valid: " session-id)
            (auth/update-session-used-time session-id)
            true)
          (error SESSION_EXPIRED)))
      (error NOT_AUTHENTICATED))))

(defn admin-access
  [request]
  (let [auth (authenticated-access request)]
    (if (= auth true)
      (let [session (:session request)
            session-id (:identity session)
            user (auth/get-user-from-session-id session-id)]
        (if (= (:user_type user) ADMIN_USER)
          true
          (error NOT_AUTHORIZED)))
      auth)))

(defn validate-user-credentials
  [request]
  (let [username (:username (:params request))
        password (:password (:params request))
        user (auth/get-user-with-password username password)]
    (if user
      (cond
        (> (:account_locked user) 0) (error LOGIN_LOCKED)
        (> (:account_disabled user) 0) (error LOGIN_DISABLED)
        (> (:force_password_change user) 0) (error CHG_PASSWORD)
        :else (error LOGIN_SUCCESS))
      (error LOGIN_FAILED))))

(defn authenticate-user-for-change-password
  "authenticate password for a change passord request"
  [request]
  (let [val (validate-user-credentials request)]
    (if (= (.get-value val) LOGIN_SUCCESS)
      (error CHG_PASSWORD)
      val)))

(defn on-error
  [request value]
  (let [errMsg (if-not (= (s/blank? value) true) value "Not Authorized")]
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    (str "<h1 align='center' style='color:red'>" errMsg "</h1>")}))

(defn on-err-from-get
  [request value]
  (let [errMsg (if-not (= (s/blank? value) true) value "Not Authorized")]
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
  (let [qs (s/join "&" (reduce #(get-qs-parm %1 %2) () (dissoc url-hash :url)))]
    (if (= (s/blank? qs) false)
      (str (:url url-hash) "?" qs)
      (:url url-hash))))

(defn logout-redirect
  "docstring"
  [request value]

  (let [error ((keyword value) authentication-error-list)
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
    (if (= value LOGIN_FAILED)
      (auth/lock-account? username))
    (if (or (= value LOGIN_SUCCESS) (and (= value LOGIN_LOCKED) (= (auth/account-unlocked? username) true)))
      (let [session (:session request)
            session-id (s/replace (str (java.util.UUID/randomUUID)) #"-" "")
            error ((keyword LOGIN_SUCCESS) authentication-error-list)
            updated-session (assoc session :identity session-id)
            redirect-url (get-redirect-url error)]
        (auth/update-user-last-login-time username)
        (auth/persist-session-id username session-id)
        (-> (rr/redirect redirect-url)
            (assoc :session updated-session)))
      (let [error ((keyword value) authentication-error-list)
            redirect-url (get-redirect-url (conj error {:username username}))]
        (rr/redirect redirect-url)))))

(def special-chars "!@#$%&*")

(defn password-counts
  "docstring"
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
      (< (count new-password) PASSWORD_MIN_LENGTH) (str "Password must be a least " PASSWORD_MIN_LENGTH " characters long")
      (not= (compare new-password confirm-password) 0) "Passwords do not match"
      (not (auth/new-password-different? username new-password)) "New password cannot be the same as the current password"
      (or (< (:upper pwd-map) 1)
          (< (:lower pwd-map) 2)
          (< (:special_chars pwd-map) 1)
          (< (:digits pwd-map) 1))
      (str "Password must contain at least<span style='color:blue'>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;1 upper case
            <br>&nbsp;&nbsp;&nbsp;&nbsp;2 lower case
            <br>&nbsp;&nbsp;&nbsp;&nbsp;1 number
            <br>&nbsp;&nbsp;&nbsp;&nbsp;1 special character</span><span style='color:purple'> " special-chars "</span>"))))

(defn redirect-change-password
  "redirect on change password login error"
  [request error]
  (if (and (:session request) (auth/session-valid? (:identity (:session request))))
    (-> ((keyword error) authentication-error-list)
        (conj {:username (:username (:params request))})
        get-redirect-url
        rr/redirect
        (assoc :session (:identity (:session request)))))
  (-> ((keyword error) authentication-error-list)
      (conj {:username (:username (:params request))})
      get-redirect-url
      rr/redirect))

(defn change-password-redirect
  "redirect a change password request as appropriate"
  [request value]
  (cond
    (= value CHG_PASSWORD) (let [validation-err-msg (validate-chg-password-input (:params request))]
                             (if (s/blank? validation-err-msg)
                               (do
                                 (auth/change-password (:username (:params request)) (:new_password (:params request)))
                                 (-> (redirect-change-password request CHG_PASSWORD_SUCCESS)
                                     (assoc :session (dissoc (:session request) :identity))))
                               (-> ((keyword CHG_PASSWORD_FAILED) authentication-error-list)
                                   (conj {:username (:username (:params request)) :msg validation-err-msg})
                                   get-redirect-url
                                   rr/redirect)))
    (= value LOGIN_FAILED) (redirect-change-password request LOGIN_FAILED_CHG_PWD)
    :else (redirect-change-password request value)))

(defn not-authenticated
  "Redirect if not authenticated"
  [request value]
  (if value
    (let [error ((keyword value) authentication-error-list)]
      (if (= value SESSION_EXPIRED)
        (do
          (let [user (auth/get-user-from-session-id (:identity (:session request)))]
            (rr/redirect (get-redirect-url (conj error {:username (:email user)})))))
        (rr/redirect (str (:url error)))))))

(defn check-login-status
  "See if a user is already logged in.  It seems backwards but we want to fire the error handler if they are logged in so they get re-directed to the first landing page"
  [request]
  (if (= (authenticated-access request) true)
    (error LOGIN_SUCCESS)
    true))

(defn check-logout-status
  "See if a user is already logged in."
  [request]
  (if (= (authenticated-access request) true)
    (error LOGOUT_SUCCESS)
    true))

(defn redirect-to-main-page
  "redirects to the main landing page if a user requests the login page but is already logged in"
  [request value]
  (let [error ((keyword value) authentication-error-list)
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
             :on-error       not-authenticated
             :request-method :get}
            {:pattern        #"(^/admin)"
             :handler        admin-access
             :on-error       not-authenticated
             :request-method :get}
            {:pattern        #"(^/testpagex)"
             :handler        admin-access
             :request-method :get}
            {:pattern        #"^/.*"
             :handler        authenticated-access
             :request-method :get
             :on-error       on-err-from-get}
            {:pattern  #"^/.*"
             :handler  authenticated-access
             :on-error not-authenticated}])