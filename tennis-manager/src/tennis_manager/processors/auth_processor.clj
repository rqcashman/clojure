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

(defn authenticated-access2
  [request]
  (println "========== access2 request =================")
  (println request)
  (println "========== access2 session =================")
  (println (:session request))
  (error "No authorization"))

(defn authenticated-access
  [request]
  (println "------------ " request)
  ;TODO verify identity here
  true)

(def errorList {:1000 {:url "/login?err=loginfailure" :msg "Login failed"}
                :1001 {:url "/login?err=loginfailure" :msg "Account locked"}
                :1002 {:url "/login?err=loginfailure" :msg "Account disabled"}
                :1003 {:url "/login?err=loginfailure" :msg "Force password change"}})

(defn get-login-error
  "docstring"
  [user]
  (if user
    (if (> (:account_locked user) 0)
      (str "1001")
      (if (> (:account_disabled user) 0)
        (str "1002")
        (if (> (:force_password_change user) 0)
          (str "1003"))))
    (str "1000")))

(defn authenticate-user
  [request]
  (let [username (:username (:params request))
        password (:password (:params request))
        user (auth/get-user username password)
        error-message (get-login-error user)]
    (if-not error-message
      true)
    (error error-message)))

(defn on-error
  [request value]
  (let [errMsg (if-not (= (s/blank? value) true) value "Not Authorizedxxx")]
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    (str "<h1 align='center' style='color:red'>" errMsg "</h1>")}))

(defn on-err-from-get
  [request value]
  (let [errMsg (if-not (= (s/blank? value) true) value "Not Authorized")]
    {:status  200
     :headers {"Content-Type" "text/plain"}
     :body    (str {:status "failed" :status-code 400 :msg "User not logged in"})}))

(defn on-err-from-login
  [request value]
  (println "on error: " value)
  (if value
    (let [error ((keyword value) errorList)]
      (println "======: " error)
      (rr/redirect (str (:url error) "&username=" (codec/url-encode (:username (:params request))) "&msg=" (codec/url-encode (:msg error)))))
    (let [session (:session request)
          updated-session (assoc session :identity (keyword (:username (:params request))))]
      (-> (rr/redirect "/mgr")
          (assoc :session updated-session)))))

(defn authenticated-access3
  "docstring"
  [request]
  (println "+++++++++++ access 3 +++++++++++++++++++++++")
  (println request)
  false)

(def rules [{:pattern #"^/mgr.*"
             :handler authenticated-access}
            {:pattern        #"(^/login*)|(^/availability-reply*)"
             :handler        any-access
             :request-method :get}
            {:pattern        #"(^/login$)"
             :handler        authenticate-user
             :on-error       on-err-from-login
             :request-method :post}
            {:pattern        #"^/.*"
             :handler        authenticated-access3
             :request-method :get
             :on-error       on-err-from-get}
            {:pattern  #"^/.*"
             :handler  authenticated-access3
             :redirect "/login"}])