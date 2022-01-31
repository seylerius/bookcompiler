(ns inkstained.bookcompiler
  (:require [hiccup.page :as hp]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [stasis.core :as stasis]
            [clojure.edn :as edn])
  (:gen-class))

(defn get-test-books []
  (let [config-file "/home/seylerius/.config/bookcompiler/books.edn"]
    (edn/read-string (slurp config-file))))

(defn render-pages []
  (let [config (get-test-books)
        {:keys [title-short
                title-long
                sources]
         :as book} (first (config :book-releases))
        ]
    (merge {"/" (hp/html5 [:head
                           [:meta {:charset "utf-8"}]
                           [:meta {:name "viewport"
                                   :content (str "width=device-width, "
                                                 "initial-scale=1")}]
                           [:title title-short]]
                          [:body [:h1 title-long]])})))

(def dev-server (stasis/serve-pages render-pages))

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
