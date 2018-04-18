(ns rf-tennis-manager.admin.admin-main
  (:require [clojure.string :as s]
            [goog.string :as gs]
            [re-frame.core :as rf]
            [cljs-pikaday.reagent :as dp]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.form-validator :as form-val]
            [rf-tennis-manager.admin.admin-events-add-club :as evt-club]
            [rf-tennis-manager.admin.admin-events-add-season :as evt-season]
            [rf-tennis-manager.admin.admin-events-add-team :as evt-team]
            [rf-tennis-manager.admin.admin-events-common :as evt-common]
            [rf-tennis-manager.admin.admin-events-load-schedule :as evt-load-sched]
            [rf-tennis-manager.admin.admin-events-select :as evt-select]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.views-common :as layout]))

(def form-span 4)
(defonce season-start-date (atom (js/Date.)))
(defonce season-end-date (atom (js/Date. (.getFullYear @season-start-date) (.getMonth @season-start-date) (+ (.getDate @season-start-date) 35))))

(defn add-form-control
  [label options]
  [:tr
   [:td {:style {:width "5%"}} (layout/nbsp)]
   [:td label]
   [:td [:input options]]
   [:td {:style {:width "5%"}} (layout/nbsp)]])

(defn add-date-control
  [label options]
  [:tr
   [:td {:style {:width "5%"}} (layout/nbsp)]
   [:td label]
   [:td [dp/date-selector options]]
   [:td {:style {:width "5%"}} (layout/nbsp)]])

(defn club-content
  []
  (fn []
    (let [title "Add Club"
          call-status @(rf/subscribe [::subs/admin-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/admin-panel-visible "add-club"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#addclubform.form-horizontal {:method "post" :action "/add-club"}
        [:table.match-info-table.table-sm.admin-form
         [:tbody
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span} [:h4 title]]]
          (layout/hr-row form-span "90%")
          (layout/add-form-input {:name "club_name" :maxLength "45" :size "45" :type "text"} "admin" "add-club" "club-name")
          (layout/add-form-input {:name "street" :maxLength "100" :size "60" :type "text"} "admin" "add-club" "street")
          (layout/add-form-input {:name "city" :maxLength "45" :size "45" :type "text"} "admin" "add-club" "city")
          (layout/add-form-select "club_state" "admin" "add-club" "state" {:Ohio "OH" :Kentucky "KY"})
          (layout/add-form-input {:name "zip_code" :maxLength "5" :size "5" :type "text"} "admin" "add-club" "zip-code")
          (layout/add-form-input {:name "phone_number" :size "10" :maxLength "10" :type "text"} "admin" "add-club" "phone-number")
          (layout/hr-row form-span "90%")
          (layout/required-message form-span)
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:button {:type "button" :onClick #(rf/dispatch [::form-val/validate-form [:admin :add-club :fields] [::evt-club/add-club-request] [::evt-common/admin-form-validation-error]])} title]]]
          (layout/empty-row form-span)]]]])))

(defn get-club-list
  [clubs selected-club]
  [:select {:name "club_id" :value (if-not (s/blank? selected-club) selected-club "") :onChange #(rf/dispatch [::evt-team/selected-club-changed (-> % .-target .-value)])}
   (reduce (fn [list clubs]
             (conj list [:option {:value (:id clubs) :key (:id clubs)} (:name clubs)]))
           () (reverse clubs))])

(defn get-club-hash
  [clubs]
  (reduce (fn [club-hash club]
            (into club-hash {(keyword (:name club)) (:id club)}))
          {} clubs))

(defn team-content
  []
  (fn []
    (let [title "Add Team"
          call-status @(rf/subscribe [::subs/admin-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/admin-panel-visible "add-team"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#addteamform.form-horizontal {:method "post" :action "/add-team"}
        [:table.match-info-table.table-sm.admin-form
         [:tbody
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span} [:h4 title]]]
          (layout/hr-row form-span "90%")
          (let [clubs @(rf/subscribe [::subs/clubs])
                selected-club @(rf/subscribe [::subs/admin-selected-club])
                club-hash (get-club-hash clubs)]
            (if-not (empty? club-hash)
              (layout/add-form-select "club_id" "admin" "add-team" "club" club-hash)))
          (layout/add-form-input {:name "team_name" :maxLength 45 :size 45 :type "text"} "admin" "add-team" "team-name")
          (layout/add-form-input {:name "sched_abbrev" :maxLength 10 :size 10 :type "text"} "admin" "add-team" "sched-abbrev")
          (layout/hr-row form-span "90%")
          (layout/required-message form-span)
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:button {:type "button" :onClick #(rf/dispatch [::form-val/validate-form [:admin :add-team :fields] [::evt-team/add-team-request] [::evt-common/admin-form-validation-error]])} title]]]
          (layout/empty-row form-span)]]]])))

