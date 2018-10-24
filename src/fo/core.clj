(ns fo.core
  (:import
    [io.netty.handler.ssl SslContextBuilder])
  (:require
    [compojure.core :as compojure :refer [GET]]
    [ring.middleware.params :as params]
    [compojure.route :as route]
    [aleph.http :as http]
    [clojure.core.async :as a]
    [clojure.java.io :refer [file]])
  (:gen-class))


(defn hello-world-handler
  [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello world!"})

(def handler
  (params/wrap-params
    (compojure/routes
      (GET "/hello"         [] hello-world-handler)
      (route/not-found "No such page."))))

(defn -main
  "I don't do a whole lot."
  []
  (http/start-server handler {:port 10000}))
