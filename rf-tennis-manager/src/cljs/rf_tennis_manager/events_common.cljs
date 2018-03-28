(ns rf-tennis-manager.events-common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs-http.client :as http]
            [enfocus.core :as ef]))

(rf/reg-cofx
  ::datetime
  (fn coeffect-handler
    [coeffect]
    (assoc coeffect :now (js/Date.))))

(rf/reg-event-fx
  ::session-timeout
  (fn [{:keys [db]} [_ call-response]]
    (let [upd-db (-> db
                     (assoc-in [:matches :call-status :success?] false)
                     (assoc-in [:matches :call-status :message] "Session timed out, please login again")
                     (assoc-in [:matches :panel-visible :call-status] true)
                     (assoc-in [:matches :call-status :on-click] nil))]
      {:db upd-db})))

(def session-expired-errno 400)
(defn send-get-request
  [request]
  (go
    (println "send-get-request: " request)
    (let [response (<! (http/get (:url request)))
          method (cond
                   (= (:status response) session-expired-errno) [::session-timeout]
                   (:success response) (:on-success request)
                   :else (:on-fail request))]
      (rf/dispatch (conj method response)))))

(defn send-post-request
  [request]
  (go
    (println "send-post-request: " request)
    (let [values (ef/from (:form-id request) (ef/read-form))
          response (<! (http/post (:url request) {:form-params values}))
          method (cond
                   (= (:status response) session-expired-errno) [::session-timeout]
                   (:success response) (:on-success request)
                   :else (:on-fail request))]
      (rf/dispatch (conj method response)))))
