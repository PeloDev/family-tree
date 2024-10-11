(ns family-tree-api.routes.app-routes
  (:require [compojure.core :as comp]
            [family-tree-api.routes.hello-world-routes :refer [hello-world-routes]]
            [family-tree-api.routes.members-routes :refer [members-routes]]))

(comp/defroutes app-routes
  (comp/context "/api" []
    hello-world-routes
    members-routes))