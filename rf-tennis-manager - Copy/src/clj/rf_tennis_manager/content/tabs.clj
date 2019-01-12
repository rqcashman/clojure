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
    [:div
     [:div#tabs.tennis-tabs {:align "center" :width "80%"}
      [:ul
       [:li [:a {:href "#matches_tab"} " Matches"]]
       [:li [:a {:href "#roster_tab" :onClick "rf_tennis_manager.core.init_roster();return false"} " Roster"]]
       [:li [:a {:href "#schedule_tab" :onClick "rf_tennis_manager.core.init_schedule();return false"} " Schedule"]]
       (if (= (:user_type user) msgs/ADMIN_USER)
         [:li [:a {:href "#admin_tab" :onClick "rf_tennis_manager.core.init_admin();return false"} " Admin"]])
       [:li [:a {:href "javascript:void(0)" :onclick "window.location.href='/chgpassword';return false;"} " Change password... "]]
       [:li [:a {:href "javascript:void(0)" :onclick "window.location.href='/logout';return false;"} [:span.red-bold " Logout "]]]]
      [:div#matches_tab
       [:div#ma_show_schedule.div-panel-show]
       [:div#ma_send_availability_email.div-panel-show]
       [:div#ma_show_availability.div-panel-show]
       [:div#ma_set_lineup.div-panel-show]
       [:div#ma_send_lineup_email.div-panel-show]
       [:div#ma_call_status.div-panel-show]]
      [:div#roster_tab
       [:div#ro_call_status.div-panel-show]
       [:div#ro_select_form.div-panel-show]
       [:div#ro_show_roster.div-panel-show]
       [:div#ro_update_player.div-panel-show]
       [:div#ro_add_player.div-panel-show]]
      [:div#schedule_tab
       [:div#sched_call_status.div-panel-show]
       [:div#sched_select_form.div-panel-show]
       [:div#sched_schedule.div-panel-show]]
      [:div#admin_tab
       [:div#admin_call_status.div-panel-show]
       [:div#admin_select.div-panel-show]
       [:div#admin_load_schedule.div-panel-show]
       [:div#admin_add_season.div-panel-show]
       [:div#admin_add_club.div-panel-show]
       [:div#admin_add_team.div-panel-show]]]
     [:script {:src "js/compiled/app.js"}]
     [:script "rf_tennis_manager.core.init();
               rf_tennis_manager.core.init_matches();"]]))

