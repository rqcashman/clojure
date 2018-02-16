(ns tennis-manager.content.tabs
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]
        [tennis-manager.content.page-layout :as layout]
        [tennis-manager.data.auth-handler :as auth]
        [tennis-manager.data.club-data-handler :as club]
        [tennis-manager.data.season-data-handler :as season]))

(defn tabs [session]
  (let [user (auth/get-user-from-session-id (:identity session))]
    [:div#tabs.tennis-tabs  {:align "center" :width "40%"}
     [:ul
      [:li [:a#matches {:href "/matches"} "Matches"]]
      [:li [:a#roster {:href "/roster"} "Roster"]]
      [:li [:a#schedule {:href "/schedule"} "Schedule"]]
      (if (= (:user_type user) 1)
        [:li [:a#admin {:href "/admin" :name "admin"} "Admin"]])
      ]]))

