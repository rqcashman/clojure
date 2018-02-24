(ns tennis-manager.data.schedule-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn add-match
  "docstring"
  [season-id date home_team_id away_team_id]
  (j/execute! sys/db-cred
              [(str "insert into schedule values (null,?,str_to_date(?, '%b-%d-%Y %l:%i %p'),?,?,0,0)") season-id date home_team_id away_team_id]))

(defn match-info
  "docstring"
  [match-id]
  (-> (j/query sys/db-cred
               [(str "select DATE_FORMAT(s.match_date,'%M %D, %Y') as match_date,DATE_FORMAT(s.match_date,'%h:%i %p') as match_time"
                     " ,cl.name as club_name, cl.address, cl.city, cl.state, cl.zip_code, cl.phone_number, s.home_team_id, s.away_team_id"
                     " from schedule s"
                     " join team t on t.id = s.home_team_id"
                     " join club cl on cl.id = t.club_id"
                     " where s.match_id=?") match-id])
      first))


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


(defn match-availability
  "docstring"
  [match-id user]
  (j/query sys/db-cred
           [(str "select p.id, p.first_name, p.last_name, p.status,
            pc.date_sent, pc.response, DATE_FORMAT(pc.response_date, '%M %D, %Y %h:%i %p') as response_date, ma.available,
            mc.home_player1, mc.home_player2, mc.away_player1, mc.away_player2, mc.court_number
            from player p
            left join player_communication pc on pc.player_id = p.id and pc.match_id = ?
            left join match_availability ma on ma.player_id = p.id and ma.match_id = ?
            left join match_courts mc on mc.match_id = ?
            and (home_player1 = p.id or home_player2 = p.id or away_player1 = p.id or away_player2 = p.id)
            where p.team_id = ?
            order by p.last_name, p.first_name") match-id match-id match-id (:team_id user)]))

(defn match-forfeits
  "docstring"
  [match-id]
  (j/query sys/db-cred
           [(str "select match_id, court_number, forfeit_team_id
           from match_courts
           where match_id =?
           order by court_number") match-id]))

(defn reset-match-availability
  "docstring"
  [match-id]
  (j/execute! sys/db-cred
              [(str "update match_availability set available=0 where match_id = ?") match-id]))

(defn reset-match-lineup
  "docstring"
  [match-id team-id]
  (let [match (match-info match-id)]
    (if (= (:home_team_id match) team-id)
      (j/execute! sys/db-cred
                  [(str "update match_courts set home_player_1 = null, home_player_2 = null where match_id = ?") match-id])
      (j/execute! sys/db-cred
                  [(str "update match_courts set away_player_1 = null, away_player_2 = null where match_id = ?") match-id]))))

(defn match_court_exists?
  "docstring"
  [match-id court]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from match_courts where match_id=? and court_number=?") match-id court])
      first :ct pos?))

(defn upsert-match-lineup
  "docstring"
  [match-id team-id court player1 player2 forfeit]
  (let [match (match-info match-id)
        forfeit_team_id (case forfeit
                          "0" nil
                          "1" (if (= team-id (:home_team_id match)) (:home_team_id match) (:away_team_id match))
                          "2" (if (= team-id (:home_team_id match)) (:away_team_id match) (:home_team_id match)))]
    (if (= (match_court_exists? match-id court) false)
      (j/execute! sys/db-cred
                  [(str "insert into match_courts values (?,?,null,null,null,null,null)") match-id, court]))
    (if (= (:home_team_id match) team-id)
      (j/execute! sys/db-cred
                  [(str "update match_courts set home_player1=?, home_player2=?, forfeit_team_id=? where match_id = ? and court_number = ?") player1 player2 forfeit_team_id match-id court])
      (j/execute! sys/db-cred
                  [(str "update match_courts set away_player1=?, away_player2=?, forfeit_team_id=? where match_id = ? and court_number = ?") player1 player2 forfeit_team_id match-id court]))))

(defn update-player-availability
  "docstring"
  [match-id player-id avail-value]
  (j/execute! sys/db-cred
              [(str "update match_availability set available=? where match_id = ? and player_id = ?")
               avail-value match-id player-id]))

(defn match_availability_exists?
  "docstring"
  [match_id player_id]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from match_availability where match_id=? and player_id=?") match_id player_id])
      first :ct pos?))

(defn upsert_player_availability
  "docstring"
  [match_id player_id available]
  (let [avail_flag (if (= available "Y") 1 0)]
    (if (match_availability_exists? match_id player_id)
      (j/execute! sys/db-cred
                  [(str "update match_availability set available=? where match_id=? and player_id=?") avail_flag match_id player_id])
      (j/execute! sys/db-cred
                  [(str "insert into match_availability values (?,?,?)") match_id player_id avail_flag]))))

(def court-null-list #{nil 0})

(defn court-valid?
  "docstring"
  [valid court]
  (if (= valid false)
    false
    (let [p1 (:player1 court)
          p2 (:player2 court)
          forfeit (:forfeit_team_id court)]
      (if (not (contains? court-null-list forfeit))
        true
        (if (and
              (not (contains? court-null-list p1))
              (not (contains? court-null-list p2)))
          true
          false)))))


(defn lineup-set?
  "docstring"
  [match-id user]
  (let [match (match-info match-id)
        player1-col (if (= (:home_team_id match) (int (:team_id user))) "home_player1 as player1," "away_player1 as player1,")
        player2-col (s/replace player1-col #"1" "2")
        sql (str "select " player1-col player2-col "forfeit_team_id
                 from match_courts
                 where match_id = ? order by court_number")
        results (j/query sys/db-cred
                         [sql match-id])]
    (if (< (count results) 4)
      false
      (reduce #(court-valid? %1 %2) true results))))

(defn match-lineup
  "Get the match lineup for the logged in user"
  [match-id user]
  (let [match (match-info match-id)
        player1-col (if (= (:home_team_id match) (:team_id user)) "home_player1" "away_player1")
        player2-col (s/replace player1-col #"1" "2")
        sql (str "select court_number," player1-col " as player1," player2-col " as player2,forfeit_team_id,t.name as forfeit_team_name,
                 concat (p1.last_name, ', ', p1.first_name) as player1_name, p1.id as player1_id,
                 concat (p2.last_name, ', ', p2.first_name) as player2_name, p2.id as player2_id
                 from match_courts mc
                 left join team t on t.id = mc.forfeit_team_id
                 left join player p1 on p1.id = " player1-col
                 " left join player p2 on p2.id = " player2-col
                 " where mc.match_id = ? order by court_number")]
    (j/query sys/db-cred
             [sql match-id])))

(def line-email-address-sql
  (str "SELECT last_name, first_name, email, status, id"
       "  FROM player"
       "  WHERE team_id = ? AND status = (--status--)"
       "  AND email IS NOT NULL and email <> ' '"
       "  UNION SELECT last_name, first_name, email, status, p.id"
       "  FROM schedule s"
       "  JOIN match_courts mc ON mc.match_id = s.match_id"
       "  JOIN player p ON p.team_id = ?"
       "       AND (mc.home_player1 = p.id"
       "       OR mc.home_player2 = p.id"
       "       OR mc.away_player1 = p.id"
       "       OR mc.away_player2 = p.id)"
       "  WHERE s.match_id = ? "
       "  AND email IS NOT NULL and email <> ' '"
       "  ORDER BY last_name"))

(defn get-lineup-email-addresses
  "docstring"
  [match-id team-id send-subs]
  (let [status (if (s/blank? send-subs) "'A'", "'A','S'")
        sql (s/replace line-email-address-sql #"--status--" status)]
    (j/query sys/db-cred
             [sql team-id team-id match-id])))





