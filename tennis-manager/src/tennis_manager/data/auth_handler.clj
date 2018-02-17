(ns tennis-manager.data.auth-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn login-valid?
  "docstring"
  [username password]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from user_login u where u.email=? and trim(u.password)=trim(sha1(?))") (s/trim username) (s/trim password)])
      first :ct pos?))

(defn get-user-with-password
  "docstring"
  [username password]
  (-> (j/query sys/db-cred
               [(str "select iduser_login, email, last_name, first_name, team_id, last_login_date,
               account_locked, account_disabled, force_password_change
            from user_login u where u.email=? and trim(u.password)=trim(sha1(?))") (s/trim username) (s/trim password)])
      (first)))

(defn get-user
  "docstring"
  [username]
  (-> (j/query sys/db-cred
               [(str "select iduser_login, email, last_name, first_name, team_id, last_login_date,
               account_locked, account_disabled, force_password_change
            from user_login u where u.email=?") (s/trim username)])
      (first)))

(defn insert-login-attempt
  "docstring"
  [username status]
  (j/execute! sys/db-cred
              [(str "insert into user_login_attempts values (?,current_timestamp(),?)") username status]))

(defn change-password
  "docstring"
  [username newpassword]
  (println "chg password - username: " username " pwd: " newpassword)
  (j/execute! sys/db-cred
              [(str "update user_login set password=sha1(?), force_password_change=0 where email=?") newpassword username]))

(defn get-system-parms
  "Returns the system parms for the category"
  [category]
  (j/query sys/db-cred
           [(str "select parm_key, value from system_parameters where category=?") category]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds {(keyword (:parm_key curr_rcd)) (:value curr_rcd)}))
                                     {},
                                     rs))}))
(def failed-login-status 1000)

(defn too-many-login-failures?
  "returns the number of login failures within the allowable timeframe"
  [username]
  (let [parms (get-system-parms "AUTHENTICATION")
        max-failed-attempts (:LOGIN_ATTEMPTS parms)
        failure-minutes (:FAILURE_MINUTES parms)]
    (let [failures (-> (j/query sys/db-cred
                                [(str "select count(1) as ct from user_login_attempts where email=? and login_status=?
             and login_time > date_sub(current_timestamp(), INTERVAL " failure-minutes " MINUTE)") username failed-login-status])
                       first :ct)]
      (println "too-many-login-failures? failures: " failures " max failures: " max-failed-attempts)
      (if (< failures (Integer/parseInt max-failed-attempts)) false true))))


(defn account-unlocked?
  "unlock account if the account lockout time has elapsed"
  [username]
  (if (= (too-many-login-failures? username) false)
    (do
      (println "unlocking account: " username)
      (j/execute! sys/db-cred
                  [(str "update user_login set account_locked=0 where email=?") username])
      true)
    false))


(defn lock-account?
  "lock account if the number of failed attempts has been reached in the given timeframe"
  [username]
  (if (= (too-many-login-failures? username) true)
    (do
      (println "locking account: " username)
      (j/execute! sys/db-cred
                  [(str "update user_login set account_locked=1 where email=?") username])
      true)
    false))

(defn get-user-from-session-id
  "Get the user info using the session id"
  [session-id]
  (-> (j/query sys/db-cred
               [(str "select u.iduser_login,u.user_type, u.email, u.last_name, u.first_name, u.team_id, u.last_login_date,
                      u.account_locked, u.account_disabled, u.force_password_change
                      from user_session s
                      join user_login u on u.iduser_login = s.iduser_login
                      where s.session_id = ?") session-id])
      (first)))

(defn persist-session-id
  "Save session id to the DB"
  [username session-id]
  (let [user (get-user username)]
    (println "Persist session.  User: " user " id: " session-id)
    (if user
      (j/execute! sys/db-cred
                  [(str "insert into user_session values (?,?,current_timestamp(), current_timestamp())") session-id (:iduser_login user)]))))

(defn session-valid?
  "Check to see if the session id exists and if it has timed out"
  [session-id]
  (let [parms (get-system-parms "AUTHENTICATION")
        session-timeout (:SESSION_TIMEOUT_MINUTES parms)]
    (println "CASHXX session-valid? timeout minutes: " session-timeout)
    (-> (j/query sys/db-cred
                 [(str "select count(1) as ct from user_session where session_id=?
                        and last_used_time > date_sub(current_timestamp(), INTERVAL " session-timeout " MINUTE)") session-id])
        first :ct pos?)))

(defn update-session-used-time
  "update last used time for session"
  [session-id]
  (j/execute! sys/db-cred
              [(str "update user_session set last_used_time = current_timestamp() where session_id=?") session-id]))

(defn update-user-last-login-time
  "update user's last login date"
  [username]
  (j/execute! sys/db-cred
              [(str "update user_login set last_login_date = current_timestamp() where email=?") username]))