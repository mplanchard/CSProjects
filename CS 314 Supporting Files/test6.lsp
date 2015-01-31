;;; test6.lsp
;;; Author: Xuming Zeng
;;; Revised: 02 Dec 14
;;; Put your code in a new file called assignment.lsp. You should not need to
;;; modify this file.

;;; Use myeval instead of eval: eval is the Lisp interpreter!

(load "asg6.lsp")

(let ((bt1 '(((23 77) -3 88) ((((99)) (7))) 15 -1)))
  (format t "bt1 = ~a~%" bt1)
  (format t "maxbt(bt1) = ~a~%" (maxbt bt1)))

(let ((expr1 '(= f (* m a)))
      (expr2 '(= f (/ (* m (expt v 2)) r)))
      (expr3 '(+ 3 (* 5 7)))
      (expr4 '(+ (- 3) (expt (- 7) (/ 4 2))))
      (expr5 '(+ 3 (* 5 b)))
      (expr6 '(+ (- c) (expt (- b (/ z w)) 3)))
      (alist '((c 3) (b 7) (z 4) (w 2) (fred 5)))
      (expr7 '(= x (* (+ a b) c)))
      (expr8 '(= x (* r (sin theta))))
      (expr9 '(= v (* v0 (exp (/ (- t) (* r c)))))))
  (format t "expr1 = ~a~%" expr1)
  (format t "vars(expr1) = ~a~%" (vars expr1))

  (format t "expr2 = ~a~%" expr2)
  (format t "vars(expr2) = ~a~%" (vars expr2))
  (format t "occurs(m, expr2) = ~a~%" (occurs 'm expr2))
  (format t "occurs(7, expr2) = ~a~%" (occurs 7 expr2))

  (format t "expr9 = ~a~%" expr9)
  (format t "vars(expr9) = ~a~%" (vars expr9))
  (format t "occurs(c, expr9) = ~a~%" (occurs 'c expr9))
  (format t "occurs(m, expr9) = ~a~%" (occurs 'm expr9))

  (format t "expr3 = ~a~%" expr3)
  (format t "eval(expr3) = ~a~%" (myeval expr3))

  (format t "expr4 = ~a~%" expr4)
  (format t "eval(expr4) = ~a~%" (myeval expr4))

  (format t "eval(b) = ~a~%" (myeval2 'b '((b 7))))

  (format t "expr5 = ~a~%" expr5)
  (format t "eval(expr5) = ~a~%" (myeval2 expr5 '((b 7))))

  (format t "expr6 = ~a~%" expr6)
  (format t "alist = ~a~%" alist)
  (format t "eval(expr6) = ~a~%" (myeval2 expr6 alist))
  (format t "english(expr5) = ~a~%" (english expr5))
  (format t "english(expr6) = ~a~%" (english expr6))
  (format t "tojava(expr1) = ~a~%" (tojava expr1))
  (format t "tojava(expr7) = ~a~%" (tojava expr7))
  (format t "tojava(expr8) = ~a~%" (tojava expr8))
  (format t "tojava(expr9) = ~a~%" (tojava expr9)))
