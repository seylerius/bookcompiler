(ns bookcompiler.voice
  (:require [hiccup.page :as hp]
            [boot.core :as boot :refer [deftask]]
            [garden.core :as g :refer [css]]
            [garden.selectors :as sel]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [clj-yaml.core :as yaml]))

(defn parse-voice
  "Process the YAML configuration for a HeartFormer voice."
  [filename]
  (yaml/parse-string (slurp filename)))

(defn crop-avatar
  "Crop the avatar file for the given voice."
  [{{filename :file {:keys [x y width height]} :crop} :avatar}]
  ;; TODO: Call ImageMagick to crop the image accordingly
  )
