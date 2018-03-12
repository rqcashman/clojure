(ns rf-tennis-manager.content.email-body
  (:use [clojure.string :as s]
        [hiccup.form]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]))


(defn availability_email_body
  "docstring"
  [message signature match-info]
  (html5
    [:table.table-sm {:width "90%" :align "left"}
     [:tr
      [:td {:nowrap "true" :cell-style "font-weight:bold"} "Match date:"]
      [:td {:width "5%"} "&nbsp;"]
      [:td {:nowrap "true" :align "left" :cell-style "font-weight:bold"} (:match_date match-info)]]
     [:tr
      [:td {:nowrap "true" :cell-style "font-weight:bold"} "Match time"]
      [:td {:width "5%"} "&nbsp;"]
      [:td {:nowrap "true" :align "left" :cell-style "font-weight:bold"} (:match_time match-info)]]
     [:tr
      [:td {:nowrap "true" :cell-style "font-weight:bold"} "Location:"]
      [:td {:width "5%"} "&nbsp;"]
      [:td {:nowrap "true" :align "left" :cell-style "font-weight:bold"} (:club_name match-info)]]]
    [:br]
    [:table.table-sm {:width "90%" :align "left"}
     [:tr
      [:td {:align "left" :colspan 2} "---salutation---,"]]
     [:tr
      [:td {:width "5%"} "&nbsp;"]
      [:td (s/replace message #" \n " " <br> ")]]
     [:tr
      [:td {:width "5%"} "&nbsp;"]
      [:td {:nowrap "true"}
       [:a {:href "'http://localhost:3000/availability-reply?available=Y&player-token=--uuid--'"} "I can play"]
       "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
       [:a {:href "'http://localhost:3000/availability-reply?available=N&player-token=--uuid--'"} "I'm out"]]]
     [:tr
      [:td {:width "5%" :colspan 2} "&nbsp;"]]
     [:tr
      [:td {:width "5%" :colspan 2} [:h4 (s/replace signature #"\n" "<br>")]]]]))
