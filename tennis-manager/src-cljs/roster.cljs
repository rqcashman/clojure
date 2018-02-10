(ns tennis-manager.roster
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

(defn display-status
  [status]
  (case status
    "A" "Active"
    "I" "Inactive"
    "S" "Sub"
    status))

(defn format-phone
  [phone-number]
  (if (> (count phone-number) 7)
    (str (subs phone-number 0 3) "." (subs phone-number 3 6) "." (subs phone-number 6))
    (if (= (count phone-number) 7)
      (str (subs phone-number 0 3) "." (subs phone-number 3))
      phone-number)))

(defn roster-row
  "generate a row for the roster"
  [row]
  (str "<tr id='" (:id row) "' onclick='update_player_form(this.id);'>"
       "<td>" (:last_name row) "</td>"
       "<td>" (:first_name row) "</td>"
       "<td>" (:email row) "</td>"
       "<td>" (format-phone (str (:phone_number row))) "</td>"
       "<td>" (display-status (:status row)) "</td>"
       "</tr>"))


(defn ^:export roster [team-id]
  ;remove all but first row in the table
  (ef/at
    "#sr-details-body tr:not(:first-child)" (ef/remove-node))
  (go
    (let [response (<! (http/get (str "team-roster/" team-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#sr-details-body tr:last-child" (ef/after (roster-row row))))
            []
            body))
        (ef/at
          "#sr-details-body tr:last-child" (ef/after (not-found-row))))
      ;remove first row which was necessary to add the data rows
      (ef/at
        "#sr-details-body tr:first-child" (ef/remove-node)))))

(defn ^:export load_update_player_form [player-id]
  (go
    (let [response (<! (http/get (str "player/" player-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#up_first_name" (ef/set-form-input (:first_name row)))
              (ef/at
                "#up_last_name" (ef/set-form-input (:last_name row)))
              (ef/at
                "#up_email" (ef/set-form-input (:email row)))
              (ef/at
                "#up_phone_number" (ef/set-form-input (:phone_number row)))
              (ef/at
                "#up_status" (ef/set-form-input (:status row)))
              (ef/at
                "#up_team_id" (ef/set-form-input (:team_id row)))
              (ef/at
                "#up_player_id" (ef/set-form-input (:id row)))
              )
            []
            body))
        (ef/at
          (js/alert "Player not found"))))))
