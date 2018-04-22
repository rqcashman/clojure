(ns rf-tennis-manager.data.player-data-handler
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.string :as s]
            [rf-tennis-manager.data.system-info :as sys]))

(defn player
  "docstring"
  [player-id]
  (-> (j/query sys/db-cred
               [(str "select id, team_id, last_name, first_name, email, phone_number, status
                      from player where id= cast (? as integer)") player-id])
      first))

(defn player-exists?
  "docstring"
  [team_id first_name last_name]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from player
                where team_id= cast (? as integer) and first_name=? and last_name=?") team_id first_name last_name])
      first :ct pos?))

(defn player-id-exists?
  "docstring"
  [player-id]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from player where id= cast (? as integer)") player-id])
      first :ct pos?))

(defn player-name-available?
  "check to make sure a player with the name is not already on that team excluding the one we are updating"
  [team_id player-id first_name last_name]
  (-> (j/query sys/db-cred
               [(str "select count(*) as ct from player
               where team_id= cast (? as integer) and first_name=? and last_name=? and id <> cast (? as integer)") team_id first_name last_name player-id])
      first :ct (= 0)))

(defn add_player
  "docstring"
  [team_id first_name last_name email phone_number status]
  (let [phone (if (s/blank? phone_number) 0 phone_number)]
    (j/execute! sys/db-cred
                [(str "insert into player
                values (default,cast (? as integer),?,?,?,cast (? as bigint),?)") team_id first_name last_name email phone status])))

(defn update-player
  "docstring"
  [player-id last_name first_name email phone_number status]
  (let [phone (if (s/blank? phone_number) 0 phone_number)]
    (j/execute! sys/db-cred
                [(str "update player set last_name=?,first_name=?, email=?,phone_number= cast (? as bigint) ,status=?
                     where id= cast (? as integer)") last_name, first_name, email, phone, status player-id])))


