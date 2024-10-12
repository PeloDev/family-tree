(ns family-tree-fe.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [family-tree-fe.components.member :refer [member-node]]))

;; -------------------------
;; Views

(defn member-nodes []
  [:div (map (fn [idx]
               ^{:key idx} [member-node idx])
             [{:first_name "Boipelo" :last_name "Matheatsie" :image_url "https://boipelo.dev/_app/immutable/assets/optimal-me.ee2f9c8d.webp"}
              {:first_name "Pookie" :last_name "Pookie" :image_url "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/025.png"}
              {:first_name "Nerdy" :last_name "Angel" :image_url "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfeNL87YxBJeBtXMDFah7DRMoFTS0Uv1yalw&s"}])])

(defn home-page []
  [:div
   [:h2 "Welcome to Reagent"]
   [member-nodes]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
