(ns rf-tennis-manager.matches-main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [goog.string :as gs]
            [re-frame.core :as rf]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.match-events-avail-email :as evt-avail-email]
            [rf-tennis-manager.match-events-common :as evt-common]
            [rf-tennis-manager.match-events-lineup-email :as evt-email-lineup]
            [rf-tennis-manager.match-events-set-availability :as evt-set-avail]
            [rf-tennis-manager.match-events-set-lineup :as evt-set-lineup]
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
        avail-func (if (:availability_sent sched-row) ::evt-set-avail/show-set-avail-form ::evt-avail-email/show-email-avail-form)
        send-lineup-func (if (:lineup_set sched-row) ::evt-email-lineup/show-email-lineup-form ::evt-set-lineup/show-set-lineup-form)
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
                [:td.text-center [:span.avail-cursor {:onClick #(re-frame.core/dispatch [::evt-email-lineup/show-email-lineup-form match-id])} (if (:lineup_sent sched-row) GREEN-CHECK RED-X)]]])))

(defn get-team-schedule
  "docstring"
  [schedule team-id]
  (reduce #(schedule-row %1 %2 team-id) () (reverse schedule)))

(defn schedule-form
  "docstring"
  [schedule team-info]
  (fn []
    (let [team-info @(rf/subscribe [::subs/team-info])
          schedule @(rf/subscribe [::subs/team-schedule])]
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
         (layout/hr-row form-span "90%")]]])))


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
                        "I" "Inactive"
                        "?")
          row-class (if (= avail "Y") "player-avail" (if (= (:status player) "I") "player-inactive" ""))
          box-checked (if (= avail "Y") true false)
          box-disabled (if (= (:status player) "I") true false)]
      (conj rows
            (let [cb-id (str "pl-av-" (:id player))
                  player-id (:id player)]
              [:tr {:class row-class :id player-id :key player-id}
               [:td.text-left (:last_name player) ", " (:first_name player)]
               [:td.text-center
                [:input {:type "checkbox" :disabled box-disabled :checked box-checked :name cb-id :id cb-id :onChange #((re-frame.core/dispatch [::evt-set-avail/swap-player-class cb-id player-id]))}]]
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

(defn add-match-info
  [match]
  [:table.match-info-table.table-sm.table-compact
   [:tbody
    [:tr
     [:td {:style {:width "1%"}} (gs/unescapeEntities "&nbsp;")]
     [:td [:span.bold-text "Match Date:"]]
     [:td {:style {:width "30px"}} (gs/unescapeEntities "&nbsp;")]
     [:td {:colSpan 2} (:match_date match)]
     [:td {:style {:width "45%"}} (gs/unescapeEntities "&nbsp;")]]
    [:tr
     [:td]
     [:td [:span.bold-text "Match Time:"]]
     [:td]
     [:td {:colSpan 2} (:match_time match)]
     [:td]]
    [:tr
     [:td]
     [:td [:span.bold-text "Location:"]]
     [:td]
     [:td {:colSpan 2} (:club_name match)]
     [:td]]]])

(defn availability-form
  "docstring"
  []
  (fn []
    (let [title "Update Availability"]
      [:div
       [:form#updateavailability.form-horizontal {:method "post" :action "/update-availability"}
        [:table.main-table.table-sm.availability-panel
         [:tbody
          (layout/empty-row form-span)
          (let [team-info @(rf/subscribe [::subs/team-info])]
            [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
          (layout/hr-row form-span "90%")
          [:tr
           [:td {:width "10%"} (gs/unescapeEntities "&nbsp;")]
           [:td.text-center {:colSpan 2}
            (let [match @(rf/subscribe [::subs/match-info])]
              (add-match-info match))]
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
                  [:tr.text-center
                   [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]
                   [:td
                    [:button {:type "button" :onClick #((re-frame.core/dispatch [::evt-set-avail/update-match-availability])) :style {:white-space "nowrap"}} title]]
                   [:td
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule]) :style {:white-space "nowrap"}} "Return to Schedule"]]
                   [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]]]]]]
          [:tr.hidden-control
           [:td.text-center {:colSpan form-span}
            (let [match-info @(rf/subscribe [::subs/match-info])
                  match-id (if (:match_id match-info) (:match_id match-info) "-1")]
              [:input.hidden-control {:id "pa_match_id" :name "match_id" :value match-id :read-only true}])
            ]]
          (layout/empty-row form-span)]]]])))

(defn add-form-control
  [label options init-data]
  [:tr
   [:td.text-left {:width "5%"} (gs/unescapeEntities "&nbsp;")]
   [:td.text-left {:style {:white-space "nowrap"}} [:label.control-label {:for (:id options)} label]]
   (case (:type options)
     "text-area" [:td [:textarea options init-data]]
     [:td.text-left [:input.form-control-sm options init-data]])
   [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]])

(defn availability-email-form
  []
  (fn []
    (let [title "Send Availability Email"]
      [:form#sendavailabilityemail.form-horizontal {:method "post" :action "/send-availability-email"}
       [:table.main-table.table-sm.email-avail-panel
        [:tbody
         (layout/empty-row form-span)
         (let [team-info @(rf/subscribe [::subs/team-info])]
           [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:width "10%"} (gs/unescapeEntities "&nbsp;")]
          [:td.text-left {:colSpan 2}
           (let [match @(rf/subscribe [::subs/match-info])]
             (add-match-info match))]
          [:td {:width "5%"} (gs/unescapeEntities "&nbsp;")]]
         (layout/hr-row form-span "90%")
         (add-form-control "Message:" {:id "av_message" :name "message" :cols 45 :maxLength 2000 :rows 7 :type "text-area"} "Let me know if you are available to play.")
         (add-form-control "Signature:" {:id "av_signature" :name "signature" :cols 45 :maxLength 200 :rows 3 :type "text-area"} "Rick Cashman\n513.227.9278")
         (add-form-control "Send to Subs:" {:id "av_send_subs" :name "send_subs" :type "checkbox"} nil)
         (layout/hr-row form-span "90%")
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span}
               [:table {:style {:width "90%"}}
                [:tbody
                 [:tr
                  [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]
                  [:td.text-right {:style {:white-space "nowrap"}}
                   [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-avail-email/send-availability-email])} title]]
                  [:td.text-left {:style {:white-space "nowrap"}}
                   [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule])} "Return to Schedule"]]
                  [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]]]]]]
         [:tr.hidden-control
          [:td.text-center {:colSpan form-span}
           (let [match-info @(rf/subscribe [::subs/match-info])
                 match-id (if (:match_id match-info) (:match_id match-info) "-1")]
             [:input.hidden-control {:id "av_match_id" :name "match_id" :value match-id :read-only true}])
           ]]
         (layout/empty-row form-span)]]])))

