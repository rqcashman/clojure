(ns tennis-manager.processors.service-processor
  (:require [tennis-manager.data.data-handler :as db]
            [tennis-manager.content.admin :as admin]
            [clojure.string :as s]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.java.io :as io])
  (:import (java.sql SQLException)
           (clojure.lang EdnReader$StringReader)
           (java.io StringReader BufferedReader)))

(def date-fmt (f/formatter "MM/dd/YYYY"))

(defn add-club
  "docstring"
  [& params]
  (println "add club parameters: " params)
  (let [p (nth params 0)
        club_name (:club_name p)]
    (try
      (if (s/blank? club_name)
        (hash-map :status "failed" :status-code 200 :msg (str "Club name required"))
        (if (db/club_exists? (:club_name p))
          (hash-map :status "failed" :status-code 200 :msg (str "Club '" club_name "' already exists."))
          (let [phone (if (s/blank? (:phone_number p)) 0 (:phone_number p))
                zip (if (s/blank? (:zip_code p)) 0 (:zip_code p))]
            (db/add_club club_name (:street p) (:city p) (:club_state p) zip phone)
            (hash-map :status "success" :status-code 200 :msg (str "Club '" club_name "' added.")))))
      (catch SQLException se
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of club '" club_name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn add-season
  "docstring"
  [& params]
  (println "add season parms: " params)
  (println "season: " (:season (nth params 0)))
  (let [
        p (nth params 0)
        season (:season p)
        start-date (:start_date p)
        end-date (:end_date p)]
    (try
      (println "season: " season)
      (println "start-date: " start-date)
      (println "end-date" end-date)
      (if (s/blank? season)
        (hash-map :status "failed" :status-code 200 :msg (str "Season name required"))
        (if (db/season-exists? season)
          (hash-map :status "failed" :status-code 200 :msg (str "Season '" season "' already exists."))
          (if (t/after? (f/parse date-fmt end-date) (f/parse date-fmt start-date))
            (do
              (db/add_season season start-date end-date)
              (hash-map :status "success" :status-code 200 :msg (str "Season '" season "' added.")))
            (hash-map :status "failed" :status-code 200 :msg (str "Season '" season "' not added. End date (" end-date ") must be greater than start date (" start-date ").")))))
      (catch SQLException se
        (println se)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of season '" season "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println e)
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn load-schedule
  "docstrng"
  [& params]
  (println "load-schedule parms: " params)
  (let [p (nth params 0)
        schedule (:schedule p)
        abbrevMap (db/team-schedule-abbreviations)]
    (doseq [line (line-seq (BufferedReader. (StringReader. schedule)))]
      (let [lineArr (s/split line #"\t")
            teamArr (s/split (nth lineArr 1) #" @ ")
            homeTeamId (get abbrevMap (keyword (nth teamArr 1)))
            awayTeamId (get abbrevMap (keyword (nth teamArr 0)))
            matchDate (nth lineArr 0)]
        (db/add-match (:season_id p) matchDate homeTeamId awayTeamId)))
    (hash-map :status "success" :status-code 200 :msg (str "Season loaded"))))

(defn load-schedule-file
  "docstrng"
  [& params]
  (println "load-schedule-file parms: " params)
  (let [p (nth params 0)
        scheduleFile (:schedule p)
        abbrevMap (db/team-schedule-abbreviations)]
    (with-open [rdr (io/reader scheduleFile)]
      (doseq [line (line-seq rdr)]
        (let [lineArr (s/split line #"\t")
              teamArr (s/split (nth lineArr 1) #" @ ")
              homeTeamId (get abbrevMap (keyword (nth teamArr 1)))
              awayTeamId (get abbrevMap (keyword (nth teamArr 0)))
              matchDate (nth lineArr 0)]
          (db/add-match (:season_id p) matchDate homeTeamId awayTeamId))))
    (hash-map :status "success" :status-code 200 :msg (str "Season loaded"))))

(defn default-match-time
  "convert time to 24 hour time '09:30' AM returns 09:30.  '02:30 PM' returns 14:30."
  [input-time]
  (let [match-time (subs input-time 0 5)]
    (if (or (s/ends-with? input-time "AM") (s/starts-with? input-time "12"))
      (str match-time)
      (let [time-arr (s/split match-time #":")]
        (str (+ (read-string (nth time-arr 0)) 12) ":" (nth time-arr 1))))))

(defn add-team
  "docstring"
  [& params]
  (println "add team parms: " params)
  (let [p (nth params 0)
        team-name (:team_name p)
        club-id (:club_id p)]
    (try
      (if (s/blank? team-name)
        (hash-map :status "failed" :status-code 200 :msg (str "Team name required"))
        (if (db/team-exists? club-id team-name)
          (hash-map :status "failed" :status-code 200 :msg (str "Team '" team-name "' already exists."))
          (do
            (db/add-team team-name club-id (default-match-time (:match_time p)))
            (hash-map :status "success" :status-code 200 :msg (str "Team '" team-name "' added.")))))
      (catch SQLException se
        (println se)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of club '" team-name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println e)
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))


