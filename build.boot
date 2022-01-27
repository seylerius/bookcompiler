(set-env!
 :source-paths #{"src"}
 :resource-paths #{"content"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [garden "1.3.10" :exclusions [org.clojure/clojure]]
                 [clj-commons/clj-yaml "0.7.0"]])

(require '[io.perun :as perun])

(deftask build
  "Build a Heartforming story site in the style of Lucy's \"Adventures\"."
  []
  (comp (perun/global-metadata :filename "books.edn")
        ))
