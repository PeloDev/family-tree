(ns kit.clojure-app.env
  (:require
    [clojure.tools.logging :as log]
    [kit.clojure-app.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[clojure-app starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[clojure-app started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[clojure-app has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})
