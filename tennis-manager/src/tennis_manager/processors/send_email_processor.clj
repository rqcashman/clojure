(ns tennis-manager.processors.send-email-processor
  (:require [clojure.string :as s]
            [tennis-manager.content.admin :as admin]
            [tennis-manager.content.gmail :as mail]
            [tennis-manager.data.communication-data-handler :as comm]
            [tennis-manager.data.schedule-data-handler :as sched]
            [tennis-manager.data.team-data-handler :as team]
            [tennis-manager.data.user-info :as usr]
            [tennis-manager.data.system-info :as sys]
            [ring.util.codec :as codec]))

(defn format-phone
  "Format phone number"
  [phone-number]
  (if (> (count phone-number) 7)
    (str (subs phone-number 0 3) "." (subs phone-number 3 6) "." (subs phone-number 6))
    (if (= (count phone-number) 7)
      (str (subs phone-number 0 3) "." (subs phone-number 3))
      phone-number)))

(defn format-map-link
  "Format a Google maps link to the match location - used in match emails"
  [address]
  (str "<a href='https://maps.google.com/maps/dir/?saddr=My+Location&daddr=" (codec/form-encode address) "'>" address "</a>"))

(defn match-header
  "docstring"
  [match-info]
  (let [address (str (:address match-info) ", " (:city match-info) " " (:state match-info) ", " (:zip_code match-info))]
    (str
      "<table width='100%' align='left'>"
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
      )))

(defn get-availability-email-body
  "Generate html to use as rich text email"
  [message signature match-info]
  (str
    (match-header match-info)
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
    "   <tr><td colspan='2'><h4>" (s/replace signature #"\n" "<br>") "</h4></td></tr>"

    "</table>"))

(defn get-lineup-row
  "Puts out the lineup for a court.  Each player TD has ---id--- which is used to hilight a players name in the lineup email they receive"
  [out court]
  (conj out (str
              "<tr>"
              "   <td><b>Court " (:court_number court) ":</b></td>"
              "   <td width='5%'>&nbsp;</td>"
              (if (= (:forfeit_team_name court) nil)
                (do
                  (str "<td---" (:player1_id court) "--->" (:player1_name court) "</td>"
                       "<td width='5%'>&nbsp;</td>"
                       "<td---" (:player2_id court) "--->" (:player2_name court) "</td>"))
                (str "<td colspan='3' align='left' style='color:red;font-weight:bold'>" (:forfeit_team_name court) " forfeit</td>"))
              "</tr>")))

(defn get-lineup-email-body
  "Generate html to use as rich text email"
  [message signature match-info]
  (str
    (match-header match-info)
    "<table width='70%' align='left'>"
    "   <tr>"
    "      <td align='left' colspan='2'>---salutation---,</td>"
    "   </tr>"
    "   <tr><td></td><td><table>---courts---</table></td></tr>"
    "   <tr><td colspan='2'>&nbsp;</td></tr>"
    "   <tr>"
    "      <td width='5%'></td>"
    "      <td> " (s/replace message #"\n" "<br>") "</td>"
    "   </tr>"
    "   <tr><td colspan='2'>&nbsp;</td></tr>"
    "   <tr><td colspan='2'><h4>" (s/replace signature #"\n" "<br>") "</h4></td></tr>"

    "</table>"))

(defn send-avail-email
  "Send a lineup availabiility email for a match"
  [{:keys [message signature match_id send_subs]}]
  (try
    (let [match-info (sched/match-info match_id)
          email-body (get-availability-email-body message signature match-info)
          subject (str "Match availability for " (:match_date match-info))
          parms (conj sys/email-cred {:from usr/user_email :to [usr/user_email] :subject subject :text email-body})]
      (doseq [player (team/team-roster usr/users_team_id)]
        (if (and (= (s/blank? (:email player)) false)
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

(defn style-courts
  "Hilights a player if they are in the lineup"
  [court-assignments player-id]
  (s/replace (s/replace court-assignments (re-pattern (str "---" player-id "---")) " style='font-weight:bold'") #"---\d+---" ""))


(defn send-lineup-email
  "Send a match lineup email"
  [{:keys [message signature match_id send_subs]}]
  (try
    (let [match-info (sched/match-info match_id)
          court-assignments (s/join (reduce #(get-lineup-row %1 %2) () (reverse (sched/match-lineup match_id))))
          email-body (get-lineup-email-body message signature match-info)
          subject (str "Lineup for " (:match_date match-info))
          parms (conj sys/email-cred {:from usr/user_email :to [usr/user_email] :subject subject :text email-body})]
      (doseq [player (sched/get-lineup-email-addresses match_id usr/users_team_id send_subs)]
        (if (= (s/blank? (:email player)) false)
          (let [msg-courts (s/replace email-body "---courts---" (style-courts court-assignments (:id player)))
                email-msg (s/replace msg-courts #"---salutation---" (str (:first_name player) " " (:last_name player)))
                email-parms (conj parms (hash-map :to [(:email player)] :text email-msg))]
            (mail/send-gmail email-parms))))
      (comm/upsert_match_lineup_email_sent match_id)
      (hash-map :status "success" :status-code 0 :msg (str "Success") :support-msg "Lineup email sent"))
    (catch Exception e
      (println "Error sending lineup email.  Msg" (.getMessage e))
      (hash-map :status "failed" :status-code 500 :msg (str "Server error sending lineup email.") :support-msg (.getMessage e)))))

