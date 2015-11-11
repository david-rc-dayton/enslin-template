(ns {{name}}.routes
  (:require [{{name}}.settings :refer [defaults]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found resources]]
            [stencil.core :refer [render-file]]))

(defroutes app
  (resources (defaults :resource-route))
  (GET "/" request (render-file "templates/main" {:message "Hello, world!"}))
  (GET "/hello/:n" [n] (println "Hello, world!:" n)))
