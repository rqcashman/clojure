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
(def LOGIN_FAILED "1000")
(def LOGIN_LOCKED "1001")
(def LOGIN_DISABLED "1002")
(def CHG_PASSWORD "1003")
(def SESSION_EXPIRED "1004")
(def NOT_AUTHENTICATED "1005")
(def NOT_AUTHORIZED "1006")
(def LOGIN_FAILED_CHG_PWD "1007")

(def ADMIN_USER 1)

(def authentication-error-list {(keyword LOGIN_SUCCESS)        {:url "/mgr"}
                                (keyword LOGOUT_SUCCESS)       {:url "/login" :err "logoutsuccess" :msg "Logout successful"}
                                (keyword LOGIN_FAILED)         {:url "/login" :err "loginfailure" :msg "Login failed"}
                                (keyword LOGIN_LOCKED)         {:url "/login" :err "acctlocked" :msg "Account locked"}
                                (keyword LOGIN_DISABLED)       {:url "/login" :err "acctdisabled" :msg "Account disabled"}
                                (keyword CHG_PASSWORD)         {:url "/chgpassword" :err "chgpassword" :msg "Password change required"}
                                (keyword SESSION_EXPIRED)      {:url "/login" :err "sessionexpired" :msg "Login session timed out due to inactivity"}
                                (keyword NOT_AUTHENTICATED)    {:url "/login"}
                                (keyword NOT_AUTHORIZED)       {:url "/notauth"}
                                (keyword LOGIN_FAILED_CHG_PWD) {:url "/chgpassword" :err "chgpwdloginfail" :msg "Password invalid"}})
(defn unauthorized-handler
  [request metadata]
  (rr/response "Unauthorized request"))

(def auth-backend
  (session-backend {:unauthorized-handler unauthorized-handler}))

(defn any-access
  [request]
  true)

(defn authenticated-access
  [request]
  (let [session (:session request)
        session-id (:identity session)]
    (if-not (= (s/blank? session-id) true)
      (do
        (println "CASHXX authenticated-access session id found: " session-id)
        (if (auth/session-valid? session-id)
          (do
            (println "CASHXX authenticated-access session valid: " session-id)
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

(defn authenticate-user
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
  (println "login-redirect value: " value " request session: " (:session request))
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

(defn not-authenticated
  "Redirect if not authenticated"
  [request value]
  (println "=========================================================")
  (println "Not authed value: " value)
  (println "Not authed request: " request)
  (println " ")
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


(defn authenticate-user-chg-password
  "If user is not authenticated we override the error to put the user back on the login page"
  [request]
  (let [auth-val (.get-value (authenticate-user request))]
    (println "auth val: " auth-val "request: " request)
    (if (and (not= auth-val LOGIN_SUCCESS) (not= auth-val CHG_PASSWORD))
      (error LOGIN_FAILED_CHG_PWD)
      true)))

(defn redirect-to-main-page
  "redirects to the main landing page if a user requests the login page but is already logged in"
  [request value]
  (let [error ((keyword value) authentication-error-list)
        redirect-url (get-redirect-url error)]
    (rr/redirect redirect-url)))

(def rules [{:pattern        #"(^/login*)|(^/chgpassword*)|(^/availability-reply*)"
             :handler        check-login-status
             :on-error       redirect-to-main-page
             :request-method :get}
            {:pattern        #"(^/login$)"
             :handler        authenticate-user
             :on-error       login-redirect
             :request-method :post}
            {:pattern        #"(^/chgpassword)"
             :handler        authenticate-user-chg-password
             :on-error       login-redirect
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
            {:pattern        #"^/.*"
             :handler        authenticated-access
             :request-method :get
             :on-error       on-err-from-get}
            {:pattern  #"^/.*"
             :handler  authenticated-access
             :on-error not-authenticated}])