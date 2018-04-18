(ns rf-tennis-manager.roster.roster-events-common
  (:require [re-frame.core :as rf]
            [rf-tennis-manager.events-common :as evt]
            [clojure.string :as s]))

(defn show-div
  [db show-div-id]
  (-> (reduce (fn [upd-db key]
                (assoc-in upd-db [:roster :panel-visible key] false))
              db (keys (get-in db [:roster :panel-visible])))
      (assoc-in [:roster :panel-visible (keyword show-div-id)] true)))

(rf/reg-event-fx
  ::show-roster
  (fn [{:keys [db]} [_]]
    {:db (-> db
             (assoc-in [:roster :selected-roster-action] "roster")
             (show-div "roster"))}))

(defn reset-form
  ([in-db] (reset-form in-db nil))
  ([in-db player]
   (let [upd-db (reduce (fn [reset-db fld]
                          (if (= (:type (val fld)) "select")
                            (assoc-in reset-db [:roster :add-update :fields (key fld) :value] "A")
                            (-> reset-db
                                (assoc-in [:roster :add-update :fields (key fld) :value] "")
                                (assoc-in [:roster :add-update :fields (key fld) :error-msg] ""))))
                        in-db (get-in in-db [:roster :add-update :fields]))]
     (if (nil? player)
       upd-db
       (-> upd-db
           (assoc-in [:roster :add-update :fields :first-name :value] (:first_name player))
           (assoc-in [:roster :add-update :fields :last-name :value] (:last_name player))
           (assoc-in [:roster :add-update :fields :email :value] (:email player))
           (assoc-in [:roster :add-update :fields :phone-number :value] (:phone_number player))
           (assoc-in [:roster :add-update :fields :status :value] (:status player)))))))

(rf/reg-event-fx
  ::show-player-update-form
  (fn [{:keys [db]} [_]]
    {:db (-> db
             (show-div "update-player"))}))

(rf/reg-event-fx
  ::show-select
  (fn [{:keys [db]} [_]]
    {:db (-> db
             (show-div "select-form")
             (reset-form))}))

(rf/reg-event-fx
  ::hide-call-status
  (fn [{:keys [db]} [_]]
    {:db (assoc-in db [:roster :panel-visible :call-status] false)}))

(rf/reg-event-fx
  ::roster-form-validation-error
  (fn [{:keys [db]} [_]]
    {:db (-> db
             (assoc-in [:roster :call-status :success?] false)
             (assoc-in [:roster :call-status :message] "Fix validation errors before submitting")
             (assoc-in [:roster :panel-visible :call-status] true)
             (assoc-in [:roster :call-status :on-click] #(re-frame.core/dispatch [::hide-call-status])))}))

(rf/reg-event-fx
  ::roster-page-failed
  (fn [{:keys [db]} [_ response]]
    (let [msg (if-not (s/blank? (get-in response [:body :msg])) (get-in response [:body :msg]) "Call to get data failed")]
      {:db (-> db
               (assoc-in [:roster :call-status :success?] false)
               (assoc-in [:roster :call-status :message] msg)
               (assoc-in [:roster :panel-visible :call-status] true)
               (assoc-in [:roster :call-status :on-click] #(re-frame.core/dispatch [::hide-call-status])))})))

(rf/reg-event-fx
  ::load-roster
  (fn [{:keys [db]} [_ status]]
    {:db (-> db
             (assoc-in [:roster :team-roster] (:body status))
             (assoc-in [:roster :call-status :message] "Success")
             (assoc-in [:roster :panel-visible :call-status] false))}))

(rf/reg-fx
  ::get-roster
  evt/send-get-request)

(rf/reg-event-fx
  ::selected-team-changed
  (fn [{:keys [db]} [_ team-id]]
    {:db          (-> db
                      (assoc-in [:roster :selected-team] (first (filter #(identical? (str (:id %)) (str team-id)) (get-in db [:teams :list]))))
                      (assoc-in [:roster :selected-roster-action] "roster")
                      (show-div "roster"))
     ::get-roster {:method     :get
                   :url        (str "/team-roster/" team-id)
                   :on-success [::load-roster]
                   :on-fail    [::roster-page-failed]}}))
