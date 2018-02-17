(ns tennis-manager.content.change-password
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]
        [tennis-manager.content.page-layout :as layout]
        [tennis-manager.data.auth-handler :as auth])
  (:require [clojure.string :as s]))


