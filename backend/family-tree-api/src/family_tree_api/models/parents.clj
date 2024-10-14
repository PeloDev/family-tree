(ns family-tree-api.models.parents
  (:require [clojure.spec.alpha :as s]))

(def template-parent {:id nil
                      :child_id nil
                      :is_blood_relation nil
                      :is_caregiver nil})

;; Parent shape
(s/def ::parent (s/keys
                 :req-un [::id ::child_id ::is_blood_relation ::is_caregiver]))

;; Parent field
(def field-specs {:id int?
                  :child_id int?
                  :is_blood_relation boolean?
                  :is_caregiver (s/and string? not-empty)})