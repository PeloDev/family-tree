(ns family-tree-api.helpers
  (:require [family-tree-api.models.members :refer [template-member]]
            [family-tree-api.models.relations :refer [template-relation]]))

(defn fill-missing-fields-strict [partial-hashmap template-hashmap]
  (merge template-hashmap (select-keys partial-hashmap (keys template-hashmap))))

(defn strip-nil-vals-from-hashmap [m]
  (reduce-kv (fn [acc k v]
               (if (nil? v)
                 acc
                 (assoc acc k v))) {} m))

(defn fill-member-keys [partial-member]
  (fill-missing-fields-strict partial-member template-member))

(defn fill-relation-keys [partial-relation]
  (fill-missing-fields-strict partial-relation template-relation))


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

(defn get-exception-type [e]
  (nil-safe-pipe e ex-data :type))

(comment
  (defn nilfn [x] nil)
  (defn convert-to-hashmap [x] {:x x})
  (pipe 3 #(+ % 2) #(* % 2) convert-to-hashmap :x #(* % %) #(- % 13))
  (nil-safe-pipe 3 #(+ % 2) #(* % 2) nilfn #(* % %) #(- % 13))
  (def myerr (ex-info "you did badly child" {:type "father's disappointment" :pain 5000}))
  (try 
    (throw myerr)
    (catch Exception e
      (println "test exception1: " (ex-data e))
      (println "test exception2: " (get-exception-type e))
      ))
  
  )

