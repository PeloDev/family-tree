(ns family-tree-api.controllers.members-controller
  (:use [ring.util.response :only [response content-type status]])
  (:require [family-tree-api.services.members-service :as members-service]
            [cheshire.core :as json]
            [family-tree-api.helpers :as helpers]))

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
           (if (= "validation-error" (helpers/get-exception-type e))
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


(comment
  (Integer/pa "no")
  ;; Rebel Ridge 🥈
  ;; Crazy Rich Asians ❌
  ;; The Princess Diaries ❌
  ;; The Devil Wears Prada 🥇
  ;; Highschool Musical ❌
  )