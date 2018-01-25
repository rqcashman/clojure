(ns tennis-manager.processors.service-processor
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [tennis-manager.content.admin :as admin]
            [tennis-manager.content.gmail :as mail]
            [tennis-manager.data.data-handler :as db])
  (:import (java.sql SQLException)
           (clojure.lang EdnReader$StringReader)
           (java.io StringReader BufferedReader)))

(def date-fmt (f/formatter "MM/dd/YYYY"))

(defn add-club
  "docstring"
  [& params]
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

(defn add-player
  "docstring"
  [& params]
  (let [p (nth params 0)
        team_id (:team_id p)
        first_name (:first_name p)
        last_name (:last_name p)
        phone_number (:phone_number p)
        email (:email p)]
    (try
      (if (or (s/blank? first_name) (s/blank? last_name))
        (hash-map :status "failed" :status-code 200 :msg (str "Player first and last name required"))
        (if (db/player-exists? team_id first_name last_name)
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' already exists."))
          (do
            (db/add_player team_id first_name last_name email phone_number)
            (hash-map :status "success" :status-code 200 :msg (str "Player '" first_name " " last_name "' added.")))))
      (catch SQLException se
        (println "SQLException in add-player: " (.getMessage se))
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of player '" first_name " " last_name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println "Exception in add-player: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn add-season
  "docstring"
  [& params]
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

(defn update-player-info
  "docstrng"
  [& params]
  (let [p (nth params 0)
        first_name (:first_name p)
        last_name (:last_name p)
        email (:email p)
        phone_number (:phone_number p)
        status (:status p)
        team_id (:team_id p)
        player_id (:player_id p)]
    (try
      (if (or (s/blank? first_name) (s/blank? last_name))
        (hash-map :status "failed" :status-code 200 :msg (str "Player first and last name required"))
        (if (db/player-id-exists? player_id)
          (if (db/player-name-available? team_id player_id first_name last_name)
            (do
              (db/update-player player_id last_name first_name email phone_number status)
              (hash-map :status "success" :status-code 200 :msg (str "Player '" first_name " " last_name "' updated.")))
            (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' exists under another player id.")))
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' does not exist with the given player id."))))
      (catch SQLException se
        (println "SQLException in update-player-info-player: " (.getMessage se))
        (hash-map :status "failed" :status-code 500 :msg (str "Update of player '" first_name " " last_name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println "Exception in add-update-player-info: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn load-schedule-file
  "docstrng"
  [& params]
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

(defn send-email
  "docstring"
  [& parms]
  (try
    (mail/send-gmail {:from     "rqcashman@gmail.com"
                      :to       ["rqcashman@gmail.com"]
                      :subject  "HP Bronze test"
                      :text     "Test message from server"
                      :user     "rqcashman@gmail.com"
                      :password "oitdgcoxpdghplmb"})
    (hash-map :status "success" :status-code 0 :msg (str "Success") :support-msg "Email sent")
    (catch Exception e
      (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e)))))



