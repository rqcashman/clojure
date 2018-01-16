(ns revenue-calculator.calculation-values
  (:require [clojure.java.jdbc :as j])
  (:use clojure.java.io))

(def non-overridable-args {:ss_rate               0.062
                           :ss_cap                118500
                           :medicare_rate         0.0145
                           :fed_unempolyment_amt  42
                           :ohio_unempolyment_amt 972
                           :unemployment_mat      1014
                           :ohio_workers_comp_pct 0.0099})

(def overridable-args {:bill_rate           110
                       :vacation_days       15
                       :holidays            10
                       :iterations          10
                       :iteration_increment 5
                       :yearly_hours        2080
                       :workday_hours       8
                       :vendor_mgmt_pct     0.05
                       :emp_pay_pct         0.55
                       :emp_dcp_pct         0.10
                       :k401_pct            0.03
                       :hsa_amt             4000
                       :dcp_hours           168
                       :profit_sharing_pct  0.10
                       :company_benefit_pct 0.80
                       :benefit_amt         12000})

(def mysql-db {:dbtype   "mysql"
               :dbname   "clojure_test"
               :host     "localhost"
               :port     3306
               :user     "root"
               :password "Cash1234"})

(defn file-name
  ([] (file-name 'defaultfilename))
  ([name] (println "in file name" name))
  )


;first attempt.  A lot more work without using reduce
(defn value-map-1
  "docstring"
  [val-overridable]
  (j/query mysql-db
           ["select * from revenue_calc_values where overridable = ? order by calc_key" val-overridable]
           {:result-set-fn (fn [rs]
                             ;tried this with doseq and storing data in a transient map
                             ;did not work, stopped storing after 8 rcds
                             ;not correct way to do it (https://groups.google.com/forum/#!topic/clojure/Tul62m1I0DM)
                             (loop [idx 0 out (transient {}) n (count rs)]
                               (if (< idx n)
                                 (do
                                   (let [row (nth rs idx)]
                                     ;(recur (inc idx) (conj! out {(keyword (:calc_key row)) (:calc_value row)}) n)))
                                     (recur (inc idx) (assoc! out (keyword (:calc_key row)) (:calc_value row)) n)))
                                 (persistent! out))))}))

(defn value-map-2
  "docstring"
  [val-overridable]
  ;as-arrays true returns a list of vectors.  The first vector has the column names prepended with a colon
  ;the rest just have the values
  ;([:calc_key :calc_value] [benefit_amt 12000.0] [bill_rate 100.0])

  ;the colon in the field returned for the column headings  meant I could not get identical to work at first
  ;casting the value to str did not work.  Next line failed
  ;   (println rs "----" (nth rs 0) (nth rs 1) "cast to str" (str (nth rs 0)) " identical: " (identical? (str (nth rs 0)) ":calc_key"))

  ;this worked
  ;   (if (not= (compare (str (nth rs 0)) ":calc_key") 0)
  ;     (conj rcds {(keyword (nth rs 0)) (nth rs 1)})
  ;     (conj rcds)))
  (j/query mysql-db
           ["select calc_key, calc_value from revenue_calc_values where overridable = ? order by calc_key" val-overridable]
           {:as-arrays?    true
            :result-set-fn (fn [rs]
                             ;(println "rcd set" rs)
                             (reduce (fn [rcds curr-rcd]
                                       ;(println "------" curr-rcd)
                                       ;need to always return rcds from loop so we need the else
                                       (if-not (identical? (nth curr-rcd 0) (keyword "calc_key"))
                                         (assoc rcds (keyword (nth curr-rcd 0)) (nth curr-rcd 1))
                                         (conj rcds)))
                                     {},
                                     rs))}))

(defn value-map-3
  "docstring"
  [val-overridable]
  ;as-arrays false returns a list of maps per row
  ;({:calc_key benefit_amt, :calc_value 12000.0} {:calc_key bill_rate, :calc_value 100.0})
  (j/query mysql-db
           ["select calc_key, calc_value from revenue_calc_values where overridable = ? order by calc_key" val-overridable]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       ;(conj rcds {(keyword (:calc_key curr_rcd)) (:calc_value curr_rcd)}))
                                       (assoc rcds (keyword (:calc_key curr_rcd)) (:calc_value curr_rcd)))
                                     rs))}))

(defn value-map
  "docstring"
  [val-overridable]
  (println "value-map: " val-overridable)
  ;as-arrays false returns a list of maps per row
  ;({:calc_key benefit_amt, :calc_value 12000.0} {:calc_key bill_rate, :calc_value 100.0})
  (j/query mysql-db
           ["select calc_key, calc_value from revenue_calc_values where overridable = ? order by calc_key" val-overridable]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       ;(conj rcds {(keyword (:calc_key curr_rcd)) (:calc_value curr_rcd)}))
                                       (assoc rcds (keyword (:calc_key curr_rcd)) (:calc_value curr_rcd)))
                                     {},
                                     rs))}))
(defn calculation-values
  ([file-name]
   (println name " " (System/getProperty "user.dir"))
   (println overridable-args)
   (let [file-args (read-string (str "{" (slurp file-name) "}"))]
     (conj overridable-args file-args non-overridable-args)
     )))

(defn calculation-values-db
  [file-name]
  (let [file-args (read-string (str "{" (slurp file-name) "}"))]
    (conj (value-map 1) file-args (value-map 0)))
  )
