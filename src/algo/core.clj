(ns algo.core)

(defn foo
  "random function that prints alisa"
  []
  (println "hi alisa"))  ; `printf "hello %s" name` for str interp


(defn merge-sorted-list [left right]
  ;; merged func name definition row with argument vector
  ;; to the same line because no docstring in between
  (cond (nil? left) right
        (nil? right) left
        :else (let [[l & *left] left
                   [r & *right] right]
                (if (< l r)
                  (cons l (merge-sorted-list *left right))
                  (cons r (merge-sorted-list left *right))))))

(defn merge-sort-algo
  "an implementation of merge sort"
  [xs]
  (let [xs-len (count xs)]
    (if (<= xs-len 1)
      xs
      (let [[left right] (split-at (/ xs-len 2) xs)]
        (merge-sorted-list
          (merge-sort-algo left)
          (merge-sort-algo right))))))

(defn merge-and-count [left right accum-count]
  ;; merged func name definition row with argument vector
  ;; to the same line because no docstring in between
    (cond (nil? left) [right accum-count]
          (nil? right) [left accum-count]
          :else (let [[l & *left] left
                      [r & *right] right]
                  (if (< l r)
                    (let [[merged final-count] (merge-and-count *left right accum-count)]
                      [(cons l merged) final-count])
                    (let [[merged final-count] (merge-and-count left *right (inc (+ accum-count (count *left))))]
                      [(cons r merged) final-count])))))

(defn count-inversion
  "piggy backing on merge sort to count the number of inversions"
  ([xs] (second (count-inversion xs 0)))
  ([xs accum-inv-count]
    (let [xs-len (count xs)]
      (if (<= xs-len 1)
        [xs accum-inv-count]
        (let [[left right] (split-at (/ xs-len 2) xs)
              [sorted-left count-left] (count-inversion left 0)
              [sorted-right count-right] (count-inversion right 0)]
          (merge-and-count sorted-left sorted-right (+ accum-inv-count count-left count-right)))))))


(def xs (map read-string (clojure.string/split (slurp "IntegerArray.txt") #"\r\n")))
(defn exercise-1 []
  (println "starting")
  (println (count-inversion xs)))

;; the hook that project.clj calls (it tries to find -main)
(defn -main [& args] (exercise-1))
