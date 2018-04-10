(ns rf-tennis-manager.admin.admin-events-add-season
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [rf-tennis-manager.admin.admin-events-common :as evt-common]
            [clojure.string :as s]))

(rf/reg-fx
  ::add-season
  evt/send-post-request)

(rf/reg-event-fx
  ::update-date
  (fn [{:keys [db]} [_ date-key value]]
    (println "===update-date key: " date-key "value: " value)
    (let [date-arr (s/split value " ")
          selected-date (js/Date. (last date-arr) (second date-arr) (nth date-arr 2))]
      (println "date-arr: " date-arr)
      (println (str (last date-arr) "-" (second date-arr) "-" (nth date-arr 2)))
      (println "date: " selected-date)
      (println "xxxxx: " (.parse js/Date value))
      ;(println (str (.getFullYear @selected-date) "/" (.getMonth @selected-date) "/" (.getDate @selected-date)))
      {:db (assoc-in db [:admin :add-season (keyword date-key)] value)})))

(rf/reg-event-fx
  ::add-season-success
  (fn [{:keys [db]} [_ status]]
    (println "::add-season-success")
    {:db (-> db
             (assoc-in [:admin :call-status :success?] true)
             (assoc-in [:admin :panel-visible :call-status] true)
             (assoc-in [:admin :call-status :message] "Season added")
             (assoc-in [:admin :call-status :on-click] #(rf/dispatch [::evt-common/hide-call-status])))}))

(rf/reg-event-fx
  ::add-season-request
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:admin :call-status :success?] true)
                     (assoc-in [:admin :call-status :message] "Processing...")
                     (assoc-in [:admin :call-status :on-click] nil)
                     (assoc-in [:admin :panel-visible :call-status] true))]
      {::add-season {:method     :post
                     :url        "/add-season"
                     :on-success [::add-season-success]
                     :form-id    "#addseasonform"
                     :on-fail    [::evt-common/admin-page-failed]}
       :db          upd-db})))

