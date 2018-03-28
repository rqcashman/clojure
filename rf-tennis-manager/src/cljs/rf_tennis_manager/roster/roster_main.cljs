(ns rf-tennis-manager.roster.roster-main
  (:require [clojure.string :as s]
            [goog.string :as gs]
            [re-frame.core :as rf]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.roster.roster-events-select :as evt-select]
            [rf-tennis-manager.roster.roster-events-common :as evt-common]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.views-common :as layout]))


(def form-span 4)
;(defn add-form-control
;  [label options]
;  [:tr
;   [:td {:style {:width "5%"}} "&nbsp;"]
;   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
;   [:td [:input.form-control-sm options]]
;   [:td {:style {:width "5%"}} "&nbsp;"]])

(defn roster-row
  [list player]
  (conj list [:tr.text-left {:key (:id player)}
              [:td (:last_name player)]
              [:td (:first_name player)]
              [:td (:email player)]
              [:td (:phone_number player)]
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
        (layout/empty-row form-span)
        [:tr [:td.text-center {:colSpan form-span :align "center"} [:h4 (:name selected-team) " Roster"]]]
        (layout/hr-row form-span "90%")
        [:tr
         [:td {:style {:width "5%"}}]
         [:td {:colSpan 2}
          [:table#sr-details.table.table-striped.table-sm
           [:thead.table-inverse
            [:tr {:align "left"}
             [:td "Last Name"]
             [:td "First Name"]
             [:td "Email"]
             [:td "Phone Number"]
             [:td "Status"]]]
           [:tbody#sr-details-body
            (reduce #(roster-row %1 %2) () roster)]]]
         [:td {:style {:width "5%"}}]]
        (layout/hr-row form-span "90%")]])))
;
;
;(defn add-player
;  []
;  (let [title "Add Player"]
;    [:form#addplayerform.form-horizontal {:method "post" :action "/add-player"}
;     [:table.match-info-table.table-sm
;      (layout/empty-row form-span)
;      [:tr [:td {:colSpan form-span :align "center"} [:h4 title]]]
;      (layout/hr-row form-span "90%")
;      (add-form-control "First name:" {:id "ap_first_name" :name "first_name" :maxlength 45 :size 45 :type= "text"})
;      (add-form-control "Last name:" {:id "ap_last_name" :name "last_name" :maxlength 45 :size 45 :type= "text"})
;      (add-form-control "Email:" {:id "ap_email" :name "email" :maxlength 45 :size 45 :type= "text"})
;      (add-form-control "Phone number:" {:id "ap_phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
;      [:tr
;       [:td {:style {:width "5%"}} "&nbsp;"]
;       [:td {:nowrap true} [:label.control-label {:for "up_status"} "Status"]]
;       [:td [:select#ap_status {:name "status"}
;             [:option {:value "A"} "Active"]
;             [:option {:value "I"} "Inactive"]
;             [:option {:value "S"} "Sub"]]]
;       [:td {:style {:width "5%"}} "&nbsp;"]]
;      (layout/hr-row form-span "90%")
;      (layout/empty-row form-span)
;      [:tr [:td {:colSpan form-span :align "center"}
;            [:button {:type "button" :onclick (str "return processRosterRequest('#addplayerform', '/add-player', '" title "')")} title]]]
;      [:tr.hidden-control [:td {:colSpan form-span :align "center"} [:input.hidden-control {:id "ap_team_id" :name "team_id"}]]]
;      (layout/empty-row form-span)]]))
;
;(defn update-player-form
;  []
;  (let [title "Update Player"]
;    [:form#updateplayerform.form-horizontal {:method "post" :action "/add-player"}
;     [:table.match-info-table.table-sm
;      (layout/empty-row form-span)
;      [:tr [:td {:colSpan form-span :align "center"} [:h4 title]]]
;      (layout/hr-row form-span "90%")
;      (add-form-control "First name:" {:id "up_first_name" :name "first_name" :maxlength 45 :size 45 :type= "text"})
;      (add-form-control "Last name:" {:id "up_last_name" :name "last_name" :maxlength 45 :size 45 :type= "text"})
;      (add-form-control "Email:" {:id "up_email" :name "email" :maxlength 45 :size 45 :type= "text"})
;      (add-form-control "Phone number:" {:id "up_phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
;      [:tr
;       [:td {:style {:width "5%"}} "&nbsp;"]
;       [:td {:nowrap true} [:label.control-label {:for "up_status"} "Status"]]
;       [:td [:select#up_status {:name "status"}
;             [:option {:value "A"} "Active"]
;             [:option {:value "I"} "Inactive"]
;             [:option {:value "S"} "Sub"]]]
;       [:td {:style {:width "5%"}} "&nbsp;"]]
;      (layout/hr-row form-span "90%")
;      (layout/empty-row form-span)
;      [:tr [:td {:colSpan form-span :align "center"}
;            [:table
;             [:td {:width "50%"} "&nbsp;"]
;             [:td {:align "right" :nowrap "true"}
;              [:button {:type "button" :onclick (str "return processRosterRequest('#updateplayerform', '/update-player', '" title "')")} title]]
;             [:td {:align "left" :nowrap "true"}
;              [:button {:type "button" :onclick "change_roster_form('show-roster');"} "Cancel"]]
;             [:td {:width "5%"} "&nbsp;"]]]]
;      [:tr.hidden-control [:td {:colSpan form-span :align "center"} [:input.hidden-control {:id "up_team_id" :name "team_id"}] [:input.hidden-control {:id "up_player_id" :name "player_id"}]]]
;      (layout/empty-row form-span)]]))

(defn get-team-list
  [teams selected-team]
  [:select {:name "team-id" :value selected-team :onChange #(rf/dispatch [::evt-select/selected-team-changed (-> % .-target .-value)])}
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
          [:td (get-team-list teams  (:id selected-team))]
          [:td {:style {:width "50%"}}]]
         [:tr
          [:td]
          [:td "Roster Action:"]
          [:td
           [:select {:name "roster-list" :value roster-action :onChange #(rf/dispatch [::evt-select/roster-action-change (-> % .-target .-value)])}
            [:option {:value "add-player" :key "add-player"} "Add player"]
            [:option {:value "show-roster" :key "show-roster"} "Show roster"]]]
          [:td]]
         (layout/hr-row form-span "90%")
         (layout/empty-row form-span)]]
       [:br] [:hr] [:br]])))

(defn call-status
  []
  (fn []
    (let [call-status @(rf/subscribe [::subs/roster-call-status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/roster-panel-visible "call-status"])
          click-fn (if-not (nil? (:on-click call-status)) (:on-click call-status) #(rf/dispatch [::evt-common/hide-call-status]))]
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
         (if-not (nil? (:on-click call-status))
           [:tr [:td.text-center {:colSpan form-span}
                 [:button#ma-status-btn {:value "ok" :onClick click-fn} "OK"]]])
         (layout/empty-row form-span)]]])))

