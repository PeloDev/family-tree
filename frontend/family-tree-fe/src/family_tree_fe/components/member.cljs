(ns family-tree-fe.components.member
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]))

(defn member-node [{:keys [first_name last_name image_url]}]
  (let []
    [:div
     {:style
      {:display "flex"
       :flex-direction "column"
       :align-items "center"
       :gap 3
       :font-size "14px"
       :line-height 1}}
     [:div
      {:style {:border-radius "100%"
               :border-width 1
               :border-color "#000"
               :width 80
               :height 80
               :background-color "#ddd"
               :display "flex"
               :align-items "center"
               :justify-content "center"}}
      [:img {:src image_url
             :alt (str first_name " " last_name)
             :style {:object-fit "cover"
                     :border-radius "100%"
                     :width "100%"
                     :height "100%"}}]]
     [:p (str first_name " " last_name)]]))