(ns rf-tennis-manager.roster.roster-main
  (:require [clojure.string :as s]
            [goog.string :as gs]
            [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.form-validator :as form-val]
            [rf-tennis-manager.roster.roster-events-add-player :as evt-add]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]
            [rf-tennis-manager.roster.roster-events-select :as evt-select]
            [rf-tennis-manager.roster.roster-events-update-player :as evt-upd]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.views-common :as layout]))


(def form-span 4)

(defn roster-row
  [list player]
  (conj list [:tr.text-left {:key (:id player) :onClick #(rf/dispatch [::evt-select/player-selected (:id player)])}
              [:td (:last_name player)]
              [:td (:first_name player)]
              [:td (:email player)]
              [:td (layout/format-phone-number (str (:phone_number player)))]
              [:td (case (:status player) "A" "Active" "S" "Sub" "I" "Inactive" "?")]]))

(defn show-roster
  []
  (fn []
    (let [roster @(rf/subscribe [::subs/roster-team-roster])
          team-info @(rf/subscribe [::subs/team-info])
          selected-team @(rf/subscribe [::subs/roster-selected-team])
          div-visible @(rf/subscribe [::subs/roster-panel-visible "roster"])]
      [:div {:className (if div-visible "div-panel-roster" "div-panel-hide")}
       [:table.match-info-table.table-sm
        [:tbody
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4 (:name selected-team) " Roster"]]]
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%"}}]
          [:td {:colSpan 2}
           [:table#sr-details.table.table-striped.table-sm
            [:thead.table-inverse
             [:tr.text-center
              [:td "Last Name"]
              [:td "First Name"]
              [:td "Email"]
              [:td "Phone Number"]
              [:td "Status"]]]
            [:tbody#sr-details-body
             (reduce #(roster-row %1 %2) () (reverse roster))]]]
          [:td {:style {:width "5%"}}]]
         (layout/hr-row form-span "90%")]]])))

(defn add-message-row
  []
  [:tr
   [:td]
   [:td.form-info {:colSpan 3} [:span.error "* "] [:span "Field is required.  Names must have at least 2 characters."]]
   [:td]])


(defn add-player
  []
  (let [title "Add Player"
        selected-team @(rf/subscribe [::subs/roster-selected-team])
        div-visible @(rf/subscribe [::subs/roster-panel-visible "add-player"])]
    [:div {:className (if div-visible "div-panel-roster" "div-panel-hide")}
     [:form#addplayerform.form-horizontal {:method "post" :action "/add-player"}
      [:table.match-info-table.table-sm
       [:tbody
        (layout/empty-row form-span)
        [:tr [:td.text-center {:colSpan form-span} [:h4 (str title " to " (:name selected-team) " Roster")]]]
        (layout/hr-row form-span "90%")
        (layout/add-form-input {:name "first_name" :maxLength "45" :size "45" :type "text"} "roster" "add-update" "first-name")
        (layout/add-form-input {:name "last_name" :maxLength "45" :size "45" :type "text"} "roster" "add-update" "last-name")
        (layout/add-form-input {:name "email" :maxLength "100" :size "45" :type "text"} "roster" "add-update" "email")
        (layout/add-form-input {:name "phone_number" :maxLength "10" :size "10" :type "text"} "roster" "add-update" "phone-number")
        (layout/add-form-select "status" "roster" "add-update" "status" {:Active "A" :Inactive "I" :Sub "S"})
        (layout/hr-row form-span "90%")
        (add-message-row)
        (layout/empty-row form-span)
        [:tr
         [:td.text-center {:colSpan form-span}
          [:table {:style {:width "100%"}}
           [:tbody
            [:tr
             [:td {:colSpan form-span :style {:width "50%"}} (layout/nbsp)]
             [:td.text-right
              [:button {:type "button" :onClick #(rf/dispatch [::form-val/validate-form [:roster :add-update :fields] [::evt-add/add-player-request] [::evt-common/roster-form-validation-error]])} title]]
             [:td.text-left
              [:button {:type "button" :onClick #(rf/dispatch [::evt-common/show-roster])} "Cancel"]]
             [:td {:style {:width "50%" :colSpan 2}} (layout/nbsp)]]]]]]
        [:tr.hidden-control
         [:td
          [:input.hidden-control {:name "team_id" :value (:id selected-team) :readOnly true}]]]
        (layout/empty-row form-span)]]]]))

(defn update-player-form
  []
  (fn []
    (let [title "Update Player"
          selected-player @(rf/subscribe [::subs/roster-selected-player])
          selected-team @(rf/subscribe [::subs/roster-selected-team])
          div-visible @(rf/subscribe [::subs/roster-panel-visible "update-player"])]
      [:div {:className (if div-visible "div-panel-roster" "div-panel-hide")}
       [:form#updateplayerform.form-horizontal {:method "post" :action "/update-player"}
        [:table.match-info-table.table-sm
         [:tbody
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span} [:h4 title]]]
          (layout/hr-row form-span "90%")
          (layout/add-form-input {:name "first_name" :maxLength "45" :size "45" :type "text"} "roster" "add-update" "first-name")
          (layout/add-form-input {:name "last_name" :maxLength "45" :size "45" :type "text"} "roster" "add-update" "last-name")
          (layout/add-form-input {:name "email" :maxLength "100" :size "45" :type "text"} "roster" "add-update" "email")
          (layout/add-form-input {:name "phone_number" :maxLength "10" :size "10" :type "text"} "roster" "add-update" "phone-number")
          (layout/add-form-select "status" "roster" "add-update" "status" {:Active "A" :Inactive "I" :Sub "S"})
          (layout/hr-row form-span "90%")
          (add-message-row)
          (layout/empty-row form-span)
          [:tr
           [:td.text-center {:colSpan form-span}
            [:table {:style {:width "100%"}}
             [:tbody
              [:tr
               [:td {:colSpan form-span :style {:width "50%"}} (layout/nbsp)]
               [:td.text-right
                [:button {:type "button" :onClick #(rf/dispatch [::form-val/validate-form [:roster :add-update :fields] [::evt-upd/update-player-request] [::evt-common/roster-form-validation-error]])} title]]
               [:td.text-left
                [:button {:type "button" :onClick #(rf/dispatch [::evt-common/show-roster])} "Cancel"]]
               [:td {:style {:width "50%"}} (layout/nbsp)]]]]]]
          [:tr.hidden-control
           [:td
            [:input.hidden-control {:name "team_id" :value (:id selected-team) :readOnly true}]
            [:input.hidden-control {:name "player_id" :value (:id selected-player) :readOnly true}]]]
          (layout/empty-row form-span)]]]])))

(defn get-team-list
  [teams selected-team]
  [:select {:name "team-id" :value selected-team :onChange #(rf/dispatch [::evt-common/selected-team-changed (-> % .-target .-value)])}
   (reduce (fn [list team]
             (conj list [:option {:value (:id team) :key (:id team)} (:name team)]))
           () (reverse (sort-by :name teams)))])

(defn roster-select-form
  []
  (fn []
    (let [title "Select roster function"
          teams @(rf/subscribe [::subs/team-list])
          roster-action @(rf/subscribe [::subs/roster-action])
          selected-team @(rf/subscribe [::subs/roster-selected-team])]
      [:div {:className "div-panel-show"}
       [:table.match-info-table.table.table-sm.roster-form
        [:thead
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4 "Select Roster Function"]]]
         (layout/hr-row form-span "90%")]
        [:tbody
         [:tr
          [:td {:style {:width "5%"}}]
          [:td "Team:"]
          [:td (get-team-list teams (:id selected-team))]
          [:td {:style {:width "50%"}}]]
         [:tr
          [:td]
          [:td "Roster Action:"]
          [:td
           [:select {:name     "roster-list" :value (if (s/blank? roster-action) "roster" roster-action)
                     :onChange #(rf/dispatch [::evt-select/roster-action-change (-> % .-target .-value)])}
            [:option {:value "add-player" :key "add-player"} "Add player"]
            [:option {:value "roster" :key "roster"} "Show roster"]]]
          [:td]]
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
    (let [call-status @(rf/subscribe [::subs/roster-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/roster-panel-visible "call-status"])]
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

