(ns rf-tennis-manager.matches.match-events-common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs-http.client :as http]
            [rf-tennis-manager.events-common :as evt]
            [enfocus.core :as ef]))

(rf/reg-cofx
  ::datetime
  (fn coeffect-handler
    [coeffect]
    (assoc coeffect :now (js/Date.))))

(rf/reg-event-fx
  ::match-info
  (fn [{:keys [db]} [_ call-response]]
    {:db (assoc-in db [:matches :match-info] (:body call-response))}))

(rf/reg-fx
  ::get-match-info
  evt/send-get-request)

(defn show-div
  [db show-div-id]
  (->
    (reduce (fn [upd-db key]
              (assoc-in upd-db [:matches :panel-visible key] false))
            db (keys (get-in db [:matches :panel-visible])))
    (assoc-in [:matches :panel-visible (keyword show-div-id)] true)))

(rf/reg-event-fx
  ::show-schedule
  (fn [{:keys [db]} [_]]
    (let [upd-db (show-div db "schedule")]
      {:db upd-db})))

(rf/reg-event-fx
  ::show-availability
  (fn [{:keys [db]} [_]]
    (let [upd-db (show-div db "availability")]
      {:db upd-db})))

(rf/reg-event-fx
  ::show-send-avail-email
  (fn [{:keys [db]} [_]]
    (let [upd-db (show-div db "send-avail-email")]
      {:db upd-db})))

(rf/reg-event-fx
  ::hide-call-status
  (fn [{:keys [db]} [_]]
    {:db (assoc-in db [:matches :panel-visible :call-status] false)}))