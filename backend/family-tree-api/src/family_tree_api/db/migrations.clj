(ns family-tree-api.db.migrations
  (:require [migratus.core :as migratus]
            [hugsql.core :as hugsql]))

(def config
  {:store                :database
   :migration-dir        "migrations"
   :db                   {:connection-uri "jdbc:postgresql://localhost:5432/family_tree?user=postgres"}})

(defn run-migrations []
  (try
    (migratus/migrate config)
    (catch Exception e
      (prn "Could not run migrations: " (str (ex-message e))))))

(defn rollback-migrations []
  (try
    (migratus/rollback config)
    (catch Exception e
      (prn "Could not rollback migrations: " (str (ex-message e))))))

(hugsql/def-db-fns "family_tree_api/db/sql/schema_migrations.sql")

(hugsql/def-sqlvec-fns "family_tree_api/db/sql/schema_migrations.sql")
