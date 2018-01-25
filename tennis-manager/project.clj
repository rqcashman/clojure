(defproject tennis-manager "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[clj-time "0.13.0"]
                 [cljsjs/bootstrap "3.3.6-1"]
                 [cljs-http "0.1.43"]
                 [cljsjs/jquery "2.2.4-0"]
                 [cljsjs/jquery-ui "1.11.4-0"]
                 [com.sun.mail/javax.mail "1.6.0"]
                 [compojure "1.5.1"]
                 [enfocus "2.1.1"]
                 [enlive "1.1.6"]
                 [goog-jar "1.0.0"]
                 [hiccup "1.0.4"]
                 [mysql/mysql-connector-java "5.1.18"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.542"]
                 [org.clojure/core.async "0.3.442"]
                 [org.clojure/java.jdbc "0.7.0-alpha3"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]]

  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-resource "16.9.1"]
            [lein-ring "0.9.7"]]
  :ring {:handler tennis-manager.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}}
  :prep-tasks ["javac" "compile" "resource"]
  :hooks [leiningen.resource]
  :resource {
             :resource-paths ["src-resources"]
             :target-path    "resources/public"
             :includes       [#".*"]
             :excludes       [#".*~"]
             :silent         false
             :verbose        false
             }
  :cljsbuild {
              :builds [{
                        :source-paths ["src-cljs"]
                        :compiler     {
                                       :output-to     "resources/public/main.js"
                                       :optimizations :whitespace
                                       :pretty-print  true}}]})

