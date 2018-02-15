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
  (println "------------ " request)
  (println "-----session ------- " (:session request))
  (let [session (:session request)
        session-id (:identity session)]
    (if-not (= (s/blank? session-id) true)
      (if (auth/session-valid? session-id)
        (do
          (auth/update-session-used-time session-id)
          true)
        (error "1004"))
      (error "1005"))))

(defn admin-access
  [request]
  (let [auth (authenticated-access request)]
    (if (= auth true)
      (let [session (:session request)
            session-id (:identity session)
            user (auth/get-user-from-session-id session-id)]
        (if (= (:user_type user) 1)
          true
          (error "1006")))
      auth)))

(def authentication-error-list {:0    {:url "/mgr" :msg "Success"}
                                :1000 {:url "/login?err=loginfailure" :msg "Login failed"}
                                :1001 {:url "/login?err=acctlocked" :msg "Account locked"}
                                :1002 {:url "/login?err=acctdisabled" :msg "Account disabled"}
                                :1003 {:url "/login?err=chgpassword" :msg "Force password change"}
                                :1004 {:url "/login?err=sessionexpired" :msg "Login session timed out due to inactivity"}
                                :1005 {:url "/login" :msg ""}
                                :1006 {:url "/noauth" :msg ""}})

;TODO change url for 10003 to change password page - not created yet
(defn authenticate-user
  [request]
  (let [username (:username (:params request))
        password (:password (:params request))
        user (auth/get-user-with-password username password)]
    (if user
      (cond
        (> (:account_locked user) 0) (error "1001")
        (> (:account_disabled user) 0) (error "1002")
        (> (:force_password_change user) 0) (error "1003")
        :else (error "0"))
      (error "1000"))))

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

(defn login-redirect
  [request value]
  (println "on error: " value)
  (let [username (:username (:params request))]
    (auth/insert-login-attempt username value)
    (if (= value "1000")
      (auth/lock-account? username))
    (if value
      (if (or (= value "0") (and (= value "1001") (= (auth/account-unlocked? username) true)))
        (let [session (:session request)
              session-id (s/replace (str (java.util.UUID/randomUUID)) #"-" "")
              error (:0 authentication-error-list)
              updated-session (assoc session :identity session-id)
              url (:url error)]
          (auth/update-user-last-login-time username)
          (auth/persist-session-id username session-id)
          (-> (rr/redirect url)
              (assoc :session updated-session)))
        (let [error ((keyword value) authentication-error-list)]
          (rr/redirect (str (:url error) "&username=" (codec/url-encode username) "&msg=" (codec/url-encode (:msg error)))))))))

(defn get-expired-session-url
  "Return expired session URL.  Puts username in login box if the session id is still in the DB"
  [session error]
  (let [user (auth/get-user-from-session-id (:identity session))
        username (:email user)]
    (if (s/blank? username)
      (str (:url error) "&msg=" (codec/url-encode (:msg error)))
      (str (:url error) "&username=" (codec/url-encode username) "&msg=" (codec/url-encode (:msg error))))))

(defn not-authenticated
  "Redirect if not authenticated"
  [request value]
  (if value
    (let [error ((keyword value) authentication-error-list)]
      (if (= value "1004")
        (rr/redirect (get-expired-session-url (:session request) error))
        (rr/redirect (str (:url error)))))))

(def rules [{:pattern        #"(^/login*)|(^/availability-reply*)"
             :handler        any-access
             :request-method :get}
            {:pattern        #"(^/login$)"
             :handler        authenticate-user
             :on-error       login-redirect
             :request-method :post}
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