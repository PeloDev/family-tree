(ns family-tree-api.services.members-service
  (:require [family-tree-api.db :refer [db]]
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