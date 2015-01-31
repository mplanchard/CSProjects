;;; test4.lsp
;;; Author: Xuming Zeng (xz4493)
;;; Revised: 02 Dec 14
;;; Put your code in a new file called asg4.lsp.
;;; You should not have to modify this file.

;;; Use function names myunion, myset-difference to avoid redefining Lisp names

;;; Useful functions for strings:
;;;   equal, string<, (char s n), (subseq s start end+1)

(load "asg4.lsp")

;; Question 1

(defun llmergesort (lst) (sort lst #'string<) )

;; You can use (equal s1 s2) and (string< s1 s2) to compare strings.

(let ((set1 '("d" "b" "c" "a"))
      (set2 '("f" "d" "b" "g" "h"))
      (set3 '("d" "b" "c" "a"))
      (set4 '("f" "d" "b" "g" "h")))
  (format t "set1 = ~a~%" set1)
  (format t "set2 = ~a~%" set2)
  (format t "union = ~a~%" (myunion set1 set2))
  
  (format t "set3 = ~a~%" set3)
  (format t "set4 = ~a~%" set4)
  (format t "difference = ~a~%" (myset-difference set3 set4)))

;; Question 2 should be done in Java.

;; Question 3: merge arrays
(let* ((arra '#("a" "big" "dog" "hippo"))
       (arrb '#("canary" "cat" "fox" "turtle"))
       (resarr (mergearr arra arrb)))
  (dotimes (i (array-total-size resarr))
    (format t "~a~%" (aref resarr i) ) ) )

;; Question 4
(let ((xmla '("<TT>" "foo" "</TT>"))
      (xmlb '("<TABLE>"
              "<TR>" "<TD>" "foo" "</TD>" "<TD>" "bar" "</TD>" "</TR>"
              "<TR>" "<TD>" "fum" "</TD>" "<TD>" "baz" "</TD>" "</TR>"
              "</TABLE>"))
      (xmlc '("<TABLE>"
              "<TR>" "<TD>" "foo" "</TD>" "<TD>" "bar" "</TD>" "</TR>"
              "<TR>" "<TD>" "fum" "</TD>" "<TD>" "baz" "</TD>" "</WHAT>"
              "</TABLE>"))
      (xmld '("<TABLE>"
              "<TR>" "<TD>" "foo" "</TD>" "<TD>" "bar" "</TD>" "" "</TR>"
              "</TABLE>" "</NOW>"))
      (xmle '("<THIS>" "<CANT>" "<BE>" "foo" "<RIGHT>"))
      (xmlf '("<CATALOG>"
              "<CD>"
              "<TITLE>" "Empire" "Burlesque" "</TITLE>"
              "<ARTIST>" "Bob" "Dylan" "</ARTIST>"
              "<COUNTRY>" "USA" "</COUNTRY>"
              "<COMPANY>" "Columbia" "</COMPANY>"
              "<PRICE>" "10.90" "</PRICE>"
              "<YEAR>" "1985" "</YEAR>"
              "</CD>"
              "<CD>"
              "<TITLE>" "Hide" "your" "heart" "</TITLE>"
              "<ARTIST>" "Bonnie" "Tyler" "</ARTIST>"
              "<COUNTRY>" "UK" "</COUNTRY>"
              "<COMPANY>" "CBS" "Records" "</COMPANY>"
              "<PRICE>" "9.90" "</PRICE>"
              "<YEAR>" "1988" "</YEAR>"
              "</CD>" "</CATALOG>")))
  (format t "xmla = ~a~%" xmla)
  (format t "result = ~a~%" (markup xmla))
  (format t "xmlb = ~a~%" xmlb)
  (format t "result = ~a~%" (markup xmlb))
  (format t "xmlc = ~a~%" xmlc)
  (format t "result = ~a~%" (markup xmlc))
  (format t "xmld = ~a~%" xmld)
  (format t "result = ~a~%" (markup xmld))
  (format t "xmle = ~a~%" xmle)
  (format t "result = ~a~%" (markup xmle))
  (format t "xmlf = ~a~%" xmlf)
  (format t "result = ~a~%" (markup xmlf)))
