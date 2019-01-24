(ns parser.labeled-expr
  (:import [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream]
           [java.io FileInputStream InputStream]
           [grammar.labeledexpr LabeledExprLexer LabeledExprParser])
  (:require [parser.eval-visitor :as eval]))

(use 'debux.core)

;;; 4.2 Building a Calculator Using a Visitor

(defn calc [input-file]
  (let [is (if input-file
             (FileInputStream. input-file)
             System/in)
        parser (-> (ANTLRInputStream. is)
                   (LabeledExprLexer.)
                   (CommonTokenStream.)
                   (LabeledExprParser.))
        tree (.prog parser)
        eval (eval/eval-visitor)]
    (.visit eval tree) ))

