(ns tennis-manager.content.matches
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]
        [tennis-manager.content.page-layout :as layout]
        [tennis-manager.data.team-data-handler :as team]
        [tennis-manager.data.schedule-data-handler :as sched]
        [tennis-manager.data.season-data-handler :as season]
        [tennis-manager.data.user-info :as usr]))

(def form-span 4)
(def sched-form-span 8)

(defn add-div
  [func-map]
  [:div.matches-action {:id (:id func-map)} (:content func-map)]
  )

(defn add-form-control
  [label options init-data]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
   (case (:type options)
     "text-area" [:td [:textarea options init-data]]
     [:td [:input.form-control-sm options init-data]])
   [:td {:width "5%"} "&nbsp;"]])

(def RED-X "&#10060;")
(def GREEN-CHECK "&#9989;")

(defn schedule-row
  "docstring"
  [rows sched-row]
  (let [home-team-id (:home_team_id sched-row)
        avail-func (if (= (:availability_sent sched-row) nil) "change_to_email_form" "change_to_avail_form")
        lineup-valid (sched/lineup-set? (:match_id sched-row))
        send-lineup-func (if (= lineup-valid true) "change_to_email_lineup_form" "set_lineup")]
    (conj rows [:tr
                [:td (:match_date sched-row)]
                [:td (:match_time sched-row)]
                [:td (if (= usr/users_team_id home-team-id) (:away_team sched-row) (:home_team sched-row))]
                [:td (:home_club_name sched-row)]
                [:td {:align "center"} (if (= usr/users_team_id home-team-id) (:home_team_points sched-row) (:away_team_points sched-row))]
                [:td {:align "center"} (if (= usr/users_team_id home-team-id) (:away_team_points sched-row) (:home_team_points sched-row))]
                [:td {:align "center"} [:span.avail-cursor {:onclick (str avail-func "('" (:match_id sched-row) "');")} (if (= (:availability_sent sched-row) nil) RED-X GREEN-CHECK)]]
                [:td {:align "center"} [:span.avail-cursor {:onclick (str "set_lineup('" (:match_id sched-row) "');")} (if (= lineup-valid false) RED-X GREEN-CHECK)]]
                [:td {:align "center"} [:span.avail-cursor {:onclick (str send-lineup-func "('" (:match_id sched-row) "');")} (if (= (:lineup_sent sched-row) nil) RED-X GREEN-CHECK)]]
                ])))

