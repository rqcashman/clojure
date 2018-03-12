(ns rf-tennis-manager.events
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.subs :as subs]))

(rf/reg-cofx
  :team-id
  (fn coeffect-handler
    [coeffect]
    (go
      (let [response (<! (http/get (str "team-id")))]
        (assoc coeffect :team_id (get-in response [:body :team_id]))))))

(rf/reg-event-db
  ::initialize-default-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-db
  ::initialize-tabbed-db
  (fn [_ _]
    db/tabbed-db))

(rf/reg-event-fx
  ::team-info
  (fn [{:keys [db]} [_ team-info]]
      {:db (assoc db :team-info team-info)}))

(rf/reg-event-fx
  ::main-btn-click
  (fn [{:keys [db]} [_ msg]]
    (println (count (:players db)))
    (let [new-state (-> db
                        (assoc :name "Rick")
                        (assoc :type "Admin")
                        (assoc-in [:players :13] {:last "Allen" :first "Perry" :id 13}))]
      {:db new-state})))

(rf/reg-event-fx
  ::list-changed
  (fn [{:keys [db]} [evt list]]
    (println "list changedzzzzz: " list)
    (let [el (.getElementById js/document (str list "-list"))
          other-list-key (if (= list "c1p1") "c1p2" "c1p1")
          current (get-in db [:matches :lineup (keyword list) :selected :current])
          selected (get-in db [:matches :lineup (keyword list) :players (keyword (str el.value))])
          upd-db (->
                   (update-in db [:matches :lineup (keyword other-list-key) :players] dissoc (keyword (str (:id selected))))
                   (assoc-in [:matches :lineup (keyword list) :selected :previous] current)
                   (assoc-in [:matches :lineup (keyword list) :selected :current] selected))]
      (println "======================================")
      (println "db " db)
      (println "current: " current " sel " selected " other list " other-list-key)
      (if (pos? (count current))
        {:db (assoc-in upd-db [:matches :lineup (keyword other-list-key) :players (keyword (str (:id current)))] current)}
        {:db upd-db}))))







