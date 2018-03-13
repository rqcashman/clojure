(ns rf-tennis-manager.matches-events
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [enfocus.core :as ef]
            [clojure.string :as s]))

(def court-player-key ["c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"])
(def non-sched-divs ["ma_send_availability_email" "ma_show_availability" "ma_set_lineup" "ma_send_lineup_email" "ma_call_status"])
(rf/reg-cofx
  :datetime
  (fn coeffect-handler
    [coeffect]
    (assoc coeffect :now (js/Date.))))

(rf/reg-cofx
  :courts
  (fn coeffect-handler
    [coeffect court-player-key]
    (assoc coeffect :courts court-player-key)))

(rf/reg-cofx
  :other-divs
  (fn coeffect-handler
    [coeffect div-id]
    (assoc coeffect :other-divs div-id)))


(rf/reg-cofx
  :get-element
  (fn coeffect-handler
    [coeffect el-id]
    (assoc coeffect (keyword el-id) (.getElementById js/document el-id))))

(defn set-player-list
  [db player court]
  (let [court-assignment (:court-assignment player)
        upd-player (dissoc player :court-assignment)]
    (if (or (s/blank? court-assignment) (= court-assignment court))
      (if (= court-assignment court)
        (->
          (assoc-in db [:matches :lineup (keyword court) :players (keyword (str (:id player)))] upd-player)
          (assoc-in [:matches :lineup (keyword court) :selected :current] upd-player))
        (assoc-in db [:matches :lineup (keyword court) :players (keyword (str (:id player)))] upd-player))
      db)))

(rf/reg-event-fx
  ::initialize-player-lists
  [(rf/inject-cofx :courts court-player-key)]
  (fn [cofx [_ players]]
    (let [updated-db (reduce (fn [in-db court]
                               (let [upd-db (->
                                              (assoc-in in-db [:matches :lineup (keyword court) :selected :current] {})
                                              (assoc-in [:matches :lineup (keyword court) :selected :previous] {})
                                              (assoc-in [:matches :lineup (keyword court) :players] {}))]
                                 (reduce #(set-player-list %1 %2 court) upd-db (vals players)))
                               ) (:db cofx) (:courts cofx))]
      {:db updated-db})))

(rf/reg-event-fx
  ::match-availability
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status") (rf/inject-cofx :get-element "ma_show_availability")]
  (fn [cofx [_ call-response]]
    ;guard against the call to get match info failing before this call finishes. Miniscule chance of a race condition.  Will take my chances
    (println "in match availability.  call status: " (get-in (:db cofx) [:matches :call-status :success?]))
    (if (get-in (:db cofx) [:matches :call-status :success?])
      (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :message] "Success")
                       (assoc-in [:matches :call-status :success?] true))]
        (set! (.-className (:ma_show_schedule cofx)) "div-panel-hide")
        (set! (.-className (:ma_call_status cofx)) "div-panel-hide")
        (set! (.-className (:ma_show_availability cofx)) "div-panel-show")
        {:db (assoc-in upd-db [:matches :roster] (:body call-response))}))))

(rf/reg-event-fx
  ::match-info
  (fn [cofx [_ call-response]]
    {:db (assoc-in (:db cofx) [:matches :match-info] (:body call-response))}))

(rf/reg-event-fx
  ::forfeits
  (fn [{:keys [db]} [_ status]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :message] "Success")
                     (assoc-in [:matches :call-status :success?] true))]
      {:db upd-db})))


(rf/reg-event-fx
  ::availability-call-failed
  [(rf/inject-cofx :get-element "ma_call_status") (rf/inject-cofx :get-element "ma_show_availability")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_show_availability cofx)) "div-panel-hide")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::show-schedule]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to get data failed"))]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      {:db upd-db})))

(rf/reg-event-fx
  ::hide-panel
  (fn [cofx [_ panel-id]]
    (set! (.-className (.getElementById js/document panel-id)) "div-panel-hide")
    {:db (:db cofx)}))

(rf/reg-event-fx
  ::update-availability-call-success
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status") (rf/inject-cofx :get-element "ma_show_availability")]
  (fn [cofx [_ call-response]]
    (set! (.-className (:ma_show_availability cofx)) "div-panel-hide")
    (set! (.-className (:ma_show_schedule cofx)) "div-panel-show")
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::hide-panel "ma_call_status"]))
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Availability updated"))]
      {:db upd-db})))

(rf/reg-event-fx
  ::update-availability-call-failed
  [(rf/inject-cofx :get-element "ma_call_status")]
  (fn [cofx [_ status]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (set! (.-onclick (:ma_call_status cofx)) #(re-frame.core/dispatch [::hide-panel "ma_call_status"]))
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

(rf/reg-fx
  ::call-update-availability
  (fn [request]
    (go
      (let [values (ef/from "#updateavailability" (ef/read-form))
            status   (<! (http/post (:url request) {:form-params values}))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (rf/dispatch (conj method status))))))

(rf/reg-fx
  ::get-match_info
  (fn [request]
    (go
      (let [status (<! (http/get (:url request)))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (rf/dispatch (conj method status))))))

(rf/reg-fx
  ::nothing-fx
  (fn []))

(rf/reg-event-fx
  ::show-email-avail-form
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    {::get-match_availability {:method     :get
                               :url        (str "http://localhost:3000/match-availability/" match-id)
                               :on-success [::match-availability]
                               :on-fail    [::availability-call-failed]}
     :db                      (:db cofx)}))

(rf/reg-event-fx
  ::update-match-availability
  [(rf/inject-cofx :get-element "ma_call_status")]
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

(rf/reg-event-fx
  ::show-schedule
  [(rf/inject-cofx :other-divs non-sched-divs) (rf/inject-cofx :get-element "ma_show_schedule")]
  (fn [cofx [_]]
    (set! (.-className (:ma_show_schedule cofx)) "div-panel-show")
    (doseq [div-id (:other-divs cofx)]
      (set! (.-className (.getElementById js/document div-id)) "div-panel-hide"))
    nil))

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
  ::show-set-avail-form
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     )]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      (println "::show-email-avail-form: " match-id)
      {::get-match_availability {:method     :get
                                 :url        (str "http://localhost:3000/match-availability/" match-id)
                                 :on-success [::match-availability]
                                 :on-fail    [::availability-call-failed]}
       ::get-match_info         {:method     :get
                                 :url        (str "http://localhost:3000/match-info/" match-id)
                                 :on-success [::match-info]
                                 :on-fail    [::availability-call-failed]}
       :db                      upd-db})))

(rf/reg-event-fx
  ::show-email-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (println "::ma_show-email-lineup-form: " match-id)))

(rf/reg-event-fx
  ::show-set-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (println "::ma_show-set-lineup-form: " match-id)))




