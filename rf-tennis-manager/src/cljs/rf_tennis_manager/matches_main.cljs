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
            [rf-tennis-manager.views-common :as layout]
            [clojure.string :as s]))

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
                [:td.text-center [:span.avail-cursor {:onClick #(re-frame.core/dispatch [::evt-set-lineup/show-set-lineup-form match-id])} (if (:lineup_set sched-row) GREEN-CHECK RED-X)]]
                [:td.text-center [:span.avail-cursor {:onClick #(re-frame.core/dispatch [send-lineup-func match-id])} (if (:lineup_sent sched-row) GREEN-CHECK RED-X)]]])))

(defn get-team-schedule
  "docstring"
  [schedule team-id]
  (reduce #(schedule-row %1 %2 team-id) () (reverse schedule)))

(defn schedule-form
  "docstring"
  []
  (fn []
    (println "==== schedule-form ====")
    (let [team-info @(rf/subscribe [::subs/team-info])
          schedule @(rf/subscribe [::subs/team-schedule])
          div-visible @(rf/subscribe [::subs/panel-visible "schedule"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:table.main-table.table-sm
        [:tbody
         (layout/empty-row form-span)
         [:tr [:td.text-center {:colSpan form-span} [:h4 (:name team-info) " Team Schedule"]]]
         (layout/hr-row form-span "90%")
         [:tr
          [:td {:style {:width "5%"}}]
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
          [:td {:style {:width "5%"}}]]
         (layout/hr-row form-span "90%")]]])))


(defn call-status
  []
  (fn []
    (let [call-status @(rf/subscribe [::subs/matches_call_status])
          cssClass (if (:success? call-status) "success" "form-error")
          div-visible @(rf/subscribe [::subs/panel-visible "call-status"])
          click-fn (if (:on-click call-status) (:on-click call-status) #(re-frame.core/dispatch [::evt-common/hide-call-status]))]
      (println "==== call-status ==== visisble? " div-visible " db onclick: " (:on-click call-status) " fn: " click-fn)
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
         [:tr [:td.text-center {:colSpan form-span}
               [:button#ma-status-btn {:value "ok" :onClick click-fn} "OK"]]]
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
                [:input {:type "checkbox" :disabled box-disabled :checked box-checked :name cb-id :id cb-id :onChange #((re-frame.core/dispatch [::evt-set-avail/player-selection-changed cb-id player-id]))}]]
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
     [:td {:style {:width "1%"}} (layout/nbsp)]
     [:td [:span.bold-text "Match Date:"]]
     [:td {:style {:width "30px"}} (layout/nbsp)]
     [:td {:colSpan 2} (:match_date match)]
     [:td {:style {:width "45%"}} (layout/nbsp)]]
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
    (println "==== availability-form ====")
    (let [title "Update Availability"
          div-visible @(rf/subscribe [::subs/panel-visible "availability"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#updateavailability.form-horizontal {:method "post" :action "/update-availability"}
        [:table.main-table.table-sm.availability-panel
         [:tbody
          (layout/empty-row form-span)
          (let [team-info @(rf/subscribe [::subs/team-info])]
            [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
          (layout/hr-row form-span "90%")
          [:tr
           [:td {:style {:width "10%"}} (layout/nbsp)]
           [:td.text-center {:colSpan 2}
            (let [match @(rf/subscribe [::subs/match-info])]
              (add-match-info match))]
           [:td {:style {:width "5%"}} (layout/nbsp)]]
          (layout/hr-row form-span "90%")
          [:tr
           [:td {:style {:width "5%"}} (layout/nbsp)]
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
           [:td {:style {:width "5%"}} (layout/nbsp)]]
          (layout/hr-row form-span "90%")
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:table {:style {:width "90%"}}
                 [:tbody
                  [:tr.text-center
                   [:td {:style {:width "55%"}} (layout/nbsp)]
                   [:td
                    [:button {:type "button" :onClick #((re-frame.core/dispatch [::evt-set-avail/update-match-availability])) :style {:white-space "nowrap"}} title]]
                   [:td
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule]) :style {:white-space "nowrap"}} "Return to Schedule"]]
                   [:td {:style {:width "40%"}} (layout/nbsp)]]]]]]
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
   [:td.text-left {:style {:width "5%"}} (layout/nbsp)]
   [:td.text-left {:style {:white-space "nowrap"}} [:label.control-label {:for (:id options)} label]]
   (case (:type options)
     "text-area" [:td [:textarea options init-data]]
     [:td.text-left [:input.form-control-sm options init-data]])
   [:td {:style {:width "5%"}} (layout/nbsp)]])

(defn availability-email-form
  []
  (fn []
    (println "==== availability-email-form ====")
    (let [title "Send Availability Email"
          div-visible @(rf/subscribe [::subs/panel-visible "send-avail-email"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#sendavailabilityemail.form-horizontal {:method "post" :action "/send-availability-email"}
        [:table.main-table.table-sm.email-avail-panel
         [:tbody
          (layout/empty-row form-span)
          (let [team-info @(rf/subscribe [::subs/team-info])]
            [:tr [:td.text-center {:colSpan form-span} [:h4 title " for " (:name team-info)]]])
          (layout/hr-row form-span "90%")
          [:tr
           [:td {:style {:width "5%"}} (layout/nbsp)]
           [:td.text-left {:colSpan 2}
            (let [match @(rf/subscribe [::subs/match-info])]
              (add-match-info match))]
           [:td {:style {:width "5%"}} (layout/nbsp)]]
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
                   [:td {:style {:width "55%"}} (layout/nbsp)]
                   [:td.text-right {:style {:white-space "nowrap"}}
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-avail-email/send-availability-email])} title]]
                   [:td.text-left {:style {:white-space "nowrap"}}
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule])} "Return to Schedule"]]
                   [:td {:style {:width "40%"}} (layout/nbsp)]]]]]]
          [:tr.hidden-control
           [:td.text-center {:colSpan form-span}
            (let [match-info @(rf/subscribe [::subs/match-info])
                  match-id (if (:match_id match-info) (:match_id match-info) "-1")]
              [:input.hidden-control {:id "av_match_id" :name "match_id" :value match-id :read-only true}])
            ]]
          (layout/empty-row form-span)]]]])))

(defn lineup-email-form
  "docstring"
  []
  (fn []
    (println "==== lineup-email-form ====")
    (let [title "Send Lineup Email"
          div-visible @(rf/subscribe [::subs/panel-visible "send-lineup-email"])]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
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
               [:td (layout/nbsp)]
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
               [:td {:style {:width "50%"}} (layout/nbsp)]
               [:td.text-right {:style {:white-space "nowrap"}}
                [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-avail-email/call-send-avail-email])} title]]
               [:td.text-left {:style {:white-space "nowrap"}}
                [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule])} "Return to Schedule"]]
               [:td {:style {:width "50%"}} (layout/nbsp)]]]]]]
          [:tr.hidden-control
           [:td.center {:colSpan form-span}
            (let [match-info @(rf/subscribe [::subs/match-info])
                  match-id (if (:match_id match-info) (:match_id match-info) "-1")]
              [:input.hidden-control {:id "li_match_id" :name "match_id" :value match-id :read-only true}])
            ]]
          (layout/empty-row form-span)]]]])))

