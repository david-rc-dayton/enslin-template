(ns {{name}}.base.engine
  (:require [{{name}}.settings :refer [debug]]
            [org.httpkit.server :refer [run-server]])
  (:import [javafx.application Platform]
           [javafx.embed.swing JFXPanel]
           [javafx.scene Scene]
           [javafx.scene.web WebEngine WebView]))

(defonce ^:private server-atom (atom nil))
(defonce ^:private webengine-atom (atom nil))
(defonce ^:private port-atom (atom nil))

(defn ^:private webengine
  []
  @webengine-atom)

(defn ^:private webengine!
  [engine]
  (reset! webengine-atom engine))

(defn port
  []
  @port-atom)

(defn ^:private port!
  []
  (if (debug :enabled?)
    (reset! port-atom (debug :port))
    (let [socket (java.net.ServerSocket. 0)]
      (reset! port-atom (.getLocalPort socket))
      (.close socket))))

(defn ^:private wrap-localhost
  [handler]
  (if (debug :enabled?)
    (fn [request] (handler request))
    (fn [request]
      (if (= (:remote-addr request) "127.0.0.1")
        (handler request)
        {:status 403 :body "forbidden"}))))

(defn load-page!
  [address]
  (let [engine (webengine)]
    (when-not (nil? engine)
      (Platform/runLater
       (fn [] (.load ^WebEngine engine
                     (str "http://localhost:" (port) address)))))))

(defn web-panel
  [address]
  (let [jfxpanel (JFXPanel.)]
    (Platform/runLater
     (fn [] (let [webview (WebView.)]
              (.setScene jfxpanel (Scene. webview))
              (webengine! (.getEngine webview))
              (load-page! address))))
    jfxpanel))

(defn start-server!
  [routes]
  (port!)
  (reset! server-atom (run-server (wrap-localhost routes) {:port (port)})))
