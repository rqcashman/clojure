(defproject revenue-calculator.calc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.sun.mail/javax.mail "1.6.0"]
                 [org.apache.poi/poi-ooxml "3.16"]
                 [org.clojure/java.jdbc "0.7.0-alpha3"]
                 [mysql/mysql-connector-java "5.1.18"]
                 [clj-time "0.13.0"]]
  :main revenue-calculator.core)
