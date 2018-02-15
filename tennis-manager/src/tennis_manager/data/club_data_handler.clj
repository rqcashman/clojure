(ns tennis-manager.data.club-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn clubs-orig
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name, address, city, state, zip_code, phone_number"
                 " from club "
                 " order by name")]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

(defn clubs
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name, address, city, state, zip_code, phone_number"
                 " from club "
                 " order by name")]))

(defn add_club
  "docstring"
  [name address city state zip_code phone_number]
  (j/execute! sys/db-cred
              [(str "insert into club values (null,?,?,?,?,?,?)") name address city state zip_code phone_number]))

(defn club_exists?
  "docstring"
  [name]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from club where name=?") name])
      first :ct pos?))

