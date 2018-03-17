(ns rf-tennis-manager.match-events-set-availability
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [cljs-http.client :as http]))

(rf/reg-event-fx
  ::update-availability-call-success
  (fn [{:keys [db]} [_ call-response]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Availability updated")
                     (assoc-in [:matches :panel-visible :availability] false)
                     (assoc-in [:matches :panel-visible :schedule] true)
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/hide-call-status])))]
      {:db upd-db})))

(rf/reg-event-fx
  ::update-availability-call-failed
  (fn [{:keys [db]} [_ status]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to update availability failed")
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/hide-call-status])))]
      {:db upd-db})))

(rf/reg-fx
  ::get-match_availability
  #(evt-common/send-get-request %1))


(rf/reg-event-fx
  ::update-match-availability
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::call-update-availability {:method     :post
                                   :url        (str "http://localhost:3000/update-availability")
                                   :on-success [::update-availability-call-success]
                                   :form-id    "#updateavailability"
                                   :on-fail    [::update-availability-call-failed]}
       :db                        upd-db})))

(rf/reg-fx
  ::call-update-availability
  #(evt-common/send-post-request %1))

;TODO Ask CK is there is a better way to do this update
(defn update-player-availability
  [player-list player update-id]
  (if (= (:id player) update-id)
    (conj player-list (assoc player :available (if (= (:available player) 1) 0 1)))
    (conj player-list player)))

(rf/reg-event-fx
  ::player-selection-changed
  (fn [{:keys [db]} [_ match-id player-id]]
    (let [roster (reduce #(update-player-availability %1 %2 player-id) [] (get-in db [:matches :roster]))]
      {:db (assoc-in db [:matches :roster] roster)})))

(rf/reg-event-fx
  ::availability-call-failed
  (fn [{:keys [db]} [_ status]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to get data failed")
                     (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/show-schedule]))
                     (evt-common/show-div "call-status"))]
      (println "::availability-call-failed " (get-in upd-db [:matches :panel-visible]))
      {:db upd-db})))

(rf/reg-event-fx
  ::match-availability
  (fn [{:keys [db]} [_ call-response]]
    ;guard against the call to get match info failing before this call finishes. Miniscule chance of a race condition.  Will take my chances
    (if (get-in db [:matches :call-status :success?])
      (let [upd-db (-> (assoc-in db [:matches :call-status :message] "Success")
                       (assoc-in [:matches :call-status :success?] true)
                       (evt-common/show-div "availability")
                       (assoc-in [:matches :roster] (:body call-response)))]
        (println "::match-availability " (get-in upd-db [:matches :panel-visible]))
        {:db upd-db}))))

(rf/reg-event-fx
  ::show-set-avail-form
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      (println "show avail form db: " (get-in upd-db [:matches :panel-visible]))
      {::get-match_availability    {:method     :get
                                    :url        (str "http://localhost:3000/match-availability/" match-id)
                                    :on-success [::match-availability]
                                    :on-fail    [::availability-call-failed]}
       ::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::evt-common/match-info]
                                    :on-fail    [::availability-call-failed]}
       :db                         upd-db})))
