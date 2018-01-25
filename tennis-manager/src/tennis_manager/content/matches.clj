(ns tennis-manager.content.matches
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.data-handler :as db]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

(def form-span 4)

(defn add-div
  [func-map]
  [:div.matches-action {:id (:id func-map)} (:content func-map)]
  )

(defn add-form-control
  [label options]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
   [:td [:input.form-control-sm options]]
   [:td {:width "5%"} "&nbsp;"]])

(defn schedule-form
  "docstring"
  []
  )

(defn availability-email-form
  "docstring"
  []
  )

(defn lineup-form
  "docstring"
  []
  )
(defn select-form
  [admin-actions]
  (println "in select-form")
  (list
    [:div#admin-form-div.panel.panel-default
     [:table.table.table-sm.admin-form
      [:thead
       (layout/empty-row 3)
       [:tr [:td {:colspan 3 :align "center"} [:h4 "Select Admin Function"]]]
       (layout/hr-row 3 "90%")]
      [:tbody
       (layout/add-select #(layout/actions admin-actions) layout/option "admin-list" "Admin Action:" 1 (str "change_form(this.value);"))
       (layout/hr-row 3 "90%")
       (layout/empty-row 3)]]]
    [:br]
    [:hr]
    [:br])
  )
(defn matches
  "docstring"
  []
  (let [match-actions
        [{:id "show-schedule" :name "Match Schedule" :content (schedule-form)}
         {:id "send-availability-email" :name "Show match" :content (availability-email-form)}
         {:id "set-lineup" :name "Show match" :content (lineup-form)}
         ]]
    (list
      (select-form match-actions)
      (map add-div match-actions)
      (add-div {:id "status-panel" :name "Status" :content (layout/status-content form-span "change_form(current_form_id);")}))))
