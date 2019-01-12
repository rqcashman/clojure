(ns rf-tennis-manager.form-validator
  (:require [clojure.string :as s]
            [re-frame.core :as rf]))

(def not-nil? (complement nil?))
(def numeric-types ["phone-number" "numeric"])

(defn numeric?
  [value]
  (= (count value) (count (str (re-find #"\d+" value)))))

(defn phone-number-valid?
  "validates a phone number is either 7 or 10 digits long.  Numeric validation happens before we get here"
  [phone-number]
  (or (= phone-number "0") (= (alength phone-number) 7) (= (alength phone-number) 10)))

(defn email-valid?
  [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (re-matches pattern email)))

(defn update-field
  "update the DB associated with a field.  We update even when there is an error.  I dislike throwing away user input - find it not a good UI design"
  ([db path fld-hash value valid error-msg]
   (let [error-msg (if (s/blank? error-msg) "" (str (:name fld-hash) " " error-msg))]
     (-> db
         (assoc-in (conj path :error-msg) error-msg)
         (assoc-in (conj path :valid?) valid)
         (assoc-in (conj path :value) value)))))

(defn validate-fld
  "validate an input field using metadata from the ratom"
  [db path fld-hash value]
  (cond
    (and (:required? fld-hash) (s/blank? value)) (update-field db path fld-hash value false "is required")
    (and (some #(= (:type fld-hash) %) numeric-types) (not (numeric? value))) (update-field db path fld-hash value false "requires numeric input")
    (and (not-nil? (:min-length fld-hash)) (> (alength value) 0) (< (alength value) (:min-length fld-hash))) (update-field db path fld-hash value false (str "must be at least " (:min-length fld-hash) " characters long"))
    (and (not-nil? (:max-length fld-hash)) (> (alength value) 0) (> (alength value) (:max-length fld-hash))) (update-field db path fld-hash value false (str "can be only " (:max-length fld-hash) " characters long"))
    (and (= (:type fld-hash) "email") (not (s/blank? value)) (not (email-valid? value))) (update-field db path fld-hash value false "format invalid")
    (and (= (:type fld-hash) "phone-number") (not (s/blank? value)) (not (phone-number-valid? value))) (update-field db path fld-hash value false "must have either 7 or 10 digits")
    :else (update-field db path fld-hash value true "")))

(comment "Doing triml here so a value can have a space in the middle of the input.
          Doing a trim in ::validate-form which is called when submitting the form to remove trailing spaces")
(rf/reg-event-fx
  ::validate-field
  (fn [{:keys [db]} [_ path value]]
    {:db (validate-fld db path (get-in db path) (s/triml (str value)))}))

(rf/reg-event-fx
  ::validate-form
  (fn [{:keys [db]} [_ path success-fn error-fn]]
    (let [upd-db (reduce (fn [upd-db key]
                           (let [fld-path (conj path key)
                                 fld-hash (get-in upd-db fld-path)]
                             (validate-fld upd-db fld-path fld-hash (s/trim (str (:value fld-hash))))))
                         db (keys (get-in db path)))]
      (let [method (if (empty? (filter #(not (:valid? (val %))) (get-in upd-db path))) success-fn error-fn)]
        {:db       upd-db
         :dispatch method}))))


