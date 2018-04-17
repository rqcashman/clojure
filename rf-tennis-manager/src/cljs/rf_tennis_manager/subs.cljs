(ns rf-tennis-manager.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::name
  (fn [db]
    (:name db)))

(re-frame/reg-sub
  ::players
  (fn [db]
    (println "players ----" db)
    (:players db)))

(re-frame/reg-sub
  ::roster
  (fn [db]
    (get-in db [:matches :roster])))

(re-frame/reg-sub
  ::c1p1
  (fn [db]
    (println "c1p1 : " db)
    (get-in db [:matches :lineup :c1p1 :players])))

(re-frame/reg-sub
  ::c1p2
  (fn [db]
    (println "c1p2: " db)
    (get-in db [:matches :lineup :c1p2 :players])))
(re-frame/reg-sub
  ::c1p3
  (fn [db]
    (println "c1p2: " db)
    (get-in db [:matches :lineup :c1p3 :players])))

(re-frame/reg-sub
  ::test
  (fn [db]
    (:test db)))

(re-frame/reg-sub
  ::team-info
  (fn [db]
    (:team-info db)))

(re-frame/reg-sub
  ::team-schedule
  (fn [db]
    (get-in db [:matches :schedule])))

(re-frame/reg-sub
  ::match-info
  (fn [db]
    (get-in db [:matches :match-info])))

(re-frame/reg-sub
  ::match-lineup
  (fn [db]
    (get-in db [:matches :lineup])))

(re-frame/reg-sub
  ::match-player-list
  (fn [db]
    (get-in db [:matches :lineup-player-list])))

(re-frame/reg-sub
  ::matches_call_status
  (fn [db]
    (get-in db [:matches :call-status])))

(re-frame/reg-sub
  ::match_forfeits
  (fn [db]
    (get-in db [:matches :forfeits])))

(re-frame/reg-sub
  ::panel-visible
  (fn [db panel-id]
    (get-in db [:matches :panel-visible (keyword (second panel-id))])))

(re-frame/reg-sub
  ::roster-call-status
  (fn [db]
    (get-in db [:roster :call-status])))

(re-frame/reg-sub
  ::roster-panel-visible
  (fn [db panel-id]
    (get-in db [:roster :panel-visible (keyword (second panel-id))])))

(re-frame/reg-sub
  ::roster-action
  (fn [db]
    (get-in db [:roster :selected-roster-action])))

(re-frame/reg-sub
  ::roster-selected-team
  (fn [db]
    (get-in db [:roster :selected-team])))

(re-frame/reg-sub
  ::roster-selected-player
  (fn [db]
    (get-in db [:roster :selected-player])))

(re-frame/reg-sub
  ::roster-team-roster
  (fn [db]
    (get-in db [:roster :team-roster])))

(re-frame/reg-sub
  ::team-list
  (fn [db]
    (get-in db [:teams :list])))

(re-frame/reg-sub
  ::seasons
  (fn [db]
    (get-in db [:seasons])))

(re-frame/reg-sub
  ::clubs
  (fn [db]
    (get-in db [:clubs])))

(re-frame/reg-sub
  ::schedule-panel-visible
  (fn [db panel-id]
    (get-in db [:schedule :panel-visible (keyword (second panel-id))])))

(re-frame/reg-sub
  ::schedule-selected-team
  (fn [db]
    (get-in db [:schedule :selected-team])))

(re-frame/reg-sub
  ::schedule-selected-season
  (fn [db]
    (get-in db [:schedule :selected-season])))

(re-frame/reg-sub
  ::schedule-team-schedule
  (fn [db]
    (get-in db [:schedule :team-schedule])))

(re-frame/reg-sub
  ::schedule-call-status
  (fn [db]
    (get-in db [:schedule :call-status])))

(re-frame/reg-sub
  ::admin-call-status
  (fn [db]
    (get-in db [:admin :call-status])))

(re-frame/reg-sub
  ::admin-panel-visible
  (fn [db panel-id]
    (get-in db [:admin :panel-visible (keyword (second panel-id))])))

(re-frame/reg-sub
  ::admin-selected-function
  (fn [db panel-id]
    (get-in db [:admin :selected-function])))

(re-frame/reg-sub
  ::admin-selected-season
  (fn [db]
    (get-in db [:admin :selected-season])))

(re-frame/reg-sub
  ::admin-selected-club
  (fn [db]
    (get-in db [:admin :selected-club])))

(re-frame/reg-sub
  ::admin-add-season
  (fn [db key]
    (get-in db [:admin :add-season (keyword (second key))])))

(re-frame/reg-sub
  ::admin-form-data
  (fn [db key]
    (get-in db [:admin (keyword (second key)) :fields (keyword (nth key 2))])))

(re-frame/reg-sub
  ::roster-form-data
  (fn [db key]
    (get-in db [:roster (keyword (second key)) :fields (keyword (nth key 2))])))


(re-frame/reg-sub
  ::form-data
  (fn [db [_ tab-name form-name field-name]]
    (get-in db [(keyword tab-name) (keyword form-name) :fields (keyword field-name)])))

