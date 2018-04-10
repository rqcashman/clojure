(ns rf-tennis-manager.form-validator
  (:require [clojure.string :as s]
            [re-frame.core :as rf]))

(defn validate-form
  "validate form input"
  [path]
  )

(def not-nil? (complement nil?))

(defn numeric?
  [value]
  (= (count value) (count (str (re-find #"\d+" value)))))

(defn email-valid?
  [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (println "email: " email)
    (println (re-matches pattern email))
    (re-matches pattern email)))

(defn update-field
  ([db path fld-hash value valid error-msg]
   (println "==============update field path: " path " msg: " error-msg " valid: " valid " val: " value)
   (let [error-msg (if (s/blank? error-msg) "" (str (:name fld-hash) " " error-msg))]
     (-> db
         (assoc-in (conj path :error-msg) error-msg)
         (assoc-in (conj path :valid?) valid)
         (assoc-in (conj path :value) value)))))

(defn validate-fld
  "docstring"
  [db path fld-hash value]
  (println "==validate-fld path: " path " hash: " fld-hash " val: " value)
  (cond
    (and (:required? fld-hash) (s/blank? value)) (update-field db path fld-hash value false "is required")
    (and (= (:type fld-hash) "numeric") (not (numeric? value))) (update-field db path fld-hash value false "requires numeric input")
    (and (not-nil? (:min-length fld-hash)) (< (alength value) (:min-length fld-hash))) (update-field db path fld-hash value false (str "must be at least " (:min-length fld-hash) " characters long"))
    (and (not-nil? (:max-length fld-hash)) (> (alength value) (:max-length fld-hash))) (update-field db path fld-hash value false (str "can be only " (:max-length fld-hash) " characters long"))
    (and (= (:type fld-hash) "email") (not (s/blank? value)) (not (email-valid? value))) (update-field db path fld-hash value false "format invalid")
    :else (update-field db path fld-hash value true "")))

(rf/reg-event-fx
  ::validate-field
  (fn [{:keys [db]} [_ path value]]
    {:db (validate-fld db path (get-in db path) value)}))

(rf/reg-event-fx
  ::validate-form
  (fn [{:keys [db]} [_ path success-fn error-fn]]
    (println "============::validate-form path: " path " fns: " success-fn error-fn)
    (let [upd-db (reduce (fn [upd-db key]
                           (let [fld-path (conj path key)
                                 fld-hash (get-in upd-db fld-path)]
                             (validate-fld upd-db fld-path fld-hash (:value fld-hash))))
                         db (keys (get-in db path)))]
      (println "fields: " (get-in upd-db path))
      (println "filter: " (filter #(not (:valid? (val %))) (get-in upd-db path)))
      (println "ct: " (count (filter #(not (:valid? (val %))) (get-in upd-db path))))
      (let [method (if (> (count (filter #(not (:valid? (val %))) (get-in upd-db path))) 0) error-fn success-fn)]
        (println "method: " method)
        {:db       upd-db
         :dispatch method}))))


