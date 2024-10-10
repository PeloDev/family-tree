(ns family-tree-api.routes.app-routes
  (:require [compojure.core :as comp]
            [family-tree-api.controllers.hello-world-controller :refer [greet-world]]))

(comp/defroutes app-routes
  (comp/context "/api" []
    (comp/context "/helloWorld" []
      (comp/GET "/" [] (greet-world)))))