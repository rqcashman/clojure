(ns tennis-manager.data.schedule-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]
            [tennis-manager.data.user-info :as usr]))

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


(defn match-availability
  "docstring"
  [match-id]
  (j/query sys/db-cred
           [(str "select p.id, p.first_name, p.last_name, p.status,
            pc.date_sent, pc.response, DATE_FORMAT(pc.response_date, '%M %D, %Y %h:%i %p') as response_date, ma.available
            from player p
            left join player_communication pc on pc.player_id = p.id and pc.match_id = ?
            left join match_availability ma on ma.player_id = p.id and ma.match_id = ?
            where p.team_id = ?
            order by p.status, p.last_name") match-id match-id usr/users_team_id]))

(defn reset-match-availability
  "docstring"
  [match-id]
  (j/execute! sys/db-cred
              [(str "update match_availability set available=0 where match_id = ?") match-id]))

(defn update-player-availability
  "docstring"
  [match-id player-id avail-value]
  (j/execute! sys/db-cred
              [(str "update match_availability set available=? where match_id = ? and player_id = ?")
               avail-value match-id player-id]))


