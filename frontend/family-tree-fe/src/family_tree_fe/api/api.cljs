(ns family-tree-fe.api.api
  (:require [family-tree-fe.api.utils :as api]))

(defn get-member-by-id [id on-response]
  (api/get (str "/members/" id) on-response))

(defn get-member-relations [id on-response]
  (api/get (str "/members/relations/" id) on-response))

