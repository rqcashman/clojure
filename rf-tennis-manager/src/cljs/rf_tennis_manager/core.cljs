(ns rf-tennis-manager.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [reagent.core :as reagent]
            [re-frame.core :as rf]
            [rf-tennis-manager.config :as config]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.matches.match-events-common :as evt-common]
            [rf-tennis-manager.matches.match-events-schedule :as sched]
            [rf-tennis-manager.matches.matches-main :as main]
            [rf-tennis-manager.views :as views]
            [rf-tennis-manager.subs :as subs]))

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
  (go
    (reagent/render [main/call-status]
                    (.getElementById js/document "ma_call_status"))
    (reagent/render [main/schedule-form]
                    (.getElementById js/document "ma_show_schedule"))
    (rf/dispatch [::sched/init-schedule-page])
    (reagent/render [main/availability-form]
                    (.getElementById js/document "ma_show_availability"))
    (reagent/render [main/availability-email-form]
                    (.getElementById js/document "ma_send_availability_email"))
    (reagent/render [main/lineup-email-form]
                    (.getElementById js/document "ma_send_lineup_email"))
    (reagent/render [main/set-lineup-form]
                    (.getElementById js/document "ma_set_lineup"))))






