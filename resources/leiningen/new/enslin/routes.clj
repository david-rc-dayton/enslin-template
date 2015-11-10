(ns {{name}}.routes
  (:require [{{name}}.settings :refer [defaults]]
            [{{name}}.templates.main :as t]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found resources]]))

(defroutes app
  (resources (defaults :resource-route))
  (GET "/" request (t/main-screen "Hello, world!"))
  (GET "/hello/:n" [n] (println "Hello, world!:" n)))
