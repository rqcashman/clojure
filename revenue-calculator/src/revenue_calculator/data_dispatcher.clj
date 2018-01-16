(ns revenue-calculator.data-dispatcher
  (:require [revenue-calculator [spreadsheet-common :as cc]]))

(defmulti cell-data (fn [rcd iteration rcd-map calc-values] (:key rcd)))

(defmethod cell-data "billrate" [rcd iteration rcd-map calc-values]
  (+ (:bill_rate calc-values) (* (:iteration_increment calc-values) (dec iteration))))

(defmethod cell-data "yearlyhours" [rcd iteration rcd-map calc-values]
  (:yearly_hours calc-values))

(defmethod cell-data "holidays" [rcd iteration rcd-map calc-values]
  (:holidays calc-values))

(defmethod cell-data "vacationdays" [rcd iteration rcd-map calc-values]
  (:vacation_days calc-values))

(defmethod cell-data "hoursbilled" [rcd iteration rcd-map calc-values]
  (let [yearly-hours (cc/cell-location iteration :yearlyhours rcd-map)
        holidays (cc/cell-location iteration :holidays rcd-map)
        vacation (cc/cell-location iteration :vacationdays rcd-map)]
    (str yearly-hours "-((" holidays "+" vacation ")*8)")))

(defmethod cell-data "vendormmgmpct" [rcd iteration rcd-map calc-values]
  (:vendor_mgmt_pct calc-values))

(defmethod cell-data "billedamt" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :billrate rcd-map) "*" (cc/cell-location iteration :hoursbilled rcd-map)))

(defmethod cell-data "vendormgmtamt" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :billedamt rcd-map) "*" (cc/cell-location iteration :vendormmgmpct rcd-map)))

(defmethod cell-data "netbilled" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :billedamt rcd-map) "-" (cc/cell-location iteration :vendormgmtamt rcd-map)))

(defmethod cell-data "pay" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :netbilled rcd-map) "*" (:emp_pay_pct calc-values)))

(defmethod cell-data "dcp" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :netbilled rcd-map) "*" (:emp_dcp_pct calc-values)))

(defmethod cell-data "k401" [rcd iteration rcd-map calc-values]
  (str "(" (cc/cell-location iteration :pay rcd-map) "+" (cc/cell-location iteration :dcp rcd-map) ")*" (:k401_pct calc-values)))

(defmethod cell-data "hsa" [rcd iteration rcd-map calc-values]
  (:hsa_amt calc-values))

(defmethod cell-data "totalcomp" [rcd iteration rcd-map calc-values]
  (let [pay (cc/cell-location iteration :pay rcd-map)
        dcp (cc/cell-location iteration :dcp rcd-map)
        k401 (cc/cell-location iteration :k401 rcd-map)
        hsa (cc/cell-location iteration :hsa rcd-map)]
    (str pay "+" dcp "+" k401 "+" hsa)))

(defmethod cell-data "sstax" [rcd iteration rcd-map calc-values]
  (let [comp-cell (cc/cell-location iteration :totalcomp rcd-map)
        ss-cap (:ss_cap calc-values)
        ss-rate (:ss_rate calc-values)]
    (str "if(" comp-cell ">" ss-cap "," ss-cap "*" ss-rate "," comp-cell "*" ss-rate ")")))

(defmethod cell-data "medicare" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :totalcomp rcd-map) "*" (:medicare_rate calc-values)))

(defmethod cell-data "unemployment" [rcd iteration rcd-map calc-values]
  (str (:fed_unempolyment_amt calc-values) "+" (:ohio_unempolyment_amt calc-values)))

(defmethod cell-data "ohworkerscomp" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :totalcomp rcd-map) "*" (:ohio_workers_comp_pct calc-values)))

(defmethod cell-data "benefitcost" [rcd iteration rcd-map calc-values]
  (str (:benefit_amt calc-values) "*" (:company_benefit_pct calc-values)))

(defmethod cell-data "netrevenue" [rcd iteration rcd-map calc-values]
  (str (cc/cell-location iteration :netbilled rcd-map)
       "-" (cc/cell-location iteration :totalcomp rcd-map)
       "-" (cc/cell-location iteration :sstax rcd-map)
       "-" (cc/cell-location iteration :medicare rcd-map)
       "-" (cc/cell-location iteration :unemployment rcd-map)
       "-" (cc/cell-location iteration :ohworkerscomp rcd-map)
       "-" (cc/cell-location iteration :benefitcost rcd-map)))

(defmethod cell-data :default [rcd iteration rcd-map calc-values]
  (str ""))