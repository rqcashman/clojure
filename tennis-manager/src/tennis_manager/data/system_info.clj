(ns tennis-manager.data.system-info
  (:require [clojure.java.io :as io]))

(defn get-system-info
  ""
  []
  (println "==== sys dir: " (System/getProperty "user.dir"))
  (let [file (str (System/getProperty "user.dir") "\\sys-data\\error.txt")
        data (into {} (with-open [rdr (clojure.java.io/reader file)]
                        (doall (map #(load-string %) (line-seq rdr)))))]
    ;(io/delete-file file)
    data))

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


