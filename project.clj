(defproject inkstained/bookcompiler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [hiccup "1.0.5"]
                 [garden "1.3.10"]
                 [stasis "2.5.1"]
                 [clj-commons/clj-yaml "0.7.107"]]
  :main ^:skip-aot inkstained.bookcompiler
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {;; :plugins [[lein-ring "0.12.6"]]
                   :dependencies [[ring "1.9.5"]]
                   :ring {:handler inkstained.bookcompiler/dev-server
                          :nrepl {:start? true}}
                   :source-paths ["src" "dev"]
                   :repl-options {:init-ns inkstained.bookcompiler
                                  :init (use '[user])}}})
