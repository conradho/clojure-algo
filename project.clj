(defproject algo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :aot [algo.core]  ; ahead of time compile might be able to optimize
  :jvm-opts ["-XX:+AggressiveHeap"   ; try to set max heap size/take up as much resources as possible

             ;; do not manually set heap size unless AggressiveHeap still gives out of memory error
             ;; (or if you don't want to take up whole system's resources)
             ;; but you are probably going to get swappiness dominating run time if you go over 2x
             ;; (because aggressiveheap prob chose max heap size of 1/2x ram)
             ;; for the dataset of ~1GB, with some replication etc the heap goes over 2GB
             ;; "-Xms4G" "-Xmx4G" "-Xss4G"

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
             "-XX:+UseParallelGC"
             ;; "-XX:+UseParNewGC"

             ;; the old generation GC- parallel because don't care about response time
             ;; for server stuff http://blog.sokolenko.me/2014/11/javavm-options-production.html
             ;; "-XX:+UseG1GC"
             "-XX:+UseParallelOldGC"
             ;; "-XX:+UseConcMarkSweepGC"

             ;; debugging stuff
             ;; also use jstat `-gcutil <pid> 10s` and compare YGCT / YGC for avg young gc time, and avg FGC time
             ;; jmap -heap <pid>
             "-verbose:gc"
             "-XX:+PrintGCDetails"
             "-XX:+PrintGCDateStamps"
             ]
  :main algo.core)
