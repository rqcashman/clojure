(ns tennis-manager.data.schedule-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn add-match
  "docstring"
  [season-id date home_team_id away_team_id]
  (j/execute! sys/db-cred
              [(str "insert into schedule values (null,?,str_to_date(?, '%b-%d-%Y %l:%i %p'),?,?,0,0)") season-id date home_team_id away_team_id]))

 (defn match-info
  "docstring"
  [match-id]
  (j/query sys/db-cred
           [(str "select DATE_FORMAT(s.match_date,'%M %D, %Y') as match_date,DATE_FORMAT(s.match_date,'%h:%i %p') as match_time"
                 " ,cl.name as club_name, cl.address, cl.city, cl.state, cl.zip_code, cl.phone_number"
                 " from schedule s"
                 " join team t on t.id = s.home_team_id"
                 " join club cl on cl.id = t.club_id"
                 " where s.match_id=?") match-id]))


(defn team-schedule-abbreviations
  "docstring"
  []
  (j/query sys/db-cred
           ["select id, sched_abbrev from team"]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (assoc rcds (keyword (:sched_abbrev curr_rcd)) (:id curr_rcd)))
                                     {},
                                     rs))}))
