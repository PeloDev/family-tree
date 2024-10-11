(ns family-tree-api.controllers.members-controller
  (:use [ring.util.response :only [response content-type status]])
  (:require [family-tree-api.services.members-service :as members-service]
            [cheshire.core :as json]
            [family-tree-api.controllers.utils :refer [get-exception-type]]))

(defn create-member [member]
  ;;   TODO
  )

(defn create-members-bulk [members]
  ;;   TODO
  )

(defn get-member-by-id [id]
  (try
    (-> (response (json/generate-string
                   (members-service/get-member-by-id id)))
        (content-type "application/json")
        (status 200))
    (catch Exception e
      (-> (response (json/generate-string
                     e))
          (content-type "application/json")
          (status
           (if (= "validation-error" (get-exception-type e))
             400
             500))))))

(defn search-member-by-name [name]
;;   TODO
  )

(defn update-member [id data]
  ;;   TODO
  )

(defn remove-member [id]
  ;;   TODO
  )
