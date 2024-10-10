(ns family-tree-api.db.members
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "family_tree_api/db/sql/members.sql")

(hugsql/def-sqlvec-fns "family_tree_api/db/sql/members.sql")