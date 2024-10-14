(ns family-tree-api.validators.spouses
  (:require
   [family-tree-api.models.spouses :as spouses-specs]
   [family-tree-api.validators.utils :refer [validate-data]]
   [family-tree-api.utils.general :as general-utils]))

;; TODO: this code is repeated too many times
(defn validate-spouse [spouse]
  (let [clean-spouse (general-utils/strip-nil-vals-from-hashmap spouse)
        shape-error (validate-data ::spouses-specs/spouse clean-spouse)]
    (if (not= nil shape-error)
      shape-error
      (let [field-errors (filter
                          #(not= nil %)
                          (mapv
                           (fn [spouse-key]
                             (validate-data (spouse-key spouses-specs/field-specs) (spouse-key clean-spouse) (name spouse-key)))
                           (keys clean-spouse)))]

        (if (empty? field-errors) nil field-errors)))))
