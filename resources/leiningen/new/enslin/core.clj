(ns {{name}}.core
  (:require [{{name}}.base.engine :refer [port start-server!]]
            [{{name}}.base.swing :refer [mainframe! substance-theme!]]
            [{{name}}.menu :as menu]
            [{{name}}.routes :as routes]
            [{{name}}.settings :refer [debug debug!]]
            [seesaw.core :as s])
  (:gen-class))

(defn debug-server
  []
  (debug! :enabled? true)
  (start-server! routes/app)
  (println (str "Launching debug server on port " (port) ".")))

(defn -main
  [& args]
  (substance-theme! :dust)
  (start-server! routes/app)
  (s/invoke-later (mainframe! (menu/menu-items))))
