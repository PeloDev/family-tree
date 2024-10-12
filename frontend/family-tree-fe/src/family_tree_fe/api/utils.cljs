(ns family-tree-fe.api.utils
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

;; TODO: use env variables
(def base-url "http://localhost:3001/api")

(defn get [path on-response & query]
  (go (let [response (<! (http/get (str base-url path)
                                   {:with-credentials? false
                                    :query-params query}))]
        ;; TODO: handle errors
        (on-response (:body response)))))

