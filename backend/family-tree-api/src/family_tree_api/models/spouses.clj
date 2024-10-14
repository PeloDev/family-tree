(ns family-tree-api.models.spouses
  (:require [clojure.spec.alpha :as s]))

(def template-spouse {:id nil
                      :spouse_id nil})

;; spouse shape
(s/def ::spouse (s/keys
                 :req-un [::id ::spouse_id]))

;; spouse field
(def field-specs {:id int?
                  :spouse_id int?})