(ns rf-tennis-manager.views-common
  (:require [re-frame.core :as re-frame]
            [goog.string :as gs]))

(defn nbsp
  []
  (gs/unescapeEntities "&nbsp;"))

(defn empty-row
  [colspan]
  [:tr [:td {:colSpan colspan} (nbsp)]])

(defn hr-row
  [colspan width]
  [:tr [:td {:colSpan colspan} [:hr {:style {:width width}}]]])

