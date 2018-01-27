(ns tennis-manager.data.data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(def date-fmt (f/formatter "yyyy-MM-dd"))

(defn player
  "docstring"
  [player-id]
  (j/query sys/db-cred
           [(str "select id, team_id, last_name, first_name, email, phone_number, status"
                 " from player "
                 " where id=?") player-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

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
(defn seasons
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name, DATE_FORMAT(start_date,'%m-%d-%Y') as start_date,DATE_FORMAT(end_date,'%m-%d-%Y') as end_date"
                 " from season "
                 " order by start_date desc")]))

(defn season
  "docstring"
  [season-id]
  (j/query sys/db-cred
           [(str "select id, name, DATE_FORMAT(start_date,'%Y-%m-%d') as start_date,DATE_FORMAT(end_date,'%Y-%m-%d') as end_date"
                 " from season "
                 " where id = ?"
                 " order by name") season-id]))

(defn season-exists?
  "docstring"
  [name]
  (j/query sys/db-cred
           [(str "select count(*) as ct from season where name=?") name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))


(defn add_season
  "docstring"
  [name start-date end-date]
  (j/execute! sys/db-cred
              [(str "insert into season values (null,?,str_to_date(?, '%m/%d/%Y'),str_to_date(?, '%m/%d/%Y'))") name start-date end-date]))


(defn add-match
  "docstring"
  [season-id date home_team_id away_team_id]
  (j/execute! sys/db-cred
              [(str "insert into schedule values (null,?,str_to_date(?, '%b-%d-%Y %l:%i %p'),?,?,0,0)") season-id date home_team_id away_team_id]))

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
  (j/query sys/db-cred
           [(str "select count(*) as ct from club where name=?") name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))
(defn team-schedule-template
  "docstring"
  [season-id team-id]
  (let [season (nth (season season-id) 0)
        start-date (f/parse date-fmt (:start_date season))
        end-date (f/parse date-fmt (:end_date season))
        team (nth (team 1) 0)
        team-name (:name team)
        team-id (:id team)
        match-time (:default_match_time team)]
    (println "start: " (.toString start-date) " end: " (.toString end-date) " season: " season)
    (loop [date start-date next-date (t/plus start-date (t/days 7)) result (transient {})]
      (println "date: " (.toString date) " next date: " (.toString next-date))
      (if (t/after? date end-date)
        (persistent! result)
        (recur next-date (t/plus next-date (t/days 7))
               (assoc! result (keyword (f/unparse date-fmt date)) {:match_id -1 :match_time match-time :home_team_id team-id :away_team_id team-id :home_team team-name :away_team team-name}))))))

(defn player-exists?
  "docstring"
  [team_id first_name last_name]
  (j/query sys/db-cred
           [(str "select count(*) as ct from player where team_id=? and first_name=? and last_name=?") team_id first_name last_name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))

(defn player-id-exists?
  "docstring"
  [player-id]
  (j/query sys/db-cred
           [(str "select count(*) as ct from player where id=?") player-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))

(defn player-name-available?
  "check to make sure a player with the name is not already on that team excluding the one we are updating"
  [team_id player-id first_name last_name]
  (j/query sys/db-cred
           [(str "select count(*) as ct from player where team_id=? and first_name=? and last_name=? and id <>?") team_id first_name last_name player-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (= (:ct (nth rs 0)) 0) true false))}))

(defn add_player
  "docstring"
  [team_id first_name last_name email phone_number]
  (let [phone (if (s/blank? phone_number) 0 phone_number)]
    (j/execute! sys/db-cred
                [(str "insert into player values (null,?,?,?,?,?,'A')") team_id first_name last_name email phone])))

(defn update-player
  "docstring"
  [player-id last_name first_name email phone_number status]
  (j/execute! sys/db-cred
              [(str "update player set last_name=?,first_name=?,
              email=?,phone_number=?,status=?
              where id=?") last_name, first_name, email, phone_number, status player-id]))

(defn current-season
  "docstring"
  []
  (j/query sys/db-cred
           [(str "select id, name from season where start_date <= date_sub(current_date(), interval 10 day) and end_date >= current_date()")]))

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
