(ns family-tree-api.validators.members
  (:require
   [family-tree-api.models.members :as members-specs]
   [family-tree-api.validators.utils :refer [validate-data]]
   [family-tree-api.helpers :as helpers]))

(defn validate-member [member]
  (let [clean-member (helpers/strip-nil-vals-from-hashmap member)
        shape-error (validate-data ::members-specs/member clean-member)]
    (if (not= nil shape-error)
      shape-error
      (let [field-errors (filter
                          #(not= nil %)
                          (mapv
                           (fn [member-key]
                             (validate-data (member-key members-specs/field-specs) (member-key clean-member) (name member-key)))
                           (keys clean-member)))]
        (if (empty? field-errors) nil field-errors)))))


(comment
  (def bmmember {:first_name "First" :last_name "Last" :date_of_birth (java.util.Date.)})
  (validate-member bmmember))