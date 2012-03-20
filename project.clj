(defproject dbmigrate-example "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :jvm-opts ["-Xmx768m" "-server"]
  :source-path "src/clj"
  :java-source-path "src/jvm"
  :resources-path "resources"
  :repositories {"conjars" "http://conjars.org/repo"}
  :plugins [lein-swank "1.4.3"] 
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [cascalog "1.8.6"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [backtype/cascading-dbmigrate "1.1.0"] 
                 [backtype/cascading-thrift "0.2.0"]
                 [org.apache.thrift/libthrift "0.8.0"]]
  :dev-dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]])

