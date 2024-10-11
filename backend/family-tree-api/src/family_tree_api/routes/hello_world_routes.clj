(ns family-tree-api.routes.hello-world-routes
  (:require [compojure.core :as comp]
            [family-tree-api.controllers.hello-world-controller :refer [greet-world]]))

(def hello-world-routes (comp/context "/helloWorld" []
                          (comp/GET "/" [] (greet-world))))