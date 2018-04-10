(ns  rf-tennis-manager.roster.roster-events-update-player
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::update-selected-player
  (fn [{:keys [db]} [_ field value]]
    (let [upd-db
          (-> db
              (assoc-in [:roster :selected-player :first_name_error] nil)
              (assoc-in [:roster :selected-player :last_name_error] nil)
              (assoc-in [:roster :selected-player :email_error] nil)
              (assoc-in [:roster :selected-player :phone_number_error] nil))]
      (cond
        (and (= field "phone_number") (not (s/blank? value)) (not= (count value) (count (str (re-find #"\d+" value))))) {:db (assoc-in upd-db [:roster :selected-player :phone_number_error] "Phone number can take only numbers")}
        (and (= field "first_name") (< (count value) 2)) {:db (assoc-in upd-db [:roster :selected-player :first_name_error] "First name must contain at least 2 characters")}
        (and (= field "last_name") (< (count value) 2)) {:db (assoc-in upd-db [:roster :selected-player :last_name_error] "Last name must contain at least 2 characters")}
        :else {:db (assoc-in upd-db [:roster :selected-player (keyword field)] value)}))))

(rf/reg-event-fx
  ::update-player-success
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :call-status :success?] true)
             (assoc-in [:roster :panel-visible :call-status] true)
             (assoc-in [:roster :call-status :message] "Player updated")
             (assoc-in [:roster :call-status :on-click] #(rf/dispatch [::evt-common/selected-team-changed (get-in db [:roster :selected-team :id])])))}))

(rf/reg-fx
  ::update-player
  evt/send-post-request)

(rf/reg-event-fx
  ::update-player-request
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:roster :call-status :success?] true)
                     (assoc-in [:roster :call-status :message] "Processing...")
                     (assoc-in [:roster :call-status :on-click] nil)
                     (assoc-in [:roster :panel-visible :call-status] true))]
      {::update-player {:method     :post
                        :url        "/update-player"
                        :on-success [::update-player-success]
                        :form-id    "#updateplayerform"
                        :on-fail    [::evt-common/roster-page-failed]}
       :db             upd-db})))

