(ns tennis-manager.processors.service-processor
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [tennis-manager.data.club-data-handler :as club]
            [tennis-manager.data.player-data-handler :as player]
            [tennis-manager.data.schedule-data-handler :as sched]
            [tennis-manager.data.season-data-handler :as season]
            [tennis-manager.data.team-data-handler :as team]
            [tennis-manager.data.user-info :as usr])
  (:import (java.sql SQLException)
           (clojure.lang EdnReader$StringReader)
           (java.io StringReader BufferedReader)))

(def date-fmt (f/formatter "MM/dd/YYYY"))

(defn add-club
  "Add a club to the DB"
  [& params]
  (let [p (nth params 0)
        club_name (:club_name p)]
    (try
      (if (s/blank? club_name)
        (hash-map :status "failed" :status-code 200 :msg (str "Club name required"))
        (if (club/club_exists? (:club_name p))
          (hash-map :status "failed" :status-code 200 :msg (str "Club '" club_name "' already exists."))
          (let [phone (if (s/blank? (:phone_number p)) 0 (:phone_number p))
                zip (if (s/blank? (:zip_code p)) 0 (:zip_code p))]
            (club/add_club club_name (:street p) (:city p) (:club_state p) zip phone)
            (hash-map :status "success" :status-code 200 :msg (str "Club '" club_name "' added.")))))
      (catch SQLException se
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of club '" club_name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn add-player
  "Add a player to the DB"
  [& params]
  (let [p (nth params 0)
        team_id (:team_id p)
        first_name (:first_name p)
        last_name (:last_name p)
        phone_number (:phone_number p)
        email (:email p)
        status (:status p)]
    (try
      (if (or (s/blank? first_name) (s/blank? last_name))
        (hash-map :status "failed" :status-code 200 :msg (str "Player first and last name required"))
        (if (player/player-exists? team_id first_name last_name)
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' already exists."))
          (do
            (player/add_player team_id first_name last_name email phone_number status)
            (hash-map :status "success" :status-code 200 :msg (str "Player '" first_name " " last_name "' added.")))))
      (catch SQLException se
        (println "SQLException in add-player: " (.getMessage se))
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of player '" first_name " " last_name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println "Exception in add-player: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn add-season
  "Add a season to the DB"
  [& params]
  (let [
        p (nth params 0)
        season (:season p)
        start-date (:start_date p)
        end-date (:end_date p)]
    (try
      (if (s/blank? season)
        (hash-map :status "failed" :status-code 200 :msg (str "Season name required"))
        (if (season/season-exists? season)
          (hash-map :status "failed" :status-code 200 :msg (str "Season '" season "' already exists."))
          (if (t/after? (f/parse date-fmt end-date) (f/parse date-fmt start-date))
            (do
              (season/add_season season start-date end-date)
              (hash-map :status "success" :status-code 200 :msg (str "Season '" season "' added.")))
            (hash-map :status "failed" :status-code 200 :msg (str "Season '" season "' not added. End date (" end-date ") must be greater than start date (" start-date ").")))))
      (catch SQLException se
        (println se)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of season '" season "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println e)
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn load-schedule
  "Loads a season schedule."
  [& params]
  (comment "Sample input:
             Jan-06-2018 12:00 PM\tHP Bronze @ WTF
             Jan-06-2018 12:00 PM\tEH Blue @ CAM
   Format is match tab then a tab and then the 2 clubs with an @ sign
   On either side of the @ is a club abbreviation which is tranlated to the correct club
  ")
  (let [p (nth params 0)
        schedule (:schedule p)
        abbrevMap (sched/team-schedule-abbreviations)]
    (doseq [line (line-seq (BufferedReader. (StringReader. schedule)))]
      (let [lineArr (s/split line #"\t")
            teamArr (s/split (nth lineArr 1) #" @ ")
            homeTeamId (get abbrevMap (keyword (nth teamArr 1)))
            awayTeamId (get abbrevMap (keyword (nth teamArr 0)))
            matchDate (nth lineArr 0)]
        (sched/add-match (:season_id p) matchDate homeTeamId awayTeamId)))
    (hash-map :status "success" :status-code 200 :msg (str "Season loaded"))))

(defn update-player-info
  "Updates player info.  "
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
        (if (player/player-id-exists? player_id)
          (if (player/player-name-available? team_id player_id first_name last_name)
            (do
              (player/update-player player_id last_name first_name email phone_number status)
              (hash-map :status "success" :status-code 200 :msg (str "Player '" first_name " " last_name "' updated.")))
            (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' exists under another player id.")))
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' does not exist with the given player id."))))
      (catch SQLException se
        (println "SQLException in update-player-info-player: " (.getMessage se))
        (hash-map :status "failed" :status-code 500 :msg (str "Update of player '" first_name " " last_name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println "Exception in add-update-player-info: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(defn update-player-availability
  "Process the request to update player availability"
  [parms]
  (let [match-id (:match_id parms)]
    (try
      (sched/reset-match-availability match-id)
      (doseq [keyval parms]
        (if (s/starts-with? (key keyval) ":pl-av-")
          (let [player-id (nth (s/split (str (key keyval)) #":pl-av-") 1)]
            (sched/upsert_player_availability match-id player-id "Y"))))
      (hash-map :status "success" :status-code 200 :msg (str "Match availability updated for team"))
      (catch Exception e
        (println "Exception in add-update-player-info: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))

(def player-id-arr '("c1-p1" "c1-p2" "c2-p1" "c2-p2" "c3-p1" "c3-p2" "c4-p1" "c4-p2"))

(defn removePlayersFromForfeitedCourts
  "Removes players from the parm array if they are assigned to a court that has been forfeited"
  [parms court]
  (let [forfeit-grp (str "c" court "-forfeit-grp")
        p1 (str "c" court "-p1")
        p2 (str "c" court "-p2")]
    (if (not= ((keyword forfeit-grp) parms) "0")
      (dissoc (dissoc parms (keyword p1)) (keyword p2))
      (conj parms))))

(defn check-for-duplicate-player-assignment
  "Returns a list of players assinged to more than one court"
  [parms]
  (let
    [player-list (reduce (fn [list id] (conj list ((keyword id) parms))) () player-id-arr)
     dupe-list (->> player-list frequencies (remove #(= 1 (val %))) keys)]
    (remove #(= nil %) dupe-list)))

(defn check-for-null-player-assignment
  [parms]
  "Returns a list where there was no player assigned to a court"
  (let [player-list (reduce (fn [list id] (conj list ((keyword id) parms))) () player-id-arr)]
    (some #(= % "0") player-list)))


(defn get-player-names
  "Convert player id to player name"
  [player-id-list]
  (reduce (fn [list id] (let [p (player/player id)]
                          (println "player: " p)
                          (conj list (str (:last_name p) ", " (:first_name p)))))
          ()
          player-id-list))

(defn validate-lineup
  "Validate the input lineup is valid.  All courts have players and no player assigned to multiple courts"
  [parms]
  (try
    (if (= (check-for-null-player-assignment parms) true)
      (hash-map :status "failed" :status-code 200 :msg (str "Not all courts have players assigned."))
      (let [dupe-list (check-for-duplicate-player-assignment parms)]
        (if (pos? (count dupe-list))
          (hash-map :status "failed" :status-code 200 :msg (str "Some players assigned to muliptle courts. " (get-player-names dupe-list))))))
    (catch Exception e
      (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e)))))

(defn update-lineup
  "Process an update lineup request"
  [inparms]
  (comment "Chrome is sending values for disabled items.  Need to remove players from the parm list if they are
            assigned to a court that is being forfeited")
  (let [parms (reduce #(removePlayersFromForfeitedCourts %1 %2) inparms (range 1 5))
        validation-errors (validate-lineup parms)]
    (try
      (if (= validation-errors nil)
        (do
          (dotimes [x 4]
            (let [court (inc x)
                  player1 ((keyword (str "c" court "-p1")) parms)
                  player2 ((keyword (str "c" court "-p2")) parms)
                  forfeit ((keyword (str "c" court "-forfeit-grp")) parms)]
              (sched/upsert-match-lineup (:match_id parms) usr/users_team_id court player1 player2 forfeit)))
          (hash-map :status "success" :status-code 200 :msg (str "Match lineup updated for team")))
        validation-errors)
      (catch Exception e
        (println "Exception in update-lineup: " e)
        (println "Exception in update-lineup: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))


(defn load-schedule-file
  "Loads a season schedule from a file.  Not working because we got the file nmae not the contents"
  [& params]
  (let [p (nth params 0)
        scheduleFile (:schedule p)
        abbrevMap (sched/team-schedule-abbreviations)]
    (with-open [rdr (io/reader scheduleFile)]
      (doseq [line (line-seq rdr)]
        (let [lineArr (s/split line #"\t")
              teamArr (s/split (nth lineArr 1) #" @ ")
              homeTeamId (get abbrevMap (keyword (nth teamArr 1)))
              awayTeamId (get abbrevMap (keyword (nth teamArr 0)))
              matchDate (nth lineArr 0)]
          (sched/add-match (:season_id p) matchDate homeTeamId awayTeamId))))
    (hash-map :status "success" :status-code 200 :msg (str "Season loaded"))))

(defn default-match-time
  "Convert time to 24 hour time '09:30' AM returns 09:30.  '02:30 PM' returns 14:30."
  [input-time]
  (let [match-time (subs input-time 0 5)]
    (if (or (s/ends-with? input-time "AM") (s/starts-with? input-time "12"))
      (str match-time)
      (let [time-arr (s/split match-time #":")]
        (str (+ (read-string (nth time-arr 0)) 12) ":" (nth time-arr 1))))))

(defn add-team
  "Retrun the Add Team page"
  [& params]
  (let [p (nth params 0)
        team-name (:team_name p)
        club-id (:club_id p)]
    (try
      (if (s/blank? team-name)
        (hash-map :status "failed" :status-code 200 :msg (str "Team name required"))
        (if (team/team-exists? club-id team-name)
          (hash-map :status "failed" :status-code 200 :msg (str "Team '" team-name "' already exists."))
          (do
            (team/add-team team-name club-id (default-match-time (:match_time p)))
            (hash-map :status "success" :status-code 200 :msg (str "Team '" team-name "' added.")))))
      (catch SQLException se
        (println se)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of club '" team-name "' failed.") :support-msg (.getMessage se)))
      (catch Exception e
        (println e)
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))
