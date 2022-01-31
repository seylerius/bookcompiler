(ns user
  (:require
   [ring.adapter.jetty :refer (run-jetty)]
   [inkstained.bookcompiler :refer (dev-server)]))

(def server (atom nil))
(def server-opts
  {:port  3000
   :join? false})

(defn ring-start
  []
  (reset! server
          (run-jetty dev-server server-opts)))

(defn ring-stop
  []
  (.stop @server))
