(ns rf-tennis-manager.match-events-avail-email
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [cljs-time.core :as t]))

(rf/reg-event-fx
  ::email-avail-get-data-failed
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_send_availability_email")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_send_availability_email cofx)) "div-panel-hide")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::evt-common/show-schedule]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to get data failed"))]
      {:db upd-db})))


(rf/reg-event-fx
  ::email-avail-form
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_send_availability_email")]
  (fn [cofx [_ call-response]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :message] "Success")
                     (assoc-in [:matches :call-status :success?] true))]
      (set! (.-className (:ma_show_schedule cofx)) "div-panel-hide")
      (set! (.-className (:ma_call_status cofx)) "div-panel-hide")
      (set! (.-className (:ma_send_availability_email cofx)) "div-panel-show")
      {:db (assoc-in upd-db [:matches :match-info] (:body call-response))})))


(rf/reg-event-fx
  ::send-availability-email
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
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
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_send_availability_email")]
  (fn [cofx [_ call-response]]
    (set! (.-className (:ma_send_availability_email cofx)) "div-panel-hide")
    (set! (.-className (:ma_show_schedule cofx)) "div-panel-show")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::evt-common/show-schedule]))
    (let [upd-schedule (reduce #(update-avail-sent %1 %2 (get-in (:db cofx) [:matches :selected-match-id])) [] (get-in (:db cofx) [:matches :schedule]))
          upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Availability email sent")
                     (assoc-in [:matches :schedule] upd-schedule)
                     )]
      (println "new db: " upd-db)
      {:db upd-db})))

(rf/reg-event-fx
  ::send-avail-email-failed
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::evt-common/hide-panel "ma_call_status"]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to send availability email failed"))]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      {:db upd-db})))



(rf/reg-event-fx
  ::show-email-avail-form
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      {::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::email-avail-form]
                                    :on-fail    [::email-avail-get-data-failed]}
       :db                         upd-db})))