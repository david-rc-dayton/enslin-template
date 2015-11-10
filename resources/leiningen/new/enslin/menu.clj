(ns {{name}}.menu
  (:require [{{name}}.base.engine :refer [load-page!]]
            [{{name}}.settings :refer [defaults]]
            [{{name}}.base.swing :as swing]
            [seesaw.core :as s]))

(defn center!
  [dialog]
  (doto dialog
    (.setLocationRelativeTo (swing/mainframe))))

(defn file-action
  [event]
  (println (str "Event triggered: " (.getActionCommand event))))

(defn about-action
  [_]
  (let [icon (defaults :icon)
        text (s/grid-panel :columns 1 :items (defaults :about-text))]
    (doto (s/dialog :content (s/flow-panel :hgap 24 :items [icon text])
                    :resizable? false)
      s/pack! center! s/show!)))

(defn file-items
  []
  [(s/menu-item :text "Action 1" :listen [:action file-action])
   (s/menu-item :text "Action 2" :listen [:action file-action])
   (s/menu-item :text "Action 3" :listen [:action file-action])])

(defn help-items
  []
  [(s/menu-item :text "About" :listen [:action about-action])])

(defn menu-items
  []
  [(s/menu :text "File" :items (file-items))
   (s/menu :text "Help" :items (help-items))])
