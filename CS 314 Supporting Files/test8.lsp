;;; test.lsp
;;; Author: Xuming Zeng
;;; Revised: 02 Dec 14
;;; Put your code in a new file called assignment.lsp. You should not need to
;;; modify this file.

(load "asg8.lsp")

(format t "battery =  = ~a~%" (solveqns eqnsbat databat 'terminal_voltage))

(format t "circular =  = ~a~%" (solveqns eqnscirc datacirc 'angular_momentum))

(format t "magnification =  = ~a~%" (solveqns eqnslens datalens 'magnification))

(format t "power =  = ~a~%" (solveqns eqnslift datalift 'power))

(defvar binaryfn)
(setq binaryfn 
      '(defun ?function (tree) 
         (if (consp tree)
             (?combine (?function (first tree))
                       (?function (rest tree)))
             ?baseanswer)))

(dolist (sub substitutions)
  (format t "sublis =  = ~a~%" (sublisl sub binaryfn)) )

(dolist (test opttests)
  (format t "opt:  ~a~%" test)
  (format t "      ~a~%" (trans test 'optpats)) )

(dolist (test derivtests)
  (let ((der (trans test 'derivpats)))
    (format t "deriv:  ~a~%" test)
    (format t "  der:  ~a~%" der)
    (format t "  opt:  ~a~%" (trans der 'optpats)) ))

(dolist (test javatests) (testjava test))

(dolist (test substitutions) (testjava (sublisl test binaryfn)))
