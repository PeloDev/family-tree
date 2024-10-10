(defproject family-tree-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler family-tree-api.handler/app}
  :resource-paths ["sql" "resources"]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [com.layerware/hugsql "0.5.3"]
                        [org.postgresql/postgresql "42.3.1"]
                        [mysql/mysql-connector-java "8.0.26"]]}})
