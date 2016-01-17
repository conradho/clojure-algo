(defproject algo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :aot [algo.core]  ; ahead of time compile might be able to optimize
  :jvm-opts ["-Xms8G" "-Xmx8G" "-Xss8G"
             ;; performance options
             ;; http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html#PerformanceTuning
             "-XX:+AggressiveOpts"
             "-XX:+OptimizeStringConcat"

             ;; garbage collection method
             ;; http://www.fasterj.com/articles/oraclecollectors1.shtml
             ;; http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/g1_gc_tuning.html#g1_gc_tuning
             ;; http://nyeggen.com/post/2012-04-16-tuning-jvm-gc-for-a-big/
             ;; http://www.cubrid.org/blog/dev-platform/how-to-tune-java-garbage-collection/
             ;; If the GC execution time meets all of the following conditions, GC tuning is not required.
             ;;        Minor GC is processed quickly (within 50 ms).
             ;;        Minor GC is not frequently executed (about 10 seconds).
             ;;        Full GC is processed quickly (within 1 second).
             ;;        Full GC is not frequently executed (once per 10 minutes).


             ;; the young generation GC- dont need one if UseG1GC
             ;; "-XX:+UseParallelGC"
             "-XX:+UseParNewGC"

             ;; the old generation GC
             ;; "-XX:+UseG1GC"
             ;; "-XX:+UseParallelOldGC"
             "-XX:+UseConcMarkSweepGC"

             ;; debugging stuff
             ;; also use jstat `-gcutil <pid> 10s` and compare YGCT / YGC for avg young gc time, and avg FGC time
             ;; "-verbose:gc"
             ;; "-XX:+PrintGCDetails"
             "-XX:+PrintGCDateStamps"
             ]
  :main algo.core)
