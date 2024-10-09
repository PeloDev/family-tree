(ns kit.clojure-app.test-utils
  (:require
    [kit.clojure-app.core :as core]
    [integrant.repl.state :as state]))

(defn system-state 
  []
  (or @core/system state/system))

(defn system-fixture
  []
  (fn [f]
    (when (nil? (system-state))
      (core/start-app {:opts {:profile :test}}))
    (f)
    (core/stop-app)))
