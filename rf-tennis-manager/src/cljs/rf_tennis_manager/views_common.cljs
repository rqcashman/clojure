(ns rf-tennis-manager.views-common
  (:require [re-frame.core :as re-frame]
            [goog.string :as gs]))

(defn empty-row
  [colspan]
  []
  [:tr [:td {:colSpan colspan} [:span (gs/unescapeEntities "&nbsp;")]]])

(defn hr-row
  "docstring"
  [colspan width]
  [:tr [:td {:colSpan colspan} [:hr {:width width}]]])

