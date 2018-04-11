(ns rf-tennis-manager.admin.admin-events-add-club
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.admin.admin-events-common :as evt-admin]
            [clojure.string :as s]))


(rf/reg-fx
  ::add-club
  evt/send-post-request)

(rf/reg-event-fx
  ::add-club-success
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:admin :call-status :success?] true)
             (assoc-in [:admin :panel-visible :call-status] true)
             (assoc-in [:admin :call-status :message] "Club added")
             (assoc-in [:admin :call-status :on-click] #(rf/dispatch [::evt-admin/hide-call-status])))}))

(rf/reg-event-fx
  ::add-club-request
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:admin :call-status :success?] true)
                     (assoc-in [:admin :call-status :message] "Processing...")
                     (assoc-in [:admin :call-status :on-click] nil)
                     (assoc-in [:admin :panel-visible :call-status] true))]
      {::add-club {:method     :post
                   :url        "/add-club"
                   :on-success [::add-club-success]
                   :form-id    "#addclubform"
                   :on-fail    [::evt-admin/admin-page-failed]}
       :db        upd-db})))

