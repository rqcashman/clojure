(ns rf-tennis-manager.views-common
  (:require [goog.string :as gs]
            [rf-tennis-manager.form-validator :as form-val]
            [rf-tennis-manager.subs :as subs]
            [re-frame.core :as rf]))

(defn nbsp
  []
  (gs/unescapeEntities "&nbsp;"))

(defn empty-row
  [colspan]
  [:tr [:td {:colSpan colspan} (nbsp)]])

(defn required-message
  [colspan]
  [:tr [:td (nbsp)] [:td {:colSpan (dec colspan)} [:span.red-bold "* "] [:span " - required field"]]])

(defn hr-row
  [colspan width]
  [:tr [:td {:colSpan colspan} [:hr {:style {:width width}}]]])

(defn add-options
  [option-hash]
  (reduce (fn [list opt-key]
            (conj list [:option {:value (opt-key option-hash) :key (opt-key option-hash)} (name opt-key)]))
          () (reverse (sort (keys option-hash)))))

(defn add-form-select
  [select-name tab-name form-name field-name option-hash]
  (let [fld-hash @(rf/subscribe [::subs/form-data tab-name form-name field-name])]
    [:tr
     [:td {:style {:width "5%"}} (nbsp)]
     [:td (:name fld-hash)]
     [:td
      [:select {:name     select-name :value (:value fld-hash)
                :onChange #(rf/dispatch [::form-val/validate-field [:roster (keyword form-name) :fields (keyword field-name)] (-> % .-target .-value)])}
       (add-options option-hash)] (nbsp) [:span.error (:error-msg fld-hash)]]
     [:td]]))

(defn add-form-input
  [options tab-name form-name field-name]
  (let [fld-hash @(rf/subscribe [::subs/form-data tab-name form-name field-name])]
    [:tr
     [:td {:style {:width "5%"}} (nbsp)]
     [:td (:name fld-hash) (if (:required? fld-hash) [:span.red-bold " *"])]
     [:td
      [:input (conj options {:value (:value fld-hash) :onChange #(rf/dispatch [::form-val/validate-field [:roster (keyword form-name) :fields (keyword field-name)] (-> % .-target .-value)])})]
      [:span.error (nbsp) (:error-msg fld-hash)]]
     [:td]]))

(defn format-phone-number
  [phone-number]
  (cond
    (> (count phone-number) 7) (str "(" (subs phone-number 0 3) ") " (subs phone-number 3 6) " - " (subs phone-number 6))
    (> (count phone-number) 4) (str (subs phone-number 0 3) " - " (subs phone-number 3))
    :else phone-number))

