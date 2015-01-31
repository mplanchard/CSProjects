;;; test7.lsp
;;; Modified by Xuming Zeng, 02 Dec 14
;;; Put your code in a new file called assignment.lsp. You should not need to
;;; modify this file.

;;; Use myeval rather than eval: eval is the Lisp interpreter.

(load "asg7.lsp")

(defvar cave '(rocks gold (monster)) )

(defvar caveb
  '(((green eggs and) ((ham))) rocks (monster (((gold (monster)))))) )

(defvar treea '((my eyes) (have seen (the light))) )

(defvar treeb '((my ears) (have heard (the music))) )

(defvar formulas
  '((= s (* 0.5 (* a (expt t 2))))
    (= s (+ s0 (* v t)))
    (= a (/ f m))
    (= v (* a t))
    (= f (/ (* m v) t))
    (= f (/ (* m (expt v 2)) r))
    (= h (- h0 (* 4.94 (expt t 2))))
    (= c (sqrt (+ (expt a 2) (expt b 2))))
    (= v (* v0 (- 1.0 (exp (/ (- t) (* r c))))))
    ))

(defvar opposites
  '((+ -) (- +) (* /) (/ *) (sqrt expt) (expt sqrt) (log exp) (exp log) ) )

(format t "cave = ~A~%" cave)
(format t "path = ~A~%" (findpath 'gold cave))
(format t "follow = ~A~%" (follow (findpath 'gold cave) cave))
(format t "caveb = ~A~%" caveb)
(format t "pathb = ~A~%" (findpath 'gold caveb))
(format t "follow = ~A~%" (follow (findpath 'gold caveb) caveb))

(format t "treea = ~A~%" treea)
(format t "treeb = ~A~%" treeb)
(format t "corresp = ~A~%" (corresp 'light treea treeb))

(format t "formulas = ~%")
(dolist (formula formulas)
  (format t "   ~A~%" formula)
  (dolist (var (vars formula))
    (format t "       ~A~%" (solve formula var)) ) )

(defvar formula1 '(* 0.5 (* a (expt t 2))) )
(defvar bindings1 '((a 32.0) (t 4.0)) )
(format t "Eval:      ~A~%" formula1)
(format t "  bindings ~A~%" bindings1)
(format t "  result = ~A~%" (myeval formula1 bindings1))

(format t "Tower: ~A~%"    (solveit formulas 'h0 '((h 0.0) (t 4.0))) )
(format t "Car: ~A~%"      (solveit formulas 'a  '((v 88.0) (t 8.0))) )
(format t "Capacitor: ~A~%"(solveit formulas 'c  '((v 3.0) (v0 6.0) (r 10000.0)
                                                   (t 5.0))) )
(format t "Ladder: ~A~%"   (solveit formulas 'b  '((a 6.0) (c 10.0))) )
