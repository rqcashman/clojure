(ns tennis-manager.content.page-layout
  (:use [hiccup.page :only (html5 include-css include-js)]
        [clojure.string :as s]))

(defn application [title js-file & content]
  (html5 {:ng-app "myApp" :lang "en"}
         [:head
          [:title title]
          (include-js "main.js")
          (include-js "https://code.jquery.com/jquery-3.2.1.min.js")
          (include-js "https://code.jquery.com/ui/1.12.1/jquery-ui.min.js")
          (include-js "https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.min.js")
          (include-js "https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js")
          (include-js "https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.js")
          (if (complement (s/blank? js-file))
            (include-js js-file))
          (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css")
          (include-css "https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css")
          (include-css "https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.css")
          (include-css "https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css")
          (include-css "tennis.css")
          [:body
           [:div content]]]))

(defn actions
  [actions]
  ;(println "actions" actions)
  actions
  )

(defn empty-row
  [colspan]
  []
  [:tr [:td {:colspan colspan}]])

(defn hr-row
  "docstring"
  [colspan width]
  [:tr [:td {:colspan colspan} [:hr {:color "black" :width width}]]])

(defn status-content
  [form-span click-fn]
  [:table.table.table-sm
   (empty-row form-span)
   [:tr [:td {:colspan form-span :align "center"} [:h4 "Status of " [:span#status-title]]]]
   (hr-row form-span "90%")
   [:tr
    [:td {:width "5%"} "&nbsp;"]
    [:td "Status:"]
    [:td {:align "left"} [:span#status-content]]
    [:td {:width "5%"} "&nbsp;"]]
   (hr-row form-span "90%")
   (empty-row form-span)
   [:tr [:td {:colspan form-span :align "center"}
         [:button {:type "button" :value "ok" :onclick click-fn} "OK"]]]
   (empty-row form-span)])


(defn option
  "this work for both season and team because both have id and name in their maps"
  [curr]
  ;(println "add option: " (:name curr))
  [:option {:value (:id curr)} (:name curr)])

(defn add-select
  ([data-fn option-fn id title colspan]
   (add-select data-fn option-fn id title colspan nil))
  ([data-fn option-fn id title colspan change-fn]
   [:tr
    [:td {:width "5%"}]
    [:td {:width "15%" :nowrap "true"} title]
    [:td {:colspan colspan}
     (conj [:select.state.form-control-sm {:id id :name id :onChange change-fn}] (map option-fn (data-fn)))]]))
