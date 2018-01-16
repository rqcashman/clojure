(ns revenue-calculator.calc-spreadsheet-dispatch
  (:require [revenue-calculator [data-dispatcher :as dd] [spreadsheet-common :as cc]])
  (:import
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
  (reduce (fn [out rcd]
            ;(conj out {(keyword (.data-key rcd)) {:ordinal (inc (.indexOf sheet-records rcd)) :rcd rcd}}))
            (assoc out (keyword (:key rcd)) (assoc rcd :ordinal (inc (.indexOf sheet-records rcd)))))
          {}
          sheet-records))

;;each entry defines a row in the spreadsheet and is output in the order of this list
(defn sheet-records
  "docstring"
  [cell-styles calc-values]
  (list
    {:key "billrate" :heading "Billing rate" :cell-type CellType/NUMERIC :cell-style (:currency cell-styles)}
    {:key "yearlyhours" :heading "Yearly hours" :cell-type CellType/NUMERIC :cell-style (:integer cell-styles)}
    {:key "holidays" :heading "Holidays" :cell-type CellType/NUMERIC :cell-style (:integer cell-styles)}
    {:key "vacationdays" :heading "Vacation days" :cell-type CellType/NUMERIC :cell-style (:integer cell-styles)}
    {:key "hoursbilled" :heading "Hours billed" :cell-type CellType/FORMULA :cell-style (:integer cell-styles)}
    {:key "vendormmgmpct" :heading "Vendor mgmt. pct." :cell-type CellType/NUMERIC :cell-style (:float cell-styles)}
    {:key "billedamt" :heading "Billed amt." :cell-type CellType/FORMULA :cell-style (:green-currency cell-styles)}
    {:key "empty" :heading "" :cell-type CellType/BLANK :cell-style (:currency cell-styles)}
    {:key "vendormgmtamt" :heading "Vendor mgmt. amt." :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "netbilled" :heading "Net billed amt." :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "pay" :heading "Pay" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "dcp" :heading "DCP" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "k401" :heading "401K" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "hsa" :heading "HSA" :cell-type CellType/NUMERIC :cell-style (:currency cell-styles)}
    {:key "totalcomp" :heading "Total compensation" :cell-type CellType/FORMULA :cell-style (:green-currency cell-styles)}
    {:key "empty2" :heading "" :cell-type CellType/BLANK :cell-style (:currency cell-styles)}
    {:key "sstax" :heading "Social security" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "medicare" :heading "Medicare" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "unemployment" :heading "Unemployment" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "ohworkerscomp" :heading "OH workers comp" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "benefitcost" :heading "Benefit expense" :cell-type CellType/FORMULA :cell-style (:currency cell-styles)}
    {:key "netrevenue" :heading "Net revenue" :cell-type CellType/FORMULA :cell-style (:green-currency cell-styles)}))


(defn create-revenue-spreadsheet
  "docstring"
  [calc-values filename]
  (println "calc spreadsheet dispatch")
  (let [workbook (XSSFWorkbook.)
        sheet (.createSheet workbook "Revenue calculator (dispatch)")
        styles (cc/cell-styles workbook)
        rcd-map (record-map (sheet-records styles calc-values))]
    ;(println "dispatch rcd map: " rcd-map)
    (doseq [cell-rcd (vals rcd-map)]
      (let [row (.createRow sheet (dec (:ordinal cell-rcd))) ;ordinal is 1 based for formulas but 0 based for rows so we decrement
            heading-cell (.createCell row 0)]
        (.setCellValue heading-cell (:heading cell-rcd))
        (.setCellStyle heading-cell (:header styles))
        ;create a column for each iteration.  start with 1 because the first column has the headings
        (loop [col-idx 1]
          (when (<= col-idx (:iterations calc-values))
            (let [data-cell (.createCell row col-idx)]
              (.setCellType data-cell (:cell-type cell-rcd))
              (.setCellStyle data-cell (:cell-style cell-rcd))
              (if (= (:cell-type cell-rcd) CellType/FORMULA)
                (.setCellFormula data-cell (dd/cell-data cell-rcd col-idx rcd-map calc-values))
                (if (= (:cell-type cell-rcd) CellType/BLANK)
                  (.setCellValue data-cell (dd/cell-data cell-rcd col-idx rcd-map calc-values))
                  (.setCellValue data-cell (double (dd/cell-data cell-rcd col-idx rcd-map calc-values))))))
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
