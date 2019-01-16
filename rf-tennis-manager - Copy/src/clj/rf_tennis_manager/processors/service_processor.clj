(ns rf-tennis-manager.processors.service-processor
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [rf-tennis-manager.data.club-data-handler :as club]
            [rf-tennis-manager.data.player-data-handler :as player]
            [rf-tennis-manager.data.schedule-data-handler :as sched]
            [rf-tennis-manager.data.season-data-handler :as season]
            [rf-tennis-manager.data.team-data-handler :as team]
            [rf-tennis-manager.data.auth-handler :as auth])
  (:import (java.sql SQLException)
           (clojure.lang EdnReader$StringReader)
           (java.io StringReader BufferedReader)))

(def date-fmt (f/formatter "MMM dd YYYY"))

(defn add-club
  "Add a club to the DB"
  [session params]
  (let [club_name (:club_name params)]
    (try
      (if (s/blank? club_name)
        (hash-map :status "failed" :status-code 200 :msg (str "Club name required"))
        (if (club/club_exists? (:club_name params))
          (hash-map :status "failed" :status-code 200 :msg (str "Club '" club_name "' already exists."))
          (let [phone (if (s/blank? (:phone_number params)) 0 (:phone_number params))
                zip (if (s/blank? (:zip_code params)) 0 (:zip_code params))]
            (club/add_club club_name (:street params) (:city params) (:club_state params) zip phone)
            (hash-map :status "success" :status-code 200 :msg (str "Club '" club_name "' added.")))))
      (catch Exception e
        (println "Exception is add-club: " e)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of club '" club_name "' failed.") :support-msg (.getMessage e))))))

(defn add-player
  "Add a player to the DB"
  [session params]
  (let [team_id (:team_id params)
        first_name (:first_name params)
        last_name (:last_name params)
        phone_number (:phone_number params)
        email (:email params)
        status (:status params)]
    (try
      (if (or (s/blank? first_name) (s/blank? last_name))
        (hash-map :status "failed" :status-code 200 :msg (str "Player first and last name required"))
        (if (player/player-exists? team_id first_name last_name)
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' already exists."))
          (if (or (< (count first_name) 2) (< (count last_name) 2))
            (hash-map :status "failed" :status-code 200 :msg "First and last name must contain at least 2 characters")
            (do
              (player/add_player team_id first_name last_name email phone_number status)
              (hash-map :status "success" :status-code 200 :msg (str "Player '" first_name " " last_name "' added."))))))
      (catch Exception e
        (println "Exception in add-player: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of player '" first_name " " last_name "' failed.") :support-msg (.getMessage e))))))

(defn add-season
  "Add a season to the DB"
  [session params]
  (let [season (:season params)
        start-date (subs (:start_date params) 4)
        end-date (subs (:end_date params) 4)]
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
      (catch Exception e
        (println "Exception in add-season: " e)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of season '" season "' failed.") :support-msg (.getMessage e))))))

(defn load-schedule
  "Loads a season schedule."
  [session params]
  (comment "Sample input:
             Jan-06-2018 12:00 PM\tHP Bronze @ WTF
             Jan-06-2018 12:00 PM\tEH Blue @ CAM
   Format is match tab then a tab and then the 2 clubs with an @ sign
   On either side of the @ is a club abbreviation which is tranlated to the correct club
  ")
  (let [schedule (:schedule params)
        abbrevMap (sched/team-schedule-abbreviations)]
    (doseq [line (line-seq (BufferedReader. (StringReader. schedule)))]
      (let [lineArr (s/split line #"\t")
            teamArr (s/split (nth lineArr 1) #" @ ")
            homeTeamId (get abbrevMap (keyword (nth teamArr 1)))
            awayTeamId (get abbrevMap (keyword (nth teamArr 0)))
            matchDate (nth lineArr 0)]
        (sched/add-match (:season_id params) matchDate homeTeamId awayTeamId)))
    (hash-map :status "success" :status-code 200 :msg (str "Season loaded"))))

(defn update-player-info
  "Updates player info.  "
  [session params]
  (let [first_name (:first_name params)
        last_name (:last_name params)
        email (:email params)
        phone_number (:phone_number params)
        status (:status params)
        team_id (:team_id params)
        player_id (:player_id params)]
    (try
      (if (or (s/blank? first_name) (s/blank? last_name))
        (hash-map :status "failed" :status-code 200 :msg (str "Player first and last name required"))
        (if (player/player-id-exists? player_id)
          (if (player/player-name-available? team_id player_id first_name last_name)
            (if (or (< (count first_name) 2) (< (count last_name) 2))
              (hash-map :status "failed" :status-code 200 :msg "First and last name must contain at least 2 characters")
              (do
                (player/update-player player_id last_name first_name email phone_number status)
                (hash-map :status "success" :status-code 200 :msg (str "Player '" first_name " " last_name "' updated."))))
            (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' exists under another player id.")))
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' does not exist with the given player id."))))
      (catch Exception e
        (println "Exception in update-player-info: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Update of player '" first_name " " last_name "' failed.") :support-msg (.getMessage e))))))

(defn update-player-availability
  "Process the request to update player availability"
  [session parms]
  (let [match-id (:match_id parms)]
    (try
      (sched/reset-match-availability match-id)
      (doseq [keyval parms]
        (if (s/starts-with? (key keyval) ":pl-av-")
          (let [player-id (nth (s/split (str (key keyval)) #":pl-av-") 1)]
            (sched/upsert_player_availability match-id player-id "Y"))))
      (hash-map :status "success" :status-code 200 :msg (str "Match availability updated for team"))
      (catch Exception e
        (println "Exception in update-player-availability: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Error updating player availability.") :support-msg (.getMessage e))))))

(def player-id-arr '("c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"))

(defn removePlayersFromForfeitedCourts
  "Removes players from the parm array if they are assigned to a court that has been forfeited"
  [parms court]
  (let [forfeit-grp (str "c" court "-forfeit-grp")
        p1 (str "c" court "p1")
        p2 (str "c" court "p2")]
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
                          (conj list (str (:last_name p) ", " (:first_name p)))))
          () player-id-list))

(defn validate-lineup
  "Validate the input lineup is valid.  All courts have players and no player assigned to multiple courts"
  [parms]
  (try
    (if (check-for-null-player-assignment parms)
      (hash-map :status "failed" :status-code 200 :msg (str "Not all courts have players assigned."))
      (let [dupe-list (check-for-duplicate-player-assignment parms)]
        (if (pos? (count dupe-list))
          (hash-map :status "failed" :status-code 200 :msg (str "Some players assigned to muliptle courts. " (get-player-names dupe-list))))))
    (catch Exception e
      (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e)))))

(defn update-lineup
  "Process an update lineup request"
  [session inparms]
  (comment "Chrome is sending values for disabled items.  Need to remove players from the parm list if they are
            assigned to a court that is being forfeited")
  (let [parms (reduce #(removePlayersFromForfeitedCourts %1 %2) inparms (range 1 5))
        validation-errors (validate-lineup parms)
        user (auth/get-user-from-session-id (:identity session))]
    (try
      (if (nil? validation-errors)
        (do
          (dotimes [x 4]
            (let [court (inc x)
                  player1 ((keyword (str "c" court "p1")) parms)
                  player2 ((keyword (str "c" court "p2")) parms)
                  forfeit-team-id ((keyword (str "c" court "-forfeit-grp")) parms)]
              (sched/upsert-match-lineup (:match_id parms) (:team_id user) court player1 player2 forfeit-team-id)))
          (hash-map :status "success" :status-code 200 :msg (str "Match lineup updated for team")))
        validation-errors)
      (catch Exception e
        (println "Exception in update-lineup: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))


(defn load-schedule-file
  "Loads a season schedule from a file.  Not working because we get the file name not the contents"
  [session params]
  (let [scheduleFile (:schedule params)
        abbrevMap (sched/team-schedule-abbreviations)]
    (with-open [rdr (io/reader scheduleFile)]
      (doseq [line (line-seq rdr)]
        (let [lineArr (s/split line #"\t")
              teamArr (s/split (nth lineArr 1) #" @ ")
              homeTeamId (get abbrevMap (keyword (nth teamArr 1)))
              awayTeamId (get abbrevMap (keyword (nth teamArr 0)))
              matchDate (nth lineArr 0)]
          (sched/add-match (:season_id params) matchDate homeTeamId awayTeamId))))
    (hash-map :status "success" :status-code 200 :msg (str "Season loaded"))))

(defn add-team
  "Add team"
  [session params]
  (let [team-name (:team_name params)
        club-id (:club_id params)
        sched-abbrev (:sched_abbrev params)]
    (try
      (cond
        (s/blank? team-name) (hash-map :status "failed" :status-code 200 :msg (str "Team name required"))
        (team/team-exists? club-id team-name) (hash-map :status "failed" :status-code 200 :msg (str "Team '" team-name "' already exists."))
        (team/sched-abbreviation-exists? sched-abbrev) (hash-map :status "failed" :status-code 200 :msg (str "Schedule abbrevation '" sched-abbrev "' already exists."))
        :else (do
                (team/add-team team-name club-id sched-abbrev)
                (hash-map :status "success" :status-code 200 :msg (str "Team '" team-name "' added."))))
      (catch Exception e
        (println "Exception in add-team: " e)
        (hash-map :status "failed" :status-code 500 :msg (str "Insert of team '" team-name "' failed.") :support-msg (.getMessage e))))))

(defn match-availability
  "docstring"
  [session params]
  (let [user (auth/get-user-from-session-id (:identity session))]
    (sched/match-availability (:match-id params) user))
  )

(defn match-lineup
  "docstring"
  [session params]
  (let [user (auth/get-user-from-session-id (:identity session))]
    (sched/match-lineup (:match-id params) user))
  )
