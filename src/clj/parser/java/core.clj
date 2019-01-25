(ns parser.java.core
  (:import [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream Token]
           [org.antlr.v4.runtime.tree ParseTreeWalker]
           [java.io FileInputStream]
           [grammar.java JavaLexer JavaParser])
  (:require [parser.java.extract-interface-listener :as extractor]))

(use 'debux.core)

;;; 4.3 Building a Translator with a Listener

(defn interface-extract-tool [[input-file]]
  (let [is (if input-file
             (FileInputStream. input-file)
             System/in)
        parser (-> (ANTLRInputStream. is)
                   (JavaLexer.)
                   (CommonTokenStream.)
                   (JavaParser.))
        tree (.compilationUnit parser)
        walker (ParseTreeWalker.)
        extractor (extractor/extract-interface-listener parser)]
    (.walk walker extractor tree) ))

