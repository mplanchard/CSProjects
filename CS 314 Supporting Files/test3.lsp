; test3.lsp       Test calls for assignment 3 in Lisp
;;; Author: Xuming Zeng
; Revised: XZ  02 Dec 2014; GSN 19 Sep 14

;;; Put your code in a new file called asg3.lsp .
;;; You should not need to modify this file.
;;; 
;;; Use the following function names to avoid Lisp system function names:
;;;   mynthcdr
;;;   myelt
;;;   mysubseq
;;;   mymapcar
;;;   mysome
;;;   myevery

(load "asg2.lsp")   ; uses binom
(load "asg3.lsp")

(defun square (x) (* x x))

(defvar mylist)
(setq mylist '(95 72 86 70 97 72 52 88 77 94 91 79 61 77 99 70 91) )

        (format t "mylist = ~A~%" mylist)
        (format t "sum = ~A~%" (sum mylist))
        (format t "mean = ~A~%" (* 1.0 (mean mylist)))
        (format t "meansq = ~A~%" (* 1.0 (meansq mylist)))
        (format t "variance = ~A~%" (* 1.0 (variance mylist)))
        (format t "stddev = ~A~%" (stdev mylist))
        (format t "sine(0.5) = ~A~%" (sine 0.5))  ; 0.47942553860420301
        (format t "nthcdr 5 = ~A~%" (mynthcdr 5 mylist))
        (format t "nthcdr 18 = ~A~%" (mynthcdr 18 mylist))
        (format t "elt 5 = ~A~%" (myelt mylist 5))
(defvar mylistb)
(setq mylistb '(0 30 56 78 96))
        (format t "mylistb =  ~A~%" mylistb)
        (format t "interpolate(3.4) =  ~A~%" (interpolate mylistb 3.4))
(defvar binom)
(setq binom (binomial 12))
        (format t "binom =  ~A~%" binom)
        (format t "interpolate(3.4) =  ~A~%" (interpolate binom 3.4))
(defvar mylistc '(1 (2 3) ((((4)) (5)) 6)) )
        (format t "mylistc =  ~A~%" mylistc)
        (format t "sumtr =  ~A~%" (sumtr mylistc))
(defvar mylistcc)
(setq mylistcc  '(1 (7 ((2) 3)) (((((((4)))) 9))) ((((5) 4) 3)) (6)) )

        (format t "mylistcc = ~A~%" mylistcc)
        (format t "sumtr = ~A~%" (sumtr mylistcc))
(defvar mylistd)
(setq mylistd '(0 1 2 3 4 5 6 ) )
        (format t "mylistd = ~A~%" mylistd)
        (format t "subseq(2 5) = ~A~%" (subseq* mylistd 2 5))

(defvar myliste)
(setq myliste '(3 17 -2 0 -3 4 -5 12 ) )
        (format t "myliste = ~A~%" myliste)
        (format t "posfilter = ~A~%" (posfilter myliste))

        (format t "subset = ~A~%" (subset #'(lambda (x) (> x 3)) myliste))

        (format t "mapcar = ~A~%" (mymapcar #'(lambda (x) (+ x 2)) mylistd))

        (format t "some = ~A~%" (mysome #'(lambda (x) (> x 3)) myliste))

        (format t "every = ~A~%" (myevery #'(lambda (x) (> x 3)) myliste))

