(ns rf-tennis-manager.match-events-schedule
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]))

(rf/reg-event-fx
  ::team-info
  (fn [{:keys [db]} [_ team-info]]
    {:db (assoc db :team-info (:body team-info))}))

(rf/reg-fx
  ::get-team-info
  #(evt-common/send-get-request %1))

(rf/reg-fx
  ::get-schedule
  #(evt-common/send-get-request %1))

(rf/reg-event-fx
  ::init-schedule-page-failed
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_show_schedule")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::show-schedule]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to get data failed"))]
      {:db upd-db})))

(rf/reg-event-fx
  ::schedule
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ call-response]]
    (if (get-in (:db cofx) [:matches :call-status :success?])
      (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :message] "Success")
                       (assoc-in [:matches :call-status :success?] true))]
        (set! (.-className (:ma_show_schedule cofx)) "div-panel-show")
        (set! (.-className (:ma_call_status cofx)) "div-panel-hide")
        {:db (assoc-in upd-db [:matches :schedule] (:body call-response))}))))

(rf/reg-event-fx
  ::init-schedule-page
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      (println "::::init-schedule-page ")
      {::get-team-info {:method     :get
                        :url        (str "http://localhost:3000/team-info")
                        :on-success [::team-info]
                        :on-fail    [::init-schedule-page-failed]}
       ::get-schedule  {:method     :get
                        :url        (str "http://localhost:3000/team-schedule-status")
                        :on-success [::schedule]
                        :on-fail    [::init-schedule-page-failed]}
       :db             upd-db})))