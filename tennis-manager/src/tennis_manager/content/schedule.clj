(ns tennis-manager.content.schedule
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.data-handler :as db]
        [tennis-manager.content.page-layout :as layout]))

(defn schedule-details
  []
  [:div#schedule.schedule {:align "center" :position "absolute"}
   [:div.panel.panel-default
    [:div.panel-heading "Tennis Schedule"]
    [:div.panel-body {:align "left"}
     [:hr {:color "black"}]
     [:table#sched-hdr.schedule-hdr
      [:tr
       [:td "Team:"]
       [:td "&nbsp;"]
       [:td#sched-team-name]
       [:td {:colspan 4}]
       (layout/empty-row 5)]
      [:tr
       [:td "Season:"]
       [:td "&nbsp;"]
       [:td#sched-season-name]
       [:td "&nbsp;"]
       [:td#sched-season-start]
       [:td "-"]
       [:td#sched-season-end]]]]]
   [:hr {:color "black"}]
   [:table#sched-details.table.table-striped.table-sm
    [:thead.table-inverse
     [:tr
      [:td "Date"]
      [:td "Time"]
      [:td "Home Team"]
      [:td "Away Team"]
      [:td "Home Points"]
      [:td "Away Points"]]]
    [:tbody#schedule-body.table-bordered (layout/empty-row 6)]]
   (hr-row 1 "80%'")
   [:button {:type "button" :onclick "show_select_form();"} "Return to select form"]])

(defn schedule []
  (println "in schedule")
  (list [:div#sched-form-div.panel.panel-default
         [:table.table.table-sm.schedule-form
          [:thead
           (layout/empty-row 3)
           [:tr [:td {:colspan 3 :align "center"} [:h4 "Select season and club"]]]
           (layout/hr-row 3 "90%")]
          [:tbody
           (layout/add-select db/seasons layout/option "season-list" "Season:" 1)
           (layout/add-select db/teams layout/option "team-list" "Team:" 1)
           (layout/hr-row 3 "90%")
           [:tr
            [:td {:colspan 3 :align "center"}
             [:button {:type "button" :onclick "team_schedule();"} "Show schedule"]]]
           (layout/empty-row 3)]]]
        (schedule-details)))