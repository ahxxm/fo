(ns fo.core
  (:import
   [io.netty.handler.ssl SslContextBuilder])
  (:require
   [taoensso.timbre :refer [refer-timbre]]
   [clojure.java.io :as jio]
   [compojure.core :as compojure :refer [GET defroutes]]
   [compojure.route :as route]
   [ring.middleware.params :as params]
   [ring.middleware.reload :refer [wrap-reload]]
   [aleph.http :as http])
  (:gen-class))

(refer-timbre)

(defn home
  [req]
  (let [index (-> "index.html" jio/resource jio/reader)]
    {:status 200
     :body (slurp index)}))

(defn not-found
  [req]
  (let [rsp "no such page"]
    (debug "no such page" req)
    rsp))

(defroutes app
  (GET "/" [] home)
  (route/not-found not-found))

(defn -main
  "I don't do a whole lot."
  []
  ;; TODO: read port from env
  (info "start http server at 1000")
  (http/start-server (wrap-reload #'app) {:port 10000}))
