(ns bookcompiler.core
  (:require [hiccup.page :as hp]
            [boot.core :as boot :refer [deftask]]
            [clojure.java.io :as io]
            [clojure.string :as s]))

;; (defn page [data]
;;   (hp/html5
;;    [:div {:style "max-width: 900px; margin: 40px auto;"}
;;     (-> data :entry :content)]))

(defn pref-checkbox
  "Preference checkbox"
  ([id checked]
   [:input {:class   "prefCB"
            :type    "checkbox"
            :id      id
            :checked "checked"}])
  ([id]
   [:input {:class "prefCB"
            :type  "checkbox"
            :id    id}]))

(defn pref-label
  "Preference label"
  [id text]
  [:label {:class "pref"
           :for   id}
   text])

(defn make-chapter-list
  "Process chapters into an ordered and numbered chapter list."
  [chapter-source]
  (->> chapter-source
       io/file
       .list
       (filter #(re-matches #"\d{4}~.+\.convo" %))
       (map #(subs % 4))
       (map #(s/replace % ".convo" ""))))

(defn menu
  "Generate the navigation menu for a given set of chapters."
  [chapters copyright author]
  [(pref-checkbox "drawerState")
   (pref-checkbox "glowState" :checked)
   (pref-checkbox "fontState")
   (pref-checkbox "annotate-voices")
   (pref-checkbox "print-state")
   [:nav.drawer
    (pref-label "fontState" "Sexy Fonts")
    (pref-label "glowState" "Glowy Text")
    (pref-label "annotate-voices" "Show Avatars")
    (pref-label "printState" "Printer Friendly")
    [:ul.chapters
     ;; TODO: Insert chapter list
     ]
    [:p.copyright]]])

(defn page
  "Overall template for a page on a Heartformer site."
  [data]
  (hp/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name    "viewport"
            :content "width=device-width, initial-scale=1"}]
    [:title (-> data :meta :title)]
    ;; TODO: Insert stylesheets here
    ]
   [:body]))
