(ns family-tree-api.controllers.members-controller
  (:require [family-tree-api.services.members-service :as members-service]
            [family-tree-api.controllers.utils :refer [json-response is-exception-bad-request?]]))

(defn create-member [member]
  ;;   TODO
  )

(defn create-members-bulk [members]
  ;;   TODO
  )

(defn get-member-by-id [id]
  (try
    (json-response {:data (members-service/get-member-by-id id) :status-code 200})
    (catch Exception e
      (json-response {:data (ex-data e)
                      :status-code (if (is-exception-bad-request? e) 400 500)}))))

(defn get-member-parents [id]
  (try
    (json-response {:data (members-service/get-member-parents id) :status-code 200})
    (catch Exception e
      (json-response {:data (ex-data e)
                      :status-code (if (is-exception-bad-request? e) 400 500)}))))

(defn get-member-children [id]
  (try
    (json-response {:data (members-service/get-member-children id) :status-code 200})
    (catch Exception e
      (json-response {:data (ex-data e)
                      :status-code (if (is-exception-bad-request? e) 400 500)}))))

(defn get-children-between-2-members [id1 id2]
  (try
    (json-response {:data (members-service/get-children-between-2-members id1 id2) :status-code 200})
    (catch Exception e
      (json-response {:data (ex-data e)
                      :status-code (if (is-exception-bad-request? e) 400 500)}))))

(defn search-member-by-name [name]
;;   TODO
  )

(defn update-member [id data]
  ;;   TODO
  )

(defn remove-member [id]
  ;;   TODO
  )
