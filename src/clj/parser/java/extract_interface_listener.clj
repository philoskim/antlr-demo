(ns parser.java.extract-interface-listener
  (:import [grammar.java JavaBaseListener]))

(use 'debux.core)

(defn extract-interface-listener [parser]
  (proxy [JavaBaseListener] []
    (enterClassDeclaration [ctx]
      (println (str "interface I" (.. ctx Identifier) " {")))
    (exitClassDeclaration [ctx]
      (println "}"))
    (enterMethodDeclaration [ctx]
      (let [tokens (.getTokenStream parser)
            type (if (.type ctx)
                   (.getText tokens (.type ctx))
                   "void")
            args (.getText tokens (.formalParameters ctx))]
        (println (str "\t" type (.Identifier ctx) args ";")) ))))


