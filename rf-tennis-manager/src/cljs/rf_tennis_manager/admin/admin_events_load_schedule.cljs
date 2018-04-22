(ns rf-tennis-manager.admin.admin-events-load-schedule
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.admin.admin-events-common :as evt-common]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::selected-season-changed
  (fn [{:keys [db]} [_ season-id]]
    {:db (assoc-in db [:admin :selected-season] (first (filter #(identical? (str (:id %)) (str season-id)) (:seasons db))))}))

(rf/reg-fx
  ::load-schedule
  evt/send-post-request)

(rf/reg-event-fx
  ::load-schedule-success
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:admin :call-status :success?] true)
             (assoc-in [:admin :panel-visible :call-status] true)
             (assoc-in [:admin :call-status :message] "Schedule loaded")
             (assoc-in [:admin :call-status :on-click] #(rf/dispatch [::evt-common/hide-call-status])))}))

(rf/reg-event-fx
  ::load-schedule-request
  (fn [{:keys [db]} [_ request]]
    (let [upd-db (-> db
                     (assoc-in [:admin :call-status :success?] true)
                     (assoc-in [:admin :call-status :message] "Processing...")
                     (assoc-in [:admin :call-status :on-click] nil)
                     (assoc-in [:admin :panel-visible :call-status] true))]
      {::load-schedule {:method     :post
                        :url        "/load-schedule"
                        :on-success [::load-schedule-success]
                        :form-id    "#loadscheduleform"
                        :on-fail    [::evt-common/admin-page-failed]}
       :db             upd-db})))
