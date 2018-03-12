(ns rf-tennis-manager.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.views :as views]
            [rf-tennis-manager.config :as config]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.matches-main :as main]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

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
    (let [team-id-response (<! (http/get (str "team-info")))
          status-response (<! (http/get (str "team-schedule-status")))]
      (rf/dispatch [::events/team-info (:body team-id-response)])
      (reagent/render [main/schedule-form (:body status-response) (:body team-id-response)]
                      (.getElementById js/document "ma_show_schedule"))
      (reagent/render [main/availability-form ]
                      (.getElementById js/document "ma_show_availability"))
      (reagent/render [main/call-status ]
                      (.getElementById js/document "ma_call_status")))))






