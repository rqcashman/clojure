(ns rf-tennis-manager.admin.admin-events-add-club
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.admin.admin-events-common :as evt-common]
            [clojure.string :as s]))


(rf/reg-event-fx
  ::function-changed
  (fn [{:keys [db]} [_ value]]
    (println "::function-changed value: " value)
    {:db (-> db
             (evt-common/show-div value)
             (assoc-in [:admin :selected-function] value))}))

(rf/reg-event-fx
  ::state-changed
  (fn [{:keys [db]} [_ state]]
    (println "::state-changed state: " state)))

(rf/reg-event-fx
  ::phone-number-changed
  (fn [{:keys [db]} [_ value]]
    (println "::phone-number-changed value: " value)))

(rf/reg-event-fx
  ::zip-code-changed
  (fn [{:keys [db]} [_ value]]
    (println "::zip-code-changed value: " value)))

(rf/reg-event-fx
  ::add-club
  (fn [{:keys [db]} [_ request]]
    (println "::add-club: " request)))

