(ns family-tree-api.routes.members-routes
  (:require [compojure.core :as comp]
            [family-tree-api.controllers.members-controller :as controller]))

;; TODO
(def members-routes (comp/context "/members" []
                      (comp/GET ["/:id" :id #"[0-9]+"] [id] (controller/get-member-by-id (read-string id)))
                      (comp/GET ["/parents/:id" :id #"[0-9]+"] [id] (controller/get-member-parents (read-string id)))
                      (comp/GET ["/children/:id" :id #"[0-9]+"] [id] (controller/get-member-children (read-string id)))
                      (comp/GET ["/children-between-couple/:id1/:id2" :id1 #"[0-9]+" :id2 #"[0-9]+"] [id1 id2]  (controller/get-children-between-2-members (read-string id1) (read-string id2)))
                    ;;   (comp/POST "/" [] (controller/create-members-bulk))
                    ;;   (comp/GET "/:name" [name] (controller/search-member-by-name name))
                    ;;   (comp/GET "/:name" [name] (controller/search-member-by-name name))
                      ))