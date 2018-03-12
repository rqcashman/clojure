(defproject rf-tennis-manager "0.1.0-SNAPSHOT"
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
                 [goog-jar "1.0.0"]
                 [hiccup "1.0.4"]
                 [mysql/mysql-connector-java "6.0.6"]
                 [org.omcljs/om "1.0.0-beta1"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/core.async "0.3.443"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.clojure/tools.reader "1.1.0"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.5"]
                 [ring "1.4.0"]
                 [ring/ring-codec "1.1.0"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [yogthos/config "0.8"]]

  :plugins [[lein-cljsbuild "1.1.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs     ["resources/public/css"]
             :ring-handler rf-tennis-manager.handler/dev-handler}
  :hooks [leiningen.cljsbuild]
  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.4"]]

    :plugins      [[lein-figwheel "0.5.13"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs" "src/cljs/rf_tennis_manager"]
     ;:figwheel     {:on-jsload "rf-tennis-manager.core/mount-root"}
     :compiler     {
                    :main                 rf-tennis-manager.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :pretty-print         true
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar          true
     :compiler     {:main            rf-tennis-manager.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    ]}

  :main rf-tennis-manager.server

  :aot [rf-tennis-manager.server]

  :uberjar-name "rf-tennis-manager.jar"

  :prep-tasks [["cljsbuild" "once" "min"] "compile"]
  )
