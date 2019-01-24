(ns parser.eval-visitor
  (:import [grammar.labeledexpr LabeledExprParser LabeledExprBaseVisitor]))

(use 'debux.core)

(defn eval-visitor []
  (let [memory (atom {})]
    (proxy [LabeledExprBaseVisitor] []
      (visitAssign [ctx]
        (let [id (.. ctx ID getText)
              value (proxy-super visit (.. ctx expr))]
          (swap! memory assoc id value)
          value))
      (visitPrintExpr [ctx]
        (let [value (proxy-super visit (.. ctx expr))]
          (println value)
          0))
      (visitInt [ctx]
        (Integer/valueOf (.. ctx INT getText)))
      (visitId [ctx]
        (let [id (.. ctx ID getText)]
          (if-let [value (get @memory id)]
            value
            (int 0) )))
      (visitMulDiv [ctx]
        (let [left (proxy-super visit (.. ctx (expr 0)))
              right (proxy-super visit (.. ctx (expr 1)))]
          (if (= (.. ctx -op getType) LabeledExprParser/MUL)
            (* left right)
            (/ left right) )))
      (visitAddSub [ctx]
        (let [left (proxy-super visit (.. ctx (expr 0)))
              right (proxy-super visit (.. ctx (expr 1)))]
          (if (= (.. ctx -op getType) LabeledExprParser/ADD)
            (+ left right) 
            (- left right) )))
      (visitParens [ctx]
        (proxy-super visit (.. ctx expr))) )))


