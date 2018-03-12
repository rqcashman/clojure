(ns tennis-manager.matches
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [enfocus.macros :as em])
  (:require [clojure.browser.repl :as repl]
            [clojure.pprint :as pp]
            [clojure.string :as s]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :as pp]
            [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)
(def blank-player-id "0")
(def courts ["c1p1" "c1p2" "c2p1" "c2p2" "c3p1" "c3p2" "c4p1" "c4p2"])
(def om-owner# (atom {}))
(defonce app-state
         (atom
           {:c1p1 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c1p2 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c2p1 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c2p2 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c3p1 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c3p2 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c4p1 {:selected {:current  {}
                              :previous {}}
                   :players  {}}
            :c4p2 {:selected {:current  {}
                              :previous {}}
                   :players  {}}}))

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
      (let [box-checked (if (= avail "Y") true false)
            box-disabled (if (= (:status row) "I") true false)]
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
             "</tr>")))))

(defn set-match-info
  "Adds the date, time and location of the match displayed at the top of a few of the pages."
  [match-id prefix]
  (go
    (let [response (<! (http/get (str "match-info/" match-id)))
          match (:body response)]
      (if match
        (do
          (ef/at
            (str "#" prefix "_match_id") (ef/set-form-input match-id)
            (str "#" prefix "_match_date") (ef/content (:match_date match))
            (str "#" prefix "_match_time") (ef/content (:match_time match))
            (str "#" prefix "_match_location") (ef/content (:club_name match))))))))

(defn ^:export email_form
  "Load the email availability form.  Not much to do here."
  [match-id]
  (set-match-info match-id "av"))

(defn send-lineup-row
  "docstring"
  [lineup-row]
  (ef/html
    [:tr
     [:td [:b "Court " (:court_number lineup-row)] ":"]
     (if (= (nil? (:forfeit_team_name lineup-row)) true)
       (list [:td (:player1_name lineup-row)]
             [:td (:player2_name lineup-row)])
       [:td {:colspan 2 :align "left" :style "font-weight:bold;color:red"} (:forfeit_team_name lineup-row) " forfeit"])]))

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
  "Selects the players in the dropdown that are assigned to a court in the DB in the Update Lineup form."
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
      (ef/at player1 (ef/set-prop "disabled" disabled)
             player2 (ef/set-prop "disabled" disabled)))))

(defn updateForfeitControls
  "Update the forfeit radio buttons"
  [court disabled]
  (let [no-forfeit (str "#c" court "-forfeit-none")
        team-forfeit (str "#c" court "-forfeit")
        opp-forfeit (str "#c" court "-forfeit-opp")]
    (go
      (ef/at no-forfeit (ef/set-prop "disabled" disabled)
             team-forfeit (ef/set-prop "disabled" disabled)
             opp-forfeit (ef/set-prop "disabled" disabled)))))

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

(defn enable-disable-forfeit-buttons
  "Function name is self documenting"
  [court-number enable-value]
  (go
    (ef/at (str "#c" court-number "-forfeit-none") (ef/set-prop "disabled" enable-value)
           (str "#c" court-number "-forfeit") (ef/set-prop "disabled" enable-value)
           (str "#c" court-number "-forfeit-opp") (ef/set-prop "disabled" enable-value))))

