(ns family-tree-api.db.parents
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "family_tree_api/db/sql/parents.sql")

(hugsql/def-sqlvec-fns "family_tree_api/db/sql/parents.sql")