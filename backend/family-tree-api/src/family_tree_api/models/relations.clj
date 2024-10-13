(ns family-tree-api.models.relations
  (:require [clojure.spec.alpha :as s]))

(def template-relation {:member_id nil
                        :relation_id nil
                        :member_to_relation nil
                        :relation_to_member nil
                        :is_blood_relation nil
                        :care nil})

;; Relation shape
(s/def ::relation (s/keys
                   :req-un [::member_id ::relation_id ::member_to_relation ::relation_to_member ::is_blood_relation]
                   :opt-un [::care]))

;; Relation field
(def field-specs {:member_id int?
                  :relation_id int?
                  :member_to_relation (s/and string? not-empty)
                  :relation_to_member (s/and string? not-empty)
                  :is_blood_relation boolean?
                  :care (s/and string? not-empty)})