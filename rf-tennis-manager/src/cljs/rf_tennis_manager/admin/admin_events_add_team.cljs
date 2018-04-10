(ns rf-tennis-manager.admin.admin-events-add-team
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.admin.admin-events-common :as evt-admin]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::selected-club-changed
  (fn [{:keys [db]} [_ club-id]]
    {:db (assoc-in db [:admin :selected-club] (first (filter #(identical? (str (:id %)) (str club-id)) (:clubs db))))}))

(rf/reg-event-fx
  ::update-field
  (fn [{:keys [db]} [_ form-name field-name value]]
    {:db (assoc-in db [:admin (keyword form-name) :fields (keyword field-name) :value] value)}))

(rf/reg-fx
  ::add-team
  evt/send-post-request)

(rf/reg-event-fx
  ::add-team-success
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:admin :call-status :success?] true)
             (assoc-in [:admin :panel-visible :call-status] true)
             (assoc-in [:admin :call-status :message] "Team added")
             (assoc-in [:admin :call-status :on-click] #(rf/dispatch [::evt-admin/hide-call-status (get-in db [:roster :selected-team :id])])))}))

(rf/reg-event-fx
  ::add-team-request
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:admin :call-status :success?] true)
                     (assoc-in [:admin :call-status :message] "Processing...")
                     (assoc-in [:admin :call-status :on-click] nil)
                     (assoc-in [:admin :panel-visible :call-status] true))]
      {::add-team {:method     :post
                   :url        "/add-team"
                   :on-success [::add-team-success]
                   :form-id    "#addteamform"
                   :on-fail    [::evt-admin/admin-page-failed]}
       :db        upd-db})))
