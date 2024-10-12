(ns family-tree-fe.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [family-tree-fe.components.member :refer [member-node]]
   [family-tree-fe.api.api :refer [get-member-by-id]]))

;; -------------------------
;; Views

(defn member-nodes [members]
  [:div (map (fn [member]
               ^{:key (:id member)} [member-node member])
             members)])

(defn member-exists? [members id]
  (not= nil (some #(= (:id %) id) members)))

(defn add-member-if-not-exists [members member]
  (if (member-exists? @members (:id member))
    @members
    (swap! members conj member)))

(def members (r/atom []))

(defn home-page []
  (get-member-by-id 1 (fn [member]
                        (add-member-if-not-exists members member)))
  [:div {:style
         {:min-width "100vw" :min-height "100vh"}}
   [:h2 "Welcome to Reagent"]
   [:div {:style
          {:position "absolute" :top "50%" :left "50%"}}
    [member-nodes @members]]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
