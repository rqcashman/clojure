(ns rf-tennis-manager.data.season-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [rf-tennis-manager.data.system-info :as sys]))

(defn seasons
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name, to_char(start_date,'Month DD, YYYY') as start_date,to_char(end_date,'Month DD, YYYY') as end_date
                  from season s order by s.start_date desc")]))

(defn season
  "docstring"
  [season-id]
  (-> (j/query sys/db-cred
               [(str "select id, name, to_char(start_date,'Month DD, YYYY') as start_date,to_char(end_date,'Month DD, YYYY') as end_date
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
              [(str "insert into season values (default,?,to_date(?, 'Mon DD YYYY'),to_date(?, 'Mon DD YYYY'))") name start-date end-date]))

(defn current-season
  "docstring"
  []
  (-> (j/query sys/db-cred
               [(str "select id, name from season where start_date <= current_date + interval '10 days' and end_date >= current_date")])
      first))

