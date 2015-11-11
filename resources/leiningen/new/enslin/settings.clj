(ns {{name}}.settings
  (:require [clojure.java.io :as io]))

(defmacro version
  []
  (System/getProperty "{{name}}.version"))

(defonce debug-atom
  (atom
   {
    :enabled? false
    :port 3000
    }))

(defonce defaults-atom
  (atom
   {
    :name "{{name}}"
    :resource-route "/"
    :launch-path "/"
    :frame-size [800 :by 600]
    :icon (io/resource "icon.png")
    :about-text ["{{name}}" (str "v" (version))]
    }))

(defn debug
  [k]
  (if (= k :all)
    @debug-atom
    (get @debug-atom k)))

(defn debug!
  [& args]
  (let [args-map (apply hash-map args)]
    (swap! debug-atom merge args-map)))

(defn defaults
  [k]
  (if (= k :all)
    @defaults-atom
    (get @defaults-atom k)))

(defn defaults!
  [& args]
  (let [args-map (apply hash-map args)]
    (swap! defaults-atom merge args-map)))
