(ns tennis-manager.content.matches
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.data-handler :as db]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

(def form-span 4)
(def sched-form-span 8)
;plan is to add logins which will give us the team id
;for now we will just use a hared-code team id
(def team-id 13)


(defn add-div
  [func-map]
  (println func-map)
  [:div.matches-action {:id (:id func-map)} (:content func-map)]
  )

(defn add-form-control
  [label options]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
   [:td [:input.form-control-sm options]]
   [:td {:width "5%"} "&nbsp;"]])

(defn schedule-row
  "docstring"
  [rows sched-row]
  (let [home-team-id (:home_team_id sched-row)]
    (conj rows [:tr
                [:td (:match_date sched-row)]
                [:td (:match_time sched-row)]
                [:td (if (= team-id home-team-id) (:away_team sched-row) (:home_team sched-row))]
                [:td (:home_club_name sched-row)]
                [:td {:align "center"} (if (= team-id home-team-id) (:home_team_points sched-row) (:away_team_points sched-row))]
                [:td {:align "center"} (if (= team-id home-team-id) (:away_team_points sched-row) (:home_team_points sched-row))]
                [:td {:align "center"} (if (= (:availability_sent sched-row) nil) "&#10060;" "&#9989;" )]
                [:td {:align "center"} (if (= (:lineup_sent sched-row) nil) "&#10060;" "&#9989;" )]])))

(defn get-team-schedule
  "docstring"
  []
  (try
    ;data is sorted by date.  Need to use reverse because conj is adding the data to the beginning of the list
    (reduce #(schedule-row %1 %2) () (reverse (db/team-schedule (:id (nth (db/current-season) 0)) team-id)))
    (catch Exception e
      (println "Exception in get-team-schedule.  Message: " + e)
      [:tr [:td.error {:colspan sched-form-span :align "center"} "Error getting team schedule"]])))

(defn schedule-form
  "docstring"
  []
  [:table.table.table-sm
   (layout/empty-row form-span)
   [:tr [:td {:colspan form-span :align "center"} [:h4 (:name (nth (db/team team-id) 0)) " Team Schedule"]]]
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
  []
  )

(defn lineup-form
  "docstring"
  []
  )
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
  (let [match-actions
        [{:id "show-schedule" :name "Match Schedule" :content (schedule-form)}
         {:id "send-availability-email" :name "Send Availability Email" :content (availability-email-form)}
         {:id "set-lineup" :name "Set Lineup" :content (lineup-form)}
         ]]
    (list
      (select-form match-actions)
      (map add-div match-actions)
      (add-div {:id "status-panel" :name "Status" :content (layout/status-content form-span "change_form(current_form_id);")}))))
