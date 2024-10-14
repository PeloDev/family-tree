(ns family-tree-api.services.parents-service
  (:require [family-tree-api.db.db :refer [db]]
            [family-tree-api.db.parents :as parents]
            [family-tree-api.validators.parents :refer [validate-parent]]
            [family-tree-api.utils.general :refer [fill-missing-fields-strict]]
            [family-tree-api.models.parents :refer [template-parent]]))

(defn fill-parent-keys [partial-parent]
  (fill-missing-fields-strict partial-parent template-parent))
