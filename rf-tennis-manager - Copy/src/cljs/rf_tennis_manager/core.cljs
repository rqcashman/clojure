(ns rf-tennis-manager.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [reagent.core :as reagent]
            [re-frame.core :as rf]
            [rf-tennis-manager.admin.admin-main :as admin]
            [rf-tennis-manager.admin.admin-events-common :as admin-evt]
            [rf-tennis-manager.config :as config]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.matches.match-events-common :as evt-common]
            [rf-tennis-manager.matches.match-events-schedule :as main-sched-evt]
            [rf-tennis-manager.matches.matches-main :as match]
            [rf-tennis-manager.roster.roster-main :as roster]
            [rf-tennis-manager.roster.roster-events-select :as roster-evt]
            [rf-tennis-manager.schedule.schedule-main :as schedule]
            [rf-tennis-manager.schedule.schedule-events :as sched-evt]
            [rf-tennis-manager.views :as views]
            [rf-tennis-manager.subs :as subs]))
(def tab-state (js-obj "matches" false "roster" false "schedule" false "admin" false))
(enable-console-print!)
(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root-test []
  (rf/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "test_lists")))

(defn ^:export init_test []
  (rf/dispatch-sync [::events/initialize-default-db])
  (dev-setup)
  (mount-root-test))

(defn ^:export init []
  (rf/dispatch-sync [::events/initialize-tabbed-db])
  (dev-setup)
  (rf/clear-subscription-cache!))

(defn ^:export init_matches []
  (if (= (aget tab-state "matches") false)
    (do
      (aset tab-state "matches" true)
      (go
        (reagent/render [match/call-status]
                        (.getElementById js/document "ma_call_status"))
        (reagent/render [match/schedule-form]
                        (.getElementById js/document "ma_show_schedule"))
        (rf/dispatch [::main-sched-evt/init-matches-page])
        (reagent/render [match/availability-form]
                        (.getElementById js/document "ma_show_availability"))
        (reagent/render [match/availability-email-form]
                        (.getElementById js/document "ma_send_availability_email"))
        (reagent/render [match/lineup-email-form]
                        (.getElementById js/document "ma_send_lineup_email"))
        (reagent/render [match/set-lineup-form]
                        (.getElementById js/document "ma_set_lineup"))))))

(defn ^:export init_roster []
  (if (= (aget tab-state "roster") false)
    (do
      (set! (.-roster tab-state) true)
      (go
        (reagent/render [roster/call-status]
                        (.getElementById js/document "ro_call_status"))
        (reagent/render [roster/roster-select-form]
                        (.getElementById js/document "ro_select_form"))
        (reagent/render [roster/show-roster]
                        (.getElementById js/document "ro_show_roster"))
        (reagent/render [roster/update-player-form]
                        (.getElementById js/document "ro_update_player"))
        (reagent/render [roster/add-player]
                        (.getElementById js/document "ro_add_player"))
        (rf/dispatch [::roster-evt/init-roster-page])))))

(defn ^:export init_schedule []
  (if (= (aget tab-state "schedule") false)
    (do
      (aset tab-state "schedule" true)
      (go
        (reagent/render [schedule/call-status]
                        (.getElementById js/document "sched_call_status"))
        (reagent/render [schedule/schedule-select-form]
                        (.getElementById js/document "sched_select_form"))
        (reagent/render [schedule/schedule]
                        (.getElementById js/document "sched_schedule"))
        (rf/dispatch [::sched-evt/init-schedule-page])))))

(defn ^:export init_admin []
  (if (= (aget tab-state "admin") false)
    (do
      (aset tab-state "admin" true)
      (go
        (reagent/render [admin/call-status]
                        (.getElementById js/document "admin_call_status"))
        (reagent/render [admin/admin-select-form]
                        (.getElementById js/document "admin_select"))
        (reagent/render [admin/load-schedule]
                        (.getElementById js/document "admin_load_schedule"))
        (reagent/render [admin/season-content]
                        (.getElementById js/document "admin_add_season"))
        (reagent/render [admin/club-content]
                        (.getElementById js/document "admin_add_club"))
        (reagent/render [admin/team-content]
                        (.getElementById js/document "admin_add_team"))
        (rf/dispatch [::admin-evt/init-admin-page])))))








