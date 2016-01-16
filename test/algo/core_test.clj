(ns algo.core-test
  (:require [clojure.test :refer :all]
            [algo.core :refer :all]))

(deftest merge-sorted-list-test
  (testing "base cases"
    (is (= (merge-sorted-list nil [1]) [1]))
    (is (= (merge-sorted-list [2] nil) [2])))
  (testing "merging two sorted lists returns sorted output"
    ;; (println (merge-sorted-list (range 5) (range 5)))
    (is (<= (merge-sorted-list (range 5) (range 5))))
    (is (<= (merge-sorted-list (range 5) (range 5 10))))
    (is (<= (merge-sorted-list (range 5 15) (range 5))))))

(deftest merge-sort-algo-test
  (testing "sorts correctly"
    (def random-list [5 4 3 2 1])
    (is (apply <= (merge-sort-algo random-list)))))


(deftest merge-and-count-test
  (testing "base cases"
    (is (= (merge-and-count nil [1]) [[1] 0]))
    (is (= (merge-and-count [2] nil) [[2] 0]))
    (is (= (merge-and-count [2 3] [1]) [[1 2 3] 2])))
  (testing "merging two sorted lists returns sorted output"
    (is (<= (first (merge-and-count (range 5) (range 5)))))
    (is (= (first (merge-and-count (range 5 10) (range 5))) (range 10))))
  (testing "merging two unsorted lists is weird because it expects sorted"
    (is (= (first (merge-and-count (range 4 -1 -1) (range 5 10))) [4 3 2 1 0 5 6 7 8 9])))
  (testing "inversions counted correctly"
    (is (= (second (merge-and-count (range 5 10) (range 5))) 25))
    ;; below is not 35, because it expects the input lists to be sorted
    (is (= (second (merge-and-count (range 10 5 -1) (range 5))) 25))))

(deftest count-inversion-test
  (testing "sorts correctly"
    (is (= (first (count-inversion (range 99 50 -1) 0)) (range 51 100))))
  (testing "counts inversions correctly"
    (is (= (second (count-inversion (concat (range 10 5 -1) (range 5)) 0)) 35)))
  (testing "has convenience function that just returns count without sorted list"
    (is (= (count-inversion [2 1]) 1))))
