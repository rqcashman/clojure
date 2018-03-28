(ns rf-tennis-manager.content.matches
  (:require [hiccup.form]))

(defn matches
  "Sets up the intial matches page."
  [session]
  (list
    [:div#ma_show_schedule {:class "div-panel-show"}]
    [:div#ma_send_availability_email {:class "div-panel-show"}]
    [:div#ma_show_availability {:class "div-panel-show"}]
    [:div#ma_set_lineup {:class "div-panel-show"}]
    [:div#ma_send_lineup_email {:class "div-panel-show"}]
    [:div#ma_call_status {:class "div-panel-show"}]
    [:script "rf_tennis_manager.core.init_matches()"]))

