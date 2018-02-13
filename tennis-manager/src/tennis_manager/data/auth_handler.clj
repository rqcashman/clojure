(ns tennis-manager.data.auth-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn login-valid?
  "docstring"
  [username password]
  (j/query sys/db-cred
           [(str "select count(*) as ct from user_login u where u.email=? and trim(u.password)=trim(sha1(?))") (s/trim username) (s/trim password)]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (println rs)
                             (if (> (:ct (nth rs 0)) 0) true false))}))
(defn get-user
  "docstring"
  [username password]
  (-> (j/query sys/db-cred
               [(str "select iduser_login, email, last_name, first_name, team_id, last_login_date,
               account_locked, account_disabled, force_password_change
            from user_login u where u.email=? and trim(u.password)=trim(sha1(?))") (s/trim username) (s/trim password)])
      (first)))


