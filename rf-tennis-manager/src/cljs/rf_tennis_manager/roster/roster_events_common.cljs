(ns rf-tennis-manager.roster.roster-events-common
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]))

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
  ::show-player-update-form
  (fn [{:keys [db]} [_]]
    (let [upd-db (show-div db "update-player")]
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

(rf/reg-event-fx
  ::roster-page-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :call-status :success?] false)
             (assoc-in [:roster :call-status :message] "Call to get data failed")
             (assoc-in [:roster :panel-visible :call-status] true)
             (assoc-in [:roster :call-status :on-click] #(re-frame.core/dispatch [::show-roster])))}))

(rf/reg-event-fx
  ::load-roster
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :team-roster] (:body status))
             (assoc-in [:roster :call-status :message] "Success")
             (assoc-in [:roster :panel-visible :call-status] false))}))

(rf/reg-fx
  ::get-roster
  evt/send-get-request)

(rf/reg-event-fx
  ::selected-team-changed
  (fn [{:keys [db]} [_ team-id]]
    {:db          (-> db
                      (assoc-in [:roster :selected-team] (first (filter #(identical? (str (:id %)) (str team-id)) (get-in db [:teams :list]))))
                      (assoc-in [:roster :selected-roster-action] "roster")
                      (show-div "roster"))
     ::get-roster {:method     :get
                   :url        (str "http://localhost:3000/team-roster/" team-id)
                   :on-success [::load-roster]
                   :on-fail    [::roster-page-failed]}}))
