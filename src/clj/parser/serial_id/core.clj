(ns parser.serial-id.core
  (:import [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream
                                 ParserRuleContext Token TokenStreamRewriter]
           [org.antlr.v4.runtime.tree ParseTreeWalker]
           [java.io FileInputStream InputStream]
           [grammar.java JavaLexer JavaParser])
  (:require [parser.serial-id.insert-serial-id-listener :as inserter]))

(use 'debux.core)

;;; 4.5 Cool Lexical Features

(defn insert-serial-id [[input-file]]
  (let [is (if input-file
             (FileInputStream. input-file)
             System/in)
        tokens (-> (ANTLRInputStream. is)
                   (JavaLexer.)
                   (CommonTokenStream.))
        parser (JavaParser. tokens)
        tree (.compilationUnit parser)
        walker (ParseTreeWalker.)
        rewriter (TokenStreamRewriter. tokens)
        inserter (inserter/insert-serial-id-listener rewriter)]
    (.walk walker inserter tree)
    (println (.. rewriter getText)) ))

