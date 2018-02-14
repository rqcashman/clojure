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
  (let [session (:session request)]
    (if-not (= (s/blank? (:identity session)) true)
      (if (= (auth/session-valid? (:identity session)) true)
        true
        (error "User session expired"))
      (error "User not logged in"))))

(def authentication-error-list {:0    {:url "/mgr" :msg "Success"}
                                :1000 {:url "/login?err=loginfailure" :msg "Login failed"}
                                :1001 {:url "/login?err=acctlocked" :msg "Account locked"}
                                :1002 {:url "/login?err=acctdisabled" :msg "Account disabled"}
                                :1003 {:url "/login?err=chgpassword" :msg "Force password change"}})

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
          (println "=== SUCCESS ===")
          (println "ERROR" error)
          (println "URL" url)
          (auth/persist-session-id username session-id)
          (-> (rr/redirect url)
              (assoc :session updated-session)))
        (let [error ((keyword value) authentication-error-list)]
          (rr/redirect (str (:url error) "&username=" (codec/url-encode (:username (:params request))) "&msg=" (codec/url-encode (:msg error)))))))))

(defn authenticated-access3
  "docstring"
  [request]
  (println "+++++++++++ access 3 +++++++++++++++++++++++")
  (println request)
  false)

(defn not-authenticated
  "docstring"
  [request value]
  (if value
    (rr/redirect "/login"))
  )

(def rules [{:pattern        #"(^/login*)|(^/availability-reply*)"
             :handler        any-access
             :request-method :get}
            {:pattern        #"(^/login$)"
             :handler        authenticate-user
             :on-error       login-redirect
             :request-method :post}
            {:pattern        #"(^/mgr$)|(^/admin)|(^/matches$)|(^/schedule$)|(^/roster$)"
             :handler        authenticated-access
             :on-error       not-authenticated
             :request-method :get}
            {:pattern        #"^/.*"
             :handler        authenticated-access
             :request-method :get
             :on-error       on-err-from-get}
            {:pattern  #"^/.*"
             :handler  authenticated-access
             :on-error not-authenticated
             }])