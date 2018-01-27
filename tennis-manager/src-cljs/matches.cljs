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
  (go
    (let [response (<! (http/get (str "match-info/" match-id)))
          body (:body response)
          rowCt (count body)]
      (if (> rowCt 0)
        (do
          (reduce
            (fn [db-rows row]
              (ef/at
                "#av_match_id" (ef/content match-id))
              (ef/at
                "#av_match_date" (ef/content (:match_date row)))
              (ef/at
                "#av_match_time" (ef/content (:match_time row)))
              (ef/at
                "#av_match_location" (ef/content (:club_name row)))
              )
            []
            body))))))

