(ns family-tree-api.models.members
  (:require [clojure.spec.alpha :as s]
            [family-tree-api.validators.utils :refer [valid-url?]]))

(def template-member {:first_name nil
                      :middle_names nil
                      :last_name nil
                      :family_name nil
                      :nickname nil
                      :preferred_name nil
                      :gender nil
                      :date_of_birth nil
                      :address_of_birth nil
                      :city_of_birth nil
                      :province_of_birth nil
                      :country_of_birth nil
                      :image_url nil
                      :ethnicity nil
                      :culture nil
                      :languages nil})
;; Member shape
(s/def ::member (s/keys
                 :req-un [::first_name ::last_name]
                 :opt-un [::middle_names
                          ::family_name
                          ::nickname
                          ::preferred_name
                          ::gender
                          ::date_of_birth
                          ::address_of_birth
                          ::city_of_birth
                          ::province_of_birth
                          ::country_of_birth
                          ::image_url
                          ::ethnicity
                          ::culture
                          ::languages]))

;; Member fields
(def field-specs {:first_name (s/and string? not-empty)
                  :middle_names (s/and string? not-empty)
                  :last_name (s/and string? not-empty)
                  :family_name (s/and string? not-empty)
                  :nickname (s/and string? not-empty)
                  :preferred_name (s/and string? not-empty)
                  :gender (s/and string? not-empty)
                  :date_of_birth inst?
                  :address_of_birth (s/and string? not-empty)
                  :city_of_birth (s/and string? not-empty)
                  :province_of_birth (s/and string? not-empty)
                  :country_of_birth (s/and string? not-empty)
                  :image_url (s/and string? valid-url?)
                  :ethnicity (s/and string? not-empty)
                  :culture (s/and string? not-empty)
                  :languages (s/and string? not-empty)})


(comment
  (def bmmember {:first_name "Muntu" :last_name "Mandla" :date_of_birth (java.util.Date.)})
  bmmember
  (s/valid? ::member bmmember)

  (s/valid? ::first_name (:first_name bmmember))
  (s/explain-str ::date_of_birth (:date_of_birth bmmember)))