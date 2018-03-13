(ns rf-tennis-manager.match-events-common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs-http.client :as http]))
(def non-sched-divs ["ma_send_availability_email" "ma_show_availability" "ma_set_lineup" "ma_send_lineup_email" "ma_call_status"])
(rf/reg-cofx
  ::datetime
  (fn coeffect-handler
    [coeffect]
    (assoc coeffect :now (js/Date.))))

(rf/reg-cofx
  ::other-divs
  (fn coeffect-handler
    [coeffect div-id]
    (assoc coeffect ::other-divs div-id)))

(rf/reg-cofx
  ::get-element
  (fn coeffect-handler
    [coeffect el-id]
    (assoc coeffect (keyword el-id) (.getElementById js/document el-id))))

(rf/reg-event-fx
  ::team-info
  [(rf/inject-cofx ::get-element "ma_show_schedule") (rf/inject-cofx ::get-element "ma_call_status") (rf/inject-cofx ::get-element "ma_send_availability_email")]
  (fn [cofx [_ call-response]]
    ;guard against the call to get match info failing before this call finishes. Miniscule chance of a race condition.  Will take my chances
    (if (get-in (:db cofx) [:matches :call-status :success?])
      (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :message] "Success")
                       (assoc-in [:matches :call-status :success?] true))]
        (set! (.-className (:ma_show_schedule cofx)) "div-panel-hide")
        (set! (.-className (:ma_call_status cofx)) "div-panel-hide")
        (set! (.-className (:ma_send_availability_email cofx)) "div-panel-show")
        {:db (assoc-in upd-db [:team-info] (:body call-response))}))))

(rf/reg-event-fx
  ::match-info
  (fn [cofx [_ call-response]]
    {:db (assoc-in (:db cofx) [:matches :match-info] (:body call-response))}))

(rf/reg-fx
  ::get-match_info
  (fn [request]
    (go
      (let [status (<! (http/get (:url request)))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (println "get match info url: " (:url request) " request: " request " method: " method " status: " status)
        (rf/dispatch (conj method status))))))

(rf/reg-event-fx
  ::hide-panel
  (fn [cofx [_ panel-id]]
    (set! (.-className (.getElementById js/document panel-id)) "div-panel-hide")
    {:db (:db cofx)}))

(rf/reg-event-fx
  ::show-schedule
  [(rf/inject-cofx ::other-divs non-sched-divs) (rf/inject-cofx ::get-element "ma_show_schedule")]
  (fn [cofx [_]]
    (set! (.-className (:ma_show_schedule cofx)) "div-panel-show")
    (doseq [div-id (::other-divs cofx)]
      (set! (.-className (.getElementById js/document div-id)) "div-panel-hide"))
    nil))