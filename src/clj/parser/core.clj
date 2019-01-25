(ns parser.core
  (:require [parser.expr.core :as expr]
            [parser.labeled-expr.core :as labeled]
            [parser.java.core :as java]
            [parser.rows.core :as rows]
            [parser.serial-id.core :as serial-id]))

(use 'debux.core)

(def usage* "Usage: lein run <grammar> <arg>*")

(defn -main [& [grammar & rest-args :as args]]
  (if (zero? (count args))
    (println usage*)
    (case grammar
      "expr" (expr/calc rest-args)
      "labeled-expr" (labeled/calc rest-args)
      "java" (java/interface-extract-tool rest-args)
      "rows" (rows/col rest-args)
      "serial-id" (serial-id/insert-serial-id rest-args)
      (println (str usage* "\n" "<grammar> not matched")) )))
     
