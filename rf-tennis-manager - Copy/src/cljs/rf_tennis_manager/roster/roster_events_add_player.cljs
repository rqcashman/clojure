(ns rf-tennis-manager.roster.roster-events-add-player
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::add-player-success
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :call-status :success?] true)
             (assoc-in [:roster :panel-visible :call-status] true)
             (assoc-in [:roster :call-status :message] "Player added")
             (assoc-in [:roster :call-status :on-click] #(rf/dispatch [::evt-common/selected-team-changed (get-in db [:roster :selected-team :id])])))}))
(rf/reg-fx
  ::add-player
  evt/send-post-request)

(rf/reg-event-fx
  ::add-player-request
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:roster :call-status :success?] true)
                     (assoc-in [:roster :call-status :message] "Processing...")
                     (assoc-in [:roster :call-status :on-click] nil)
                     (assoc-in [:roster :panel-visible :call-status] true))]
      {::add-player {:method     :post
                     :url        "/add-player"
                     :on-success [::add-player-success]
                     :form-id    "#addplayerform"
                     :on-fail    [::evt-common/roster-page-failed]}
       :db          upd-db})))
