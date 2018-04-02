(ns rf-tennis-manager.views-common
  (:require [re-frame.core :as re-frame]
            [goog.string :as gs]))

(defn nbsp
  "docstring"
  []
  (gs/unescapeEntities "&nbsp;"))

(defn empty-row
  [colspan]
  []
  [:tr [:td {:colSpan colspan} [:span (nbsp)]]])

(defn hr-row
  "docstring"
  [colspan width]
  [:tr [:td {:colSpan colspan} [:hr {:style {:width width}}]]])