(defn init-forfeit-btns
  "this intiializes the forfeit buttons.  It sets the value from the DB and sets the other forfeit buttons accordingly."
  [match-id]
  (go
    (comment "Need to reset all of the forfeit radio buttons to none and disable all but court 4 buttons.
              If the previously selected match had more forfeits than the current match
              then the radio buttons could be incorrect.")
    (doseq [court-number (range 1 5)]
      (ef/at (str "#c" court-number "-forfeit-none")
             (ef/set-prop "checked" "checked"))
      (if (< court-number 4)
        (enable-disable-forfeit-buttons court-number "disabled")
        (enable-disable-forfeit-buttons court-number "")))
    (let [response (<! (http/get (str "match-forfeits/" match-id)))
          body (:body response)
          rowCt (count body)]
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

(comment "Replace the listener on the button to go to the availability form to reflect the currently selected match")
(em/defaction avail-setup [match-id]
              ["#lineuptoavail"] (events/remove-listeners :click)
              ["#lineuptoavail"] (events/listen :click #(lineup-to-availability-form match-id)))

(defn player-option
  "Adds a player option for a list"
  [data owner select-id]
  (reify
    om/IRenderState
    (render-state [this state]
      (let [comma (if (= (:id data) 0) "" ", ")]
        (dom/option #js {:value (:id data) :id (str select-id "-" (:id data))} (str (:last data) comma (:first data)))))))

(defn update-player-list
  "Updates the state of a ref cursor based on the change of another ref cursor.
   Used to add/remove players from the other court lists when a player is selected."
  [tgt-data src-data]
  (let [prev-id (keyword (str (:id (:previous (:selected src-data)))))
        curr-id (keyword (str (:id (:current (:selected src-data)))))]
    (if (pos? (count (:previous (:selected src-data))))
      (if (= (name curr-id) blank-player-id)
        (assoc-in tgt-data [:players prev-id] (:previous (:selected src-data)))
        (->
          (assoc-in tgt-data [:players prev-id] (:previous (:selected src-data)))
          (update-in [:players] dissoc curr-id)))
      (if (= (name curr-id) blank-player-id)
        tgt-data
        (update-in tgt-data [:players] dissoc curr-id)))))

(defn update-player
  "Updates the app-state to reflect the current and previous list selection"
  [data player-id]
  (->
    (assoc-in @data [:selected :previous] (:current (:selected @data)))
    (assoc-in [:selected :current] ((keyword player-id) (:players @data)))))

(defn player-changed
  "Does the work when a player needs to be set as current.  Called from the onChange event and when the page is loaded"
  [owner data player-id list-id]
  ;update state of list to reflect current and previous selections
  (om/transact! data #(update-player data player-id))
  (om/set-state! owner data)

  ;remove current selection and restore previous selection to the other player lists
  (doseq [cur-key (remove #(= list-id %) courts)]
    (let [cur (om/ref-cursor ((keyword cur-key) (om/root-cursor app-state)))]
      (om/transact! cur #(update-player-list cur @data))
      (om/set-state! ((keyword cur-key) @om-owner#) cur))))

(defn player-changed-event [e owner data]
  "Process the change event from a list"
  (let [player-id (.. e -target -value)
        list-id (.. e -target -id)]
    (player-changed owner data player-id list-id)))

(defn player-list
  "Get one player list as a React object"
  [data owner court-key]
  (swap! om-owner# assoc (keyword court-key) owner)
  (reify
    om/IInitState
    (init-state [_]
      {_ (conj data)})
    om/IRenderState
    (render-state [this state]
      (let [xs (om/ref-cursor ((keyword court-key) (om/root-cursor app-state)))
            players (vals (:players @xs))]
        (dom/div nil
                 (dom/select #js {:id       court-key
                                  :name     court-key
                                  :onChange #(player-changed-event % owner xs)
                                  :value    (:id (:current (:selected @xs)))}
                             (om/build-all #(player-option %1 %2 court-key)
                                           (sort-by (juxt :last :first) players)
                                           {:init-state xs})))))))

(defn load-list [list-id]
  "Loads one player select list"
  (om/root #(player-list %1 %2 list-id) nil
           {:target (. js/document (getElementById (str (name list-id) "-div")))}))

(defn load-state
  "Loads the state.  We reset everything and then load the players from the DB."
  [players]
  (doseq [cur-key courts]
    (swap! app-state assoc-in [(keyword cur-key) :players] {})
    (swap! app-state assoc-in [(keyword cur-key) :players] players)
    (swap! app-state assoc-in [(keyword cur-key) :selected :current] {})
    (swap! app-state assoc-in [(keyword cur-key) :selected :previous] {})))

(defn load-selected-state
  "update the state to reflect which players are assigned to a court in the match"
  [players]
  (doseq [av-pl players]
    (if-not (nil? (:court_number av-pl))
      (let [court (str "c" (:court_number av-pl))
            id (:id av-pl)
            player (cond
                     (or (= (:home_player1 av-pl) id) (= (:away_player1 av-pl) id)) "p1"
                     (or (= (:home_player2 av-pl) id) (= (:away_player2 av-pl) id)) "p2")
            ct-key (keyword (str court player))
            id-key (keyword (str (:id av-pl)))]
        (if-not (nil? player)
          (do
            (swap! app-state assoc-in [ct-key :selected :current] (id-key (:players (ct-key @app-state))))
            (let [cur (om/ref-cursor (ct-key (om/root-cursor app-state)))]
              (player-changed (ct-key @om-owner#) cur (name id-key) (name ct-key)))))))))

(defn get-players
  "return a hash of the available players"
  [players av-pl]
  (if (= (:available av-pl) 1)
    (conj players {(keyword (str (:id av-pl))) {:last (:last_name av-pl) :first (:first_name av-pl) :id (:id av-pl)}})
    players))

(defn ^:export set_lineup
  "load the set lineup form"
  [match-id]
  (comment "calling the match-availability function and will only use players marked as available
            doing this rather than writing an available players method")
  (go
    (let [response (<! (http/get (str "match-availability/" match-id)))
          body (:body response)
          rowCt (count body)
          players (reduce #(get-players %1 %2) {:0 {:last "  -- none --  " :first "" :id 0}} body)]
      (load-state players)
      (doall (map load-list courts))
      (load-selected-state body))
    (init-forfeit-btns match-id)
    (avail-setup match-id)
    (set-match-info match-id "ml")))