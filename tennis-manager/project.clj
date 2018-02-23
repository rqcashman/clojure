(defproject tennis-manager "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[buddy/buddy-auth "2.1.0"]
                 [buddy/buddy-core "1.4.0"]
                 [clj-time "0.13.0"]
                 [cljs-http "0.1.43"]
                 [cljsjs/bootstrap "3.3.6-1"]
                 [cljsjs/jquery "2.2.4-0"]
                 [cljsjs/jquery-ui "1.11.4-0"]
                 [com.sun.mail/javax.mail "1.6.0"]
                 [com.taoensso/nippy "2.13.0"]
                 [compojure "1.6.0"]
                 [enfocus "2.1.1"]
                 [enlive "1.1.6"]
                 [goog-jar "1.0.0"]
                 [hiccup "1.0.4"]
                 [mysql/mysql-connector-java "6.0.6"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/core.async "0.3.443"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.clojure/tools.reader "1.1.0"]
                 [ring/ring-codec "1.1.0"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-resource "17.06.1"]
            [lein-ring "0.12.3"]]
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
                                       :optimizations :advanced
                                       :pretty-print  true}}]})

