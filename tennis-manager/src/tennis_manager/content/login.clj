(ns tennis-manager.content.login
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]
        [tennis-manager.content.page-layout :as layout]
        [tennis-manager.data.club-data-handler :as club]
        [tennis-manager.data.season-data-handler :as season]))

(defn add-form-control
  [label options]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:b [:label.control-label {:for (:id options)} label]]]
   [:td [:input.form-control-sm options]]
   [:td {:width "5%"} "&nbsp;"]])

(defn add-form-button
  []
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:colspan 2 :align "center"}
    [:button {:type "submit"} "Login"]]
   [:td {:width "5%"} "&nbsp;"]])

(defn login
  "docstring"
  []
  [:form#addclubform.form-horizontal {:method "post" :action "/login"}
   [:table {:align "center" :border "2px" :width "20%"}
    [:tr
     [:td
      [:table {:width "100%"}
       [:thead.table-inverse
        [:td {:colspan 4} "Login"]]
       (layout/empty-row 4)
       (layout/empty-row 4)
       (add-form-control "User name:" {:id "username" :name "username" :maxlength 45 :size 30 :type "text"})
       (layout/empty-row 4)
       (add-form-control "Password:" {:id "password" :name "password" :maxlength 45 :size 30 :type "password"})
       (layout/empty-row 4)
       (layout/hr-row 4 "90%")
       (layout/empty-row 4)
       (add-form-button)
       (layout/empty-row 4)]]]]])
