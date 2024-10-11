(ns family-tree-api.controllers.utils
  (:require [family-tree-api.utils.general :refer [nil-safe-pipe]]))

(defn get-exception-type [e]
  (nil-safe-pipe e ex-data :type))