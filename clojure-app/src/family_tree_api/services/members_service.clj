(ns family-tree-api.services.members-service
  (:require [family-tree-api.db :refer [db]]
            [family-tree-api.db.members :as members]))

;; TODO
(defn insert-member [member]
  (members/insert-member db member))

(defn insert-members [members]
  (members/insert-members db members))


(defn delete-member [id]
  (members/delete-member-by-id db {:id id}))