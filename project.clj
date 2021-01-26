(defproject fo "0.1.0-SNAPSHOT"
  :description "与佛论禅"
  :url "https://folol.herokuapp.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]

                 [com.taoensso/timbre "5.1.0"]
                 [buddy/buddy-core "1.6.0"]
                 [compojure "1.6.2"]
                 [ring "1.8.2"]
                 [aleph "0.4.6"]]

  :resource-paths ["resources/"]
  :ring {:auto-refresh? true :auto-reload? true
         :reload-paths ["src"]
         :refresh-paths ["resources/"]}

  :main fo.core
  :aot [fo.core]
  )
