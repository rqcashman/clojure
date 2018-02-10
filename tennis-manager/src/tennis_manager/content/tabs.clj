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
    [:li [:a {:href "/matches"} "Matches"]]
    [:li [:a {:href "/schedule"} "Schedule"]]
    [:li [:a {:href "/roster"} "Roster"]]
    [:li [:a {:href "/admin"} "Admin"]]]])

