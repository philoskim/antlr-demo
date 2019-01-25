(ns parser.serial-id.insert-serial-id-listener
  (:import [org.antlr.v4.runtime TokenStreamRewriter]
           [grammar.java JavaBaseListener]))

(use 'debux.core)

(defn insert-serial-id-listener [rewriter]
  (proxy [JavaBaseListener] []
    (enterClassBody [ctx]
      (let [field "\n\tpublic static final long serialVersionUID = 1L;"]
        (.. rewriter (insertAfter (.-start ctx) field)) ))))

