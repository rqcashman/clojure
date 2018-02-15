(ns tennis-manager.content.schedule
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.season-data-handler :as season]
        [tennis-manager.data.team-data-handler :as team]
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
       [:td {:width "10%"} "&nbsp;"]
       [:td "Team:"]
       [:td {:width "5%"}"&nbsp;"]
       [:td#sched-team-name {:style "font-weight:bold"}]
       [:td {:colspan 4}]
       (layout/empty-row 5)]
      [:tr
       [:td "&nbsp;"]
       [:td "Season:"]
       [:td "&nbsp;"]
       [:td#sched-season-name {:style "font-weight:bold"}]
       [:td {:width "5%"}"&nbsp;"]
       [:td#sched-season-start]
       [:td {:width "5%"} "&nbsp;&nbsp;-&nbsp;&nbsp;"]
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
  (list [:div#sched-form-div.panel.panel-default
         [:table.table.table-sm.schedule-form
          [:thead
           (layout/empty-row 3)
           [:tr [:td {:colspan 3 :align "center"} [:h4 "Select season and club"]]]
           (layout/hr-row 3 "90%")]
          [:tbody
           (layout/add-select season/seasons layout/option "season-list" "Season:" 1)
           (layout/add-select team/teams layout/option "team-list" "Team:" 1)
           (layout/hr-row 3 "90%")
           [:tr
            [:td {:colspan 3 :align "center"}
             [:button {:type "button" :onclick "team_schedule();"} "Show schedule"]]]
           (layout/empty-row 3)]]]
        (schedule-details)))