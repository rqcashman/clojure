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
    (println "c1p1 xx : " db)
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
    (println "test subxx: " db)
    (:test db)))

(re-frame/reg-sub
  ::team-info
  (fn [db]
    (println "team-info: " db)
    (:team-info db)))

(re-frame/reg-sub
  ::team-schedule
  (fn [db]
    (println "::team-schedule: " db)
    (get-in db [:matches :schedule])))

(re-frame/reg-sub
  ::match-info
  (fn [db]
    (println "match-info: " db)
    (get-in db [:matches :match-info])))

(re-frame/reg-sub
  ::matches_call_status
  (fn [db]
    (println "-----------------------------------------------::matches_call_status " db)
    (get-in db [:matches :call-status])))



