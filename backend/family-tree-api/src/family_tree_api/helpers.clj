(ns family-tree-api.helpers
  (:require [family-tree-api.models.members :refer [template-member]]
            [family-tree-api.models.relations :refer [template-relation]]))

(defn fill-missing-fields-strict [partial-hashmap template-hashmap]
  (merge template-hashmap (select-keys partial-hashmap (keys template-hashmap))))

(defn strip-nil-vals-from-hashmap [m]
  (reduce-kv (fn [acc k v]
               (if (nil? v)
                 acc
                 (assoc acc k v))) {} m))

(defn fill-member-keys [partial-member]
  (fill-missing-fields-strict partial-member template-member))

(defn fill-relation-keys [partial-relation]
  (fill-missing-fields-strict partial-relation template-relation))

