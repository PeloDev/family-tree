(ns family-tree-api.controllers.utils
  (:use [ring.util.response :only [response content-type status]])
  (:require
   [cheshire.core :as json]
   [family-tree-api.utils.general :refer [nil-safe-pipe]]))

(def bad-request-error-types ["validation-error"])

(defn get-exception-type [e]
  (nil-safe-pipe e ex-data :type))

(defn is-exception-bad-request? [e]
  (.contains bad-request-error-types (get-exception-type e)))

(defn json-response [{:keys [data status-code]}]
  (-> (response (json/generate-string
                 data))
      (content-type "application/json")
      (status status-code)))
