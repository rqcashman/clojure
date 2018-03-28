(ns rf-tennis-manager.content.roster
  (:use [hiccup.form]))

(defn roster
  [session]
  (list
    [:div#ro_call_status {:class "div-panel-show"}]
    [:div#ro_select_form{:class "div-panel-show"}]
    [:div#ro_show_roster{:class "div-panel-show"}]
    [:div#ro_update_player {:class "div-panel-show"}]
    [:div#ro_add_player{:class "div-panel-show"}]
    [:script "rf_tennis_manager.core.init_roster()"]))