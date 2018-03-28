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
(defn add-form-control
  [label options]
  [:tr
   [:td {:style {:width "5%"}} "&nbsp;"]
   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
   [:td [:input.form-control-sm options]]
   [:td {:style {:width "5%"}} "&nbsp;"]])

(defn show-roster
  []
  [:table.match-info-table.table-sm
   (layout/empty-row form-span)
   [:tr [:td {:colSpan form-span :align "center"} [:h4 [:span#sr_team_name "Team"] " Roster"]]]
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
       (layout/empty-row 5)]]]
    [:td {:style {:width "5%"}}]]
   (layout/hr-row form-span "90%")])


(defn add-player
  []
  (let [title "Add Player"]
    [:form#addplayerform.form-horizontal {:method "post" :action "/add-player"}
     [:table.match-info-table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colSpan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (add-form-control "First name:" {:id "ap_first_name" :name "first_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Last name:" {:id "ap_last_name" :name "last_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Email:" {:id "ap_email" :name "email" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Phone number:" {:id "ap_phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
      [:tr
       [:td {:style {:width "5%"}} "&nbsp;"]
       [:td {:nowrap true} [:label.control-label {:for "up_status"} "Status"]]
       [:td [:select#ap_status {:name "status"}
             [:option {:value "A"} "Active"]
             [:option {:value "I"} "Inactive"]
             [:option {:value "S"} "Sub"]]]
       [:td {:style {:width "5%"}} "&nbsp;"]]
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colSpan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRosterRequest('#addplayerform', '/add-player', '" title "')")} title]]]
      [:tr.hidden-control [:td {:colSpan form-span :align "center"} [:input.hidden-control {:id "ap_team_id" :name "team_id"}]]]
      (layout/empty-row form-span)]]))

(defn update-player-form
  []
  (let [title "Update Player"]
    [:form#updateplayerform.form-horizontal {:method "post" :action "/add-player"}
     [:table.match-info-table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colSpan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (add-form-control "First name:" {:id "up_first_name" :name "first_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Last name:" {:id "up_last_name" :name "last_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Email:" {:id "up_email" :name "email" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Phone number:" {:id "up_phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
      [:tr
       [:td {:style {:width "5%"}} "&nbsp;"]
       [:td {:nowrap true} [:label.control-label {:for "up_status"} "Status"]]
       [:td [:select#up_status {:name "status"}
             [:option {:value "A"} "Active"]
             [:option {:value "I"} "Inactive"]
             [:option {:value "S"} "Sub"]]]
       [:td {:style {:width "5%"}} "&nbsp;"]]
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colSpan form-span :align "center"}
            [:table
             [:td {:width "50%"} "&nbsp;"]
             [:td {:align "right" :nowrap "true"}
              [:button {:type "button" :onclick (str "return processRosterRequest('#updateplayerform', '/update-player', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_roster_form('show-roster');"} "Cancel"]]
             [:td {:width "5%"} "&nbsp;"]]]]
      [:tr.hidden-control [:td {:colSpan form-span :align "center"} [:input.hidden-control {:id "up_team_id" :name "team_id"}] [:input.hidden-control {:id "up_player_id" :name "player_id"}]]]
      (layout/empty-row form-span)]]))

(defn get-team-list
  [teams team-info]
  [:select {:name "team-id" :onChange #(rf/dispatch [::evt-select/team-selected (-> % .-target .-value)])}
   (reduce (fn [list team]
             (let [team-selected (if (= (get-in team-info [:team-info :id]) (:id team)) true false)]
               (conj list [:option {:value (:id team) :key (:id team)} (:name team)])))
           () (reverse (sort-by :name teams)))])

(defn roster-select-form
  []
  (fn []
    (let [title "Select roster function"
          div-visible @(rf/subscribe [::subs/match-panel-visible "select-form"])
          teams @(rf/subscribe [::subs/team-list])
          team-info @(rf/subscribe [::subs/team-info])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:table.match-info-table.table.table-sm.roster-form
        [:thead
         (layout/empty-row 3)
         [:tr [:td {:colSpan 3 :align "center"} [:h4 "Select Roster Function"]]]
         (layout/hr-row 3 "90%")]
        [:tbody
         (get-team-list teams team-info)
         ;(layout/add-select team/teams layout/option "team_id" "Team:" 1 (str "team_changed(this.value);"))
         ;(layout/add-select #(layout/actions roster-actions) layout/option "roster-list" "Roster Action:" 1 (str "change_roster_form(this.value);"))
         (layout/hr-row 3 "90%")
         (layout/empty-row 3)]]]
      [:br] [:hr] [:br])))



(defn call-status
  []
  (fn []
    (let [call-status @(rf/subscribe [::subs/roster_call_status])
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

