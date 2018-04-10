(ns rf-tennis-manager.schedule.schedule-events
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [clojure.string :as s]))

(rf/reg-event-fx
  ::schedule-page-failed
  (fn [{:keys [db]} [_ response]]
    (let [msg (if-not (s/blank? (get-in response [:body :msg])) (get-in response [:body :msg]) "Call to get data failed")]
      {:db (-> db
               (assoc-in [:schedule :call-status :success?] false)
               (assoc-in [:schedule :call-status :message] msg)
               (assoc-in [:schedule :panel-visible :call-status] true)
               (assoc-in [:schedule :call-status :on-click] #(re-frame.core/dispatch [::hide-call-status])))})))

(defn show-div
  [db show-div-id]
  (->
    (reduce (fn [upd-db key]
              (assoc-in upd-db [:schedule :panel-visible key] false))
            db (keys (get-in db [:schedule :panel-visible])))
    (assoc-in [:schedule :panel-visible (keyword show-div-id)] true)))

(rf/reg-fx
  ::get-schedule
  evt/send-get-request)

(rf/reg-fx
  ::get-teams
  evt/send-get-request)

(rf/reg-fx
  ::get-seasons
  evt/send-get-request)

(rf/reg-event-fx
  ::load-schedule
  (fn [{:keys [db]} [_ response]]
    {:db (-> db
             (assoc-in [:schedule :team-schedule] (:body response))
             (show-div "schedule"))}))

(rf/reg-event-fx
  ::show-schedule
  (fn [{:keys [db]} [_]]
    (let [team-id (get-in db [:schedule :selected-team :id])
          season-id (get-in db [:schedule :selected-season :id])]
      (if-not (or (s/blank? team-id) (s/blank? season-id))
        {:db            (-> db
                            (assoc-in [:schedule :selected-team] (first (filter #(identical? (str (:id %)) (str team-id)) (get-in db [:teams :list]))))
                            (assoc-in [:schedule :call-status :success?] true)
                            (assoc-in [:schedule :panel-visible :call-status] true)
                            (assoc-in [:schedule :call-status :message] "Processing..."))
         ::get-schedule {:method     :get
                         :url        (str "/team-schedule/" season-id "/" team-id)
                         :on-success [::load-schedule]
                         :on-fail    [::schedule-page-failed]}}))))

(rf/reg-event-fx
  ::selected-team-changed
  (fn [{:keys [db]} [_ team-id]]
    {:db (assoc-in db [:schedule :selected-team] (first (filter #(identical? (str (:id %)) (str team-id)) (get-in db [:teams :list]))))}))

(rf/reg-event-fx
  ::selected-season-changed
  (fn [{:keys [db]} [_ schedule-id]]
    (println "==== season changed: id: " schedule-id " sched: " (get-in db [:seasons]))
    {:db (assoc-in db [:schedule :selected-season] (first (filter #(identical? (str (:id %)) (str schedule-id)) (get-in db [:seasons]))))}))

(rf/reg-event-fx
  ::load-teams
  (fn [{:keys [db]} [_ response]]
    {:db       (-> db
                   (assoc-in [:teams :list] (:body response))
                   (assoc-in [:schedule :call-status :message] "Success")
                   (assoc-in [:schedule :panel-visible :call-status] false)
                   (assoc-in [:schedule :panel-visible :select-form] true))
     :dispatch [::selected-team-changed (get-in db [:team-info :id])]}))

(rf/reg-event-fx
  ::load-seasons
  (fn [{:keys [db]} [_ response]]
    {:db (-> db
             (assoc :seasons (:body response))
             (assoc-in [:schedule :selected-season] (first (:body response))))}))

(rf/reg-event-fx
  ::hide-call-status
  (fn [{:keys [db]} [_ response]]
    {:db (assoc-in db [:schedule :panel-visible :call-status] false)}))

(rf/reg-event-fx
  ::show-select-form
  (fn [{:keys [db]} [_ response]]
    {:db (show-div db "select-form")}))

(rf/reg-event-fx
  ::init-schedule-page
  (fn [{:keys [db]} [_]]
    (println "=== init sched page ===")
    (let [upd-db (-> db
                     (assoc-in [:schedule :call-status :success?] true)
                     (assoc-in [:schedule :call-status :message] "Processing...")
                     (assoc-in [:schedule :call-status :on-click] nil)
                     (show-div "select-form")
                     (assoc-in [:schedule :panel-visible :call-status] true))]
      {::get-teams   {:method     :get
                      :url        "/teams"
                      :on-success [::load-teams]
                      :on-fail    [::schedule-page-failed]}
       ::get-seasons {:method     :get
                      :url        "/seasons"
                      :on-success [::load-seasons]
                      :on-fail    [::schedule-page-failed]}
       :db           upd-db})))
