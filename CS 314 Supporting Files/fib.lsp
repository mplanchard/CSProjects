; fib.lsp                         31 Mar 08; 19 Aug 09

(defun fib2 (n)
  (if (< n 2)
      n
      (+ (fib2 (- n 2))
         (fib2 (- n 1)) ) ) )

(defun fib1 (n) (fib1b 0 1 n))

(defun fib1b (lo hi n)
  (if (= n 0)
      lo
      (fib1b hi (+ lo hi) (- n 1)) ) )
