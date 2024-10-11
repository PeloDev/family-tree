(ns family-tree-api.handler
  (:require [family-tree-api.routes.app-routes :refer [app-routes]]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [family-tree-api.db :refer [db]]
            [family-tree-api.db.members :as members]
            [family-tree-api.db.relations :as relations]
            [family-tree-api.helpers :as helpers]))

(defn drop-tables []
  (relations/drop-relations-table db)
  (members/drop-members-table db))

(defn create-tables []
  (members/create-members-table db)
  (relations/create-relations-table db))

(defn insert-dummy-data []
  (let [{mid1 :id} (members/insert-member db (helpers/fill-member-keys {:first_name "Boipelo" :middle_names "Molefe" :last_name "Matheatsie" :gender "male"}))
        {mid2 :id} (members/insert-member db (helpers/fill-member-keys {:first_name "Pookie" :last_name "Pookie"}))
        {mid3 :id} (members/insert-member db (helpers/fill-member-keys {:first_name "Nerdy" :middle_names "Sweet" :last_name "Angel" :gender "female"}))]

    (relations/insert-relation db (helpers/fill-relation-keys {:member_id mid1 :relation_id mid2 :direct_relation "parent" :is_blood_relation false :care "giver"}))
    (relations/insert-relation db (helpers/fill-relation-keys {:member_id mid3 :relation_id mid2 :direct_relation "parent" :is_blood_relation false :care "giver"}))))

(defn init-app []
  (drop-tables) ;; TODO: remove, for dev only. Consider migrations for future
  (create-tables)
  (insert-dummy-data) ;; TODO: remove, for dev only

  (wrap-defaults app-routes site-defaults))

(def app
  (init-app))
