(defproject fo "0.1.0-SNAPSHOT"
  :description "与佛论禅"
  :url "https://folol.herokuapp.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]

                 [com.taoensso/timbre "5.2.1"]
                 [buddy/buddy-core "1.10.413"]
                 [compojure "1.6.3"]
                 [ring "1.9.5"]
                 [aleph "0.4.7"]]

  :resource-paths ["resources/"]
  :ring {:auto-refresh? true :auto-reload? true
         :reload-paths ["src"]
         :refresh-paths ["resources/"]}

  :main fo.core
  :aot [fo.core]
  )