(defn season-content
  []
  (fn []
    (let [title "Add Season"
          call-status @(rf/subscribe [::subs/admin-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/admin-panel-visible "add-season"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#addseasonform.form-horizontal {:method "post" :action "/add-season"}
        [:table.match-info-table.table-sm.admin-form
         [:tbody
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span} [:h4 title]]]
          (layout/hr-row form-span "90%")
          (add-form-control "Season name:" {:id :season :name "season" :maxLength 45 :size 45 :type "text"})
          (add-date-control "Season start date" {:date-atom season-start-date :input-attrs {:onChange #(rf/dispatch [::evt-season/update-date "start-date" (-> % .-target .-value)])}})
          (add-date-control "Season end date" {:date-atom season-end-date :input-attrs {:onSelect #(rf/dispatch [::evt-season/update-date "end-date" (-> % .-target .-value)])}})
          (layout/hr-row form-span "90%")
          (layout/required-message form-span)
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:button {:type "button" :onClick #(rf/dispatch [::evt-season/add-season-request])} title]]]
          (layout/empty-row form-span)]]
        (let [start-date @(rf/subscribe [::subs/admin-add-season "start-date"])]
          [:input.hidden-control {:name "start_date" :value (if-not (nil? start-date) start-date "") :readOnly true}])
        (let [end-date @(rf/subscribe [::subs/admin-add-season "end-date"])]
          [:input.hidden-control {:name "end_date" :value (if-not (nil? end-date) end-date "") :readOnly true}])]])))

(defn get-season-list
  [seasons selected-season]
  [:select {:name "season-id" :value (if-not (s/blank? selected-season) selected-season "") :onChange #(rf/dispatch [::evt-load-sched/selected-season-changed (-> % .-target .-value)])}
   (reduce (fn [list seasons]
             (conj list [:option {:value (:id seasons) :key (:id seasons)} (:name seasons)]))
           () (reverse seasons))])

(defn load-schedule
  []
  (fn []
    (let [title "Load Schedule"
          call-status @(rf/subscribe [::subs/admin-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/admin-panel-visible "load-schedule"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#loadscheduleform.form-horizontal {:method "post" :action "/load-schedule"}
        [:table.match-info-table.table-sm.admin-form
         [:tbody
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span} [:h4 title]]]
          (layout/hr-row form-span "90%")
          (let [seasons @(rf/subscribe [::subs/seasons])
                selected-season @(rf/subscribe [::subs/admin-selected-season])]
            [:tr
             [:td {:style {:width "5%"}}]
             [:td "Season:"]
             [:td (get-season-list seasons (:id selected-season))]
             [:td {:style {:width "50%"}}]])
          [:tr [:td.text-center {:colSpan form-span}
                [:textarea {:name "schedule" :rows 30 :cols 50}]]]
          (layout/hr-row form-span "90%")
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:button {:type "button" :onClick #(rf/dispatch [::evt-load-sched/load-schedule])} title]]]
          (layout/empty-row form-span)]]]])))

(defn admin-select-form
  [admin-actions]
  (fn []
    (let [title "Select Admin Function"
          selected-func @(rf/subscribe [::subs/admin-selected-function])]
      [:div {:className "div-panel-show"}
       [:table.match-info-table.table-sm.admin-form
        [:tbody
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4 title]]]
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%"}} (layout/nbsp)]
          [:td "Admin Action:"]
          [:td [:select {:name     "admin-list" :value (if-not (nil? selected-func) selected-func "add-club")
                         :onChange #(rf/dispatch [::evt-select/function-changed (-> % .-target .-value)])}
                [:option {:value "add-club"} "Add Club"]
                [:option {:value "add-season"} "Add Season"]
                [:option {:value "add-team"} "Add Team"]
                [:option {:value "load-schedule"} "Load Schedule"]]]
          [:td {:style {:width "60%"}} (layout/nbsp)]]
         (layout/hr-row form-span "90%")
         (layout/empty-row form-span)]]
       [:br] [:hr] [:br]])))

(defn add-call-status-btn
  [call-status-fn]
  (if-not (nil? call-status-fn)
    [:tr [:td.text-center {:colSpan form-span} [:button {:value "ok" :onClick call-status-fn} "OK"]]]))

(defn call-status
  []
  (fn []
    (let [call-status @(rf/subscribe [::subs/admin-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/admin-panel-visible "call-status"])]
      [:div {:className (if div-visible "div-panel-call-status" "div-panel-hide")}
       [:table.main-table.table-sm.call-status
        [:tbody
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4#ma-status-title "Getting data"]]]
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%"}} (layout/nbsp)]
          [:td "Status:"]
          [:td.text-left {:className cssClass} (:message call-status)]
          [:td {:style {:width "5%"}} (layout/nbsp)]]
         (layout/hr-row form-span "90%")
         (layout/empty-row form-span)
         (add-call-status-btn (:on-click call-status))
         (layout/empty-row form-span)]]])))



