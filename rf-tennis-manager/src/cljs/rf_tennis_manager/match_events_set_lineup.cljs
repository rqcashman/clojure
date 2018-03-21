(ns rf-tennis-manager.match-events-set-lineup
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.match-events-common :as evt-common]
            [clojure.string :as s]))
(def court-player-key ["c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"])
(def no-forfeits
  {:c1 0
   :c2 0
   :c3 0
   :c4 0})

(rf/reg-cofx
  ::courts
  (fn coeffect-handler
    [coeffect court-player-key]
    (assoc coeffect :courts court-player-key)))

(rf/reg-cofx
  ::no-forfeits
  (fn coeffect-handler
    [coeffect court-player-key]
    (assoc coeffect :no-forfeits no-forfeits)))

(rf/reg-event-fx
  ::set-match-info
  (fn [{:keys [db]} [_ call-response]]
    {:db (assoc-in db [:matches :match-info] (:body call-response))}))


(rf/reg-event-fx
  ::update-lineup
  (fn [{:keys [db]} [_ match-id]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::call-update-lineup {:method     :post
                             :url        (str "http://localhost:3000/update-lineup")
                             :on-success [::update-lineup-success]
                             :form-id    "#updatelineup"
                             :on-fail    [::update-lineup-failed "Call to update lineup failed"]}
       :db                  upd-db})))

(rf/reg-fx
  ::call-update-lineup
  #(evt-common/send-post-request %1))

(defn update-lineup-set
  [list match match-id]
  (if (= match-id (:match_id match))
    (conj list (assoc match :lineup_set "1"))
    (conj list match)))

(comment "Process lineup update success.
            The backend has editing so we can still call the failed method if there was a data validation error")
(rf/reg-event-fx
  ::update-lineup-success
  (fn [{:keys [db]} [_ call-response]]
    (if-not (= "failed" (get-in call-response [:body :status]))
      (let [upd-schedule (reduce #(update-lineup-set %1 %2 (get-in db [:matches :selected-match-id])) [] (get-in db [:matches :schedule]))
            upd-db (-> (assoc-in db [:matches :call-status :success?] true)
                       (assoc-in [:matches :schedule] upd-schedule)
                       (assoc-in [:matches :call-status :message] "Lineup updated")
                       (assoc-in [:matches :panel-visible :set-lineup] false)
                       (assoc-in [:matches :panel-visible :schedule] true)
                       (assoc-in [:matches :panel-visible :call-status] true)
                       (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/hide-call-status])))]
        {:db upd-db})
      (rf/dispatch [::update-lineup-failed (get-in call-response [:body :msg])]))))

(rf/reg-event-fx
  ::update-lineup-failed
  (fn [{:keys [db]} [_ msg status]]
    (println "update lineup failed status: " status " msg: " msg)
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] msg)
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/hide-call-status])))]
      {:db upd-db})))

(def no-plyayer-selected {:last_name " ----- none selected -----" :first_name "" :id 0})
(def init-player-list {
                       :selected no-plyayer-selected
                       :players  {(keyword (str (:id no-plyayer-selected))) no-plyayer-selected}})

(defn get-player-court-assignment
  "get court assignment for player"
  [player]
  (if-not (nil? (:court_number player))
    (let [player-id (:id player)]
      (cond
        (or (= player-id (:home_player1 player)) (= player-id (:away_player1 player))) (str "c" (:court_number player) "p1")
        (or (= player-id (:home_player2 player)) (= player-id (:away_player2 player))) (str "c" (:court_number player) "p2")))))

(def player-available 1)

(defn add-player-list
  "Add player to list unless they are selected in a different list"
  [list player court-key]
  (if (= player-available (:available player))
    (let [court-assignment (get-player-court-assignment player)
          add-player {:last_name (:last_name player) :first_name (:first_name player) :id (:id player)}]
      (if (nil? court-assignment)
        (assoc-in list [:players (keyword (str (:id add-player)))] add-player)
        (if (= court-assignment court-key)
          (->
            (assoc-in list [:players (keyword (str (:id add-player)))] add-player)
            (assoc :selected add-player))
          list)))
    list))

