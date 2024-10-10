(ns family-tree-api.db
  (:require [hugsql.core :as hugsql]))

;; TODO: use env variables
(def db
  {:subprotocol "mysql"
   :subname "//localhost:3307/family_tree"
   :user "root"
   :useSSL false})