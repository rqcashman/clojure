(ns tennis-manager.matches
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [enfocus.macros :as em])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :as pp]
            [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects]
            [clojure.string :as s]))


(defn not-found-row
  "generate a not found row"
  []
  (str "<tr><td colspan='5' align='center'><h2 style='color:red'>No players added to roster</h2></td></tr>"))

(defn availability-row
  "generate an availability row"
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
  "docstring"
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

(defn ^:export email_form [match-id]
  (set-match-info match-id "av"))

(defn ^:export availability [match-id]
  (ef/at
    "#av-details-body tr:not(:first-child)" (ef/remove-node))
  (go
    (let [response (<! (http/get (str "match-availability/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#av-details-body tr:last-child" (ef/after (availability-row row true))))
            []
            body)
          (reduce
            (fn [db-rows row]
              (ef/at
                "#av-details-body tr:last-child" (ef/after (availability-row row false))))
            []
            body))))
    (ef/at
      "#av-details-body tr:first-child" (ef/remove-node)))
  (set-match-info match-id "pa"))

(defn add-options
  "docstring"
  [list elem]
  )

(defn add-option
  "docstring"
  [list row]
  (if (= (:available row) 1)
    (conj list (str "<option value='" (:id row) "'>" (:last_name row) ", " (:first_name row) "</option>"))
    list))

(defn set-option
  "docstring"
  [court_number player_number player_id]
  (ef/at (str "#c" court_number "-p" player_number " option[value='" player_id "']") (ef/set-prop "selected" "selected"))
  )

(defn init-player-options
  "docstring"
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
            (set-option court_number 1 player_id))
          (if (or (= home_p2 player_id) (= away_p2 player_id))
            (set-option court_number 2 player_id)))))))

(defn updatePlayerControls
  [court disabled]
  (let [player1 (str "#c" court "-p1")
        player2 (str "#c" court "-p2")]
    (go
      (ef/at player1 (ef/set-prop "disabled" disabled))
      (ef/at player2 (ef/set-prop "disabled" disabled)))))

(defn updateForfeitControls
  [court disabled]
  (let [no-forfeit (str "#c" court "-forfeit-none")
        team-forfeit (str "#c" court "-forfeit")
        opp-forfeit (str "#c" court "-forfeit-opp")]
    (go
      (ef/at no-forfeit (ef/set-prop "disabled" disabled))
      (ef/at team-forfeit (ef/set-prop "disabled" disabled))
      (ef/at opp-forfeit (ef/set-prop "disabled" disabled)))))

(defn ^:export forfeit_selected
  [elem-name elem-value]
  ;elem-name is c4-forfeit-none (0) c4-forfeit (1) or c4-forfeit-opp (2).  Second character is the court number
  ;forfeits must start from court 4 so we enable/disable the controls accordingly
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
  "docstring"
  [forfeit-val team-id]
  (cond
    (or (= forfeit-val "0") (= forfeit-val nil)) "0"
    (= forfeit-val (int team-id)) "1"
    :else "2"))

;(defn init-forfeit-btnsxxx
;  "docstring"
;  [rcds]
;  (go
;    (dotimes [x 4]
;      (let [court (inc x)
;            btn-val (get-btn-val (:forfeit_team_id rds) (ef/at "ml_team_id" (ef/get-attr "value")))]
;        (js/alert "btn val: " btn-val)
;        ; (ef/at "#c2-forfeit-opp" (ef/set-prop "selected" "selected"))
;        (case btn-val
;          "2" (ef/at (str "#c" court "-forfeit-none") (ef/set-prop "selected" "selected"))
;          "1" (ef/at (str "#c" court "-forfeit") (ef/set-prop "selected" "selected"))
;          "0" (ef/at (str "#c" court "-forfeit-opp") (ef/set-prop "selected" "selected")))))))

(defn select-forfeit-btn
  "docstring"
  [btn-id value]
  (ef/at (str "#" btn-id) (ef/set-prop "checked" "checked"))
  (forfeit_selected btn-id value)
  )

(defn init-forfeit-btns
  "docstring"
  [match-id]
  (go
    (let [response (<! (http/get (str "match-forfeits/" match-id)))
          body (:body response)
          rowCt (count body)]
      ;using reverse because we must process from court 4 to court and the SQL is sorted by court number
      (doseq [row (reverse body)]
        (let [court_number (:court_number row)
              btn-val (get-btn-val (:forfeit_team_id row) (:team_id (ef/from "#updatelineup" (ef/read-form))))]
          (case btn-val
            "0" (select-forfeit-btn (str "c" court_number "-forfeit-none") btn-val)
            "1" (select-forfeit-btn (str "c" court_number "-forfeit") btn-val)
            "2" (select-forfeit-btn(str "c" court_number "-forfeit-opp") btn-val)))))))

(defn ^:export set_lineup [match-id]
  (ef/at "option" (ef/remove-node))
  (go
    ;calling the match-availability function and will only use player marked as available
    ;doing this rather than writing an available players method
    (let [response (<! (http/get (str "match-availability/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (let [first-opt (list (str "<option value='0'></option>"))
              options (reduce #(add-option %1 %2) first-opt body)]
          (ef/at "select" (ef/content (s/join (reverse options))))
          (init-player-options body)
          (init-forfeit-btns match-id)))))
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







