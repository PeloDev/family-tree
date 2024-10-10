(ns family-tree-api.routes.app-routes
  (:require [compojure.core :as comp]
            [family-tree-api.routes.hello_world_routes :refer [hello-world-routes]]))

(comp/defroutes app-routes
  (comp/context "/api" []
    hello-world-routes))