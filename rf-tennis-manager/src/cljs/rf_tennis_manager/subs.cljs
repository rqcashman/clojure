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



