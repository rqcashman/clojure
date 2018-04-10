(ns rf-tennis-manager.admin.admin-events-load-schedule
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::selected-season-changed
  (fn [{:keys [db]} [_ season-id]]
    {:db (assoc-in db [:admin :selected-season] (first (filter #(identical? (str (:id %)) (str season-id)) (:seasons db))))}))

(rf/reg-event-fx
  ::load-schedule
  (fn [{:keys [db]} [_ request]]
    (println "::load-schedule: " request)))
