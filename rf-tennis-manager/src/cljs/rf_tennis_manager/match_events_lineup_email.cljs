(ns rf-tennis-manager.match-events-lineup-email
  (:require [re-frame.core :as rf]))

(rf/reg-event-fx
  ::show-email-lineup-form
  (fn [{:keys [db]} [_ match-id]]
    (println "::show-email-lineup-form: " match-id)))