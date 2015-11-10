(ns {{name}}.core
  (:require [{{name}}.base.engine :refer [port start-server!]]
            [{{name}}.base.swing :refer [mainframe! substance-theme!]]
            [{{name}}.menu :as menu]
            [{{name}}.routes :as routes]
            [{{name}}.settings :refer [debug debug!]]
            [seesaw.core :as s])
  (:gen-class))

(defn enable-debug!
  []
  (debug! :enabled? true))

(defn -main
  [& args]
  (substance-theme! :dust)
  (start-server! routes/app)
  (when (debug :enabled?)
    (println (str "Starting debug server on port " (port) ".")))
  (s/invoke-later (mainframe! (menu/menu-items))))
