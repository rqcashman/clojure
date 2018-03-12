(ns rf-tennis-manager.content.tabs
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]
        [rf-tennis-manager.content.page-layout :as layout]
        [rf-tennis-manager.data.auth-handler :as auth]
        [rf-tennis-manager.data.auth-messages :as msgs]
        [rf-tennis-manager.data.club-data-handler :as club]
        [rf-tennis-manager.data.season-data-handler :as season]))

(defn tabs [session request]
  (let [user (auth/get-user-from-session-id (:identity session))]
    (println "session: " session)
    (println request)
    [:div#tabs.tennis-tabs {:align "center" :width "80%"}
     [:script (str "var user_team_id = " (:team_id user))]
     [:ul
      [:li [:a#matches {:href "/matches"} " Matches"]]
      [:li [:a#roster {:href " /roster"} " Roster"]]
      [:li [:a#schedule {:href " /schedule"} " Schedule"]]
      (if (= (:user_type user) msgs/ADMIN_USER)
        [:li [:a#admin {:href " /admin" :name " admin"} " Admin"]])
      [:li [:a#chgpass {:href "javascript:void(null)" :onclick "window.location.href='/chgpassword';return false;"} " Change password... "]]
      [:li [:a#lo {:href "javascript:void(null)" :onclick "window.location.href='/logout';return false;"} [:span {:style "color:red;font-weight:bold"} " Logout "]]]]
     [:script {:src "js/compiled/app.js"}]
     [:script "rf_tennis_manager.core.init()"]]))

