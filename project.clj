(defproject parser "0.1.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.antlr/antlr4 "4.7.2"]
                 [philoskim/debux "0.5.3"]]
  :main parser.core
  :source-paths ["src/clj"]
  :java-source-paths ["src/java"])
