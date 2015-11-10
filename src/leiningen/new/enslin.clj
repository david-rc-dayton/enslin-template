(ns leiningen.new.enslin
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def render (renderer "enslin"))

(def proj-dir (io/file (System/getProperty "leiningen.original.pwd")))

(defn unpack
  [name-proj name-in name-out]
  (let [p  (string/join "/" ["leiningen" "new" "enslin" name-in])
        i  (io/resource p)
        o  (io/file proj-dir name-proj name-out)
        _  (io/make-parents o)
        is (io/input-stream i)
        os (io/output-stream o)]
    (io/copy is os)
    (.flush os)))

(defn enslin
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' enslin project.")
    (->files data
             ["project.clj"
              (render "project.clj" data)]
             ["src/{{sanitized}}/core.clj"
              (render "core.clj" data)]
             ["src/{{sanitized}}/menu.clj"
              (render "menu.clj" data)]
             ["src/{{sanitized}}/routes.clj"
              (render "routes.clj" data)]
             ["src/{{sanitized}}/settings.clj"
              (render "settings.clj" data)]
             ["src/{{sanitized}}/base/engine.clj"
              (render "engine.clj" data)]
             ["src/{{sanitized}}/base/swing.clj"
              (render "swing.clj" data)]
             ["src/{{sanitized}}/templates/main.clj"
              (render "main.clj" data)]
             ["resources/public/css/w3.css"
              (render "w3.css" data)]
             ["resources/public/js/script.js"
              (render "script.js" data)])
    (mapv #(apply unpack (:name data) %)
          [["icon.png" "resources/icon.png"]])))
