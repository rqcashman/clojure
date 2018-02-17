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
    [:div#tabs.tennis-tabs {:align "center" :width "80%"}
     [:ul
      [:li [:a#matches {:href "/matches"} " Matches"]]
      [:li [:a#roster {:href " /roster"} " Roster"]]
      [:li [:a#schedule {:href " /schedule"} " Schedule"]]
      (if (= (:user_type user) 1)
        [:li [:a#admin {:href " /admin" :name " admin"} " Admin"]])
      ;[:li [:a#schedule {:href "/logout" :onclick "$('#tabs').tabs();$('#tabs').tabs( 'destroy' )"} " Logout"]]
     ; [:li [:a#schedule {:href "" :onclick "$('#tabs').tabs();window.location.href='/logout';$('#tabs').tabs('destroy');return false"} " Logout"]]
      [:li [:a#schedule {:href "" :onclick "window.location.href='/logout'"} " Logout"]]
      ]]))

