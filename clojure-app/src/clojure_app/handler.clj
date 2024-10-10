(ns clojure-app.handler
  (:require [clojure-app.routes.app-routes :refer [app-routes]]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]))

(def app
  (wrap-defaults app-routes site-defaults))
