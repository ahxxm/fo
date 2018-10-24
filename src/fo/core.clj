(ns fo.core
  (:import
   [io.netty.handler.ssl SslContextBuilder])
  (:require
   [taoensso.timbre :refer [refer-timbre]]
   [clojure.java.io :as jio]
   [compojure.core :as compojure :refer [GET POST defroutes]]
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

(defn encode
  [req]
  (info req)
  (let [body (:body req)]
    ;; TODO: encryption here
    (if (nil? body)
      {:status 200 :body ""}
      {:status 200 :body (slurp body)})))

(defn decode
  [req]
  (info req)
  (let [body (:body req)]
    ;; TODO: decryption here
    (if (nil? body)
      {:status 200 :body ""}
      {:status 200 :body (slurp body)})))

(defroutes app
  (GET "/" [] home)
  (POST "/encode" [] encode)
  (POST "/decode" [] decode)
  (route/not-found not-found))

(defn -main
  "I don't do a whole lot."
  []
  ;; TODO: read port from env
  (info "start http server at 1000")
  (http/start-server (wrap-reload #'app) {:port 10000}))
