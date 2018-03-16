(ns rf-tennis-manager.match-events-set-lineup
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [clojure.string :as s]))
(def court-playe-key ["c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"])

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

;(rf/reg-event-fx
;  ::initialize-player-lists
;  [(rf/inject-cofx :courts court-player-key)]
;  (fn [cofx [_ players]]
;    (let [updated-db (reduce (fn [in-db court]
;                               (let [upd-db (->
;                                              (assoc-in in-db [:matches :lineup (keyword court) :selected :current] {})
;                                              (assoc-in [:matches :lineup (keyword court) :selected :previous] {})
;                                              (assoc-in [:matches :lineup (keyword court) :players] {}))]
;                                 (reduce #(set-player-list %1 %2 court) upd-db (vals players)))
;                               ) (:db cofx) (:courts cofx))]
;      {:db updated-db})))




(rf/reg-event-fx
  ::update-lineup
  (fn [{:keys [db]} [_]]
    (println "::update-lineup")
    ))


(rf/reg-event-fx
  ::initialize-player-lists
  (fn [{:keys [db]} [_ players]]
    (println "::initialize-player-lists " (:body players))
    ))

(def NO-FORFEITS
  {:c1 nil
   :c2 nil
   :c3 nil
   :c4 nil
   })

(rf/reg-event-fx
  ::init-forfeit-btns
  (fn [{:keys [db]} [_ forfeits]]
    (println "::init-forfeit-btns " (:body forfeits))
    (let [forfeit-hash (if (pos? (count (:body forfeits)))
                         (reduce (fn [list court]
                                   (assoc list (keyword (str "c" (:court_number court))) (:forfeit_team_id court))
                                   ) {} (:body forfeits))
                         NO-FORFEITS)]
      (println "forfeits: " forfeit-hash)
      (println (assoc-in db [:matches :forfeits] forfeit-hash))
      {:db (assoc-in db [:matches :forfeits] forfeit-hash)})))

(rf/reg-event-fx
  ::set-lineup-data-failed
  [(rf/inject-cofx ::courts court-playe-key)]
  (fn [cofx [_ players]]
    (println "::set-lineup-data-failed")
    ))


(rf/reg-fx
  ::get-match-availability
  #(evt-common/send-get-request %1))

(rf/reg-fx
  ::get-match-forfeits
  #(evt-common/send-get-request %1))

(rf/reg-event-fx
  ::show-set-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :selected-match-id] match-id)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::get-match-availability {:method     :get
                                 :url        (str "http://localhost:3000/match-availability/" match-id)
                                 :on-success [::initialize-player-lists]
                                 :on-fail    [::set-lineup-data-failed]}
       ::get-match-forfeits     {:method     :get
                                 :url        (str "http://localhost:3000/match-forfeits/" match-id)
                                 :on-success [::init-forfeit-btns]
                                 :on-fail    [::set-lineup-data-failed]}
       :db                      upd-db})
    ))