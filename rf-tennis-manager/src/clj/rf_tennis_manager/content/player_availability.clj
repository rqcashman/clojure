(ns rf-tennis-manager.content.player-availability
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [rf-tennis-manager.data.communication-data-handler :as comm]
        [rf-tennis-manager.data.schedule-data-handler :as sched]
        [rf-tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

(defn show_update_status
  "docstring"
  [comm_detail available]
  (let [avail_text (if (= available "Y") [:span {:style "color:green;font-weight:bold"} "available"] [:span {:style "color:red;font-weight:bold"} "unavailable"])]
    [:br] [:br]
    [:table.table-sm {:width "20%" :border "2px" :align "center"}
     [:tr
      [:td
       [:table
        [:thead.tab
         [:tr
          [:td {:colspan 4} "Availability Updated"]]]
        [:tr
         [:td {:width "25px"} "&nbsp;"]
         [:td {:nowrap "true"} (:first_name comm_detail) " " (:last_name comm_detail) ","]
         [:td]
         [:td {:width "25px"} "&nbsp;"]]
        [:tr
         [:td "&nbsp;"]
         [:td]
         [:td {:nowrap "true" :colspan 2} "You have been marked as " avail_text " for the match on " (:match_date comm_detail) " at " (:match_time comm_detail)]
         [:td {:width "25px"} "&nbsp;"]]
        [:tr [:td {:colspan 4} "&nbsp;"]]]]]]))


(defn update_availability
  "docstring"
  [player-token available]
  (try
    (let [comm_detail (comm/get_communication_detail player-token)]
      (list
        (if (and comm_detail (contains? #{"Y" "N"} available))
          (do
            (comm/update_player_commuication_response player-token available)
            (sched/upsert_player_availability (:match_id comm_detail) (:player_id comm_detail) available)
            (show_update_status comm_detail available))
          [:h2 "Invalid link.  The link was invalid.  If you copied and pasted the link make sure you copied the complete link"])))
    (catch Exception e
      (println "Error in update_availability.  Msg: " (.getMessage e))
      [:h2 "Server error updating availablitiy"])))
