(ns family-tree-api.routes.member_routes
  (:require [compojure.core :as comp]
            [family-tree-api.controllers.members-controller :as controller]))

;; TODO
(def members-routes (comp/context "members" []
                    ;;   (comp/POST "/" [] (controller/create-members-bulk))
                    ;;   (comp/GET "/:name" [name] (controller/search-member-by-name name))
                    ;;   (comp/GET "/:name" [name] (controller/search-member-by-name name))
                      ))