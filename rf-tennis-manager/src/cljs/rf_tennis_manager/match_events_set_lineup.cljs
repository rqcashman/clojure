(ns rf-tennis-manager.match-events-set-lineup
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [clojure.string :as s]))
(def court-player-key ["c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"])

(rf/reg-cofx
  ::courts
  (fn coeffect-handler
    [coeffect court-player-key]
    (assoc coeffect :courts court-player-key)))

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
  ::update-forfeit-btns
  (fn [{:keys [db]} [_ btn-id value]]
    (println "::update-forfeit-btns id: " btn-id "value: " value)
    ))


(rf/reg-event-fx
  ::update-lineup
  (fn [{:keys [db]} [_]]
    (println "::update-lineup")
    ))

(rf/reg-event-fx
  ::forfeits
  (fn [{:keys [db]} [_ status]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :message] "Success")
                     (assoc-in [:matches :call-status :success?] true))]
      {:db upd-db})))

(rf/reg-event-fx
  ::show-set-lineup-form
  [(rf/inject-cofx ::evt-common/get-element "ma_set_lineup") (rf/inject-cofx ::evt-common/get-element "ma_call_status")]
  (fn [cofx [_ match-id]]
    (let [upd-db (-> (assoc-in (:db cofx) [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id))]
      (set! (.-className (:ma_call_status cofx)) "div-panel-call-status")
      {::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::email-avail-form]
                                    :on-fail    [::email-avail-get-data-failed]}
       :db                         upd-db})))