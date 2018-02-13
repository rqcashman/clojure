(ns tennis-manager.content.tabs
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.club-data-handler :as club]
        [tennis-manager.data.season-data-handler :as season]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))


(defn tabs []
  [:div#tabs
   [:ul
    [:li [:a#matches {:href "/mgr/matches"} "Matches"]]
    [:li [:a#schedule {:href "/mgr/schedule"} "Schedule"]]
    [:li [:a#roster {:href "/mgr/roster"} "Roster"]]
    [:li [:a#admin {:href "/mgr/admin" :name "admin"} "Admin"]]]])

