(ns rf-tennis-manager.content.page-layout
  (:use [hiccup.page :only (html5 include-css include-js)]
        [clojure.string :as s]))

(defn logout-btns
  "docstring"
  []
  ([:hr]))


(defn application [title js-file react? & content]
  (html5 {:ng-app "myApp" :lang "en"}
         [:head
          [:title title]
          (if react?
            (include-js "js/compiled/app.js"))
          (include-js "https://code.jquery.com/jquery-3.2.1.min.js")
          (include-js "https://code.jquery.com/ui/1.12.1/jquery-ui.min.js")
          (include-js "https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.min.js")
          (include-js "https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js")
          (include-js "https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.js")
          (if-not (s/blank? js-file)
            (include-js js-file))
          (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css")
          (include-css "https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css")
          (include-css "https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.css")
          (include-css "https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css")
          (include-css "tennis.css")
          (include-css "pickaday.css")
          [:body
           [:div content]]]))

(defn empty-row
  [colspan]
  []
  [:tr [:td {:colspan colspan}] "&nbsp;"])

(defn hr-row
  [colspan width]
  [:tr [:td {:colspan colspan} [:hr {:width width}]]])


