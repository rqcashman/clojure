(ns rf-tennis-manager.data.team-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [rf-tennis-manager.data.auth-handler :as auth]
            [rf-tennis-manager.data.schedule-data-handler :as sched]
            [rf-tennis-manager.data.season-data-handler :as season]
            [rf-tennis-manager.data.system-info :as sys]))

(defn team-roster
  "docstring"
  [team-id]
  (j/query sys/db-cred
           [(str "select id, last_name, first_name, email, phone_number, status"
                 " from player "
                 " where team_id = ?"
                 " order by last_name") team-id]))

(defn team-schedule
  ([season-id team-id]
   (j/query sys/db-cred
            [(str "select s.match_id, s.season_id, DATE_FORMAT(s.match_date,'%Y-%m-%d') as match_date,DATE_FORMAT(s.match_date,'%h:%i %p') as match_time
                  ,s.home_team_id, s.away_team_id, ht.name as home_team, at.name as away_team, home_team_points, away_team_points, cl.name as home_club_name
                  ,mc.availability_sent, mc.lineup_sent
                  from schedule s
                  join team ht on ht.id = s.home_team_id
                  join club cl on cl.id = ht.club_id
                  join team at on at.id = s.away_team_id
                  left join match_communication mc on mc.match_id = s.match_id
                  where season_id = ? and (s.home_team_id = ? or s.away_team_id = ?)
                  order by s.match_date")
             season-id team-id team-id])))

(defn team-schedule-status
  "docstring"
  [session]
  (let [user (auth/get-user-from-session-id (:identity session))
        season (season/current-season)]
    (reduce (fn [matches match]
              (conj matches (assoc match :lineup_set (sched/lineup-set? (:match_id match) user)))
              ) [] (team-schedule (:id season) (:team_id user)))))

(defn teams
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name from team order by name")]))

(defn team
  "docstring"
  [team-id]
  (-> (j/query sys/db-cred
               [(str "select id, name, club_id, default_match_time
                      from team where id = ? order by name") team-id])
      first))

(defn add-team
  "docstring"
  [name club-id sched_abbrev]
  (j/execute! sys/db-cred
              [(str "insert into team values (null,?,?,?,1)") name club-id sched_abbrev]))

(defn team-exists?
  "docstring"
  [club-id name]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from team where club_id=? and name=?") club-id name])
      first :ct pos?))

(defn sched-abbreviation-exists?
  "docstring"
  [sched-abbrevation]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from team where sched_abbrev = ?") sched-abbrevation])
      first :ct pos?))


