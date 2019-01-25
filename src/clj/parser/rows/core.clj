(ns parser.rows.core
  (:import [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream]
           [grammar.rows RowsLexer RowsParser] ))

(use 'debux.core)

;;; 4.4 Making Things Happen During the Parse

(defn col [[column]]
  (let [parser (-> (ANTLRInputStream. System/in)
                   (RowsLexer.)
                   (CommonTokenStream.)
                   (RowsParser. (Integer/valueOf column)))]
    (doto parser
      (.setBuildParseTree false)
      (.file) )))

