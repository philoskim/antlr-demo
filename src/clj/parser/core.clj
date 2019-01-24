(ns parser.core
  (:require [parser.expr :as expr]
            [parser.labeled-expr :as labeled] ))

(use 'debux.core)

(defn -main [& [grammar input-file :as args]]
  (if (zero? (count args))
    (println "Usage: lein run <grammar> <input-file>")
    (case grammar
      "expr" (expr/calc input-file)
      "labeled-expr" (labeled/calc input-file) )))

