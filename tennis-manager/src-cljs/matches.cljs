(ns tennis-manager.matches
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [enfocus.macros :as em])
  (:require [clojure.browser.repl :as repl]
            [clojure.string :as s]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :as pp]
            [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects]))

(defn not-found-row
  "Generate a row when there are no players on the roster for the team"
  []
  (str "<tr><td colspan='5' align='center'><h2 style='color:red'>No players added to roster</h2></td></tr>"))

(defn availability-row
  "Generate a player availability row"
  [row active?]
  (let [sent_flag (if (= (:date_sent row) nil) "N", "Y")
        player_response (case (:response row)
                          0 "N"
                          1 "Y"
                          2 "?"
                          "NA")
        avail (case (:available row)
                1 "Y"
                "N")
        long-status (case (:status row)
                      "A" "Active"
                      "S" "Sub"
                      "I" "Inactive")
        row-class (if (= avail "Y") "player-avail" (if (= (:status row) "I") "player-inactive" ""))]
    ;we want the inactive players at the bottom
    ;so we call this twice with the same result set

    (if (or
          (and (not= (:status row) "I") (= active? true))
          (and (= (:status row) "I") (= active? false)))
      ;(let [box-checked (if (= avail "Y") true false)
      ;      box-disabled (if (= (:status row) "I") true false)]
      ;(ef/html
      ;  [:tr {:class row-class :id (:id row)}
      ;   [:td {:nowrap true} (:last_name row) ", " (:first_name row)]
      ;   [:td {:align "center"}
      ;    [:input {:type "checkbox" :disabled box-disabled :checked box-checked :name (str "pl-av-" (:id row)) :onclick "swapClass(this)"}]]
      ;   [:td {:align "center"} player_response]
      ;   [:td {:align "center"} sent_flag]
      ;   [:td (if (= (:response_date row) nil) "" (:response_date row))]
      ;   [:td {:align "center"} long-status]
      ;   ])))))
      (str "<tr class='" row-class "' id='" (:id row) "' onclick=''>"
           "<td nowrap>" (:last_name row) ", " (:first_name row) "</td>"
           "<td align='center'>
               <input type='checkbox' name='pl-av-" (:id row) "'"
           (if (= avail "Y") " checked")
           (if (= (:status row) "I") " disabled")
           " onclick='swapClass(this);'</input>"
           "</td>"
           "<td align='center'>" player_response "</td>"
           "<td align='center'>" sent_flag "</td>"
           "<td>" (if (= (:response_date row) nil) "" (:response_date row)) "</td>"
           "<td align='center'>" long-status "</td>"
           "</tr>"))))

