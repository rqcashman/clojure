(ns tennis-manager.data.player-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [tennis-manager.data.system-info :as sys]))

(defn player
  "docstring"
  [player-id]
  (j/query sys/db-cred
           [(str "select id, team_id, last_name, first_name, email, phone_number, status"
                 " from player "
                 " where id=?") player-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (reduce (fn [rcds curr_rcd]
                                       (conj rcds curr_rcd))
                                     [],
                                     rs))}))

(defn player-exists?
  "docstring"
  [team_id first_name last_name]
  (j/query sys/db-cred
           [(str "select count(*) as ct from player where team_id=? and first_name=? and last_name=?") team_id first_name last_name]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))

(defn player-id-exists?
  "docstring"
  [player-id]
  (j/query sys/db-cred
           [(str "select count(*) as ct from player where id=?") player-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (> (:ct (nth rs 0)) 0) true false))}))

(defn player-name-available?
  "check to make sure a player with the name is not already on that team excluding the one we are updating"
  [team_id player-id first_name last_name]
  (j/query sys/db-cred
           [(str "select count(*) as ct from player where team_id=? and first_name=? and last_name=? and id <>?") team_id first_name last_name player-id]
           {:as-arrays?    false
            :result-set-fn (fn [rs]
                             (if (= (:ct (nth rs 0)) 0) true false))}))

(defn add_player
  "docstring"
  [team_id first_name last_name email phone_number]
  (let [phone (if (s/blank? phone_number) 0 phone_number)]
    (j/execute! sys/db-cred
                [(str "insert into player values (null,?,?,?,?,?,'A')") team_id first_name last_name email phone])))

(defn update-player
  "docstring"
  [player-id last_name first_name email phone_number status]
  (j/execute! sys/db-cred
              [(str "update player set last_name=?,first_name=?,
              email=?,phone_number=?,status=?
              where id=?") last_name, first_name, email, phone_number, status player-id]))


