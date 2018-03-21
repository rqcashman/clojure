(ns rf-tennis-manager.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.match-events-schedule :as sched]
            [rf-tennis-manager.views :as views]
            [rf-tennis-manager.config :as config]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.matches-main :as main]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [rf-tennis-manager.match-events-common :as evt-common]))

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






