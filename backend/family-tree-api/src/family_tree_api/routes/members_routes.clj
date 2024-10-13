(ns family-tree-api.routes.members-routes
  (:require [compojure.core :as comp]
            [family-tree-api.controllers.members-controller :as controller]))

;; TODO
(def members-routes (comp/context "/members" []
                      (comp/GET ["/:id" :id #"[0-9]+"] [id] (controller/get-member-by-id (read-string id)))
                      (comp/GET ["/relations/:id" :id #"[0-9]+"] [id] (controller/get-member-direct-relations (read-string id)))
                    ;;   (comp/POST "/" [] (controller/create-members-bulk))
                    ;;   (comp/GET "/:name" [name] (controller/search-member-by-name name))
                    ;;   (comp/GET "/:name" [name] (controller/search-member-by-name name))
                      ))