(defn get-player-list
  "docstring"
  [lineup court-key]
  (let [court-list ((keyword court-key) lineup)
        id (str court-key "-list")
        sel-id (get-in court-list [:selected :id])]
    [:select {:name court-key :id id :value sel-id :onChange #(rf/dispatch [::evt-set-lineup/update-player-lists court-key (-> % .-target .-value)])}
     (reduce (fn [list player]
               (let [comma (if (s/blank? (:first_name player)) " " ", ")
                     player-selected (if (= sel-id (:id player)) true false)]
                 (conj list [:option {:value (:id player) :key (:id player)} (str (:last_name player) comma (str (:first_name player)))])))
             () (reverse (sort-by (juxt :last_name :first_name) (vals (:players court-list)))))]))

(def value-no-forfeit 0)
(def highest-court-number 4)
(def lowest-court-number 1)

(defn next-grp-has-no-forfeit
  "returns true if the next highest court number does NOT have a forfeit"
  [court forfeits]
  (if (= court highest-court-number)
    false
    (if (= value-no-forfeit ((keyword (str "c" (inc court))) forfeits))
      true
      false)))

(defn prev-grp-has-forfeit
  "returns true if the next lowest court number has a forfeit"
  [court forfeits]
  (if (= court lowest-court-number)
    false
    (if (= value-no-forfeit ((keyword (str "c" (dec court))) forfeits))
      false
      true)))

(defn add-match-select-controls
  "add the player lists and forfeit radio buttons for a court"
  [list court team-info lineup forfeits match-info]
  (println "add-match-select-controls forfeits: " forfeits)
  (let [btn-grp (str "c" court "-forfeit-grp")
        no-forfeit (str "c" court "-forfeit-none")
        team-forfeit (str "c" court "-forfeit")
        opp-forfeit (str "c" court "-forfeit-opp")
        btn-disabled (if (or (next-grp-has-no-forfeit court forfeits) (prev-grp-has-forfeit court forfeits)) true false)
        btn-value ((keyword (str "c" court)) forfeits)
        team-id (:id team-info)
        opp-id (if (= team-id (:home_team_id match-info)) (:away_team_id match-info) (:home_team_id match-info))]
    (conj list
          [:tr {:key (str "c" court "-line-up-row")}
           [:td [:span {:style {:font-weight "bold"}} (str "Court " court)]]
           [:td (get-player-list lineup (str "c" court "p1"))]
           [:td (get-player-list lineup (str "c" court "p2"))]
           [:td
            [:fieldset {:id btn-grp}
             [:input {:type     "radio" :name btn-grp :value value-no-forfeit :key no-forfeit :disabled btn-disabled
                      :checked  (if (= btn-value value-no-forfeit) true false)
                      :onChange #(re-frame.core/dispatch [::evt-set-lineup/update-forfeit-btns court value-no-forfeit])}]
             (layout/nbsp) "None" [:br]
             [:input {:type     "radio" :name btn-grp :value team-id :key team-forfeit :disabled btn-disabled
                      :checked  (if (= btn-value team-id) true false)
                      :onChange #(re-frame.core/dispatch [::evt-set-lineup/update-forfeit-btns court team-id])}]
             (layout/nbsp) (:name team-info) [:br]
             [:input {:type     "radio" :name btn-grp :value opp-id :key opp-forfeit :disabled btn-disabled
                      :checked  (if (= btn-value opp-id) true false)
                      :onChange #(re-frame.core/dispatch [::evt-set-lineup/update-forfeit-btns court opp-id])}]
             (layout/nbsp) "Opponent" [:br]]]])))

(defn set-lineup-form
  "Sets up the lineup page."
  []
  (fn []
    (println "==== set-lineup-form ====")
    (let [div-visible @(rf/subscribe [::subs/panel-visible "set-lineup"])
          title "Update Match Lineup"]
      [:div {:className (if div-visible "div-panel-show" "div-panel-hide")}
       [:form#updatelineup.form-horizontal {:method "post" :action "/update-lineup"}
        [:table.main-table.lineup-panel.table-sm
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
               [:td (layout/nbsp)]
               [:td "Player 1"]
               [:td "Player 2"]
               [:td "Forfeit"]]]
             [:tbody#match-lineup-body
              (let [team-info @(rf/subscribe [::subs/team-info])
                    lineup @(rf/subscribe [::subs/match-lineup])
                    forfeits @(rf/subscribe [::subs/match_forfeits])
                    match-info @(rf/subscribe [::subs/match-info])]
                (reduce #(add-match-select-controls %1 %2 team-info lineup forfeits match-info) () (reverse (range 1 5))))]]]
           [:td {:style {:width "5%:"}}]]
          (layout/empty-row form-span)
          [:tr [:td.text-center {:colSpan form-span}
                [:table {:style {:width "90%"}}
                 [:tbody
                  [:tr
                   [:td {:style {:width "40%"}} (layout/nbsp)]
                   [:td.text-right {:style {:white-space "nowrap"}}
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-set-lineup/update-lineup])} title]]
                   [:td.text-left {:style {:white-space "nowrap"}}
                    [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-common/show-schedule])} "Return to Schedule"]]
                   [:td.text-left {:style {:white-space "nowrap"}}
                    (let [match @(rf/subscribe [::subs/match-info])]
                      [:button {:type "button" :onClick #(re-frame.core/dispatch [::evt-set-avail/show-set-avail-form (:match_id match)])} "Go to Availability"])]
                   [:td {:style {:width "40%"}} (layout/nbsp)]]]]]]
          [:tr.hidden-control
           [:td {:colSpan form-span}
            (let [match-info @(rf/subscribe [::subs/match-info])
                  match-id (if (:match_id match-info) (:match_id match-info) "-1")]
              [:input.hidden-control {:id "ml_match_id" :name "match_id" :value match-id :read-only true}])]]
          (layout/hr-row form-span "90%")]]]])))