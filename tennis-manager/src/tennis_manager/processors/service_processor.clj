(ns tennis-manager.processors.service-processor
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [tennis-manager.content.admin :as admin]
            [tennis-manager.content.email-body :as em]
            [tennis-manager.content.gmail :as mail]
            [tennis-manager.data.club-data-handler :as club]
            [tennis-manager.data.communication-data-handler :as comm]
            [tennis-manager.data.player-data-handler :as player]
            [tennis-manager.data.schedule-data-handler :as sched]
            [tennis-manager.data.season-data-handler :as season]
            [tennis-manager.data.team-data-handler :as team]
            [tennis-manager.data.user-info :as usr]
            [tennis-manager.data.system-info :as sys]
            [ring.util.codec :as codec])
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
        (if (player/player-exists? team_id first_name last_name)
          (hash-map :status "failed" :status-code 200 :msg (str "Player '" first_name " " last_name "' already exists."))
          (do
            (player/add_player team_id first_name last_name email phone_number)
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
  "docstrng"
  [& params]
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
  "docstring"
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

(defn check-for-duplicate-player-assignment
  "docstring"
  [parms]
  (let [player-list (reduce (fn [list id] (conj list ((keyword id) parms))) () player-id-arr)
        dupe-list (->> player-list frequencies (remove #(= 1 (val %))) keys)]
    dupe-list))

(defn check-for-null-player-assignment
  [parms]
  "docstring"
  (let [player-list (reduce (fn [list id] (conj list ((keyword id) parms))) () player-id-arr)]
    (some #(= % "0") player-list)))


(defn get-player-names
  "docstring"
  [player-id-list]
  (reduce (fn [list id] (let [p (nth (player/player id) 0)]
                          (println "player: " p)
                          (conj list (str (:last_name p) ", " (:first_name p)))))
          ()
          player-id-list))

(defn update-lineup
  "docstring"
  [parms]
  (let [match-id (:match_id parms)]
    (try
      (if (= (check-for-null-player-assignment parms) true)
        (hash-map :status "failed" :status-code 200 :msg (str "Not all courts have players assigned."))
        (let [dupe-list (check-for-duplicate-player-assignment parms)]
          (if (> (count dupe-list) 0)
            (hash-map :status "failed" :status-code 200 :msg (str "Some players assigned to muliptle courts. " (get-player-names dupe-list)))
            (do
              (println " parms: " parms)
              (dotimes [x 4]
                (let [court (inc x)
                      player1 ((keyword (str "c" court "-p1")) parms)
                      player2 ((keyword (str "c" court "-p2")) parms)]
                  (println "===========================")
                    (println "x: " x)
                    (println " court: " court)
                    (println "p1: " player1)
                    (println " p2: " player2)
                    (sched/upsert-match-lineup match-id usr/users_team_id court player1 player2)))
              (hash-map :status "success" :status-code 200 :msg (str "Match lineup updated for team"))))))
      (catch Exception e
        (println "Exception in update-lineup: " e)
        (println "Exception in update-lineup: " (.getMessage e))
        (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e))))))


(defn load-schedule-file
  "docstrng"
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

(defn send-email-orig
  "docstring"
  [& parms]
  (try
    (mail/send-gmail {:from     "rqcashman@gmail.com"
                      :to       ["rqcashman@gmail.com"]
                      :subject  "HP Bronze test"
                      :text     "<h1>This is a test</h1>"
                      :user     "rqcashman@gmail.com"
                      :password "oitdgcoxpdghplmb"})
    (hash-map :status "success" :status-code 0 :msg (str "Success") :support-msg "Email sent")
    (catch Exception e
      (hash-map :status "failed" :status-code 500 :msg (str "Server error.") :support-msg (.getMessage e)))))

(defn send-avail-email-orig
  "docstring"
  [{:keys [message signature match_id send_subs]}]
  (try
    (let [match-info (nth (sched/match-info match_id) 0)
          message (str "<table width='100%' align='left' cellpadding='0' cellspacing='0'>"
                       "<tr><td nowrap><b>Match date:</b><td nowrap>" (:match_date match-info) "</td><td width='80%'>&nbsp;</td></tr>"
                       "<tr><td nowrap><b>Match time:</b><td nowrap>" (:match_time match-info) "</td><td width='80%'>&nbsp;</td></tr>"
                       "<tr><td nowrap><b>Location:</b><td nowrap>" (:club_name match-info) "</td><td width='80%'>&nbsp;</td></tr>"
                       "<tr><td colspan='3'>&nbsp;</td></tr>"
                       "</table><br>"
                       "<table width='70%' align='left'>"
                       "<tr><td align='left' colspan='2'>---salutation---,</td></tr>"
                       "<tr><td width='5%'></td><td>" (s/replace message #"\n" "<br>") "</td></tr>"
                       "<tr><td width='5%'></td>"
                       "<td nowrap><a href='http://localhost:3000/availability-reply/Y/--uuid--'>I can play </a>"
                       "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://localhost:3000/availability-reply/N/--uuid--'>I'm out</a></td></tr>"
                       "<tr><td colspan='2'>&nbsp;</td></tr>"
                       "<tr><td colspan='2'><h4>" (s/replace signature #"\n" "<br>") "</h4></td></tr>"
                       "</table>"
                       )
          subject (str "Match availability for " (:match_date match-info))
          parms (conj sys/email-cred {:from usr/user_email :to [usr/user_email] :subject subject :text message})]
      (println "destructured input: " message signature match_id send_subs)
      (doseq [player (team/team-roster usr/users_team_id)]
        (println (:last_name player))
        (if (and (not= (:status player) "I") (or (not= (:status player) "S") (not= send_subs nil)))
          (let [uuid (str (java.util.UUID/randomUUID))
                email-msg (s/replace (s/replace message #"---salutation---" (str (:first_name player) " " (:last_name player))) #"--uuid--" uuid)
                email-parms (conj parms (hash-map :to [(:email player)] :text email-msg))]
            (mail/send-gmail email-parms)))))
    (hash-map :status "success" :status-code 0 :msg (str "Success") :support-msg "Availability email sent")
    (catch Exception e
      (println "Error sending availability email.  Msg" (.getMessage e))
      (hash-map :status "failed" :status-code 500 :msg (str "Server error sending availability email.") :support-msg (.getMessage e)))))

(defn format-phone
  [phone-number]
  (if (> (count phone-number) 7)
    (str (subs phone-number 0 3) "." (subs phone-number 3 6) "." (subs phone-number 6))
    (if (= (count phone-number) 7)
      (str (subs phone-number 0 3) "." (subs phone-number 3))
      phone-number)))

(defn format-map-link
  "docstring"
  [address]
  (str "<a href='https://maps.google.com/maps/dir/?saddr=My+Location&daddr=" (codec/form-encode address) "'>" address "</a"))

(defn get-email-body
  "generate html to use as rich text email"
  [message signature match-info]
  (let [address (str (:address match-info) ", " (:city match-info) " " (:state match-info) ", " (:zip_code match-info))]
    (str
      "<table width='100%' align='left' cellpadding='0' cellspacing='0'>"
      "   <tr> "
      "      <td nowrap style='font-weight:bold'>Match date:</td>"
      "      <td width='5%'>&nbsp;</td>"
      "      <td nowrap>" (:match_date match-info) "</td> "
      "      <td width='80%'>&nbsp;</td>"
      "   </tr>"
      "   <tr>"
      "      <td nowrap><b>Match time:</b></td>"
      "      <td width='5%'>&nbsp;</td>"
      "      <td nowrap>" (:match_time match-info) "</td>"
      "      <td width='80%'>&nbsp;</td>"
      "   </tr>"
      "   <tr>"
      "      <td nowrap><b>Location:</b></td>"
      "      <td width='5%'>&nbsp;</td>"
      "      <td nowrap>" (:club_name match-info) "</td>"
      "      <td width='80%'>&nbsp;</td>"
      "   </tr>"
      "   <tr>"
      "      <td nowrap><b>Address:</b></td>"
      "      <td width='5%'>&nbsp;</td>"
      "      <td nowrap>" (format-map-link address) "</td>"
      "      <td width='80%'>&nbsp;</td>"
      "   </tr>"
      "   <tr>"
      "      <td nowrap><b>Phone number:</b></td>"
      "      <td width='5%'>&nbsp;</td>"
      "      <td nowrap>" (format-phone (str (:phone_number match-info))) "</td>"
      "      <td width='80%'>&nbsp;</td>"
      "   </tr>"
      "   <tr><td colspan='4'>&nbsp;</td></tr>"
      "</table><br>"
      "<table width='70%' align='left'>"
      "   <tr>"
      "      <td align='left' colspan='2'>---salutation---,</td>"
      "   </tr>"
      "   <tr>"
      "      <td width='5%'></td>"
      "      <td> " (s/replace message #"\n" "<br>") "</td>"
      "   </tr>"
      "   <tr>"
      "      <td width='5%'></td> "
      "      <td nowrap><a href='http://localhost:3000/availability-reply?available=Y&player-token=--uuid--'>I can play</a> "
      "            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://localhost:3000/availability-reply?available=N&player-token=--uuid--'>I'm out</a>"
      "      </td> "
      "   </tr>"
      "   <tr><td colspan='2'>&nbsp;</td>"
      "    <tr>"
      "        <tr><td colspan='2'><h4>" (s/replace signature #"\n" "<br>") "</h4></td>"
      "    </tr>"
      "</table>")))

(defn send-avail-email
  "docstring"
  [{:keys [message signature match_id send_subs]}]
  (try
    (let [match-info (nth (sched/match-info match_id) 0)
          email-body (get-email-body message signature match-info)
          subject (str "Match availability for " (:match_date match-info))
          parms (conj sys/email-cred {:from usr/user_email :to [usr/user_email] :subject subject :text email-body})]
      (doseq [player (team/team-roster usr/users_team_id)]
        (if (and (complement (s/blank? (:email player)))
                 (not= (:status player) "I")
                 (or (not= (:status player) "S") (not= send_subs nil)))
          (let [uuid (s/replace (str (java.util.UUID/randomUUID)) #"-" "")
                email-msg (s/replace (s/replace email-body #"---salutation---" (str (:first_name player) " " (:last_name player))) #"--uuid--" uuid)
                email-parms (conj parms (hash-map :to [(:email player)] :text email-msg))]
            (mail/send-gmail email-parms)
            (comm/add_player_communication (:id player) match_id uuid)))))
    (comm/upsert_match_avail_email_sent match_id)
    (hash-map :status "success" :status-code 0 :msg (str "Success") :support-msg "Availability email sent")
    (catch Exception e
      (println "Error sending availability email.  Msg" (.getMessage e))
      (hash-map :status "failed" :status-code 500 :msg (str "Server error sending availability email.") :support-msg (.getMessage e)))))
