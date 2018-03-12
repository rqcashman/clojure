(ns rf-tennis-manager.data.system-info
  (:require [clojure.java.io :as io]
            [clojure.string :as s]
            [taoensso.nippy :as nippy]
            [rf-tennis-manager.data.password-extractor :as pwd]))

(defn get-system-info
  "System parms are in an encrypted file.  We determine the password for the file and then read it into a map"
  []
  (try
    (println "**************************************************************************************************************************")
    (let [password-file (str (System/getProperty "user.dir") "\\sys-data\\logs\\log.txt")
          parm-file (str (System/getProperty "user.dir") "\\sys-data\\sys-parms.txt")
          password (pwd/get-password password-file)
          parm-arr (-> (nippy/thaw-from-file parm-file {:password [:salted password]})
                       (s/split #"\n"))
          sys-parms (reduce (fn [parm-map parm]
                              (conj parm-map (first (read-string parm))))
                            {}
                            parm-arr)]
      ;(io/delete-file password-file)
      ;(io/delete-file parm-file)
      sys-parms)
    (catch Exception e
      (println "System config error message: " (.getMessage e))
      (println e)
      (throw (Exception. "Error starting server due to configuration error"))
      )))

(def system-info (get-system-info))

(def email-cred
  {:user     (:email-user system-info)
   :password (:email-password system-info)})

(def db-cred
  {:dbtype   (:dbtype system-info)
   :dbname   (:dbname system-info)
   :host     (:host system-info)
   :port     (:port system-info)
   :user     (:user system-info)
   :useSSL   (:useSSL system-info)
   :password (:password system-info)})



(println "SYS INFO: " system-info)
(println "DB INFO: " db-cred)
(println "EM INFO: " email-cred)


