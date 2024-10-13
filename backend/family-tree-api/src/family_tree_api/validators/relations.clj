(ns family-tree-api.validators.relations
  (:require
   [family-tree-api.models.relations :as relations-specs]
   [family-tree-api.validators.utils :refer [validate-data]]
   [family-tree-api.utils.general :as general-utils]))

(defn validate-relation [relation]
  (let [clean-relation (general-utils/strip-nil-vals-from-hashmap relation)
        shape-error (validate-data ::relations-specs/relation clean-relation)]
    (if (not= nil shape-error)
      shape-error
      (let [field-errors (filter
                          #(not= nil %)
                          (mapv
                           (fn [relation-key]
                             (validate-data (relation-key relations-specs/field-specs) (relation-key clean-relation) (name relation-key)))
                           (keys clean-relation)))]

        (if (empty? field-errors) nil field-errors)))))

(comment
  (def bmrelation {:member_id 1
                   :relation_id 2
                   :member_to_relation "cuz"
                   :relation_to_member "cuz"
                   :is_blood_relation false
                   :care "giver"})
  (validate-relation bmrelation))