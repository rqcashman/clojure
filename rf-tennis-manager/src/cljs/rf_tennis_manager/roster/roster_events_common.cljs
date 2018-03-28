(ns rf-tennis-manager.roster.roster-events-common
  (:require [re-frame.core :as rf]))

(defn show-div
  [db show-div-id]
  (-> (reduce (fn [upd-db key]
                (assoc-in upd-db [:roster :panel-visible key] false))
              db (keys (get-in db [:roster :panel-visible])))
      (assoc-in [:roster :panel-visible (keyword show-div-id)] true)))

(rf/reg-event-fx
  ::show-roster
  (fn [{:keys [db]} [_]]
    (let [upd-db (show-div db "roster")]
      {:db upd-db})))

(rf/reg-event-fx
  ::show-select
  (fn [{:keys [db]} [_]]
    (let [upd-db (show-div db "select-form")]
      {:db upd-db})))

(rf/reg-event-fx
  ::hide-call-status
  (fn [{:keys [db]} [_]]
    {:db (assoc-in db [:roster :panel-visible :call-status] false)}))
