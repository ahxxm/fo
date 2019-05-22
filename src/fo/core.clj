(ns fo.core
  (:import
   [io.netty.handler.ssl SslContextBuilder]
   [java.net InetSocketAddress])
  (:require
   [taoensso.timbre :refer [refer-timbre]]
   [clojure.java.io :as jio]
   [compojure.core :as compojure :refer [GET POST defroutes]]
   [compojure.route :as route]
   [ring.middleware.reload :refer [wrap-reload]]
   [aleph.http :as http]
   [fo.scheme :as s])
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

(defn encode
  [req]
  (let [body (:body req)]
    (if (nil? body)
      {:status 200 :body ""}
      {:status 200 :body (-> body slurp s/fo-enc)})))

(defn decode
  [req]
  (let [body (:body req)]
    (if (nil? body)
      {:status 200 :body ""}
      {:status 200 :body (-> body slurp s/fo-dec)})))

(defroutes app
  (GET "/" [] home)
  (POST "/encode" [] encode)
  (POST "/decode" [] decode)
  (route/not-found not-found))

(defn -main
  []
  (let [port (or (-> "PORT" System/getenv) 10000)
        host (or (-> "FO_HOST" System/getenv) "localhost")]
    (info "start http server at" host ":" port)
    (let [bind (InetSocketAddress. host (Integer. port))]
      (http/start-server (wrap-reload #'app) {:socket-address bind}))))
