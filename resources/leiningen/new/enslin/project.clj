(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.github.insubstantial/substance "7.3"]
                 [compojure "1.4.0"]
                 [http-kit "2.1.19"]
                 [seesaw "1.4.5"]
                 [stencil "0.5.0"]]
  :local-repo ".m2/repository"
  :main ^:skip-aot {{name}}.core
  :profiles {:uberjar {:aot :all :omit-source true}})
