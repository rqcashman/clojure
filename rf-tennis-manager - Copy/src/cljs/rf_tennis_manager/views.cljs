(ns rf-tennis-manager.views
  (:require [re-frame.core :as re-frame]
            [rf-tennis-manager.events :as events]
            [rf-tennis-manager.subs :as subs]
            [rf-tennis-manager.db :as db]))
(enable-console-print!)

(defn add-player
  [player]
  [:option {:value (:id player) :key (:id player)} (str (:last player) ", " (:first player))])

(defn add-test
  "docstring"
  [test-str]
  (println test-str)
  [:option {:value test-str :key test-str} test-str])


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        players (re-frame/subscribe [::subs/players])]
    (fn []
      [:div
       [:div "Helloxxxx from " @name]
       [:br]
       [:button {:type "button" :onClick #(re-frame/dispatch-sync [::events/initialize-default-db])} "Re-init"]
       [:br]
       [:button {:type "button" :onClick #(re-frame.core/dispatch [::events/main-btn-click "Hi from the main panel"])} "Change Panel"]
       [:br]
       [:div
        [:table
         [:tbody
          [:tr
           [:td]
           (let [test (re-frame/subscribe [::subs/test])]
             (println "build test " @test)
             [:select {:name "test" :id "test"}
              (doall (map add-test @test))])
           [:td
            (let [list1 (re-frame/subscribe [::subs/c1p1])]
              (println "build list 1")
              [:select {:name "c1p1" :id "c1p1-list" :onChange #(re-frame.core/dispatch [::events/list-changed "c1p1"])}
               (doall (map add-player (sort-by (juxt :last :first) (vals @list1))))])]
           [:td
            (let [list3 (re-frame/subscribe [::subs/c1p3])]
              (println "build list 3")
              [:select {:name "c1p3" :id "c1p3-list"}
               (doall (map add-player (sort-by (juxt :last :first) (vals @list3))))])]
           [:td
            (let [list2 (re-frame/subscribe [::subs/c1p2])]
              (println "build list 2")
              [:select {:name "c1p2" :id "c1p2-list" :onChange #(re-frame.core/dispatch [::events/list-changed "c1p2"])}
               (doall (map add-player (sort-by (juxt :last :first) (vals @list2))))])]]]]]])))

