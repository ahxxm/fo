(defproject fo "0.1.0-SNAPSHOT"
  :description "与佛论禅"
  :url "https://folol.herokuapp.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.12.0"]

                 [com.taoensso/timbre "6.5.0"]
                 [buddy/buddy-core "1.12.0-430"]
                 [compojure "1.7.1"]
                 [ring "1.12.2"]
                 [aleph "0.8.1"]]

  :resource-paths ["resources/"]
  :ring {:auto-refresh? true :auto-reload? true
         :reload-paths ["src"]
         :refresh-paths ["resources/"]}

  :main fo.core
  :aot [fo.core]
  )