(defn lineup-email-form
  "docstring"
  []
  (fn []
    (let [title "Send Lineup Email"]
      [:form#sendlineupemail.form-horizontal {:method "post" :action "/send-lineup-email"}
       [:table.table.table-sm
        [:tbody
         (layout/empty-row form-span)
         (let [team-info @(rf/subscribe [::subs/team-info])]
           [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%"}}]
          [:td {:colSpan 2}
           (let [match @(rf/subscribe [::subs/match-info])]
             (add-match-info match))]
          [:td {:style {:width "5%:"}}]]
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%"}}]
          [:td {:colSpan 2}
           [:table#email-lineup.table
            [:thead.table-inverse
             [:tr.text-left
              [:td (gs/unescapeEntities "&nbsp;")]
              [:td "Player 1"]
              [:td "Player 2"]]]
            [:tbody#email-lineup-body
             ;TODO ADD SUBSCRIPTION TO GET LINEUP
             (layout/empty-row 4)]]]
          [:td {:style {:width "5%:"}}]]
         (layout/empty-row form-span)
         (add-form-control "Message:" {:id "li_message" :name "message" :cols 45 :maxLength 2000 :rows 7 :type "text-area"} "Please arrive 10 to 15 minutes before the match starts.")
         (add-form-control "Signature:" {:id "li_signature" :name "signature" :cols 45 :maxLength 200 :rows 3 :type "text-area"} "Rick Cashman\n513.227.9278")
         (add-form-control "Send to Subs:" {:id "li_send_subs" :name "send_subs" :type "checkbox"} nil)
         (layout/hr-row form-span "90%")
         (layout/empty-row form-span)
         [:tr
          [:td.text-center {:colSpan form-span}
           [:table {:style {:width "90%"}}
            [:tbody
             [:tr
              [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]
              [:td.text-right {:style {:white-space "nowrap"}}
               [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-avail-email/send-lineup-email])} title]]
              [:td.text-left {:style {:white-space "nowrap"}}
               [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule])} "Return to Schedule"]]
              [:td {:style {:width "50%"}} (gs/unescapeEntities "&nbsp;")]]]]]]
         [:tr.hidden-control
          [:td.center {:colSpan form-span}
           (let [match-info @(rf/subscribe [::subs/match-info])
                 match-id (if (:match_id match-info) (:match_id match-info) "-1")]
             [:input.hidden-control {:id "li_match_id" :name "match_id" :value match-id :read-only true}])
           ]]
         (layout/empty-row form-span)]]])))


