(ns family-tree-api.utils.general)

(defn fill-missing-fields-strict [partial-hashmap template-hashmap]
  (merge template-hashmap (select-keys partial-hashmap (keys template-hashmap))))

(defn strip-nil-vals-from-hashmap [m]
  (reduce-kv (fn [acc k v]
               (if (nil? v)
                 acc
                 (assoc acc k v))) {} m))

(defn pipe [data & fns]
  (reduce
   (fn [res fn]
     (fn res))
   data
   fns))

(defn nil-safe-pipe [data & fns]
  (apply
   pipe
   data
   (interpose
    (fn [d]
      (if (nil? d) (reduced d) d))
    fns)))

(defn compose [& fns]
  (fn [data] (apply pipe data fns)))