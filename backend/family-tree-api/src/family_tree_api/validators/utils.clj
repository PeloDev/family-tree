(ns family-tree-api.validators.utils
  (:require [clojure.spec.alpha :as s])
  (:import java.net.URL))



(defn valid-url? [url]
  (try
    (URL. url)
    true
    (catch Exception _ false)))

(defn validate-data [spec-def data & [k]]
  (let [is-valid (s/valid? spec-def data)]
    (if (not is-valid)
      (if (nil? k) (s/explain-str spec-def data) (str "(" k ") " (s/explain-str spec-def data)))
      nil)))