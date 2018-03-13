(ns rf-tennis-manager.match-events-set-availability
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [cljs-http.client :as http]
            [enfocus.core :as ef]))


(rf/reg-event-fx
  ::update-availability-call-success
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_show_availability")]
  (fn [cofx [_ call-response]]
    (set! (.-className (:ma_show_availability cofx)) "div-panel-hide")
    (set! (.-className (:ma_show_schedule cofx)) "div-panel-show")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::evt-common/hide-panel "ma_call_status"]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Availability updated"))]
      {:db upd-db})))

(rf/reg-event-fx
  ::update-availability-call-failed
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::evt-common/hide-panel "ma_call_status"]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to update availability failed"))]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      {:db upd-db})))

(rf/reg-fx
  ::get-match_availability
  (fn [request]
    (go
      (let [status (<! (http/get (:url request)))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (rf/dispatch (conj method status))))))


(rf/reg-event-fx
  ::update-match-availability
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      {::call-update-availability {:method     :post
                                   :url        (str "http://localhost:3000/update-availability")
                                   :on-success [::update-availability-call-success]
                                   :on-fail    [::update-availability-call-failed]}
       :db                        upd-db})))

(rf/reg-fx
  ::call-update-availability
  (fn [request]
    (go
      (let [values (ef/from "#updateavailability" (ef/read-form))
            status (<! (http/post (:url request) {:form-params values}))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (rf/dispatch (conj method status))))))

(rf/reg-event-fx
  ::swap-player-class
  (fn [{:keys [db]} [_ match-id player-id]]
    (let [cb-el (.getElementById js/document match-id)
          row-el (.getElementById js/document player-id)]
      (set! (.-className row-el) (if (.-checked cb-el) "player-avail" ""))
      (.-checked cb-el)
      {:db db})))
;(:db (assoc-in db [:matches :roster] (assoc (first (filter #(= player-id (:id %)) (get-in db [:matches :roster]))) :available (if (.-checked cb-el) 1 0)))))))

(rf/reg-event-fx
  ::availability-call-failed
  [(rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_show_availability")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_show_availability cofx)) "div-panel-hide")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::show-schedule]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to get data failed"))]
      {:db upd-db})))

(rf/reg-event-fx
  ::match-availability
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status") (rf/inject-cofx ::evt-common/get-element "ma_show_availability")]
  (fn [cofx [_ call-response]]
    ;guard against the call to get match info failing before this call finishes. Miniscule chance of a race condition.  Will take my chances
    (if (get-in (:db cofx) [:matches :call-status :success?])
      (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :message] "Success")
                       (assoc-in [:matches :call-status :success?] true))]
        (set! (.-className (:ma_show_schedule cofx)) "div-panel-hide")
        (set! (.-className (:ma_call_status cofx)) "div-panel-hide")
        (set! (.-className (:ma_show_availability cofx)) "div-panel-show")
        {:db (assoc-in upd-db [:matches :roster] (:body call-response))}))))

(rf/reg-event-fx
  ::show-set-avail-form
  [(rf/inject-cofx ::evt-common/get-element "ma_show_schedule") (rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      (println "::show-email-avail-form: " match-id)
      {::get-match_availability    {:method     :get
                                    :url        (str "http://localhost:3000/match-availability/" match-id)
                                    :on-success [::match-availability]
                                    :on-fail    [::availability-call-failed]}
       ::evt-common/get-match_info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::evt-common/match-info]
                                    :on-fail    [::availability-call-failed]}
       :db                         upd-db})))
