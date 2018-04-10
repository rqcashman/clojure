(ns rf-tennis-manager.content.admin
  (:use [hiccup.form]))

(defn admin
  [session]
  (list
    [:div#admin_call_status {:class "div-panel-show"}]
    [:div#admin_select {:class "div-panel-show"}]
    [:div#admin_load_schedule {:class "div-panel-show"}]
    [:div#admin_add_season {:class "div-panel-show"}]
    [:div#admin_add_club {:class "div-panel-show"}]
    [:div#admin_add_team {:class "div-panel-show"}]
    [:script "rf_tennis_manager.core.init_admin()"]))
