(ns rf-tennis-manager.schedule.schedule-main
  (:require [clojure.string :as s]
            [goog.string :as gs]
            [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.schedule.schedule-events :as event]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.views-common :as layout]))

(def form-span 4)

(defn add-call-status-btn
  [call-status-fn]
  (if-not (nil? call-status-fn)
    [:tr [:td.text-center {:colSpan form-span} [:button {:value "ok" :onClick call-status-fn} "OK"]]]))

(defn add-schedule-rows
  [team-schedule team-id]
  (reduce (fn [list sched-row]
            (conj list [:tr {:key (:match_id sched-row)}
                        [:td (:match_date sched-row)]
                        [:td (:match_time sched-row)]
                        [:td (if (= team-id (:home_team_id sched-row)) {:className "schedule-selected-team"}) (:home_team sched-row)]
                        [:td (if (= team-id (:away_team_id sched-row)) {:className "schedule-selected-team"}) (:away_team sched-row)]
                        [:td (:home_team_points sched-row)]
                        [:td (:away_team_points sched-row)]]))
          () team-schedule))

(defn schedule
  []
  (fn []
    (let [team @(rf/subscribe [::subs/schedule-selected-team])
          season @(rf/subscribe [::subs/schedule-selected-season])
          team-schedule @(rf/subscribe [::subs/schedule-team-schedule])
          div-visible @(rf/subscribe [::subs/schedule-panel-visible "schedule"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:table.team-schedule.table-sm-table.table-sm {:style {:width "70%"}}
        [:tbody
         (layout/hr-row 7 "100%")
         [:tr.text-left
          [:td {:style {:width "2%"}} (layout/nbsp)]
          [:td "Team:"]
          [:td {:style {:width "2%"}} (layout/nbsp)]
          [:td.bold-text (:name team)]
          [:td (layout/nbsp)]
          [:td (layout/nbsp)]
          [:td {:style {:width "40%"}} (layout/nbsp)]]
         [:tr.text-left
          [:td (layout/nbsp)]
          [:td "Season:"]
          [:td (layout/nbsp)]
          [:td.bold-text (:name season)]
          [:td {:style {:width "2%"}} (layout/nbsp)]
          [:td.text-left (str (:start_date season) " - " (:end_date season))]
          [:td (layout/nbsp)]]
         (layout/hr-row 7 "100%")]]
       [:table.match-info-table.team-schedule.table-striped.table-sm {:style {:width "70%"}}
        [:thead.table-inverse
         [:tr
          [:td "Date"]
          [:td "Time"]
          [:td "Home Team"]
          [:td "Away Team"]
          [:td "Home Points"]
          [:td "Away Points"]]]
        [:tbody
         (add-schedule-rows (reverse team-schedule) (:id team))

         [:tr
          [:td.text-center {:colSpan 6}
           [:hr {:style {:width "90%"}}]
           [:br]
           [:button {:type "button" :onClick #(rf/dispatch [::event/show-select-form])} "Return to select form"]
           [:br] (layout/nbsp)]]]]])))

(defn get-team-list
  [teams selected-team]
  [:select {:name "team-id" :value (if-not (s/blank? selected-team) selected-team "") :onChange #(rf/dispatch [::event/selected-team-changed (-> % .-target .-value)])}
   (reduce (fn [list team]
             (conj list [:option {:value (:id team) :key (:id team)} (:name team)]))
           () (reverse (sort-by :name teams)))])

(defn get-season-list
  [seasons selected-season]
  [:select {:name "team-id" :value (if-not (s/blank? selected-season) selected-season "") :onChange #(rf/dispatch [::event/selected-season-changed (-> % .-target .-value)])}
   (reduce (fn [list seasons]
             (conj list [:option {:value (:id seasons) :key (:id seasons)} (:name seasons)]))
           () (reverse seasons))])

(defn schedule-select-form
  []
  (fn []
    (let [div-visible @(rf/subscribe [::subs/schedule-panel-visible "select-form"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:table.match-info-table.table-sm.team-schedule
        [:thead
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4 "Select season and club"]]]
         (layout/hr-row form-span "90%")]
        [:tbody
         (let [teams @(rf/subscribe [::subs/team-list])
               selected-team @(rf/subscribe [::subs/schedule-selected-team])]
           [:tr
            [:td {:style {:width "5%"}}]
            [:td "Team:"]
            [:td (get-team-list teams (:id selected-team))]
            [:td {:style {:width "50%"}}]])
         (let [seasons @(rf/subscribe [::subs/seasons])
               selected-season @(rf/subscribe [::subs/schedule-selected-season])]
           [:tr
            [:td {:style {:width "5%"}}]
            [:td "Season:"]
            [:td (get-season-list seasons (:id selected-season))]
            [:td {:style {:width "50%"}}]])
         (layout/hr-row form-span "90%")
         [:tr
          [:td.text-center {:colSpan form-span}
           [:button {:type "button" :onClick #(rf/dispatch [::event/show-schedule])} "Show schedule"]]]
         (layout/empty-row form-span)]]])))

(defn call-status
  []
  (fn []
    (let [call-status @(rf/subscribe [::subs/schedule-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/schedule-panel-visible "call-status"])]
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
