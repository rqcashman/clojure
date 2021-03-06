(ns tennis-manager.data.season-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn seasons
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name, DATE_FORMAT(start_date,'%M %D, %Y') as start_date,DATE_FORMAT(end_date,'%m-%d-%Y') as end_date
                  from season s order by s.start_date desc")]))

(defn season
  "docstring"
  [season-id]
  (-> (j/query sys/db-cred
               [(str "select id, name, DATE_FORMAT(start_date,'%M %D, %Y') as start_date,DATE_FORMAT(end_date,'%M %D, %Y') as end_date
                      from season  where id = ?") season-id])
      first))

(defn season-exists?
  "docstring"
  [name]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from season where name=?") name])
      first :ct pos?))

(defn add_season
  "docstring"
  [name start-date end-date]
  (j/execute! sys/db-cred
              [(str "insert into season values (null,?,str_to_date(?, '%m/%d/%Y'),str_to_date(?, '%m/%d/%Y'))") name start-date end-date]))

(defn current-season
  "docstring"
  []
  (-> (j/query sys/db-cred
               [(str "select id, name from season where start_date <= date_sub(current_date(), interval 10 day) and end_date >= current_date()")])
      first))

