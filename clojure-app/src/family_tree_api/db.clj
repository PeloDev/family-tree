(ns family-tree-api.db
  (:require [hugsql.core :as hugsql]))

;; TODO: use env variables
(def db
  {:subprotocol "postgres"
   :subname "//localhost:5432/family_tree"
   :user "postgres"
   :useSSL false})