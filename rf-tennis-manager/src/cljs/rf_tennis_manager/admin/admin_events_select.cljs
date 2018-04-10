(ns rf-tennis-manager.admin.admin-events-select
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

