(ns tennis-manager.data.team-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn team-roster
  "docstring"
  [team-id]
  (j/query sys/db-cred
           [(str "select id, last_name, first_name, email, phone_number, status"
                 " from player "
                 " where team_id = ?"
                 " order by last_name") team-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

(defn team-schedule
  "docstring"
  [season-id team-id]
  (j/query sys/db-cred
           [(str "select s.match_id, s.season_id, DATE_FORMAT(s.match_date,'%Y-%m-%d') as match_date,DATE_FORMAT(s.match_date,'%h:%i %p') as match_time"
                 " ,s.home_team_id, s.away_team_id, ht.name as home_team, at.name as away_team, home_team_points, away_team_points, cl.name as home_club_name"
                 " ,mc.availability_sent, mc.lineup_sent"
                 " from schedule s"
                 " join team ht on ht.id = s.home_team_id"
                 " join club cl on cl.id = ht.club_id"
                 " join team at on at.id = s.away_team_id"
                 " left join match_communication mc on mc.match_id = s.match_id"
                 " where season_id = ? and (s.home_team_id = ? or s.away_team_id = ?)"
                 " order by s.match_date")
            season-id team-id team-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

(defn teams
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name"
                 " from team "
                 " order by name")]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))
(defn team
  "docstring"
  [team-id]
  (j/query sys/db-cred
           [(str "select id, name, club_id, default_match_time"
                 " from team "
                 " where id = ?"
                 " order by name") team-id]))

(defn add-team
  "docstring"
  [name club-id start-time]
  (j/execute! sys/db-cred
              [(str "insert into team values (null,?,?,?,1)") name club-id start-time]))

(defn team-exists?
  "docstring"
  [club-id name]
  (j/query sys/db-cred
           [(str "select count(*) as ct from team where club_id=? and name=?") club-id name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))

