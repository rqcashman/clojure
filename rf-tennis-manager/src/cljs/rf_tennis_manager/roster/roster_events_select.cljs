(ns rf-tennis-manager.roster.roster-events-select
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]))

(rf/reg-fx
  ::get-teams
  evt/send-get-request)

(rf/reg-fx
  ::get-roster
  evt/send-get-request)

(rf/reg-event-fx
  ::show-roster
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :panel-visible :call-status] false)
             (assoc-in [:roster :panel-visible :roster] true))}))

(rf/reg-event-fx
  ::roster-action-change
  (fn [{:keys [db]} [_ item-value]]
    {:db (assoc-in db [:roster :selected-roster-action] item-value)}))

(rf/reg-event-fx
  ::load-teams
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:teams :list] (:body status))
             (assoc-in [:roster :call-status :message] "Success")
             (assoc-in [:roster :panel-visible :call-status] false)
             (assoc-in [:roster :panel-visible :roster] true)
             (assoc-in [:roster :selected-roster-action] "show-roster"))}))

(rf/reg-event-fx
  ::load-roster
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :team-roster] (:body status))
             (assoc-in [:roster :call-status :message] "Success")
             (assoc-in [:roster :panel-visible :call-status] false))}))

(rf/reg-event-fx
  ::roster-page-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :call-status :success?] false)
             (assoc-in [:roster :call-status :message] "Call to get data failed")
             (assoc-in [:roster :panel-visible :call-status] true)
             (assoc-in [:roster :call-status :on-click] #(re-frame.core/dispatch [::show-roster])))}))

(rf/reg-event-fx
  ::selected-team-changed
  (fn [{:keys [db]} [_ team-id]]
    ;(println "=============================== ::selected-team-changed id: " team-id)
    ;(println "list: " (get-in db [:teams :list]))
    ;(println "list: " (filter #(= (str (:id %)) team-id) (get-in db [:teams :list])))
    ;(println "list: " (first (filter #(= (:id %) team-id) (get-in db [:teams :list]))))
    {:db          (assoc-in db [:roster :selected-team] (first (filter #(identical? (str (:id %)) (str team-id)) (get-in db [:teams :list]))))
     ::get-roster {:method     :get
                   :url        (str "http://localhost:3000/team-roster/" team-id)
                   :on-success [::load-roster]
                   :on-fail    [::roster-page-failed]}}))

(rf/reg-event-fx
  ::init-roster-page
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:roster :call-status :success?] true)
                     (assoc-in [:roster :selected-roster-action] "show-roster")
                     (assoc-in [:roster :call-status :message] "Processing...")
                     (assoc-in [:roster :call-status :on-click] nil)
                     (assoc-in [:roster :panel-visible :call-status] true))]
      {::get-teams {:method     :get
                    :url        (str "http://localhost:3000/teams")
                    :on-success [::load-teams]
                    :on-fail    [::roster-page-failed]}
       :dispatch   [::selected-team-changed (get-in upd-db [:team-info :id])]
       :db         upd-db})))