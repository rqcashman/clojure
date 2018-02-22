(ns tennis-manager.data.system-info
  (:require [clojure.java.io :as io]
            [clojure.string :as s]
            [taoensso.nippy :as nippy]
            [tennis-manager.data.password-extractor :as pwd]))

(defn get-system-info
  "System parms are in an encrypted file.  We determine the password for the file and then read it into a map"
  []
  (println "=================================================================================================================")
  (let [password-file (str (System/getProperty "user.dir") "\\sys-data\\logs\\log.txt")
        parm-file (str (System/getProperty "user.dir") "\\sys-data\\sys-parms.txt")
        password (pwd/get-password password-file)
        parm-arr (-> (nippy/thaw-from-file parm-file {:password [:salted password]})
                     (s/split #"\n"))
        sys-parms (reduce (fn [parm-map parm]
                            (let [pm (first (read-string parm))]
                              (assoc parm-map (key pm) (val pm))))
                          {}
                          parm-arr)]
    ;(io/delete-file password-file)
    ;(io/delete-file parm-file)
    sys-parms))

(def system-info (get-system-info))

(def email-cred
  {:email-user     (:email-user system-info)
   :email-password (:email-password system-info)})

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


