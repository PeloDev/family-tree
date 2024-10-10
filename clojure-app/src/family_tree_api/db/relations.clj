(ns family-tree-api.db.relations
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "family_tree_api/db/sql/relations.sql")

(hugsql/def-sqlvec-fns "family_tree_api/db/sql/relations.sql")