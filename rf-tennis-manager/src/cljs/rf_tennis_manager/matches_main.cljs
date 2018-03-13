(ns rf-tennis-manager.matches-main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [goog.string :as gs]
            [re-frame.core :as rf]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.matches-events :as match-events]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.db :as db]
            [rf-tennis-manager.views-common :as layout]))

(def form-span 4)

(def RED-X \u274C)
(def GREEN-CHECK \u2705)

(defn schedule-row
  "docstring"
  [rows sched-row team-id]
  (let [home-team-id (:home_team_id sched-row)
        avail-func (if (:availability_sent sched-row) ::match-events/show-set-avail-form ::match-events/show-email-avail-form)
        send-lineup-func (if (:lineup_set sched-row) ::match-events/show-email-lineup-form ::match-events/show-set-lineup-form)
        match-id (:match_id sched-row)]
    (conj rows [:tr {:key match-id}
                [:td (:match_date sched-row)]
                [:td (:match_time sched-row)]
                [:td (if (= team-id home-team-id) (:away_team sched-row) (:home_team sched-row))]
                [:td (:home_club_name sched-row)]
                [:td.text-center (if (= team-id home-team-id) (:home_team_points sched-row) (:away_team_points sched-row))]
                [:td.text-center (if (= team-id home-team-id) (:away_team_points sched-row) (:home_team_points sched-row))]
                [:td.text-center [:span.avail-cursor {:onClick #(re-frame.core/dispatch [avail-func match-id])} (if (:availability_sent sched-row) GREEN-CHECK RED-X)]]
                [:td.text-center [:span.avail-cursor {:onClick #(re-frame.core/dispatch [send-lineup-func match-id])} (if (:lineup_set sched-row) GREEN-CHECK RED-X)]]
                [:td.text-center [:span.avail-cursor {:onClick #(re-frame.core/dispatch [::match-events/show-email-lineup-form match-id])} (if (:lineup_sent sched-row) GREEN-CHECK RED-X)]]
                ])))

(defn get-team-schedule
  "docstring"
  [schedule team-id]
  (reduce #(schedule-row %1 %2 team-id) () (reverse schedule)))

(defn schedule-form
  "docstring"
  [schedule team-info]
  (fn []
    [:div
     [:table.main-table.table-sm
      [:tbody
       (layout/empty-row form-span)
       [:tr [:td.text-center {:colSpan form-span} [:h4 (:name team-info) " Team Schedule"]]]
       (layout/hr-row form-span "90%")
       [:tr
        [:td {:width "5%:"}]
        [:td {:colSpan "2"}
         [:table#match-sched.table-striped.table-sm.table
          [:thead.table-inverse
           [:tr
            [:td "Date"]
            [:td "Time"]
            [:td "Opponent"]
            [:td "Location"]
            [:td {:name "team-name"} "Points"]
            [:td "Opponent Points"]
            [:td "Availability Email Sent"]
            [:td "Lineup Set"]
            [:td "Lineup Sent"]]]
          [:tbody#match-sched-body
           (get-team-schedule schedule (:team_id team-info))]
          ]]
        [:td {:width "5%:"}]]
       (layout/hr-row form-span "90%")]]]))


(defn call-status
  []
  (fn []
    (let [call-status @(rf/subscribe [::subs/matches_call_status])
          cssClass (if (:success? call-status) "success" "form-error")]
      [:div
       [:table.main-table.table-sm.call-status
        [:tbody
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4#ma-status-title "Getting data"]]]
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]
          [:td "Status:"]
          [:td.text-left {:className cssClass} (:message call-status)]
          [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]]
         (layout/hr-row form-span "90%")
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span}
               [:button#ma-status-btn {:value "ok" :onClick #(re-frame.core/dispatch [])} "OK"]]]
         (layout/empty-row form-span)]]])))

(defn availability-row
  "docstring"
  [rows player active?]
  (if (or
        (and (not= (:status player) "I") (= active? true))
        (and (= (:status player) "I") (= active? false)))
    (let [sent_flag (if (= (:date_sent player) nil) "N", "Y")
          player_response (case (:response player)
                            0 "N"
                            1 "Y"
                            2 "?"
                            "NA")
          avail (case (:available player)
                  1 "Y"
                  "N")
          long-status (case (:status player)
                        "A" "Active"
                        "S" "Sub"
                        "I" "Inactive")
          row-class (if (= avail "Y") "player-avail" (if (= (:status player) "I") "player-inactive" ""))
          box-checked (if (= avail "Y") true false)
          box-disabled (if (= (:status player) "I") true false)]
      (println "----- write tr " player)
      (conj rows
            (let [cb-id (str "pl-av-" (:id player))
                  player-id (:id player)]
              [:tr {:class row-class :id player-id :key player-id}
               [:td.text-left (:last_name player) ", " (:first_name player)]
               [:td.text-center
                [:input {:type "checkbox" :disabled box-disabled :checked box-checked :name cb-id :id cb-id :onChange #((re-frame.core/dispatch [::match-events/swap-player-class cb-id player-id]))}]]
               [:td.text-center player_response]
               [:td.text-center sent_flag]
               [:td (if (= (:response_date player) nil) "" (:response_date player))]
               [:td.text-center long-status]])))
    rows))

(defn get-player-rows
  "docstring"
  [roster]
  (let [active_rows (reduce #(availability-row %1 %2 false) () (reverse roster))]
    (reduce #(availability-row %1 %2 true) active_rows (reverse roster))))

(defn availability-form
  "docstring"
  []
  (fn []
    (let [title "Update Availability"]
      [:div
       [:form#updateavailability.form-horizontal {:method "post" :action "/update-availability"}
        [:table.main-table.table-sm.avail-small
         [:tbody
          (layout/empty-row form-span)
          (let [team-info @(rf/subscribe [::subs/team-info])]
            [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
          (layout/hr-row form-span "90%")
          [:tr
           [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]
           [:td.text-center {:colSpan 2}
            [:table.main-table.table-sm.table-compact
             (let [match @(rf/subscribe [::subs/match-info])]
               [:tbody
                [:tr
                 [:td {:style {:width "1%"}} (gs/unescapeEntities "&nbsp;")]
                 [:td.text-left {:style {:width "18%"}} [:span.bold-text "Match Date:"]]
                 [:td.text-left {:colSpan 2} [:span#pa_match_date (:match_date match)]]]
                [:tr
                 [:td]
                 [:td.text-left [:span.bold-text "Match Time:"]]
                 [:td.text-left {:colSpan 2} [:span#pa_match_time (:match_time match)]]]
                [:tr
                 [:td]
                 [:td.text-left [:span.bold-text "Location:"]]
                 [:td.text-left {:colSpan 2} [:span#pa_match_location (:club_name match)]]]])]]
           [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]]
          (layout/hr-row form-span "90%")
          [:tr
           [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]
           [:td.text-center {:colSpan 2}
            [:table.main-table.table-sm
             [:thead.table-inverse
              [:tr
               [:th.text-left "Player name"]
               [:th.text-center "Available"]
               [:th.text-center "Email Response"]
               [:th.text-center "Email Sent"]
               [:th.text-left "Email Response Time"]
               [:th.text-center "Status"]]]
             [:tbody
              (let [roster @(rf/subscribe [::subs/roster])]
                (get-player-rows roster))]]]
           [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]]
          (layout/hr-row form-span "90%")
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:table {:style {:width "90%"}}
                 [:tbody
                  [:tr {:style {:align-items "center"}}
                   [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]
                   [:td
                    [:button {:type "button" :onClick #((re-frame.core/dispatch [::match-events/update-match-availability])) :style {:white-space "nowrap"}} title]]
                   [:td
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::match-events/show-schedule]) :style {:white-space "nowrap"}} "Return to Schedule"]]
                   [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]]]]]]
          [:tr.hidden-control
           [:td.text-center {:colSpan form-span}
            (let [match-info @(rf/subscribe [::subs/match-info])
                  match-id (if (:match_id match-info) (:match_id match-info) "-1")]
              [:input.hidden-control {:id "pa_match_id" :name "match_id" :value match-id :read-only true}])
            ]]
          (layout/empty-row form-span)]]]])))

(defn availability-email-form
  "docstring"
  []
  (fn []
    (println "adding availability-email-form")
    (let [title "Update Availability"]
      [:div]
      )))

(defn lineup-email-form
  "docstring"
  []
  (fn []
    (println "adding lineup-email-form")
    (let [title "Update Availability"]
      [:div]
      )))

(defn set-lineup-form
  "docstring"
  []
  (fn []
    (println "adding set-lineup-form")
    (let [title "Update Availability"]
      [:div]
      )))

