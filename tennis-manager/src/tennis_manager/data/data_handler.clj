(ns tennis-manager.data.data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]))

(def date-fmt (f/formatter "yyyy-MM-dd"))

(def db-spec
  {:dbtype   "mysql"
   :dbname   "tennis_manager"
   :host     "localhost"
   :port     3306
   :user     "root"
   :password "Cash1234"})


(defn team-schedule
  "docstring"
  [season-id team-id]
  (println "in team-schedulexx " season-id team-id)
  (j/query db-spec
           [(str "select s.match_id, s.season_id, DATE_FORMAT(s.match_date,'%Y-%m-%d') as match_date,DATE_FORMAT(s.match_date,'%H:%i') as match_time"
                 " ,s.home_team_id, s.away_team_id, ht.name as home_team, at.name as away_team, home_team_points, away_team_points"
                 " from schedule s"
                 " join team ht on ht.id = s.home_team_id"
                 " join team at on at.id = s.away_team_id"
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
  (println "in teams ")
  (j/query db-spec
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
  (println "in team " team-id)
  (j/query db-spec
           [(str "select id, name, club_id, default_match_time"
                 " from team "
                 " where id = ?"
                 " order by name") team-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))
(defn add-team
  "docstring"
  [name club-id start-time]
  (println "in add-team " name club-id start-time)
  (j/execute! db-spec
              [(str "insert into team values (null,?,?,?,1)") name club-id start-time]))

(defn team-exists?
  "docstring"
  [club-id name]
  (println "in team-exists " club-id name)
  (j/query db-spec
           [(str "select count(*) as ct from team where club_id=? and name=?") club-id name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))
(defn seasons
  "docstring"
  []
  (println "in seasons ")
  (j/query db-spec
           [(str "select id, name, DATE_FORMAT(start_date,'%m-%d-%Y') as start_date,DATE_FORMAT(end_date,'%m-%d-%Y') as end_date"
                 " from season "
                 " order by start_date desc")]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

(defn season
  "docstring"
  [season-id]
  (println "in season " season-id)
  (j/query db-spec
           [(str "select id, name, DATE_FORMAT(start_date,'%Y-%m-%d') as start_date,DATE_FORMAT(end_date,'%Y-%m-%d') as end_date"
                 " from season "
                 " where id = ?"
                 " order by name") season-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

(defn season-exists?
  "docstring"
  [name]
  (println "in season-exists " name)
  (j/query db-spec
           [(str "select count(*) as ct from season where name=?") name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))


  (defn add_season
    "docstring"
    [name start-date end-date]
    (println "in add-season " name start-date end-date)
    (j/execute! db-spec
             [(str "insert into season values (null,?,str_to_date(?, '%m/%d/%Y'),str_to_date(?, '%m/%d/%Y'))") name start-date end-date]))


(defn add-match
  "docstring"
  [season-id date home_team_id away_team_id]
  (println "add match parms: " season-id date home_team_id away_team_id)
  (j/execute! db-spec
              [(str "insert into schedule values (null,?,str_to_date(?, '%b-%d-%Y %l:%i %p'),?,?,0,0)") season-id date home_team_id away_team_id]))

(defn team-schedule-abbreviations
  "docstring"
  []
  (j/query db-spec
           ["select id, sched_abbrev from team"]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (assoc rcds (keyword (:sched_abbrev curr_rcd)) (:id curr_rcd)))
                                     {},
                                     rs))}))
  (defn clubs
    "docstring"
    []
    (println "in clubs ")
    (j/query db-spec
             [(str "select id, name, address, city, state, zip_code, phone_number"
                   " from club "
                   " order by name")]
             {:as-arrays?    false
              :result-set-fn (fn [rs]
                               (reduce (fn [rcds curr_rcd]
                                         (conj rcds curr_rcd))
                                       [],
                                       rs))}))

  (defn add_club
    "docstring"
    [name address city state zip_code phone_number]
    (println "in add-club " name address city state zip_code phone_number)
    (j/execute! db-spec
                [(str "insert into club values (null,?,?,?,?,?,?)") name address city state zip_code phone_number]))

  (defn club_exists?
    "docstring"
    [name]
    (println "in club-exists " name)
    (j/query db-spec
             [(str "select count(*) as ct from club where name=?") name]
             {:as-arrays?    false
              :result-set-fn (fn [rs]
                               (if (> (:ct (nth rs 0)) 0) true false))}))
  (defn team-schedule-template
    "docstring"
    [season-id team-id]
    (println "team-schedule-template " season-id team-id)
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

  (defn team-schedule-all
    "docstring"
    [season-id team-id]
    (let [combined-schedule (conj (team-schedule-template season-id team-id)
                                  (reduce
                                    #(assoc %1 (keyword (:match_date %2)) (dissoc %2 :match_date :season_id))
                                    {}
                                    (team-schedule season-id team-id)))]
      (doseq [k (sort (keys combined-schedule))]
        (println "key: " k " val: " (k combined-schedule)))))
