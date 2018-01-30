(ns tennis-manager.content.matches
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.team-data-handler :as team]
        [tennis-manager.data.season-data-handler :as season]
        [tennis-manager.data.user-info :as usr]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

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

(defn schedule-row
  "docstring"
  [rows sched-row]
  (let [home-team-id (:home_team_id sched-row)
        avail-func (if (= (:availability_sent sched-row) nil) "change_to_email_form" "change_to_avail_form")]
    (conj rows [:tr
                [:td (:match_date sched-row)]
                [:td (:match_time sched-row)]
                [:td (if (= usr/users_team_id home-team-id) (:away_team sched-row) (:home_team sched-row))]
                [:td (:home_club_name sched-row)]
                [:td {:align "center"} (if (= usr/users_team_id home-team-id) (:home_team_points sched-row) (:away_team_points sched-row))]
                [:td {:align "center"} (if (= usr/users_team_id home-team-id) (:away_team_points sched-row) (:home_team_points sched-row))]
                [:td {:align "center"} [:span.avail-cursor {:onclick (str avail-func "('" (:match_id sched-row) "');")} (if (= (:availability_sent sched-row) nil) "&#10060;" "&#9989;")]]
                [:td {:align "center"} [:span.avail-cursor {:onclick (str "set_lineup('" (:match_id sched-row) "');")} (if (= (:lineup_sent sched-row) nil) "&#10060;" "&#9989;")]]])))

(defn get-team-schedule
  "docstring"
  []
  (try
    ;data is sorted by date.  Need to use reverse because conj is adding the data to the beginning of the list
    (reduce #(schedule-row %1 %2) () (reverse (team/team-schedule (:id (nth (season/current-season) 0)) usr/users_team_id)))
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
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title " for " team-name]]]
      (layout/hr-row form-span "90%")
      [:tr [:td] [:td {:colspan 3 :align "left"} [:h5 "Email Header"]]]
      [:tr [:td]
       [:td [:span.bold-text "Match Date:"]]
       [:td {:colspan 2} [:span#av_match_date "January 3"]]]
      [:tr [:td]
       [:td [:span.bold-text "Match Time:"]]
       [:td {:colspan 2} [:span#av_match_time "02:30 PM"]]]
      [:tr [:td]
       [:td [:span.bold-text "Location:"]]
       [:td {:colspan 2} [:span#av_match_location "Harpers"]]]
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
              [:button {:type "button" :onclick (str "return processRequest('#sendavailabilityemail', '/send-availability-email', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_form('show-schedule');"} "Return to Schedule"]]
             [:td {:width "5%"} "&nbsp;"]]]]
      [:tr.hidden-control
       [:td {:colspan form-span :align "center"}
        [:input.hidden-control {:id "av_team_id" :name "team_id"}]
        [:input.hidden-control {:id "av_match_id" :name "match_id"}]]]
      (layout/empty-row form-span)]]))


(defn show-availability-form
  "docstring"
  [team-name]
  (let [title "Show Availability"]
    [:form#showavailabilityemail.form-horizontal {:method "post" :action "/get-availability"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title " for " team-name]]]
      (layout/hr-row form-span "90%")
      [:tr [:td] [:td {:colspan 3 :align "left"} [:h5 "Email Header"]]]
      [:tr [:td]
       [:td [:span.bold-text "Match Date:"]]
       [:td {:colspan 2} [:span#av_match_date "January 3"]]]
      [:tr [:td]
       [:td [:span.bold-text "Match Time:"]]
       [:td {:colspan 2} [:span#av_match_time "02:30 PM"]]]
      [:tr [:td]
       [:td [:span.bold-text "Location:"]]
       [:td {:colspan 2} [:span#av_match_location "Harpers"]]]
      (layout/hr-row form-span "90%")
      (add-form-control "Message:" {:id "sv_message" :name "message" :cols 45 :maxlength 2000 :rows 7 :type "text-area"} "Let me know if you are available to play.")
      ;(add-form-control "Signature:" {:id "av_signature" :name "signature" :cols 45 :maxlength 200 :rows 3 :type "text-area"} "Rick Cashman\n513.227.9278")
      ;(add-form-control "Send to Subs:" {:id "av_send_subs" :name "send_subs" :type "checkbox"} nil)
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr
       [:td {:colspan form-span :align "center"}
        [:button {:type "button" :onclick "change_form('show-schedule');"} "Return to Schedule"]]]
      [:tr.hidden-control
       [:td {:colspan form-span :align "center"}
        [:input.hidden-control {:id "av_team_id" :name "team_id"}]
        [:input.hidden-control {:id "av_match_id" :name "match_id"}]]]
      (layout/empty-row form-span)]]))


(defn lineup-form
  "docstring"
  [team-name]
  [:table.table.table-sm
   (layout/empty-row form-span)
   [:tr [:td {:colspan form-span :align "center"} [:h4 team-name " Match Lineup"]]]
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
        [:td "Lineup Sent"]]]
      [:tbody#match-sched-body]
      (get-team-schedule)]]
    [:td {:width "5%:"}]]
   (layout/hr-row form-span "90%")])

(defn select-form
  [match-actions]
  (list
    [:div#matches-form-div.panel.panel-default
     [:table.table.table-sm.match-form
      [:thead
       (layout/empty-row 3)
       [:tr [:td {:colspan 3 :align "center"} [:h4 "Select Match Function"]]]
       (layout/hr-row 3 "90%")]
      [:tbody
       (layout/add-select #(layout/actions match-actions) layout/option "match-list" "Match Action:" 1 (str "change_form(this.value);"))
       (layout/hr-row 3 "90%")
       (layout/empty-row 3)]]]
    [:br]
    [:hr]
    [:br])
  )
(defn matches
  "docstring"
  []
  (println "matches")
  (let [team-name (:name (nth (team/team usr/users_team_id) 0))
        match-actions
        [{:id "show-schedule" :name "Match Schedule" :content (schedule-form team-name)}
         {:id "send-availability-email" :name "Send Availability Email" :content (availability-email-form team-name)}
         {:id "show-availability" :name "Send Availability Email" :content (show-availability-form team-name)}
         {:id "set-lineup" :name "Set Lineup" :content (lineup-form team-name)}
         ]]
    (list
      ;(select-form match-actions)
      (map add-div match-actions)
      (add-div {:id "status-panel" :name "Status" :content (layout/status-content form-span "location.reload();")}))))
