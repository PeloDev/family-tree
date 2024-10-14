(ns family-tree-api.services.spouses-service
  (:require [family-tree-api.db.db :refer [db]]
            [family-tree-api.db.spouses :as spouses]
            [family-tree-api.validators.spouses :refer [validate-spouse]]
            [family-tree-api.utils.general :refer [fill-missing-fields-strict]]
            [family-tree-api.models.spouses :refer [template-spouse]]))

(defn fill-spouse-keys [partial-spouse]
  (fill-missing-fields-strict partial-spouse template-spouse))