(rf/reg-event-fx
  ::show-lineup-form
  [(rf/inject-cofx ::courts court-player-key)]
  (fn [cofx [_ players]]
    (let [player-lists (reduce (fn [player-list court-key]
                                 (assoc player-list (keyword court-key) (reduce #(add-player-list %1 %2 court-key) init-player-list, (:body players))))
                               {}, (:courts cofx))]
      {:db (->
             (assoc-in (:db cofx) [:matches :lineup-player-list] player-lists)
             (assoc-in [:matches :call-status :message] "Success")
             (evt-common/show-div "set-lineup"))})))

(rf/reg-event-fx
  ::init-forfeit-btns
  [(rf/inject-cofx ::no-forfeits no-forfeits)]
  (fn [cofx [_ forfeits]]
    (let [forfeit-hash (if (pos? (count (:body forfeits)))
                         (reduce (fn [list court]
                                   (assoc list (keyword (str "c" (:court_number court))) (if (nil? (:forfeit_team_id court)) 0 (:forfeit_team_id court))))
                                 {} (:body forfeits))
                         (:no-forfeits cofx))]
      (println "forfeits: " forfeit-hash)
      {:db (assoc-in (:db cofx) [:matches :forfeits] forfeit-hash)})))

(rf/reg-event-fx
  ::set-lineup-data-failed
  (fn [{:keys [db]} [_ status]]
    (let [upd-db (-> (assoc-in db [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Call to get data failed")
                     (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/show-schedule]))
                     (evt-common/show-div "call-status"))]
      {:db upd-db})))

(rf/reg-event-fx
  ::update-forfeit-btns
  (fn [{:keys [db]} [_ court value]]
    {:db (assoc-in db [:matches :forfeits (keyword (str "c" court))] value)}))

(def no-selection-player-id 0)

(defn update-player-list
  "update the player list for each court.  The newly seletect item will be removed and the previously selected item will be restored - excluding the 'none' selection"
  [db player-list prev-selection new-selection]
  (if-not (or (= (:id prev-selection) no-selection-player-id) (= (:id new-selection) no-selection-player-id))
    (->
      (assoc-in db [:matches :lineup-player-list (keyword player-list) :players (keyword (str (:id prev-selection)))] prev-selection)
      (update-in [:matches :lineup-player-list (keyword player-list) :players] dissoc (keyword (str (:id new-selection)))))
    (if-not (= (:id prev-selection) no-selection-player-id)
      (assoc-in db [:matches :lineup-player-list (keyword player-list) :players (keyword (str (:id prev-selection)))] prev-selection)
      (update-in db [:matches :lineup-player-list (keyword player-list) :players] dissoc (keyword (str (:id new-selection)))))))

(rf/reg-event-fx
  ::update-player-lists
  [(rf/inject-cofx ::courts court-player-key)]
  (fn [cofx [_ court-key value]]
    (println "::update-player-lists:" court-key " value: " value)
    (let [ct-key (keyword court-key)
          prev-selection (get-in (:db cofx) [:matches :lineup-player-list ct-key :selected])
          new-selection (get-in (:db cofx) [:matches :lineup-player-list ct-key :players (keyword (str value))])
          upd-db (reduce #(update-player-list %1 %2 prev-selection new-selection)
                         (:db cofx)
                         (remove #(= court-key %) (:courts cofx)))]
      {:db (assoc-in upd-db [:matches :lineup-player-list ct-key :selected] new-selection)})))

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
      {::get-match-availability    {:method     :get
                                    :url        (str "http://localhost:3000/match-availability/" match-id)
                                    :on-success [::show-lineup-form]
                                    :on-fail    [::set-lineup-data-failed]}
       ::get-match-forfeits        {:method     :get
                                    :url        (str "http://localhost:3000/match-forfeits/" match-id)
                                    :on-success [::init-forfeit-btns]
                                    :on-fail    [::set-lineup-data-failed]}
       ::evt-common/get-match-info {:method     :get
                                    :url        (str "http://localhost:3000/match-info/" match-id)
                                    :on-success [::set-match-info]
                                    :on-fail    [::set-lineup-data-failed]}
       :db                         upd-db})))