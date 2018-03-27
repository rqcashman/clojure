(ns rf-tennis-manager.match-events-set-availability
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [cljs-http.client :as http]))

(rf/reg-event-fx
  ::update-availability-call-success
  (fn [{:keys [db]} [_ call-response]]
    {:db (-> db
             (assoc-in [:matches :call-status :success?] true)
             (assoc-in [:matches :call-status :message] "Availability updated")
             (assoc-in [:matches :panel-visible :availability] false)
             (assoc-in [:matches :panel-visible :schedule] true)
             (assoc-in [:matches :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/hide-call-status])))}))

(rf/reg-event-fx
  ::update-availability-call-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to update availability failed")
             (assoc-in [:matches :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/hide-call-status])))}))

(rf/reg-fx
  ::get-match_availability
  evt-common/send-get-request)


(rf/reg-event-fx
  ::update-match-availability
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :call-status :on-click] nil)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::call-update-availability {:method     :post
                                   :url        (str "http://localhost:3000/update-availability")
                                   :on-success [::update-availability-call-success]
                                   :form-id    "#updateavailability"
                                   :on-fail    [::update-availability-call-failed]}
       :db                        upd-db})))

(rf/reg-fx
  ::call-update-availability
  evt-common/send-post-request)

(def player-available 1)
(def player-unavailable 0)

(defn update-player-availability
  [player-list player update-id]
  (if (= (:id player) update-id)
    (conj player-list (assoc player :available (if (= (:available player) player-available) player-unavailable player-available)))
    (conj player-list player)))

(rf/reg-event-fx
  ::player-selection-changed
  (fn [{:keys [db]} [_ player-id]]
    (let [roster (reduce #(update-player-availability %1 %2 player-id) [] (get-in db [:matches :roster]))]
      {:db (assoc-in db [:matches :roster] roster)})))

(rf/reg-event-fx
  ::availability-call-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to get data failed")
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/show-schedule]))
             (evt-common/show-div "call-status"))}))

(rf/reg-event-fx
  ::match-availability
  (fn [{:keys [db]} [_ call-response]]
    ;guard against the call to get match info failing before this call finishes. Miniscule chance of a race condition.  Will take my chances
    (if (get-in db [:matches :call-status :success?])
      {:db (-> db
               (assoc-in [:matches :call-status :message] "Success")
               (assoc-in [:matches :call-status :success?] true)
               (assoc-in [:matches :roster] (:body call-response))
               (evt-common/show-div "availability"))})))

(rf/reg-event-fx
  ::show-set-avail-form
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :call-status :on-click] nil)
                     (assoc-in [:matches :selected-match-id] match-id)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::get-match_availability    {:method     :get
                                    :url        (str "http://localhost:3000/match-availability/" match-id)
                                    :on-success [::match-availability]
                                    :on-fail    [::availability-call-failed]}
       ::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::evt-common/match-info]
                                    :on-fail    [::availability-call-failed]}
       :db                         upd-db})))
