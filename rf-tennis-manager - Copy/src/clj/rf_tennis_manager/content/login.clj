(ns rf-tennis-manager.content.login
  (:require [clojure.string :as s]
            [hiccup.form]
            [hiccup.element :only (link-to)]
            [hiccup.page :only (html5 include-css include-js)]
            [rf-tennis-manager.content.page-layout :as layout]
            [rf-tennis-manager.data.auth-messages :as am]
            [rf-tennis-manager.data.auth-handler :as auth]
            [rf-tennis-manager.data.club-data-handler :as club]
            [rf-tennis-manager.data.season-data-handler :as season]))

(def LOGIN_PAGE 1)
(def CHANGE_PASSWORD_PAGE 2)

(defn add-form-control
  [label options]
  [:tr]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:nowrap true} [:b [:label.control-label {:for (:id options)} label]]]
   [:td [:input.form-control-sm options]]
   [:td {:width "5%"} "&nbsp;"]])

(defn add-form-button
  [title]
  [:tr
   [:td {:width "5%"} "&nbsp;"]
   [:td {:colspan 2 :align "center"}
    [:button {:type "submit"} title]]
   [:td {:width "5%"} "&nbsp;"]])

(defn add-error-message
  "docstring"
  [errorno]
  (if-not (s/blank? errorno)
    (list [:tr
           [:td "&nbsp;"]
           [:td [:b "Login message:"]]
           [:td [:span {:style "font-weight:bold;color:red"} (:msg ((keyword errorno) am/authentication-error-list))]]
           [:td "&nbsp;"]]
          (layout/empty-row 4))))

(defn get-user-name
  "docstring"
  [request session]
  (cond
    (= (s/blank? (:username request)) false) (:username request)
    (= (s/blank? (:identity session)) false) (:email (auth/get-user-from-session-id (:identity session)))))

(defn login
  "docstring"
  [page-type session request]
  (let [title (if (= page-type LOGIN_PAGE) "Login" "Change Password")
        action (if (= page-type LOGIN_PAGE) "/login" "/chgpassword")
        username (get-user-name request session)
        errorno (:errno request)]
    [:form#loginform.form-horizontal {:method "post" :action action}
     [:table.table.table-sm.login-form {:align "center"}
      [:tr
       [:td
        [:table {:width "100%"}
         [:thead.table-inverse
          [:td {:colspan 4} title]]
         (layout/empty-row 4)
         (layout/empty-row 4)
         (add-error-message errorno)
         (add-form-control "User name:" {:id "username" :name "username" :autocomplete "username" :maxlength 45 :size 30 :type "text" :value username})
         (add-form-control "Password" {:id "password" :name "password" :autocomplete "current-password" :maxlength 45 :size 30 :type "password"})
         (if (= page-type CHANGE_PASSWORD_PAGE)
           (list
             (layout/hr-row 4 "10%")
             (add-form-control "New password" {:id "new_password" :name "new_password" :maxlength 45 :size 30 :type "password"})
             (add-form-control "Re-type password" {:id "confirm_password" :name "confirm_password" :maxlength 45 :size 30 :type "password"})))
         (layout/empty-row 4)
         (layout/hr-row 4 "90%")
         (layout/empty-row 4)
         (add-form-button title)
         (layout/empty-row 4)]]]]]))
