(ns revenue-calculator.spreadsheet-common
  (:import (org.apache.poi.ss.usermodel BorderStyle
                                        CellStyle
                                        CellType
                                        Color
                                        IndexedColors)))

(def heading-col-idx 0)
(def heading-col-width 6000)
(def data-col-width 3300)

(defn cell-styles
  "docstring"
  [workbook]
  (def bold-font (.createFont workbook))
  (.setBold bold-font true)
  (.setColor bold-font (.getIndex IndexedColors/BLACK))

  (def green-bold-font (.createFont workbook))
  (.setBold green-bold-font true)
  (.setColor green-bold-font (.getIndex IndexedColors/GREEN))

  (def header-style (.createCellStyle workbook))
  (.setFont header-style bold-font)
  (.setFillForegroundColor header-style (.getIndex IndexedColors/TEAL))
  (.setFillPattern header-style (CellStyle/SOLID_FOREGROUND))
  (.setBorderTop header-style (BorderStyle/THIN))
  (.setBorderBottom header-style (BorderStyle/THIN))
  (.setBorderLeft header-style (BorderStyle/THIN))
  (.setBorderRight header-style (BorderStyle/THIN))

  (def df (.createDataFormat workbook))
  (def integer-style (.createCellStyle workbook))
  (.setDataFormat integer-style (.getFormat df "#,##0"))

  (def currency-style (.createCellStyle workbook))
  (.setDataFormat currency-style (.getFormat df "$#,##0.00"))

  (def green-currency-style (.createCellStyle workbook))
  (.setDataFormat green-currency-style (.getFormat df "$#,##0.00"))
  (.setFont green-currency-style green-bold-font)

  (def percent-style (.createCellStyle workbook))
  (.setDataFormat percent-style (.getFormat df "#0.0##%"))

  (def float-style (.createCellStyle workbook))
  (.setDataFormat float-style (.getFormat df "#,0##.00"))
  {:header header-style :currency currency-style :green-currency green-currency-style :integer integer-style :percent percent-style :float float-style})

(def letters (to-array "ABCDEFGHIJKLMNOPQRSTUVWXYZ"))

(defn cell-location
  "returns cell location for rcd key (A5, B6, etc.)"
  [iteration data-key rcd-map]
  (str (nth letters iteration) (:ordinal (data-key rcd-map))))