(defn set-match-info
  "Adds the date, time and location of the match displayed at the top of a few of the pages."
  [match-id prefix]
  (go
    (let [response (<! (http/get (str "match-info/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (doseq [row body]
          (ef/at
            (str "#" prefix "_match_id") (ef/set-form-input match-id))
          (ef/at
            (str "#" prefix "_match_date") (ef/content (:match_date row)))
          (ef/at
            (str "#" prefix "_match_time") (ef/content (:match_time row)))
          (ef/at
            (str "#" prefix "_match_location") (ef/content (:club_name row))))))))

(defn ^:export email_form
  "Load the email availability form.  Not much to do here."
  [match-id]
  (set-match-info match-id "av"))

(defn send-lineup-row
  "docstring"
  [lineup-row]
  (ef/html
    [:tr
     [:td [:b "Court " (:court_number lineup-row)]]
     [:td (:player1_name lineup-row)]
     [:td (:player2_name lineup-row)]
     [:td (:forfeit_team_name lineup-row)]]))

(defn ^:export lineup_email_form
  "Load the email lineup form."
  [match-id]
  (ef/at
    "#email-lineup-body tr:not(:first-child)" (ef/remove-node))
  (go
    (let [response (<! (http/get (str "match-lineup/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#email-lineup-body tr:last-child" (ef/after (send-lineup-row row))))
            []
            body))))
    (ef/at
      "#email-lineup-body tr:first-child" (ef/remove-node)))
  (set-match-info match-id "li"))

(defn ^:export availability
  "Loads the Update Availability form"
  [match-id]
  (comment "We process the data twice so that Inactive players are at the bottom.")
  (ef/at
    "#av-details-body tr:not(:first-child)" (ef/remove-node))
  (go
    (let [response (<! (http/get (str "match-availability/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          ;Add active players
          (reduce
            (fn [db-rows row]
              (ef/at
                "#av-details-body tr:last-child" (ef/after (availability-row row true))))
            []
            body)
          ;Add inactive players
          (reduce
            (fn [db-rows row]
              (ef/at
                "#av-details-body tr:last-child" (ef/after (availability-row row false))))
            []
            body))))
    (ef/at
      "#av-details-body tr:first-child" (ef/remove-node)))
  (set-match-info match-id "pa"))

(defn add-player-to-select-list
  "Adds a players to the drop down if they are available to play that week."
  [list row]
  (if (= (:available row) 1)
    (conj list (str "<option value='" (:id row) "'>" (:last_name row) ", " (:first_name row) "</option>"))
    list))

(defn select-player
  "Sets a player to selected in the player dropdown on the Update Lineup form."
  [court_number player_number player_id]
  (ef/at (str "#c" court_number "-p" player_number " option[value='" player_id "']") (ef/set-prop "selected" "selected"))
  )

(defn init-player-options
  "Selects the players in the dropdown that are assingned to a court in the DB in the Update Lineup form."
  [rcds]
  (doseq [row rcds]
    (let [court_number (:court_number row)]
      (if (not= court_number nil)
        (let [home_p1 (:home_player1 row)
              home_p2 (:home_player2 row)
              away_p1 (:away_player1 row)
              away_p2 (:away_player2 row)
              player_id (:id row)]
          (if (or (= home_p1 player_id) (= away_p1 player_id))
            (select-player court_number 1 player_id))
          (if (or (= home_p2 player_id) (= away_p2 player_id))
            (select-player court_number 2 player_id)))))))

(defn updatePlayerControls
  "Update the player select drop down.  Used to disable the dropdowns if a court was forfeited."
  [court disabled]
  (let [player1 (str "#c" court "-p1")
        player2 (str "#c" court "-p2")]
    (go
      (ef/at player1 (ef/set-prop "disabled" disabled))
      (ef/at player2 (ef/set-prop "disabled" disabled)))))

(defn updateForfeitControls
  "Update the forfeit radio buttons"
  [court disabled]
  (let [no-forfeit (str "#c" court "-forfeit-none")
        team-forfeit (str "#c" court "-forfeit")
        opp-forfeit (str "#c" court "-forfeit-opp")]
    (go
      (ef/at no-forfeit (ef/set-prop "disabled" disabled))
      (ef/at team-forfeit (ef/set-prop "disabled" disabled))
      (ef/at opp-forfeit (ef/set-prop "disabled" disabled)))))

(defn ^:export forfeit_selected
  "This is called when a forfeit button is selected on the UI and when the page loads.  It updates the other forfeit buttons based on the selected value."
  [elem-name elem-value]
  (comment "elem-name is c4-forfeit-none (0) c4-forfeit (1) or c4-forfeit-opp (2).  Second character is the court number
  forfeits must start from court 4 so we enable/disable the controls accordingly")
  (let [court (int (nth elem-name 1))
        playerControlEnabled (if (not= elem-value "0") "disabled" "")
        higherRadioGrpEnabled (if (not= elem-value "0") "disabled" "")
        lowerRadioGrpEnabled (if (not= elem-value "0") "" "disabled")]
    (updatePlayerControls court playerControlEnabled)
    (if (not= court 4)
      (updateForfeitControls (inc court) higherRadioGrpEnabled))
    (if (not= court 1)
      (updateForfeitControls (dec court) lowerRadioGrpEnabled))))

(defn get-btn-val
  "Calculate the value of a forfeit button based on what is in the DB"
  [forfeit-val team-id]
  (cond
    (or (= forfeit-val "0") (= forfeit-val nil)) "0"
    (= forfeit-val (int team-id)) "1"
    :else "2"))

(defn select-forfeit-btn
  "This sets the value of a forfeit button and calls forfeit selected to updated the other forfeit buttons"
  [btn-id value]
  (ef/at (str "#" btn-id) (ef/set-prop "checked" "checked"))
  (forfeit_selected btn-id value)
  )

(defn enable-disable-buttons
  [court-number enable-value]
  (go
    (ef/at (str "#c" court-number "-forfeit-none")
           (ef/set-prop "disabled" enable-value))
    (ef/at (str "#c" court-number "-forfeit")
           (ef/set-prop "disabled" enable-value))
    (ef/at (str "#c" court-number "-forfeit-opp")
           (ef/set-prop "disabled" enable-value))))

(defn init-forfeit-btns
  "this intiializes the forfeit buttons.  It sets the value from the DB and sets the other forfeit buttons accordingly."
  [match-id]
  (go
    (let [response (<! (http/get (str "match-forfeits/" match-id)))
          body (:body response)
          rowCt (count body)]
      (comment "Need to reset all of the forfeit radio buttons to none.
                If the previously selected match had more forfeits than the current match
                then the radio buttons could be incorrect.")
      (doseq [court-number (range 1 5)]
        (ef/at (str "#c" court-number "-forfeit-none")
               (ef/set-prop "checked" "checked"))
        (if (< court-number 4)
          (enable-disable-buttons court-number "disabled")
          (enable-disable-buttons court-number "")))
      ;using reverse because we must process from court 4 to court 1 and the SQL is sorted by court number
      (if (> rowCt 0)
        (doseq [row (reverse body)]
          (let [court_number (:court_number row)
                btn-val (get-btn-val (:forfeit_team_id row) (:team_id (ef/from "#updatelineup" (ef/read-form))))]
            (case btn-val
              "0" (select-forfeit-btn (str "c" court_number "-forfeit-none") btn-val)
              "1" (select-forfeit-btn (str "c" court_number "-forfeit") btn-val)
              "2" (select-forfeit-btn (str "c" court_number "-forfeit-opp") btn-val))))))))

(defn lineup-to-availability-form
  "Call the js to change the form to the availability form"
  [match-id]
  (js/change_to_avail_form match-id))

(em/defaction avail-setup [match-id]
              ["#lineuptoavail"] (events/remove-listeners :click)
              ["#lineuptoavail"] (events/listen :click #(lineup-to-availability-form match-id)))

(defn ^:export set_lineup
  "load the set lineup form"
  [match-id]
  (ef/at "option" (ef/remove-node))
  (go
    (comment "calling the match-availability function and will only use player marked as available
    doing this rather than writing an available players method")
    (let [response (<! (http/get (str "match-availability/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (let [first-opt (list (str "<option value='0'></option>"))
              options (reduce #(add-player-to-select-list %1 %2) first-opt body)]
          (ef/at "select" (ef/content (s/join (reverse options))))
          (init-player-options body)
          (init-forfeit-btns match-id)))))
  (avail-setup match-id)
  (set-match-info match-id "ml"))

;(def select-ids (list "c1-p1" "c1-p2" "c2-p1" "c2-p2" "c3-p1" "c3-p2" "c4-p1" "c4-p2"))
;
;(defn ^:export player_selected [selected-val]
;  (if (> selected-val 0)
;    (do
;      (let [form-vals (ef/from "#updatelineup" (ef/read-form))])
;      false)))


;;cannot use as desired until I can figure out how to get a handle to the event
;(em/defaction change [msg ]
;              (js/alert msg))
;
;(em/defaction setup []
;              ["input[name*=\"forfeit\"]"] (events/listen-live :click "input" #(change  "clicked" )))
;
;(set! (.-onload js/window) setup)







