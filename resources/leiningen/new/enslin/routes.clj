(ns {{name}}.routes
  (:require [{{name}}.settings :refer [defaults]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.json :refer [wrap-json-response]]
            [stencil.core :refer [render-file]]))

(defroutes handler
  (resources (defaults :resource-route))
  (GET "/" request (render-file "templates/main" {:message "Hello, world!"}))
  (GET "/hello/:n" [n] (println "Hello, world!:" n)))

(defroutes app
  (wrap-json-response handler))
