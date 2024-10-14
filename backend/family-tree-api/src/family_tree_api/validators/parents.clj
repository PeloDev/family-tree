(ns family-tree-api.validators.parents
  (:require
   [family-tree-api.models.parents :as parents-specs]
   [family-tree-api.validators.utils :refer [validate-data]]
   [family-tree-api.utils.general :as general-utils]))

(defn validate-parent [parent]
  (let [clean-parent (general-utils/strip-nil-vals-from-hashmap parent)
        shape-error (validate-data ::parents-specs/parent clean-parent)]
    (if (not= nil shape-error)
      shape-error
      (let [field-errors (filter
                          #(not= nil %)
                          (mapv
                           (fn [parent-key]
                             (validate-data (parent-key parents-specs/field-specs) (parent-key clean-parent) (name parent-key)))
                           (keys clean-parent)))]

        (if (empty? field-errors) nil field-errors)))))
