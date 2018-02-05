(ns tennis-manager.data.communication-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]
            [tennis-manager.data.user-info :as usr]))

(defn add_player_communication
  "docstring"
  [player_id match_id uuid]
  (j/execute! sys/db-cred
              [(str "insert into player_communication values (?,?,?,current_timestamp(),?,2,null)") player_id, match_id, usr/users_team_id, uuid]))

(defn add_match_avail_email_sent
  "docstring"
  [match_id]
  (j/execute! sys/db-cred
              [(str "insert into match_communication values (?,?,current_timestamp(),null)") match_id, usr/users_team_id]))

(defn update_match_avail_email_sent_date
  "docstring"
  [match_id]
  (j/execute! sys/db-cred
              [(str "update match_communication set availability_sent = current_timestamp() where match_id=? and team_id = ?") match_id, usr/users_team_id]))

(defn match_avail_email_sent
  "docstring"
  [match_id]
  (j/query sys/db-cred
           [(str "select count(*) as ct from match_communication where match_id=? and team_id=?") match_id usr/users_team_id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))


(defn get_communication_detail
  "docstring"
  [uuid]
  (j/query sys/db-cred
           [(str "select c.player_id, c.match_id, c.team_id, c.response, c.response_date, p.first_name, p.last_name,
           DATE_FORMAT(s.match_date,'%M %D, %Y') as match_date,DATE_FORMAT(s.match_date,'%h:%i %p') as match_time
           from player_communication c
           left join player p on p.id = c.player_id
           left join schedule s on s.match_id = c.match_id
           where uuid=?") uuid]))

(defn update_player_commuication_response
  "docstring"
  [uuid available]
  (let [avail_flag (if (= available "Y") 1 0)]
    (j/execute! sys/db-cred
                [(str "update player_communication set response=?, response_date=current_timestamp() where uuid=?") avail_flag uuid])))

(defn upsert_match_avail_email_sent
  "docstring"
  [match_id]
  (if (match_avail_email_sent match_id)
    (update_match_avail_email_sent_date match_id)
    (add_match_avail_email_sent match_id))
  )






