(ns rf-tennis-manager.core)

(def all-ints (range))

(defn test-me
  []
  (take 2 (reduce (fn [list i]
                    (let [x (inc i)]
                      (println x)
                      (if (odd? x) (conj list x) list)))
                  () (take 100 all-ints))))



(take 5 (drop 1000 (filter odd? (map inc all-ints))))
(take 5 (filter even? (map inc all-ints)))

(->> all-ints
     (map inc)
     (filter odd?)
     (drop 1000)
     (take 5))

(->> all-ints
     (map inc)
     (drop 1000)
     (take 5))

(->> all-ints
     (filter odd?)
     (drop 1000)
     (take 5))

(-> all-ints
    (map inc)
    (filter odd?)
    (drop 1000)
    (take 5))

(loop [idx 1]
  (when (< idx 5)
    (println "idx: " idx)
    (recur (inc idx))))

(defn test-recur
  "docstring"
  [idx stop-idx]
  (if (<= idx stop-idx)
    (do
      (println "idx: " idx " stop: " stop-idx)
      (test-recur (inc idx) stop-idx))
    (println "=== THE END === idx:" idx " stop: " stop-idx)))

(map + [1 2 100] [3 4] [5 6 7])

(take 5 (iterate inc 1))

(def xx (list "rick" "cashman"))

(def team [{:last "Cashman" :first "Rick" :address {:street "Main St."}}
           {:last "Allen" :first "Mark" :address {:street "First St."}}
           {:last "Barnhill" :first "Bruce" :address {:street "Elm Ave."}}
           {:last "Flowers" :first "Henry" :address {:street "Maple Blvd."}}])

(filter #(= "Allen" (:last %)) team)
(doseq [name (reverse (sort-by #(:last %) (conj team {:first "Raghu" :last "Reddy"})))]
  (println "First: " (:last name) " Last: " (:first name) " Address: " (-> name :address :street))
  )

(into (first team) {:new "New attr"})
(assoc (last team) :new "new")
(sort-by #(:last %) team)
(sort-by #(:first %) team)
(sort-by #(-> % :first) team)
(sort-by #(-> % :address :street) team)
(filter #(println %) team)

(defn fib-num
  [start-val]
  (let [num (condp = start-val
              0 0
              1 1
              (+ (fib-num (dec start-val)) (fib-num (- start-val 2))))]
    (println "fib-num: " num)
    num))

(defn fib-num2
  [in-val]
  (if (< in-val 2)
    in-val
    (+ (fib-num2 (dec in-val)) (fib-num2 (- in-val 2)))))


(fib-num2 12)



