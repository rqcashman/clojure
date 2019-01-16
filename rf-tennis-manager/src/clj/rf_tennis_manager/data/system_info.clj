(ns rf-tennis-manager.data.system-info
  (:require [clojure.java.io :as io]
            [clojure.string :as s]
            [taoensso.nippy :as nippy]
            [rf-tennis-manager.data.password-extractor :as pwd]))

;(defn get-system-info-orig
;  "System parms are in an encrypted file.  We determine the password for the file and then read it into a map"
;  []
;  (try
;    (println "**************************************************************************************************************************")
;    (let [password-file (str (System/getProperty "user.dir") "\\sys-data\\logs\\log.txt")
;          parm-file (str (System/getProperty "user.dir") "\\sys-data\\sys-parms.txt")
;          password (pwd/get-password password-file)
;          parm-arr (-> (nippy/thaw-from-file parm-file {:password [:salted password]})
;                       (s/split #"\n"))
;          sys-parms (reduce (fn [parm-map parm]
;                              (conj parm-map (first (read-string parm))))
;                            {} parm-arr)]
;      ;(io/delete-file password-file)
;      ;(io/delete-file parm-file)
;      sys-parms)
;    (catch Exception e
;      (println "System config error message: " (.getMessage e))
;      (println e)
;      (throw (Exception. "Error starting server due to configuration error")))))
;
;
;(def system-info (get-system-info))
(def test {:rick "rick cashman" :mo "maureen cashman"})
(def email-cred (atom nil))
(def db-cred (atom nil))
(defn get-email-cred
  []
  {:user     (System/getenv "HEROKU_EMAIL_USER")
   :password (System/getenv "HEROKU_EMAIL_PASSWORD")})

(defn get-db-cred
  []
  (println "db cred type: " (System/getenv "HEROKU_DB_TYPE"))
  {:dbtype   (System/getenv "HEROKU_DB_TYPE")
   :dbname   (System/getenv "HEROKU_DB_NAME")
   :host    (System/getenv "HEROKU_DB_HOST")
   ;:port       (:port system-info)
   :user     (System/getenv "HEROKU_DB_USER")
   :password (System/getenv "HEROKU_DB_PWD")
   :ssl      false
   ;:ssl        (:useSSL system-info)
   ;:sslfactory (:sslfactory system-info)
   })
(defn init
  []
  (reset! email-cred (get-email-cred))
  (reset! db-cred (get-db-cred)))

(init)
(println "mo: " (:mo test))
(println "host: " (:host @db-cred))
(println "DB INFOxxxxxxxxxxxxx: " db-cred)
(println "EM INFO: " email-cred)
