;;; test2.lsp      Code to test CS 314 assignment 2
;;; Author: Xuming Zeng
;;; Revised: 02 Dec 14
;;;
;;; Put your code in a new file called asg2.lsp. You should not need to
;;; modify this file.

(load "asg2.lsp")

(defun test (formula result)
  (format t "(~a) = ~a~%" formula result))

(test "sumsq 5" (sumsq 5))

(test "peanoplus 3 5" (peanoplus 3 5))

(test "peanotimes 3 5" (peanotimes 3 5))
(test "peanotimes 30 30" (peanotimes 30 30))

(test "choose 5 3" (choose 5 3))
(test "choose 100 3" (choose 100 3))
(test "choose 20 10" (choose 20 10))
(test "choose 100 5" (choose 100 5))
(loop for i from 0 to 4 do
	  (format t "(choose 4 ~a) = ~a~%" i (choose 4 i)))

(let ((mylist '(3 4 8 2))
	  (mylistb '(2 1 6 5)))

  (format t "mylist = ~a~%" mylist)
  (test "sumlistr mylist" (sumlistr mylist))
  (test "sumlisttr mylist" (sumlisttr mylist))

  (format t "mylistb = ~a~%" mylistb)
  (test "sumsqdiffi mylist mylistb" (sumsqdiffi mylist mylistb))
  (test "sumsqdiffr mylist mylistb" (sumsqdiffr mylist mylistb))
  (test "sumsqdifftr mylist mylistb" (sumsqdifftr mylist mylistb))

  (test "maxlisti mylist" (maxlisti mylist))
  (test "maxlistr mylist" (maxlistr mylist))
  (test "maxlisttr mylist" (maxlisttr mylist))

  (test "binomial 4" (binomial 4))
  (test "binomial 20" (binomial 20)))
