(ns {{name}}.templates.main
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [html5 include-css include-js]]))

(defn content
  [message]
  (html
   [:div {:class "w3-container w3-padding-16"}
    [:button {:class "w3-btn" :onclick "helloAction()" :id "helloButton"}
     message]]))

(defn main-screen
  [message]
  (html5
   [:head
    (include-css "/res/css/w3.css")
    (include-js "/res/js/script.js")]
   [:body {:class "w3-dark-grey w3-animate-opacity"}
    [:header {:class "w3-container w3-red w3-card-8"}
     [:h4 "Enslin Application Template"]]
    (content message)]))
