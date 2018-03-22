(ns rf-tennis-manager.match-events-avail-email
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [cljs-time.core :as t]))

(rf/reg-event-fx
  ::email-avail-get-data-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> (assoc-in db [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to get data failed")
             (assoc-in [:matches :call-status :panel-visible :send-avail-email] false)
             (assoc-in [:matches :call-status :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/show-schedule])))}))

(rf/reg-event-fx
  ::email-avail-form
  (fn [{:keys [db]} [_ call-response]]
    {:db (-> (assoc-in db [:matches :call-status :message] "Success")
             (assoc-in [:matches :call-status :success?] true)
             (evt-common/show-div "send-avail-email")
             (assoc-in [:matches :match-info] (:body call-response)))}))


(rf/reg-event-fx
  ::send-availability-email
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::call-send-avail-email {:method     :post
                                :url        (str "http://localhost:3000/send-availability-email")
                                :on-success [::send-avail-email-success]
                                :form-id    "#sendavailabilityemail"
                                :on-fail    [::send-avail-email-failed]}
       :db                     upd-db})))

(rf/reg-fx
  ::call-send-avail-email
  #(evt-common/send-post-request %1))

(defn update-avail-sent
  [list match match-id]
  (if (= match-id (:match_id match))
    (conj list (assoc match :availability_sent "1"))
    (conj list match)))

(rf/reg-event-fx
  ::send-avail-email-success
  (fn [{:keys [db]} [_ call-response]]
    (let [upd-schedule (reduce #(update-avail-sent %1 %2 (get-in db [:matches :selected-match-id])) [] (get-in db [:matches :schedule]))
          upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Availability email sent")
                     (assoc-in [:matches :schedule] upd-schedule)
                     (assoc-in [:matches :panel-visible :send-avail-email] false)
                     (assoc-in [:matches :panel-visible :schedule] true)
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/show-schedule])))]
      {:db upd-db})))

(rf/reg-event-fx
  ::send-avail-email-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> (assoc-in db [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to send availability email failed")
             (assoc-in [:matches :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/hide-call-status])))}))

(rf/reg-event-fx
  ::show-email-avail-form
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::email-avail-form]
                                    :on-fail    [::email-avail-get-data-failed]}
       :db                         upd-db})))