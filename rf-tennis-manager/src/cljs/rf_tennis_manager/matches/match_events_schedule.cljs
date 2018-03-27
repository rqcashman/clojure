(ns rf-tennis-manager.matches.match-events-schedule
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.matches.match-events-common :as evt-common]
            [cljs-http.client :as http]))

(rf/reg-event-fx
  ::team-info
  (fn [{:keys [db]} [_ team-info]]
    {:db (assoc db :team-info (:body team-info))}))

(rf/reg-fx
  ::get-team-info
  evt-common/send-get-request)

(rf/reg-fx
  ::get-schedule
  evt-common/send-get-request)

(rf/reg-event-fx
  ::init-schedule-page-failed
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:matches :call-status :success?] false)
             (assoc-in [:matches :call-status :message] "Call to get data failed")
             (assoc-in [:matches :panel-visible :call-status] true)
             (assoc-in [:matches :call-status :on-click] #(re-frame.core/dispatch [::evt-common/show-schedule])))}))

(rf/reg-event-fx
  ::schedule
  (fn [{:keys [db]} [_ call-response]]
    (if (get-in db [:matches :call-status :success?])
      {:db (-> db
               (assoc-in [:matches :call-status :message] "Success")
               (assoc-in [:matches :call-status :success?] true)
               (assoc-in [:matches :schedule] (:body call-response))
               (evt-common/show-div "schedule"))})))

(rf/reg-event-fx
  ::init-schedule-page
  (fn [{:keys [db]} [_ match-id]]
    (println "====== ::init-schedule-page")
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] true)
                     (assoc-in [:matches :call-status :message] "Processing...")
                     (assoc-in [:matches :call-status :on-click] nil)
                     (assoc-in [:matches :panel-visible :call-status] true))]
      {::get-team-info       {:method     :get
                              :url        (str "http://localhost:3000/team-info")
                              :on-success [::team-info]
                              :on-fail    [::init-schedule-page-failed]}
       ::get-schedule {:method     :get
                              :url        (str "http://localhost:3000/team-schedule-status")
                              :on-success [::schedule]
                              :on-fail    [::init-schedule-page-failed]}
       :db                   upd-db})))