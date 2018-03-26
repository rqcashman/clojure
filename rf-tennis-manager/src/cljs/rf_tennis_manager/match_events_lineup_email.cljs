(ns rf-tennis-manager.match-events-lineup-email
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]))

(rf/reg-event-fx
  ::email-lineup-get-data-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to get data failed")
             (assoc-in [:matches :call-status :panel-visible :send-lineup-email] false)
             (assoc-in [:matches :call-status :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/show-schedule])))}))

(rf/reg-event-fx
  ::email-lineup-form
  (fn [{:keys [db]} [_ call-response]]
    (if (get-in db [:matches :call-status :success?])
      {:db (-> db
               (assoc-in [:matches :call-status :message] "Success")
               (assoc-in [:matches :call-status :success?] true)
               (assoc-in [:matches :lineup] (:body call-response))
               (evt-common/show-div "send-lineup-email"))})))


(rf/reg-event-fx
  ::send-lineup-email
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::call-send-lineup-email {:method     :post
                                 :url        (str "http://localhost:3000/send-lineup-email")
                                 :on-success [::send-lineup-email-success]
                                 :form-id    "#sendlineupemail"
                                 :on-fail    [::send-lineup-email-failed]}
       :db                      upd-db})))

(rf/reg-fx
  ::call-send-lineup-email
  #(evt-common/send-post-request %1))

(defn update-lineup-sent
  [list match match-id]
  (if (= match-id (:match_id match))
    (conj list (assoc match :lineup_sent "1"))
    (conj list match)))

(rf/reg-event-fx
  ::send-lineup-email-success
  (fn [{:keys [db]} [_ call-response]]
    (let [upd-schedule (reduce #(update-lineup-sent %1 %2 (get-in db [:matches :selected-match-id])) [] (get-in db [:matches :schedule]))
          upd-db (-> db
                     (assoc-in [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Lineup email sent")
                     (assoc-in [:matches :schedule] upd-schedule)
                     (assoc-in [:matches :panel-visible :send-lineup-email] false)
                     (assoc-in [:matches :panel-visible :schedule] true)
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/show-schedule])))]
      {:db upd-db})))

(rf/reg-event-fx
  ::send-lineup-email-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to send lineup email failed")
             (assoc-in [:matches :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(rf/dispatch [::evt-common/hide-call-status])))}))

(rf/reg-fx
  ::get-match-lineup
  #(evt-common/send-get-request %1))

(rf/reg-event-fx
  ::show-email-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::get-match-lineup          {:method     :get
                                    :url        (str "http://localhost:3000/match-lineup/" match-id)
                                    :on-success [::email-lineup-form]
                                    :on-fail    [::email-lineup-get-data-failed]}
       ::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::evt-common/match-info]
                                    :on-fail    [::email-lineup-get-data-failed]}
       :db                         upd-db})))




