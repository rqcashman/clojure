(ns tennis-manager.content.player-availability
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.communication-data-handler :as comm]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

(defn show_update_status
  "docstring"
  [comm_detail available]
  (println "comm detail: " comm_detail)
  (let [avail_text (if (= available "Y") [:span {:style "color:green;font-weight:bold"} "available"] [:span {:style "color:red;font-weight:bold"} "unavailable"])]
    [:br] [:br]
    [:table.table-sm {:width "40%"}
     [:tr [:td {:colspan 3} "&nbsp;"]]
     [:tr [:td {:colspan 3} "&nbsp;"]]
     [:tr
      [:td {:width "25px"} "&nbsp;"]
      [:td {:nowrap "true"} (:first_name comm_detail) " " (:last_name comm_detail) ","]
      [:td]]
     [:tr
      [:td "&nbsp;"]
      [:td]
      [:td {:nowrap "true"}  "You have been marked as " avail_text " for the match on " (:match_date comm_detail) " at " (:match_time comm_detail)]]]
    ))

(defn update_availability
  "docstring"
  [player-token available]
  (println "update availability " player-token available)
  (let [comm_detail (comm/get_communication_detail player-token)]
    (list
      (try
        (if (and (> (count comm_detail) 0) (contains? #{"Y" "N"} available))
          (do
            (let [comm_rcd (nth comm_detail 0)]
              (comm/update_player_commuication_response player-token available)
              (comm/upsert_player_availability (:match_id comm_rcd) (:player_id comm_rcd) available)
              (show_update_status comm_rcd available)))
          [:h2 "Invalid link.  The link was invalid.  If you copied and pasted the link make sure you copied the complete link"])
        (catch Exception e
          (println "Error in update_availability.  Msg: " (.getMessage e))
          [:h2 "Server error updating availablitiy"])))))
