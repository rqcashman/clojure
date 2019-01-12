(ns rf-tennis-manager.data.crypto
  (require
    [buddy.core.crypto :as crypto]
    [buddy.core.codecs :as codecs]
    [buddy.core.hash :as hash]
    [buddy.core.nonce :as nonce]
    [rf-tennis-manager.data.system-info :as sysinfo]))

(defn encrypt-item
  "Encrypt data item"
  [data]
  (crypto/encrypt (codecs/to-bytes data) (:key sysinfo/crypto-keys) (:iv sysinfo/crypto-keys)
                  {:algorithm :aes128-cbc-hmac-sha256}))

(defn decrypt-item
  "Decrypt data item"
  [data]
  (crypto/decrypt (codecs/to-bytes data) (:key sysinfo/crypto-keys) (:iv sysinfo/crypto-keys)
                  {:algorithm :aes128-cbc-hmac-sha256}))


(def test-value "rqcashman@gmail.com")
(let [en] (encrypt-item test-value)
          (println "Encrypted: " en)
          (println "Decrypted: " (decrypt-item en)))