(ns family-tree-api.handler
  (:require [family-tree-api.routes.app-routes :refer [app-routes]]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [family-tree-api.db :refer [db]]
            [family-tree-api.db.members :as members]
            [family-tree-api.db.relations :as relations]))

(defn drop-tables []
  (relations/drop-relations-table db)
  (members/drop-members-table db))

(defn create-tables []
  (members/create-members-table db)
  (relations/create-relations-table db))

(defn insert-dummy-data []
  (let [empty_member {:first_name nil
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
                      :languages nil}
        empty_relation {:member_id nil
                        :relation_id nil
                        :direct_relation nil
                        :is_blood_relation nil
                        :care nil}
        {mid1 :id} (members/insert-member db (assoc empty_member :first_name "Boipelo" :middle_names "Molefe" :last_name "Matheatsie"))
        {mid2 :id} (members/insert-member db (assoc empty_member :first_name "Pookie" :last_name "Pookie"))]

    (relations/insert-relation db (assoc empty_relation :member_id mid1 :relation_id mid2 :direct_relation "parent" :is_blood_relation false :care "giver"))))

(defn init-app []
  (drop-tables) ;; TODO: remove, for dev only. Consider migrations for future
  (create-tables)
  (insert-dummy-data) ;; TODO: remove, for dev only

  (wrap-defaults app-routes site-defaults))

(def app
  (init-app))
