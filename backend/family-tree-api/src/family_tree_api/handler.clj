(ns family-tree-api.handler
  (:require [family-tree-api.routes.app-routes :refer [app-routes]]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.cors :refer [wrap-cors]]
            [family-tree-api.db.db :refer [db]]
            [family-tree-api.db.members :as members]
            [family-tree-api.db.relations :as relations]
            [family-tree-api.services.members-service :as members-service]
            [family-tree-api.services.relations-service :as relations-service]
            [family-tree-api.db.migrations :as migrations]))

(defn drop-tables []
  (relations/drop-relations-table db)
  (members/drop-members-table db)
  (migrations/drop-migrations-table db))

(defn create-tables []
  (members/create-members-table db)
  (relations/create-relations-table db))

(defn insert-dummy-data []
  (let [{mid1 :id} (members/insert-member db (members-service/fill-member-keys {:first_name "Boipelo" :middle_names "Molefe" :last_name "Matheatsie" :gender "male" :image_url "https://boipelo.dev/_app/immutable/assets/optimal-me.ee2f9c8d.webp"}))
        {mid2 :id} (members/insert-member db (members-service/fill-member-keys {:first_name "Pookie" :last_name "Pookie" :image_url "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/025.png"}))
        {mid3 :id} (members/insert-member db (members-service/fill-member-keys {:first_name "Nerdy" :middle_names "Sweet" :last_name "Angel" :gender "female" :image_url "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfeNL87YxBJeBtXMDFah7DRMoFTS0Uv1yalw&s"}))]

    (relations/insert-relation db (relations-service/fill-relation-keys {:member_id mid1 :relation_id mid2 :member_to_relation "parent" :relation_to_member "child" :is_blood_relation false :care "giver"}))
    (relations/insert-relation db (relations-service/fill-relation-keys {:member_id mid3 :relation_id mid2 :member_to_relation "parent" :relation_to_member "child" :is_blood_relation false :care "giver"}))
    (relations/insert-relation db (relations-service/fill-relation-keys {:member_id mid1 :relation_id mid3 :member_to_relation "spouse" :relation_to_member "spouse" :is_blood_relation false}))))


(defn init-app []
  (drop-tables) ;; TODO: remove, for dev only. Consider migrations for future
  (create-tables)
  (migrations/run-migrations)
  (insert-dummy-data) ;; TODO: remove, for dev only

  (-> app-routes
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete]
                 :access-control-allow-headers #{"accept"
                                                 "accept-encoding"
                                                 "accept-language"
                                                 "authorization"
                                                 "content-type"
                                                 "origin"})
      wrap-json-body
      wrap-json-response
      (wrap-defaults site-defaults)))

(def app
  (init-app))
