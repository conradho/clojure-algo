(defproject algo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  ;; :aot [algo.core]
  :jvm-opts ["-Xms16G" "-Xmx16G" "-Xss16G"
             ;; performance options
             ;; http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html#PerformanceTuning
             "-XX:+AggressiveOpts"
             "-XX:+OptimizeStringConcat"
             ;; garbage collection method
             ;; http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/g1_gc_tuning.html#g1_gc_tuning
             ;; "-XX:+UseG1GC"
             "-XX:+UseConcMarkSweepGC"
             ;; debugging stuff
             "-verbose:gc"
             ;; "-XX:+PrintGCDetails"
             "-XX:+PrintGCDateStamps"
             ]
  :main algo.core)
