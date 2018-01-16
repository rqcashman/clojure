(ns revenue-calculator.calc-spreadsheet
  (:require [revenue-calculator [data-records :as dr] [spreadsheet-common :as cc]])
  (:import (revenue_calculator.data_records BenefitCost
                                            BilledAmount
                                            BillingRate
                                            BlankRow
                                            DCP
                                            EmployeeBenefitCost
                                            HSAAmount
                                            Holidays
                                            HoursBilled
                                            K401
                                            Medicare
                                            NetBilledAmount
                                            NetRevenue
                                            OhioWorkerComp
                                            PayAmount
                                            SocialSecurity
                                            TotalCompensation
                                            Unemployment
                                            VacationDays
                                            VendorManagementAmount
                                            VendorManagementPercent
                                            YearlyHours)
           (java.io FileOutputStream)
           (java.util HashMap)
           (org.apache.poi.hssf.util HSSFColor)
           (org.apache.poi.ss.usermodel Cell
                                        CellType
                                        DataFormat
                                        Row)
           (org.apache.poi.xssf.usermodel XSSFSheet XSSFWorkbook)
           (org.apache.xmlbeans)))

;;make a map out of the sheet-records and keep track of the order it appears in the list
;;this allows for changing the order or adding rows without manually tracking the order in the list
;;it is also used in writing formulas for a cell based on other cell values
(defn record-map
  "docstring"
  [sheet-records]
  (comment
    ;first attempt.  reduce is your friend
    (loop [idx 0 out (transient {}) n (count sheet-records)]
      (if (< idx n)
        (do
          (let [rcd (nth sheet-records idx)]
            (recur (inc idx) (conj! out {(keyword (.data-key rcd)) {:ordinal (inc idx) :rcd rcd}}) n)))
        (persistent! out))))

  (reduce (fn [out rcd]
            (assoc out (keyword (.data-key rcd)) {:ordinal (inc (.indexOf sheet-records rcd)) :rcd rcd}))
          {}
          sheet-records))

;;each entry defines a row in the spreadsheet and is output in the order of this list
(defn sheet-records
  "docstring"
  [cell-styles calc-values]
  (list
    ;cannot get -> constructor to work
    ;(map->BillingRate {:heading "Billing rate" :cell-type CellType/NUMERIC :cell-style (:currency cell-styles) :calc-values calc-values :data-key "billrate"})
    (BillingRate. "Billing rate" CellType/NUMERIC (:currency cell-styles) calc-values "billrate")
    (YearlyHours. "Yearly hours" CellType/NUMERIC (:integer cell-styles) calc-values "yearlyhours")
    (Holidays. "Holidays" CellType/NUMERIC (:integer cell-styles) calc-values "holidays")
    (VacationDays. "Vacation days" CellType/NUMERIC (:integer cell-styles) calc-values "vacationdays")
    (HoursBilled. "Hours billed" CellType/FORMULA (:integer cell-styles) calc-values "hoursbilled")
    (VendorManagementPercent. "Vendor mgmt. pct." CellType/NUMERIC (:float cell-styles) calc-values "vendormmgmpct")
    (BilledAmount. "Billed amt." CellType/FORMULA (:currency cell-styles) calc-values "billedamt")
    (VendorManagementAmount. "Vendor mgmt. amt." CellType/FORMULA (:currency cell-styles) calc-values "vendormgmtamt")
    (NetBilledAmount. "Net billed amt." CellType/FORMULA (:green-currency cell-styles) calc-values "netbilled")
    (BlankRow. "" CellType/BLANK nil nil "empty1")
    (EmployeeBenefitCost. "Employee benefit cost" CellType/FORMULA (:currency cell-styles) calc-values "empbenefitcost")
    (PayAmount. "Pay" CellType/FORMULA (:currency cell-styles) calc-values "pay")
    (DCP. "DCP" CellType/FORMULA (:currency cell-styles) calc-values "dcp")
    (K401. "401K" CellType/FORMULA (:currency cell-styles) calc-values "k401")
    (HSAAmount. "HSA" CellType/NUMERIC (:currency cell-styles) calc-values "hsa")
    (TotalCompensation. "Total compensation" CellType/FORMULA (:green-currency cell-styles) calc-values "totalcomp")
    (BlankRow. "" CellType/BLANK nil nil "empty2")
    (SocialSecurity. "Social security" CellType/FORMULA (:currency cell-styles) calc-values "sstax")
    (Medicare. "Medicare" CellType/FORMULA (:currency cell-styles) calc-values "medicare")
    (Unemployment. "Unemployment" CellType/FORMULA (:currency cell-styles) calc-values "unemployment")
    (OhioWorkerComp. "OH workers comp" CellType/FORMULA (:currency cell-styles) calc-values "ohworkerscomp")
    (BenefitCost. "Benefit expense" CellType/FORMULA (:currency cell-styles) calc-values "benefitcost")
    (NetRevenue. "Net revenue" CellType/FORMULA (:green-currency cell-styles) calc-values "netrevenue")))

(defn create-revenue-spreadsheet
  "docstring"
  [calc-values filename]
  (println "calc spreadsheet")
  (let [workbook (XSSFWorkbook.)
        sheet (.createSheet workbook "Revenue calculator (protocol)")
        styles (cc/cell-styles workbook)
        rcd-map (record-map (sheet-records styles calc-values))]
    ;(println "protocol rcd map: " rcd-map)
    (doseq [entry (vals rcd-map)]
      (let [cell-rcd (:rcd entry)
            row (.createRow sheet (dec (:ordinal entry)))   ;ordinal is 1 based for formulas but 0 based for rows so we decrement
            heading-cell (.createCell row 0)]
        (.setCellValue heading-cell (.heading cell-rcd))
        (.setCellStyle heading-cell (:header styles))
        ;create a column for each iteration.  start with 1 because the first column has the headings
        (loop [col-idx 1]
          (when (<= col-idx (:iterations calc-values))
            (let [data-cell (.createCell row col-idx)]
              (.setCellType data-cell (.cell-type cell-rcd))
              (.setCellStyle data-cell (.cell-style cell-rcd))
              (if (= (.cell-type cell-rcd) CellType/FORMULA)
                (.setCellFormula data-cell (.data cell-rcd col-idx rcd-map))
                (if (= (.cell-type cell-rcd) CellType/BLANK)
                  (.setCellValue data-cell (.data cell-rcd col-idx rcd-map))
                  (.setCellValue data-cell (double (.data cell-rcd col-idx rcd-map))))))
            (recur (inc col-idx))))
        ))
    (.setColumnWidth sheet cc/heading-col-idx cc/heading-col-width)
    (loop [col-idx 1]
      (when (<= col-idx (:iterations calc-values))
        (.setColumnWidth sheet col-idx cc/data-col-width)
        (recur (inc col-idx))))
    (let [os (FileOutputStream. filename)]
      (.write workbook os)
      (.close workbook)
      (.close os))))





