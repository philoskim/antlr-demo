(ns parser.expr
  (:import [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream]
           [java.io FileInputStream InputStream]
           [grammar.expr ExprLexer ExprParser] ))

(use 'debux.core)

;;; 4.1 Matching an Arithmetic Expression Language

(defn calc [input-file]
  (let [is (if input-file
             (FileInputStream. input-file)
             System/in)
        parser (-> (ANTLRInputStream. is)
                   (ExprLexer.)
                   (CommonTokenStream.)
                   (ExprParser.))
        tree (.prog parser)]
    (println (.toStringTree tree parser)) ))

