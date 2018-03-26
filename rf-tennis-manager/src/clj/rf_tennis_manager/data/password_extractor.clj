(ns rf-tennis-manager.data.password-extractor
  (:require [clojure.string :as s]))
(comment "This reads a file and pulls out characters from across the file to create a password.
          This allows me to hide the password for my encrypted system parms file in plain sight.
          This same input file and indexes were used to encrypt the parm file in another project")
(def substring-indexes
  [[900 903]
   [144 148]
   [377 383]
   [713 720]
   [93 94]
   [823 829]
   [219 222]
   [588 592]
   [4 7]
   [827 839]
   [499 507]])

(defn extract-password-fragement
  "Extract a password segment"
  [pwd idx alpha]
  (conj pwd (subs alpha (first idx) (second idx))))

(defn get-password
  "Generate a password from the input file"
  ([input-file] (get-password input-file substring-indexes))
  ([input-file substring-arr]
   (let [alpha (-> (slurp input-file)
                   (s/replace #"\n" "")
                   (s/replace #"\s" ""))]
     (-> (reduce #(extract-password-fragement %1 %2 alpha)
                 ()
                 substring-arr)
         s/join s/reverse))))



