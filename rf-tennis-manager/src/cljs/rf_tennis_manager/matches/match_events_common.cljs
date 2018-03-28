(ns rf-tennis-manager.matches.match-events-common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs-http.client :as http]
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

(rf/reg-event-fx
  ::session-timeout
  (fn [{:keys [db]} [_ call-response]]
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Session timed out, please login again")
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] nil))]
      {:db upd-db})))

(def session-expired-errno 400)
(defn send-get-request
  [request]
  (go
    (let [response (<! (http/get (:url request)))
          method (cond
                   (= (:status response) session-expired-errno) [::session-timeout]
                   (:success response) (:on-success request)
                   :else (:on-fail request))]
      (rf/dispatch (conj method response)))))

(defn send-post-request
  [request]
  (go
    (let [values (ef/from (:form-id request) (ef/read-form))
          response (<! (http/post (:url request) {:form-params values}))
          method (cond
                   (= (:status response) session-expired-errno) [::session-timeout]
                   (:success response) (:on-success request)
                   :else (:on-fail request))]
      (rf/dispatch (conj method response)))))

(rf/reg-fx
  ::get-match-info
  send-get-request)

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