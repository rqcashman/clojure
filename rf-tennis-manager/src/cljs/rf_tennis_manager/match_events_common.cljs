(ns rf-tennis-manager.match-events-common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs-http.client :as http]
            [enfocus.core :as ef]))
(def non-sched-divs ["ma_send_availability_email" "ma_show_availability" "ma_set_lineup" "ma_send_lineup_email" "ma_call_status" "ma_show_schedule"])
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
  ::match-info
  (fn [cofx [_ call-response]]
    {:db (assoc-in (:db cofx) [:matches :match-info] (:body call-response))}))

(defn send-get-request
  [request]
  (go
    (let [status (<! (http/get (:url request)))
          method (if (:success status) (:on-success request) (:on-fail request))]
      (rf/dispatch (conj method status)))))

(defn send-post-request
  [request]
  (go
    (let [values (ef/from (:form-id request) (ef/read-form))
          status (<! (http/post (:url request) {:form-params values}))
          method (if (:success status) (:on-success request) (:on-fail request))]
      (rf/dispatch (conj method status)))))

(rf/reg-fx
  ::get-match-info
  #(send-get-request %1))

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
    (doseq [div-id (remove #(= (str (:ma_show_schedule cofx) %)) (::other-divs cofx))]
        (set! (.-className (.getElementById js/document div-id)) "div-panel-hide"))
    nil))

(rf/reg-event-fx
  ::show-availability
  [(rf/inject-cofx ::other-divs non-sched-divs) (rf/inject-cofx ::get-element "ma_show_availability")]
  (fn [cofx [_]]
    (set! (.-className (:ma_show_availability cofx)) "div-panel-show")
    (doseq [div-id (remove #(= (str (:ma_show_schedule cofx) %)) (::other-divs cofx))]
      (set! (.-className (.getElementById js/document div-id)) "div-panel-hide"))
    nil))

