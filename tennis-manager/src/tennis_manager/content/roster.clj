(ns tennis-manager.content.roster
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [tennis-manager.data.team-data-handler :as team]
        [tennis-manager.content.page-layout :as layout]
        [hiccup.page :only (html5 include-css include-js)]))

(def form-span 4)

(defn add-div
  [func-map]
  [:div.roster-action {:id (:id func-map)} (:content func-map)]
  )

(defn add-form-control
  [label options]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:label.control-label {:for (:id options)} label]]
   [:td [:input.form-control-sm options]]
   [:td {:width "5%"} "&nbsp;"]])

(defn show-roster
  []
  [:table.table.table-sm
   (layout/empty-row form-span)
   [:tr [:td {:colspan form-span :align "center"} [:h4 [:span#sr_team_name "Team"] " Roster"]]]
   (layout/hr-row form-span "90%")
   [:tr
    [:td {:width "5%:"}]
    [:td {:colspan 2}
     [:table#sr-details.table.table-striped.table-sm
      [:thead.table-inverse
       [:tr {:align "left"}
        [:td "Last Name"]
        [:td "First Name"]
        [:td "Email"]
        [:td "Phone Number"]
        [:td "Status"]]]
      [:tbody#sr-details-body
       (layout/empty-row 5)]]]
    [:td {:width "5%:"}]]
   (layout/hr-row form-span "90%")])


(defn add-player
  "docstring"
  []
  (let [title "Add Player"]
    [:form#addplayerform.form-horizontal {:method "post" :action "/add-player"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (add-form-control "First name:" {:id "ap_first_name" :name "first_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Last name:" {:id "ap_last_name" :name "last_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Email:" {:id "ap_email" :name "email" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Phone number:" {:id "ap_phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:button {:type "button" :onclick (str "return processRequest('#addplayerform', '/add-player', '" title "')")} title]]]
      [:tr.hidden-control [:td {:colspan form-span :align "center"} [:input.hidden-control {:id "ap_team_id" :name "team_id"}]]]
      (layout/empty-row form-span)]]))

(defn update-player-form
  "docstring"
  []
  (let [title "Update Player"]
    [:form#updateplayerform.form-horizontal {:method "post" :action "/add-player"}
     [:table.table.table-sm
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"} [:h4 title]]]
      (layout/hr-row form-span "90%")
      (add-form-control "First name:" {:id "up_first_name" :name "first_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Last name:" {:id "up_last_name" :name "last_name" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Email:" {:id "up_email" :name "email" :maxlength 45 :size 45 :type= "text"})
      (add-form-control "Phone number:" {:id "up_phone_number" :name "phone_number" :maxlength 10 :size 10 :onkeypress "return isNumberKey(event)"})
      [:tr
       [:td {:width "5%"} "&nbsp;"]
       [:td {:nowrap true} [:label.control-label {:for "up_status"} "Status"]]
       [:td [:select#up_status {:name "status"}
             [:option {:value "A"} "Active"]
             [:option {:value "I"} "Inactive"]
             [:option {:value "S"} "Sub"]]]
       [:td {:width "5%"} "&nbsp;"]]
      (layout/hr-row form-span "90%")
      (layout/empty-row form-span)
      [:tr [:td {:colspan form-span :align "center"}
            [:table
             [:td {:width "50%"} "&nbsp;"]
             [:td {:align "right" :nowrap "true"}
              [:button {:type "button" :onclick (str "return processRequest('#updateplayerform', '/update-player', '" title "')")} title]]
             [:td {:align "left" :nowrap "true"}
              [:button {:type "button" :onclick "change_form('show-roster');"} "Cancel"]]
             [:td {:width "5%"} "&nbsp;"]]]]
      [:tr.hidden-control [:td {:colspan form-span :align "center"} [:input.hidden-control {:id "up_team_id" :name "team_id"}] [:input.hidden-control {:id "up_player_id" :name "player_id"}]]]
      (layout/empty-row form-span)]]))



(defn select-form
  [roster-actions]
  (list
    [:div#roster-form-div.panel.panel-default
     [:table.table.table-sm.roster-form
      [:thead
       (layout/empty-row 3)
       [:tr [:td {:colspan 3 :align "center"} [:h4 "Select Roster Function"]]]
       (layout/hr-row 3 "90%")]
      [:tbody
       (layout/add-select team/teams layout/option "team_id" "Team:" 1 (str "team_changed(this.value);"))
       (layout/add-select #(layout/actions roster-actions) layout/option "roster-list" "Roster Action:" 1 (str "change_form(this.value);"))
       (layout/hr-row 3 "90%")
       (layout/empty-row 3)]]]
    [:br]
    [:hr]
    [:br])
  )

(defn roster []
  (let [roster-actions
        [{:id "add-player" :name "Add player" :content (add-player)}
         {:id "show-roster" :name "Show roster" :content (show-roster)}]]
    (list
      (select-form roster-actions)
      (map add-div roster-actions)
      (add-div {:id "update-player" :name "Update player" :content (update-player-form)})
      (add-div {:id "status-panel" :name "Status" :content (layout/status-content form-span)}))))
