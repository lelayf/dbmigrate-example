(ns dbmigrate-example.core
   (:use cascalog.api)
   (:require [cascalog [workflow :as w] [ops :as c] [vars :as v]])
   (:import cascading.dbmigrate.tap.DBMigrateTap)
   (:import cascading.dbmigrate.tap.DBMigrateTap$Options)
   (:import com.photobox.thrift.Member))

(defn db-range [min max]
       (let [opts (new cascading.dbmigrate.tap.DBMigrateTap$Options)]
          (set! (. opts :minId) min)
          (set! (. opts :maxId) max)
          opts))

(defn to-thrift [id first-name]
  (vector
   (Member.
     id
     first-name)))

(defn db-tap [table cols]
  (cascading.dbmigrate.tap.DBMigrateTap.
          1
          "com.mysql.jdbc.Driver"
          "jdbc:mysql://localhost:3306/test"
          "root"
          ""
          table
          "id"
          (into-array cols)
          (db-range 1 1800000))) ;; Only load first 100 records

(defn fetch-and-dump []
  (?<- (hfs-seqfile "/tmp/members" :sinkmode :replace)
       [?m]
       ((db-tap "members" ["id" "first_name"]) ?id ?name)
       (to-thrift ?id ?name :> ?m)))

