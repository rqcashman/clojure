(ns rf-tennis-manager.matches-events
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [clojure.string :as s]))

(def court-player-key ["c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"])
(rf/reg-cofx
  :datetime
  (fn coeffect-handler
    [coeffect]
    (assoc coeffect :now (js/Date.))))

(rf/reg-cofx
  :courts
  (fn coeffect-handler
    [coeffect cp-key]
    (assoc coeffect :courts cp-key)))


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
    (set! (.-className (:ma_show_schedule cofx)) "div-panel-hide")
    (set! (.-className (:ma_call_status cofx)) "div-panel-hide")
    (set! (.-className (:ma_show_availability cofx)) "div-panel-show")
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :message] "Success")
                     (assoc-in [:matches :call-status :success?] true))]
      {:db (assoc-in upd-db [:matches :roster] (:body call-response))})))

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
  ::call-failed
  (fn [{:keys [db]} [_ status]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Call to get data failed"))]
      {:db upd-db})))

(rf/reg-fx
  ::get-match_availability
  (fn [request]
    (go
      (let [status (<! (http/get (:url request)))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (rf/dispatch (conj method status))))))

(rf/reg-fx
  ::get-match_info
  (fn [request]
    (go
      (let [status (<! (http/get (:url request)))
            method (if (:success status) (:on-success request) (:on-fail request))]
        (rf/dispatch (conj method status))))))

(rf/reg-event-fx
  ::show-email-avail-form
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    {::get-match_availability {:method     :get
                               :url        (str "http://localhost:3000/match-availability/" match-id)
                               :on-success [::match-availability]
                               :on-fail    [::call-failed]}
     :db                      (:db cofx)}))

(rf/reg-event-fx
  ::swap-player-class
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    ;(set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (println "swap class")
    ))


(rf/reg-event-fx
  ::show-call-status
  (fn [{:keys [db]} [_]]
    (println "::ma_call_status: ")))

(rf/reg-event-fx
  ::show-set-avail-form
  [(rf/inject-cofx :get-element "ma_show_schedule") (rf/inject-cofx :get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
    (println "::show-email-avail-form: " match-id)
    {::get-match_availability {:method     :get
                               :url        (str "http://localhost:3000/match-availability/" match-id)
                               :on-success [::match-availability]
                               :on-fail    [::call-failed]}
     ::get-match_info         {:method     :get
                               :url        (str "http://localhost:3000/match-info/" match-id)
                               :on-success [::match-info]
                               :on-fail    [::call-failed]}
     :db                      (:db cofx)}))

(rf/reg-event-fx
  ::show-email-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (println "::ma_show-email-lineup-form: " match-id)))

(rf/reg-event-fx
  ::show-set-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (println "::ma_show-set-lineup-form: " match-id)))




