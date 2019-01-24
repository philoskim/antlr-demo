(ns parser.core
  (:require [parser.expr.core :as expr]
            [parser.labeled-expr.core :as labeled]
            [parser.java.core :as java]
            ))

(use 'debux.core)

(defn -main [& [grammar input-file :as args]]
  (if (zero? (count args))
    (println "Usage: lein run <grammar> <input-file>")
    (case grammar
      "expr" (expr/calc input-file)
      "labeled-expr" (labeled/calc input-file)
      "java" (java/interface-extract-tool input-file)
      )))
     
