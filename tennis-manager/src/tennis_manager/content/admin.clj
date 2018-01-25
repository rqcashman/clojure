(ns tennis-manager.content.admin
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.data-handler :as db]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

(def form-span 4)

(defn add-div
  [func-map]
  [:div.admin-action {:id (:id func-map)} (:content func-map)]
  )

(defn add-form-control
  [label options]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
   [:td [:input.form-control-sm options]]
   [:td {:width "5%"} "&nbsp;"]])

(defn states
  []
  [{:id "OH" :name "Ohio"} {:id "KY" :name "Kentucky"}])

(defn club-content
  []
  (let [title "Add Club"]
    [:form#addclubform.form-horizontal {:method "post" :action "/add-club"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (add-form-control "Club name:" {:id "club_name" :name "club_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Street:" {:id "street" :name "street" :maxlength 100 :size 70})
      (add-form-control "City:" {:id "city" :name "city" :maxlength 45 :size 45})
      (layout/add-select states layout/option "club_state" "State:" (- form-span 2))
      (add-form-control "Zip code:" {:id "zip_code" :name "zip_code" :maxlength 5 :size 5 :onkeypress "return isNumberKey(event)"})
      (add-form-control "Phone number:" {:id "phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRequest('#addclubform', '/add-club', '" title "')")} title]]]
      (layout/empty-row form-span)]]))

(defn team-content
  []
  (let [title "Add Team"]
    [:form#addteamform.form-horizontal {:method "post" :action "/add-team"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (layout/add-select db/clubs layout/option "club_id" "Club:" (- form-span 2))
      (add-form-control "Team name:" {:id "team_name" :name "team_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Default match time:" {:id "match_time" :name "match_time" :maxlength 10 :size 10})
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRequest('#addteamform', '/add-team', '" title "')")} title]]]
      (layout/empty-row form-span)]]))

(defn season-content
  []
  (let [title "Add Season"]
    [:form#addseasonform.form-horizontal {:method "post" :action "/add-season"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (add-form-control "Season name:" {:class "required" :id "season" :name "season" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Start date:" {:class "startdate" :id "start_date" :name "start_date" :size 9})
      (add-form-control "End date:" {:class "enddate" :id "end_date" :name "end_date" :size 9})
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRequest('#addseasonform', '/add-season', '" title "')")} title]]]
      (layout/empty-row form-span)]]))

(defn load-schedule
  []
  (println "load schedule")
  (let [title "Load Schedule"]
    [:form#loadscheduleform.form-horizontal {:method "post" :action "/load-schedule"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (layout/add-select db/seasons layout/option "season_id" "Season:" (- form-span 2))
      [:tr [:td {:colspan form-span :align "center"}
            [:textarea {:name "schedule" :rows 30 :cols 50}]]]
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRequest('#loadscheduleform', '/load-schedule', '" title "')")} title]]]
      (layout/empty-row form-span)]]))


(defn load-schedule-file
  []
  (println "load schedule file")
  (let [title "Load Schedule File"]
    [:form#loadschedulefileform.form-horizontal {:method "post" :action "/load-schedule-file" :enctype "multipart/form-data"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (layout/add-select db/seasons layout/option "season_id" "Season:" (- form-span 2))
      (add-form-control "Select file:" {:class "required" :id "schedule" :name "schedule" :type "file" :size 100})
     ; [:tr [:td {:colspan form-span :align "center"}
      ;      [:input {:name "schedule" :type "file"}]]]
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRequest('#loadschedulefileform', '/load-schedule-file', '" title "')")} title]]]
      (layout/empty-row form-span)]]))



;(def admin-actions
;  [{:id "add-club" :name "Add club" :content (club-content)}
;   {:id "add-season" :name "Add season" :content (season-content)}
;   {:id "add-team" :name "Add team" :content (team-content)}
;   {:id "manage-schedule" :name "Manage schedule" :content (load-schedule)}])



(defn select-form
  [admin-actions]
  (println "in select-form")
  (list
    [:div#admin-form-div.panel.panel-default
     [:table.table.table-sm.admin-form
      [:thead
       (layout/empty-row 3)
       [:tr [:td {:colspan 3 :align "center"} [:h4 "Select Admin Function"]]]
       (layout/hr-row 3 "90%")]
      [:tbody
       (layout/add-select #(layout/actions admin-actions) layout/option "admin-list" "Admin Action:" 1 (str "change_form(this.value);"))
       (layout/hr-row 3 "90%")
       (layout/empty-row 3)]]]
    [:br]
    [:hr]
    [:br])
  )

(defn admin []
  (println "in admin")
  (let [admin-actions
        [{:id "add-club" :name "Add club" :content (club-content)}
         {:id "add-season" :name "Add season" :content (season-content)}
         {:id "add-team" :name "Add team" :content (team-content)}
         {:id "load-schedule" :name "Load schedule" :content (load-schedule)}
         {:id "load-schedule-file" :name "Load schedule file" :content (load-schedule-file)}]]
    (list
      (select-form admin-actions)
      (map add-div admin-actions)
      (add-div {:id "status-panel" :name "Status" :content (layout/status-content form-span "location.reload();")}))))
