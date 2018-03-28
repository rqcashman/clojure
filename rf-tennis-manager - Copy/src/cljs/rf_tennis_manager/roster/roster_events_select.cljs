(ns rf-tennis-manager.roster.roster-events-select
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]))

(rf/reg-fx
  ::get-teams
  evt/send-get-request)

(rf/reg-event-fx
  ::show-roster
  (fn [{:keys [db]} [_ status]]
    (println "::show-roster: " status)
    ))

(rf/reg-event-fx
  ::init-roster-page-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :call-status :success?] false)
             (assoc-in [:roster :call-status :message] "Call to get data failed")
             (assoc-in [:roster :panel-visible :call-status] true)
             (assoc-in [:roster :call-status :on-click] #(re-frame.core/dispatch [::show-roster])))}))

(rf/reg-event-fx
  ::team-selected
  (fn [{:keys [db]} [team-id]]
    (println "team selected: " team-id)
    ))

(rf/reg-event-fx
  ::init-roster-page
  (fn [{:keys [db]} [_]]
    (println "====== ::init-roster-page")
    (let [upd-db (-> db
                     (assoc-in [:roster :call-status :success?] true)
                     (assoc-in [:roster :call-status :message] "Processing...")
                     (assoc-in [:roster :call-status :on-click] nil)
                     (assoc-in [:roster :panel-visible :call-status] true))]
      {::get-teams {:method     :get
                    :url        (str "http://localhost:3000/teams")
                    :on-success [::load-teams]
                    :on-fail    [::init-roster-page-failed]}
       :db         upd-db})))