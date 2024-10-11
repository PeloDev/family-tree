(ns family-tree-api.models.relations
  (:require [clojure.spec.alpha :as s]))

(def template-relation {:member_id nil
                        :relation_id nil
                        :direct_relation nil
                        :is_blood_relation nil
                        :care nil})

;; Relation shape
(s/def ::relation (s/keys
                   :req-un [::member_id ::relation_id ::direct_relation ::is_blood_relation]
                   :opt-un [::care]))

;; Relation field
(def field-specs {:member_id int?
                  :relation_id int?
                  :direct_relation (s/and string? not-empty)
                  :is_blood_relation boolean?
                  :care (s/and string? not-empty)})