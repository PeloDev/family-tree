(ns family-tree-fe.components.member
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]))

;; TODO: How to render a family tree
;; Idea #1:
;;   1. fetch the root member, render
;;   2. fetch all their spouses, render beside them
;;   3. fetch all children between root member and each spouse, render children beneath each spouse pairing
;;   4. fetch all children of root member without spouse, render below somewhere away from spouse sides (figure out how to deal with >2 spouses at some point...)
;;   5. repeat 2-5 with each child as root member until concluded
;;   * for ease, make the inital root member someone high up the tree

(defn member-node [{:keys [first_name last_name image_url member_to_relation]}]
  (let [base-style {:position "absolute"
                    :display "flex"
                    :flex-direction "column"
                    :align-items "center"
                    :gap 3
                    :font-size "14px"
                    :line-height 1}
        offset-style (if (= member_to_relation "parent")
                       {:top 200 :left 150}
                       (if (or
                            (= member_to_relation "spouse")
                            (= member_to_relation "sibling"))
                         {:left 300}
                         {}))]
    [:div
     {:style (merge base-style offset-style)}
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