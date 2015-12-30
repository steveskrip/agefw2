(ns agefw2.core
  (:require [cljs.reader :refer (read-string)]
            [dommy.core :as dom]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(def input (dom/sel1 :#years))
(def result (dom/sel1 :#feedback))
(def button (dom/sel1 :#button))
(def buttonout (dom/sel1 :#buttonout))

(defn calculate
  [year]
  (* year 365))

(defn on-change
  [event]
  (let [target (.-target event)
        value (read-string (dom/value target))
        days (calculate value)]
    (dom/set_html! result days)))

(defn button-event
  [e]
  (dom/set_html! buttonout (.toString (js/Date.))))

(dom/listen! input :keyup on-change)
(dom/listen! button :mouseover button-event)

