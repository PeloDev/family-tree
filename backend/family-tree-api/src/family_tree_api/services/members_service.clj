(ns family-tree-api.services.members-service
  (:require [family-tree-api.db.db :refer [db]]
            [family-tree-api.db.members :as members]
            [family-tree-api.validators.members :refer [validate-member]]
            [family-tree-api.utils.general :refer [fill-missing-fields-strict]]
            [family-tree-api.models.members :refer [template-member]]))

(defn fill-member-keys [partial-member]
  (fill-missing-fields-strict partial-member template-member))

(defn insert-member [member]
  (let [validation-errors (validate-member member)]
    (when (not= nil validation-errors)
      (throw
       (ex-info "insert-member service error" {:type "validation-error" :errors validation-errors}))))

  (try
    (members/insert-member db (fill-member-keys member))
    (catch Exception e
      (throw
       (ex-info "insert-member db error" {:type "database-error" :errors [e]})))))

(defn insert-members [members]
  (let [validation-errors (mapv #(validate-member %) members)]
    (when (not-every? nil? validation-errors)
      (throw
       (ex-info "insert-members service error" {:type "validation-error" :errors validation-errors}))))

  (try
    (members/insert-members db (map #(fill-member-keys %) members))
    (catch Exception e
      (throw
       (ex-info "insert-members db error" {:type "database-error" :errors [e]})))))

(defn get-member-by-id [id]
  (when (not (int? id))
    (throw
     (ex-info "get-member-by-id service error" {:type "validation-error" :errors
                                                ["Invalid type provided for id"]})))
  (try
    (members/member-by-id db {:id id})
    (catch Exception e
      (throw
       (ex-info "get-member-by-id db error" {:type "database-error" :errors [e]})))))

(defn get-member-parents [id]
  (when (not (int? id))
    (throw
     (ex-info "get-member-parents service error" {:type "validation-error" :errors
                                                  ["Invalid type provided for id"]})))
  (try
    (members/member-parents db {:id id})
    (catch Exception e
      (throw
       (ex-info "get-member-parents db error" {:type "database-error" :errors [e]})))))

(defn get-member-children [id]
  (when (not (int? id))
    (throw
     (ex-info "get-member-children service error" {:type "validation-error" :errors
                                                   ["Invalid type provided for id"]})))
  (try
    (members/member-children db {:id id})
    (catch Exception e
      (throw
       (ex-info "get-member-children db error" {:type "database-error" :errors [e]})))))

(defn get-children-between-2-members [member1_id member2_id]
  (when (not (or (int? member1_id) (int? member2_id)))
    (throw
     (ex-info "get-children-between-2-members service error" {:type "validation-error" :errors
                                                              ["Invalid type provided for id"]})))
  (try
    (members/children-between-members db {:parent1_id member1_id :parent2_id member2_id})
    (catch Exception e
      (throw
       (ex-info "get-children-between-2-members db error" {:type "database-error" :errors [e]})))))

(defn delete-member [id]
  (when (not (int? id))
    (throw
     (ex-info "delete-member service error" {:type "validation-error" :errors
                                             ["Invalid type provided for id"]})))
  (try
    (members/delete-member-by-id db {:id id})
    (catch Exception e
      (throw
       (ex-info "delete-member db error" {:type "database-error" :errors [e]})))))