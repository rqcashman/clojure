(ns revenue-calculator.data-records
  (:require [clojure.string :as str]
            [revenue-calculator [spreadsheet-common :as cc]]))


(defprotocol ACellData
  (data [this iteration rcd-map]))

;use defrecord instead of deftype here - no difference in how I am using it
;deftype needs to access fields with a . and not a : (externally not internally)
(defrecord BillingRate [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    ;decrement iteration or we will not show the starting bill rate
    (+ (:bill_rate calc-values) (* (:iteration_increment calc-values) (dec iteration)))))

(deftype YearlyHours [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (:yearly_hours calc-values)))

(deftype Holidays [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (:holidays calc-values)))

(deftype VacationDays [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (:vacation_days calc-values)))

(deftype HoursBilled [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (let [yearly-hours (cc/cell-location iteration :yearlyhours rcd-map)
          holidays (cc/cell-location iteration :holidays rcd-map)
          vacation (cc/cell-location iteration :vacationdays rcd-map)]
      (str yearly-hours "-((" holidays "+" vacation ")*8)"))))

(deftype VendorManagementPercent [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (:vendor_mgmt_pct calc-values)))

(deftype BilledAmount [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :billrate rcd-map) "*" (cc/cell-location iteration :hoursbilled rcd-map))))

(deftype VendorManagementAmount [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :billedamt rcd-map) "*" (cc/cell-location iteration :vendormmgmpct rcd-map))))

(deftype NetBilledAmount [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :billedamt rcd-map) "-" (cc/cell-location iteration :vendormgmtamt rcd-map))))

(deftype PayAmount [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :netbilled rcd-map) "*" (:emp_pay_pct calc-values))))

(deftype DCP [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :netbilled rcd-map) "*" (:emp_dcp_pct calc-values))))

(deftype K401 [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str "(" (cc/cell-location iteration :pay rcd-map) "+" (cc/cell-location iteration :dcp rcd-map) ")*" (:k401_pct calc-values))))

(deftype HSAAmount [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (:hsa_amt calc-values)))

(deftype TotalCompensation [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (let [pay (cc/cell-location iteration :pay rcd-map)
          dcp (cc/cell-location iteration :dcp rcd-map)
          k401 (cc/cell-location iteration :k401 rcd-map)
          hsa (cc/cell-location iteration :hsa rcd-map)]
      (str pay "+" dcp "+" k401 "+" hsa))))

(deftype SocialSecurity [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (let [comp-cell (cc/cell-location iteration :totalcomp rcd-map)
          ss-cap (:ss_cap calc-values)
          ss-rate (:ss_rate calc-values)]
      (str "if(" comp-cell ">" ss-cap "," ss-cap "*" ss-rate "," comp-cell "*" ss-rate ")"))))

(deftype Medicare [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :totalcomp rcd-map) "*" (:medicare_rate calc-values))))

(deftype Unemployment [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (:fed_unempolyment_amt calc-values) "+" (:ohio_unempolyment_amt calc-values))))

(deftype OhioWorkerComp [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :totalcomp rcd-map) "*" (:ohio_workers_comp_pct calc-values))))

(deftype BenefitCost [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (:benefit_amt calc-values) "*" (:company_benefit_pct calc-values))))

(deftype EmployeeBenefitCost [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (let [benefit-amt (:benefit_amt calc-values)
          company-benefit-pct (:company_benefit_pct calc-values)]
      (str benefit-amt "*(1-" company-benefit-pct ")"))))

(deftype NetRevenue [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str (cc/cell-location iteration :netbilled rcd-map)
         "-" (cc/cell-location iteration :totalcomp rcd-map)
         "-" (cc/cell-location iteration :sstax rcd-map)
         "-" (cc/cell-location iteration :medicare rcd-map)
         "-" (cc/cell-location iteration :unemployment rcd-map)
         "-" (cc/cell-location iteration :ohworkerscomp rcd-map)
         "-" (cc/cell-location iteration :benefitcost rcd-map))))

(deftype BlankRow [heading cell-type cell-style calc-values data-key]
  ACellData
  (data [this iteration rcd-map]
    (str "")))