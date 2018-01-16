(ns revenue-calculator.core
  (:gen-class)
  (:require [revenue-calculator [calculation-values :as cv]
             [calc-spreadsheet :as sh]
             [calc-spreadsheet-dispatch :as di]])
  (:import (java.util Date)))


(defn -main
  "docstring"
  []
  (let [dir (str (System/getProperty "user.dir") "\\test_files")
        calc-values (cv/calculation-values-db (str dir "\\input\\input_values.txt"))
        sfx (int (rand 1000))]
    ;(println calc-values)
    (sh/create-revenue-spreadsheet calc-values (str dir "\\output\\revenue_" sfx ".xlsx"))
    (di/create-revenue-spreadsheet calc-values (str dir "\\output\\revenue_di_" sfx ".xlsx")))
  )