(defn get-team-schedule
  "docstring"
  []
  (try
    ;data is sorted by date.  Need to use reverse because conj is adding the data to the beginning of the list
    (reduce #(schedule-row %1 %2) () (reverse (team/team-schedule (:id (season/current-season)) usr/users_team_id)))
    (catch Exception e
      (println "Exception in get-team-schedule.  Message: " + e)
      [:tr [:td.error {:colspan sched-form-span :align "center"} "Error getting team schedule"]])))

(defn schedule-form
  "docstring"
  [team-name]
  [:table.table.table-sm
   (layout/empty-row form-span)
   [:tr [:td {:colspan form-span :align "center"} [:h4 team-name " Team Schedule"]]]
   (layout/hr-row form-span "90%")
   [:tr
    [:td {:width "5%:"}]
    [:td {:colspan 2}
     [:table#match-sched.table.table-striped.table-sm
      [:thead.table-inverse
       [:tr {:align "center"}
        [:td "Date"]
        [:td "Time"]
        [:td "Opponent"]
        [:td "Location"]
        [:td {:name "team-name"} "Points"]
        [:td "Opponent Points"]
        [:td "Availability Email Sent"]
        [:td "Lineup Set"]
        [:td "Lineup Sent"]]]
      [:tbody#match-sched-body]
      (get-team-schedule)]]
    [:td {:width "5%:"}]]
   (layout/hr-row form-span "90%")])

(defn availability-email-form
  "docstring"
  [team-name]
  (let [title "Send Availability Email"]
    [:form#sendavailabilityemail.form-horizontal {:method "post" :action "/send-availability-email"}
     [:table.table.table-sm
      [:tr [:td {:colspan form-span :align "center"} [:h4 title " for " team-name]]]
      (layout/hr-row form-span "90%")
      [:tr
       [:td {:width "50%"} "&nbsp;"]
       [:td {:colspan 2 :align "center"}
        [:table.table.table-sm.table-compact
         [:tr [:td] [:td {:colspan 3 :align "center"} [:h5 "Email Header"]]]
         [:tr [:td]
          [:td [:span.bold-text "Match Date:"]]
          [:td {:colspan 2} [:span#av_match_date "January 3"]]]
         [:tr [:td]
          [:td [:span.bold-text "Match Time:"]]
          [:td {:colspan 2} [:span#av_match_time "02:30 PM"]]]
         [:tr [:td]
          [:td [:span.bold-text "Location:"]]
          [:td {:colspan 2} [:span#av_match_location "Harpers"]]]]]
       [:td {:width "50%"} "&nbsp;"]]
      (layout/hr-row form-span "90%")
      (add-form-control "Message:" {:id "av_message" :name "message" :cols 45 :maxlength 2000 :rows 7 :type "text-area"} "Let me know if you are available to play.")
      (add-form-control "Signature:" {:id "av_signature" :name "signature" :cols 45 :maxlength 200 :rows 3 :type "text-area"} "Rick Cashman\n513.227.9278")
      (add-form-control "Send to Subs:" {:id "av_send_subs" :name "send_subs" :type "checkbox"} nil)
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:table
             [:td {:width "50%"} "&nbsp;"]
             [:td {:align "right" :nowrap "true"}
              [:button {:type "button" :onclick (str "return processMatchRequest('#sendavailabilityemail', '/send-availability-email', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_match_form('show-schedule');"} "Return to Schedule"]]
             [:td {:width "5%"} "&nbsp;"]]]]
      [:tr.hidden-control
       [:td {:colspan form-span :align "center"}
        [:input.hidden-control {:id "av_team_id" :name "team_id"}]
        [:input.hidden-control {:id "av_match_id" :name "match_id"}]]]
      (layout/empty-row form-span)]]))

(defn show-availability-form
  "docstring"
  [team-name]
  (let [title "Update Availability"]
    [:form#updateavailability.form-horizontal {:method "post" :action "/update-availability"}
     [:table.table.table-sm
      [:tr [:td {:colspan form-span :align "center"} [:h4 title " for " team-name]]]
      (layout/hr-row form-span "90%")
      [:tr
       [:td {:width "5%"} "&nbsp;"]
       [:td {:colspan 2 :align "center"}
        [:table.table.table-sm.table-compact
         [:tr [:td]
          [:td {:width "12%" :nowrap "true"} [:span.bold-text "Match Date:"]]
          [:td {:colspan 2 :align "left"} [:span#pa_match_date "January 32nd"]]]
         [:tr [:td]
          [:td {:nowrap "true"} [:span.bold-text "Match Time:"]]
          [:td {:colspan 2 :align "left"} [:span#pa_match_time "25:30 PM"]]]
         [:tr [:td]
          [:td {:nowrap "true"} [:span.bold-text "Location:"]]
          [:td {:colspan 2 :align "left"} [:span#pa_match_location "No where"]]]]]
       [:td {:width "5%"} "&nbsp;"]]
      (layout/hr-row form-span "90%")
      [:tr
       [:td {:width "5%"} "&nbsp;"]
       [:td {:colspan 2 :align "center"}
        [:table.table.table-sm
         [:thead.table-inverse
          [:td {:align "left"} "Player name"]
          [:td {:align "center"} "Available"]
          [:td {:align "center"} "Email Response"]
          [:td {:align "center"} "Email Sent"]
          [:td {:align "left"} "Email Response Time"]
          [:td {:align "center"} "Status"]]
         [:tbody#av-details-body
          (layout/empty-row 6)]]
        [:td {:width "5%"} "&nbsp;"]]]
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:table
             [:td {:width "50%"} "&nbsp;"]
             [:td {:align "right" :nowrap "true"}
              [:button {:type "button" :onclick (str "return processMatchRequest('#updateavailability', '/update-availability', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_match_form('show-schedule');"} "Return to Schedule"]]
             [:td {:width "5%"} "&nbsp;"]]]]
      [:tr.hidden-control
       [:td {:colspan form-span :align "center"}
        [:input.hidden-control {:id "pa_match_id" :name "match_id"}]]]
      (layout/empty-row form-span)]]))

(defn add-match-select-controls
  "docstring"
  [list court team-name]
  (let [player-1 (str "c" court "-p1")
        player-2 (str "c" court "-p2")
        btn-grp (str "c" court "-forfeit-grp")
        no-forfeit (str "c" court "-forfeit-none")
        team-forfeit (str "c" court "-forfeit")
        opp-forfeit (str "c" court "-forfeit-opp")
        btn-disabled (if (= court 4) false true)]
    (conj list
          [:tr
           [:td [:span {:style "font-weight:bold"} (str "Court " court)]]
           [:td [:select {:id player-1 :name player-1}
                 [:option "Rick Cashman"]]]
           [:td [:select {:id player-2 :name player-2}]]
           [:td
            [:fieldset {:id btn-grp}
             "&nbsp;&nbsp;" [:input {:type "radio" :value "0" :id no-forfeit :name btn-grp :disabled btn-disabled :checked true :onclick "updateForfeitBtns(this)"} "&nbsp;&nbsp;None"] [:br]
             "&nbsp;&nbsp;" [:input {:type "radio" :value "1" :id team-forfeit :name btn-grp :disabled btn-disabled :onclick "updateForfeitBtns(this)"} "&nbsp;&nbsp;" team-name] [:br]
             "&nbsp;&nbsp;" [:input {:type "radio" :value "2" :id opp-forfeit :name btn-grp :disabled btn-disabled :onclick "updateForfeitBtns(this)"} "&nbsp;&nbsp;Opponent"] [:br]]]])))

(defn lineup-form
  "Sets up the lineup page."
  [team-name]
  [:form#updatelineup.form-horizontal {:method "post" :action "/update-lineup"}
   (let [title "Update Match Lineup"]
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 team-name " " title]]]
      (layout/hr-row form-span "90%")
      [:tr
       [:td {:width "5%"} "&nbsp;"]
       [:td {:colspan 2 :align "center"}
        [:table.table.table-sm.table-compact
         [:tr [:td]
          [:td {:width "20%" :nowrap "true"} [:span.bold-text "Match Date:"]]
          [:td {:colspan 2 :align "left"} [:span#ml_match_date "January 32nd"]]]
         [:tr [:td]
          [:td {:nowrap "true"} [:span.bold-text "Match Time:"]]
          [:td {:colspan 2 :align "left"} [:span#ml_match_time "25:30 PM"]]]
         [:tr [:td]
          [:td {:nowrap "true"} [:span.bold-text "Location:"]]
          [:td {:colspan 2 :align "left"} [:span#ml_match_location "No where"]]]]]
       [:td {:width "5%"} "&nbsp;"]]
      (layout/empty-row form-span)
      [:tr
       [:td {:width "5%:"}]
       [:td {:colspan 2}
        [:table#match-lineup.table.table-striped.table-sm
         [:thead.table-inverse
          [:tr {:align "left"}
           [:td ""]
           [:td "Player 1"]
           [:td "Player 2"]
           [:td "Forfeit"]]]
         [:tbody#match-lineup-body]
         (layout/empty-row 4)
         (reduce #(add-match-select-controls %1 %2 team-name) () (reverse (range 1 5)))
         (layout/empty-row 4)]
        [:td {:width "5%:"}]]]
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:table
             [:td {:width "30%"} "&nbsp;"]
             [:td {:align "right" :nowrap "true"}
              [:button {:type "button" :onclick (str "return processMatchRequest('#updatelineup', '/update-lineup', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_match_form('show-schedule');"} "Return to Schedule"]]
             [:td {:align "left" :nowrap "true"}
              [:button#lineuptoavail {:type "button"} "Go to Availability"]]
             [:td {:width "30%"} "&nbsp;"]]]]
      [:tr.hidden-control
       [:td {:colspan form-span :align "center"}
        [:input.hidden-control {:id "ml_team_id" :name "team_id" :value usr/users_team_id}]
        [:input.hidden-control {:id "ml_match_id" :name "match_id"}]]]
      (layout/hr-row form-span "90%")])])

(defn show-lineup-email-form
  "docstring"
  [team-name]
  (let [title "Send Lineup Email"]
    [:form#sendlineupemail.form-horizontal {:method "post" :action "/send-lineup-email"}
     [:table.table.table-sm
      [:tr [:td {:colspan form-span :align "center"} [:h4 title " for " team-name]]]
      (layout/hr-row form-span "90%")
      [:tr
       [:td {:width "50%"} "&nbsp;"]
       [:td {:colspan 2 :align "center"}
        [:table.table.table-sm.table-compact
         [:tr [:td] [:td {:colspan 3 :align "center"} [:h5 "Email Header"]]]
         [:tr [:td]
          [:td [:span.bold-text "Match Date:"]]
          [:td {:colspan 2} [:span#av_match_date "January 3"]]]
         [:tr [:td]
          [:td [:span.bold-text "Match Time:"]]
          [:td {:colspan 2} [:span#av_match_time "02:30 PM"]]]
         [:tr [:td]
          [:td [:span.bold-text "Location:"]]
          [:td {:colspan 2} [:span#av_match_location "Harpers"]]]]]
       [:td {:width "50%"} "&nbsp;"]]
      (layout/hr-row form-span "90%")
      [:tr
       [:td {:width "5%:"}]
       [:td {:colspan 2}
        [:table#email-lineup.table
         [:thead.table-inverse
          [:tr {:align "left"}
           [:td ""]
           [:td "Player 1"]
           [:td "Player 2"]]]
         [:tbody#email-lineup-body]
         (layout/empty-row 4)]
        [:td {:width "5%:"}]]]
      (layout/empty-row form-span)
      (add-form-control "Message:" {:id "li_message" :name "message" :cols 45 :maxlength 2000 :rows 7 :type "text-area"} "Please arrive 10 to 15 minutes before the match starts.")
      (add-form-control "Signature:" {:id "li_signature" :name "signature" :cols 45 :maxlength 200 :rows 3 :type "text-area"} "Rick Cashman\n513.227.9278")
      (add-form-control "Send to Subs:" {:id "li_send_subs" :name "send_subs" :type "checkbox"} nil)
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:table
             [:td {:width "50%"} "&nbsp;"]
             [:td {:align "right" :nowrap "true"}
              [:button {:type "button" :onclick (str "return processMatchRequest('#sendlineupemail', '/send-lineup-email', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_match_form('show-schedule');"} "Return to Schedule"]]
             [:td {:width "5%"} "&nbsp;"]]]]
      [:tr.hidden-control
       [:td {:colspan form-span :align "center"}
        [:input.hidden-control {:id "li_team_id" :name "team_id" :value usr/users_team_id}]
        [:input.hidden-control {:id "li_match_id" :name "match_id"}]]]
      (layout/empty-row form-span)]]))

(defn matches
  "Sets up the intial matches page."
  []
  (let [team-name (:name (team/team usr/users_team_id))
        match-actions
        [{:id "show-schedule" :name "Match Schedule" :content (schedule-form team-name)}
         {:id "send-availability-email" :name "Send Availability Email" :content (availability-email-form team-name)}
         {:id "show-availability" :name "Send Availability Email" :content (show-availability-form team-name)}
         {:id "set-lineup" :name "Set Lineup" :content (lineup-form team-name)}
         {:id "send-lineup-email" :name "Send Lineup Email" :content (show-lineup-email-form team-name)}]
        prefix "match"]
    (list
      ;(select-form match-actions)
      (map add-div match-actions)
      (add-div {:id (str prefix "-status-panel") :name "Status" :content (layout/status-content form-span prefix)}))))
