(ns rf-tennis-manager.match-events-avail-email
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]))

(rf/reg-event-fx
  ::email-avail-get-data-failed
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_send_availability_email")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_send_availability_email cofx)) "div-panel-hide")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::show-schedule]))
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
  (fn [{:keys [db]} [_ match-id player-id]]
    (println "::send-availability-email")
    nil))

(rf/reg-event-fx
  ::show-email-avail-form
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      (println "::show-email-avail-form: " match-id)
      {::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::email-avail-form]
                                    :on-fail    [::email-avail-get-data-failed]}
       :db                         upd-db})))