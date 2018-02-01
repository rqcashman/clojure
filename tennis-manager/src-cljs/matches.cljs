(ns tennis-manager.matches
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :as pp]
            [enfocus.core :as ef]
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
           "<td align='center'>" long-status "</td>"
           "<td>" (if (= (:response_date row) nil) "" (:response_date row)) "</td>"
           "</tr>"))))

(defn ^:export email_form [match-id]
  (go
    (let [response (<! (http/get (str "match-info/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#av_match_id" (ef/set-form-input match-id))
              (ef/at
                "#av_match_date" (ef/content (:match_date row)))
              (ef/at
                "#av_match_time" (ef/content (:match_time row)))
              (ef/at
                "#av_match_location" (ef/content (:club_name row)))
              )
            []
            body))))))

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
    (let [response (<! (http/get (str "match-info/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#pa_match_id" (ef/set-form-input match-id))
              (ef/at
                "#pa_match_date" (ef/content (:match_date row)))
              (ef/at
                "#pa_match_time" (ef/content (:match_time row)))
              (ef/at
                "#pa_match_location" (ef/content (:club_name row)))
              )
            []
            body))))
    (ef/at
      "#av-details-body tr:first-child" (ef/remove-node))))