(defn add-match-select-controls
  [list court team-name]
  (let [player-1 (str "c" court "p1-div")
        player-2 (str "c" court "p2-div")
        btn-grp (str "c" court "-forfeit-grp")
        no-forfeit (str "c" court "-forfeit-none")
        team-forfeit (str "c" court "-forfeit")
        opp-forfeit (str "c" court "-forfeit-opp")
        btn-disabled (if (= court 4) false true)]
    (conj list
          [:tr {:key (str "c" court "-line-up-row")}
           [:td [:span {:style {:font-weight "bold"}} (str "Court " court)]]
           [:td [:div {:id player-1}]]
           [:td [:div {:id player-2}]]
           [:td
            ;[:fieldset {:id btn-grp}
            ; (gs/unescapeEntities "&nbsp;")
            ; [:input {:type "radio" :value "0" :id no-forfeit :name btn-grp :disabled btn-disabled :checked true :onClick #(re-frame.core/dispatch [::evt-set-lineup/update-forfeit-btns no-forfeit 0])}
            ;  (gs/unescapeEntities "&nbsp;") "None"] [:br]
            ; (gs/unescapeEntities "&nbsp;")
            ; [:input {:type "radio" :value "1" :id team-forfeit :name btn-grp :disabled btn-disabled :onClick #(re-frame.core/dispatch [::evt-set-lineup/update-forfeit-btns team-forfeit 1])}
            ;  (gs/unescapeEntities "&nbsp;") team-name] [:br]
            ; (gs/unescapeEntities "&nbsp;")
            ; [:input {:type "radio" :value "2" :id opp-forfeit :name btn-grp :disabled btn-disabled :onClick #(re-frame.core/dispatch [::evt-set-lineup/update-forfeit-btns opp-forfeit 2])}
            ;  (gs/unescapeEntities "&nbsp;") "Opponent"] [:br]]
            ]])))

(defn set-lineup-form
  "Sets up the lineup page."
  []
  (fn []
    [:form#updatelineup.form-horizontal {:method "post" :action "/update-lineup"}
     (let [title "Update Match Lineup"]
       [:table.table.table-sm
        [:tbody
         (layout/empty-row form-span)
         (let [team-info @(rf/subscribe [::subs/team-info])]
           [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%:"}}]
          [:td {:colSpan 2}
           (let [match @(rf/subscribe [::subs/match-info])]
             (add-match-info match))]
          [:td {:style {:width "5%:"}}]]
         (layout/empty-row form-span)
         [:tr
          [:td {:style {:width "5%:"}}]
          [:td {:colSpan 2}
           [:table#match-lineup.table.table-striped.table-sm
            [:thead.table-inverse
             [:tr.text-left
              [:td (gs/unescapeEntities "&nbsp;")]
              [:td "Player 1"]
              [:td "Player 2"]
              [:td "Forfeit"]]]
            [:tbody#match-lineup-body
             (layout/empty-row 4)
             (let [team-info @(rf/subscribe [::subs/team-info])]
               (reduce #(add-match-select-controls %1 %2 (:name team-info)) () (reverse (range 1 5))))
             (layout/empty-row 4)]]]
          [:td {:style {:width "5%:"}}]]
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span}
               [:table {:style {:width "90%"}}
                [:tbody
                 [:tr
                  [:td {:style {:width "30%"}} (gs/unescapeEntities "&nbsp;")]
                  [:td.text-right {:style {:white-space "nowrap"}}
                   [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-set-lineup/update-lineup])} title]]
                  [:td.text-left {:style {:white-space "nowrap"}}
                   [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule])} "Return to Schedule"]]
                  [:td.text-left {:style {:white-space "nowrap"}}
                   [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-availability])} "Go to Availability"]]
                  [:td {:style {:width "30%"}} (gs/unescapeEntities "&nbsp;")]]]]]]
         [:tr.hidden-control
          [:td {:colSpan form-span}
           (let [match-info @(rf/subscribe [::subs/match-info])
                 match-id (if (:match_id match-info) (:match_id match-info) "-1")]
             [:input.hidden-control {:id "ml_match_id" :name "match_id" :value match-id :read-only true}])
           ]]
         (layout/hr-row form-span "90%")]])]))

(defn set-lineup-formx
  []
  (fn []
    (println "set lineup")))