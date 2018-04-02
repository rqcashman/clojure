(ns rf-tennis-manager.content.schedule
  (:use [hiccup.form]))

(defn schedule
  [session]
  (list
    [:div#sched_call_status {:class "div-panel-show"}]
    [:div#sched_select_form{:class "div-panel-show"}]
    [:div#sched_schedule{:class "div-panel-show"}]
    [:script "rf_tennis_manager.core.init_schedule()"]))
