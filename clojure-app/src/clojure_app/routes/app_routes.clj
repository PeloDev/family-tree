(ns clojure-app.routes.app-routes
  (:require [compojure.core :as comp]
            [clojure-app.controllers.hello-world-controller :refer [greet-world]]))

(comp/defroutes app-routes
  (comp/context "/api" []
    (comp/context "/helloWorld" []
      (comp/GET "/" [] (greet-world)))))