(ns rf-tennis-manager.roster.roster-events-select
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]
            [clojure.string :as s]))

(rf/reg-fx
  ::get-teams
  evt/send-get-request)

(rf/reg-event-fx
  ::show-roster
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :panel-visible :call-status] false)
             (assoc-in [:roster :panel-visible :roster] true))}))

(rf/reg-event-fx
  ::player-selected
  (fn [{:keys [db]} [_ player-id]]
    {:db       (assoc-in db [:roster :selected-player] (first (filter #(identical? (str (:id %)) (str player-id)) (get-in db [:roster :team-roster]))))
     :dispatch [::evt-common/show-player-update-form]}))

(rf/reg-event-fx
  ::roster-action-change
  (fn [{:keys [db]} [_ item-value]]
    {:db (-> db
             (assoc-in [:roster :selected-roster-action] item-value)
             (assoc-in [:roster :add-player] {:id 0 :first_name "" :last_name "" :status "A"})
             (evt-common/show-div item-value))}))

(rf/reg-event-fx
  ::load-teams
  (fn [{:keys [db]} [_ status]]
    {:db       (-> db
                   (assoc-in [:teams :list] (:body status))
                   (assoc-in [:roster :call-status :message] "Success")
                   (assoc-in [:roster :panel-visible :call-status] false)
                   (assoc-in [:roster :panel-visible :roster] true)
                   (assoc-in [:roster :selected-roster-action] "roster"))
     :dispatch [::evt-common/selected-team-changed (get-in db [:team-info :id])]}))

(rf/reg-event-fx
  ::init-roster-page
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (evt-common/show-div "roster")
                     (assoc-in [:roster :call-status :success?] true)
                     (assoc-in [:roster :call-status :message] "Processing...")
                     (assoc-in [:roster :call-status :on-click] nil)
                     (assoc-in [:roster :panel-visible :call-status] true))]
      {::get-teams {:method     :get
                    :url        (str "http://localhost:3000/teams")
                    :on-success [::load-teams]
                    :on-fail    [::evt-common/roster-page-failed]}
       :db         upd-db})))