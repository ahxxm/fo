(defproject fo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.async "0.4.474"]

                 [compojure "1.6.1"]
                 [ring "1.7.0"]
                 [aleph "0.4.6"]]

  :resource-paths ["resources/"]

  :main fo.core
  :aot [fo.core]
  )
