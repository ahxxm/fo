(ns fo.core-test
  (:require [clojure.test :refer :all]
            [fo.core :refer [app]]
            [fo.scheme :refer [fo-enc fo-dec]]
            [aleph.http :as http]
            [aleph.netty :as netty]
            [byte-streams :as bs]))

(def source "123")
(def expected "至豆友智三量濟僧舍伊千阿北灭兄念开親牟")

(deftest fo-test
  (testing "enc and dec"
    (is (= (fo-enc source) (vec expected))
        (= (fo-dec expected) source))))

(deftest endpoint-test
  (let [server (http/start-server app {:port 0})
        port (netty/port server)
        base (str "http://localhost:" port)]
    (try
      (testing "GET / serves index page"
        (let [rsp @(http/get (str base "/"))
              body (bs/to-string (:body rsp))]
          (is (= 200 (:status rsp)))
          (is (.contains body "与佛论禅"))))

      (testing "encode-decode roundtrip"
        (let [plaintext "hello world"
              enc-rsp @(http/post (str base "/encode") {:body plaintext})
              encoded (bs/to-string (:body enc-rsp))
              dec-rsp @(http/post (str base "/decode") {:body encoded})
              decoded (bs/to-string (:body dec-rsp))]
          (is (= 200 (:status enc-rsp)))
          (is (seq encoded))
          (is (= 200 (:status dec-rsp)))
          (is (= plaintext decoded))))

      (finally
        (.close server)))))
