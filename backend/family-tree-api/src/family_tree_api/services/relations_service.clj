(ns family-tree-api.services.relations-service
  (:require [family-tree-api.db :refer [db]]
            [family-tree-api.db.relations :as relations]
            [family-tree-api.validators.relations :refer [validate-relation]]
            [family-tree-api.utils.general :refer [fill-missing-fields-strict]]
            [family-tree-api.models.relations :refer [template-relation]]))

(defn fill-relation-keys [partial-relation]
  (fill-missing-fields-strict partial-relation template-relation))
