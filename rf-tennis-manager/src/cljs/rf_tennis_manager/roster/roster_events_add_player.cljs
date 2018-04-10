(ns rf-tennis-manager.roster.roster-events-add-player
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::edit-add-player
  (fn [{:keys [db]} [_ field value]]
    (let [upd-db
          (-> db
              (assoc-in [:roster :add-player :first_name_error] nil)
              (assoc-in [:roster :add-player :last_name_error] nil)
              (assoc-in [:roster :add-player :email_error] nil)
              (assoc-in [:roster :add-player :phone_number_error] nil))]
      (if (< (count value) 2)
        {:db (assoc-in upd-db [:roster :add-player (keyword (str field "_error"))] "Name must be at least 2 characters")}))))

(rf/reg-event-fx
  ::update-add-player
  (fn [{:keys [db]} [_ field value]]
    (let [upd-db
          (-> db
              (assoc-in [:roster :add-player :first_name_error] nil)
              (assoc-in [:roster :add-player :last_name_error] nil)
              (assoc-in [:roster :add-player :email_error] nil)
              (assoc-in [:roster :add-player :phone_number_error] nil))]
      (cond
        (and (= field "phone_number") (not (s/blank? value)) (not= (count value) (count (str (re-find #"\d+" value))))) {:db (assoc-in upd-db [:roster :add-player :phone_number_error] "Phone number can take only numbers")}
        :else {:db (assoc-in upd-db [:roster :add-player (keyword field)] value)}))))

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
