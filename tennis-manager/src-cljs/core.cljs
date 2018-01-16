(ns tennis-manager.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :as pp]
            [enfocus.core :as ef]
            [clojure.string :as s]))

(defn match-time
  "docstring"
  [match-time-24]
  (let [hour (subs match-time-24 0 2)]
    (if (< hour 13)
      (str match-time-24 " AM")
      (str (pp/cl-format nil "~2,'0d" (- hour 12)) (subs match-time-24 2) " PM"))))

(defn schedule-row
  "generate a row for the schedule"
  [row]
  (comment
    Adding a name attribute to the home and away team so we can hilight the selected team
    Used name instead of id because id must be unique
    )
  (str "<tr id='match-" (:match_id row) "'>"
       "<td>" (:match_date row) "</td>"
       "<td>" (match-time (:match_time row)) "</td>"
       "<td name='id-" (:home_team_id row) "'>" (:home_team row) "</td>"
       "<td name='id-" (:away_team_id row) "'>" (:away_team row) "</td>"
       "<td class='points'>" (:home_team_points row) "</td>"
       "<td class='points'>" (:away_team_points row) "</td>"
       "</tr>"))

(defn not-found-row
  "generate a not found row"
  []
  (str "<tr><td colspan='6' align='center'><h2 style='color:red'>No schedule found for team</h2></td></tr>"))

(defn season-list-option
  "docstring"
  [row]
  (str "<option value='" (:id row) "'>" (:name row) "</option>"))


(defn team-list-option
  "docstring"
  [row]
  (str "<option value='" (:id row) "'>" (:name row) "</option>"))

(defn ^:export schedule [season-id team-id]
  ;remove all but first row in the table
  (ef/at
    "#schedule-body tr:not(:first-child)" (ef/remove-node))
  (go
    (let [response (<! (http/get (str "team-schedule/" season-id "/" team-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#schedule-body tr:last-child" (ef/after (schedule-row row))))
            []
            body)
          ;add hilighting to cells of the selected team
          (ef/at
            (str "#schedule-body td[name='id-" team-id "']") (ef/add-class "schedule-selected-team")))
        (ef/at
          "#schedule-body tr:last-child" (ef/after (not-found-row))))
      ;remove first row which was necessary to add the data rows
      (ef/at
        "#schedule-body tr:first-child" (ef/remove-node))
      (js/show_schedule)))
  (go
    (let [response (<! (http/get (str "team/" team-id)))
          body (:body response)]
      (reduce
        (fn [db-rows row]
          (ef/at
            "#sched-hdr td[id='sched-team-name']" (ef/content (:name row))))
        []
        body)))
  (go
    (let [response (<! (http/get (str "season/" season-id)))
          body (:body response)]
      (reduce
        (fn [db-rows row]
          (ef/at
            "#sched-hdr td[id='sched-season-name']" (ef/content (:name row))
            "#sched-hdr td[id='sched-season-start']" (ef/content (:start_date row))
            "#sched-hdr td[id='sched-season-end']" (ef/content (:end_date row))
            ))
        []
        body))))

(defn ^:export load_schedule_form []
  (go
    (let [response (<! (http/get "seasons"))
          body (:body response)]
      (reduce
        (fn [db-rows row]
          (ef/at
            "#season-list option:last-child " (ef/after (season-list-option row))))
        []
        body)
      ;remove first empty row which was necessary to add the data rows
      (ef/at
        "#season-list option:first-child" (ef/remove-node))))
  (go
    (let [response (<! (http/get "teams"))
          body (:body response)]
      (reduce
        (fn [db-rows row]
          (ef/at
            "#team-list option:last-child " (ef/after (team-list-option row))))
        []
        body)
      ;remove first empty row which was necessary to add the data rows
      (ef/at
        "#team-list option:first-child" (ef/remove-node)))))

(defn ^:export db_update_request [form_id uri status_title]
  (go
    (let [values (ef/from form_id (ef/read-form))
          response (<! (http/post uri {:form-params values}))
          body (:body response)]
      (let [status-class (if (= (:status body) "success") "success" "error")]
        (ef/at
          "#status-title" (ef/content status_title)
          "#status-content" (ef/do->
                              (ef/content (:msg body))
                              (ef/set-class status-class)))))))

