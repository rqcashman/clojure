(ns rf-tennis-manager.admin.admin-events-common
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [clojure.string :as s]))

(defonce season-start-date (atom (js/Date.)))
(defonce season-end-date (atom (js/Date. (.getFullYear @season-start-date) (.getMonth @season-start-date) (+ (.getDate @season-start-date) 35))))

(defn show-div
  [db show-div-id]
  (-> (reduce (fn [upd-db key]
                (assoc-in upd-db [:admin :panel-visible key] false))
              db (keys (get-in db [:admin :panel-visible])))
      (assoc-in [:admin :panel-visible (keyword show-div-id)] true)))

(def skip-reset-types ["date" "select"])
(defn reset-form
  [in-db form-name]
  (reduce (fn [upd-db fld]
            (if (not-any? #(= (:type (val fld)) %) skip-reset-types)
              (-> upd-db
                  (assoc-in [:admin (keyword form-name) :fields (key fld) :value] "")
                  (assoc-in [:admin (keyword form-name) :fields (key fld) :error-msg] ""))
              upd-db))
          in-db (get-in in-db [:admin (keyword form-name) :fields])))

(rf/reg-event-fx
  ::hide-call-status
  (fn [{:keys [db]} [_]]
    {:db (assoc-in db [:admin :panel-visible :call-status] false)}))

(rf/reg-fx
  ::get-clubs
  evt/send-get-request)

(rf/reg-fx
  ::get-seasons
  evt/send-get-request)

(rf/reg-event-fx
  ::admin-page-failed
  (fn [{:keys [db]} [_ response]]
    (let [msg (if-not (s/blank? (get-in response [:body :msg])) (get-in response [:body :msg]) "Call to get data failed")]
      {:db (-> db
               (assoc-in [:admin :call-status :success?] false)
               (assoc-in [:admin :call-status :message] msg)
               (assoc-in [:admin :panel-visible :call-status] true)
               (assoc-in [:admin :call-status :on-click] #(re-frame.core/dispatch [::hide-call-status])))})))

(rf/reg-event-fx
  ::admin-form-validation-error
  (fn [{:keys [db]} [_]]
    {:db (-> db
             (assoc-in [:admin :call-status :success?] false)
             (assoc-in [:admin :call-status :message] "Fix validation errors before submitting")
             (assoc-in [:admin :panel-visible :call-status] true)
             (assoc-in [:admin :call-status :on-click] #(re-frame.core/dispatch [::hide-call-status])))}))

(rf/reg-event-fx
  ::load-seasons
  (fn [{:keys [db]} [_ response]]
    {:db (-> db
             (assoc :seasons (:body response))
             (assoc-in [:admin :selected-season] (first (:body response))))}))

(rf/reg-event-fx
  ::load-clubs
  (fn [{:keys [db]} [_ response]]
    {:db (-> db
             (assoc-in [:clubs] (:body response))
             (assoc-in [:admin :call-status :message] "Success")
             (assoc-in [:admin :panel-visible :call-status] false)
             (assoc-in [:admin :panel-visible :select-form] true)
             (assoc-in [:admin :add-team :fields :club :value] (-> response :body first :id)))}))

(rf/reg-event-fx
  ::init-admin-page
  (fn [{:keys [db]} [_]]
    (let [upd-db (-> db
                     (assoc-in [:admin :add-season :fields :start-date :value] @season-start-date)
                     (assoc-in [:admin :add-season :fields :end-date :value] @season-end-date)
                     (assoc-in [:admin :call-status :success?] true)
                     (assoc-in [:admin :call-status :message] "Processing...")
                     (assoc-in [:admin :call-status :on-click] nil)
                     (show-div "add-club")
                     (assoc-in [:admin :panel-visible :call-status] true))]
      {::get-clubs   {:method     :get
                      :url        "/clubs"
                      :on-success [::load-clubs]
                      :on-fail    [::admin-page-failed]}
       ::get-seasons {:method     :get
                      :url        "/seasons"
                      :on-success [::load-seasons]
                      :on-fail    [::admin-page-failed]}
       :db           upd-db})))
