(ns family-tree-api.db.spouses
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "family_tree_api/db/sql/spouses.sql")

(hugsql/def-sqlvec-fns "family_tree_api/db/sql/spouses.sql")