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

(defn merge-and-count
  "think of the world as three jars of pebbles and a score board
  this function moves around the pebbles and increments the score board
  but leaves everything else the same/no side effects"
  [left right]
  (loop [left left, right right, left-len (count left),
         merged [], accum-count 0]  ; initialize merged & accum-count
    (if (or (nil? left) (nil? right))
      ;; at the end, return just the merged list and the count params
      [(concat merged left right) accum-count]
      ;; do tail recursion with (left, right, merged, count) params
      (let [[l-head & l-tail] left
            [r-head & r-tail] right]
        (if (< l-head r-head)
          (recur l-tail right (dec left-len) (conj merged l-head)  accum-count)
          (recur left r-tail left-len (conj merged r-head) (+ accum-count left-len)))))))

(defn count-inversion
  "piggy backing on merge sort to count the number of inversions"
  ([xs] (second (count-inversion xs 0)))
  ([xs accum-inv-count]
    (let [xs-len (count xs)]
      (if (<= xs-len 1)
        [xs accum-inv-count]
        (let [[left right] (split-at (/ xs-len 2) xs)
              [sorted-left count-left] (count-inversion left 0)
              [sorted-right count-right] (count-inversion right 0)
              [merged merge-count] (merge-and-count sorted-left sorted-right)]
          [merged (+ merge-count count-left count-right)]
          )))))


(def xs (map read-string (clojure.string/split (slurp "IntegerArray.txt") #"\r\n")))
(defn exercise-1 []
  (println "starting")
  (println (count-inversion xs)))

;; the hook that project.clj calls (it tries to find -main)
(defn -main [& args] (exercise-1))
