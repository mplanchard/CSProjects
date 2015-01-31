; 21 Nov 2014 13:37:35 CST
; hstrans.lsp

; Copyright (c) 2014 Gordon S. Novak Jr. and The University of Texas at Austin.
; 25-Aug-89; 24 Sep 91; 10 Sep 93; 28 Sep 94; 02 Oct 02; 13 Sep 06; 21 Nov 14

; Heuristic Search Example

; This file is the compiled version the program, not very readable.

; -------------------------------------------------------------------------
; This program is free software; you can redistribute it and/or modify
; it under the terms of the GNU General Public License as published by
; the Free Software Foundation; either version 2 of the License, or
; (at your option) any later version.

; This program is distributed in the hope that it will be useful,
; but WITHOUT ANY WARRANTY; without even the implied warranty of
; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
; GNU General Public License for more details.

; You should have received a copy of the GNU General Public License
; along with this program; if not, see <http://www.gnu.org/licenses/>.
; -------------------------------------------------------------------------


; Execute    lisp    or    /p/bin/xgcl      or     M-x run-lisp
;            (xgcl)
;            (load "/projects/cs314/hstrans.lsp")

; This program illustrates the effectiveness of different heuristic
; functions by displaying the moves made in searching from a start
; position to a goal position on an x-y grid with diagonals.

; Example: (hstest 0.8)
; Note how different *hfactor* multipliers change the directedness of search.

; You can change (setq *hssleep* 0.01) to speed it up or slow it down.

; Also try adding a barrier in the space:
; (barrier '(20 10) '(25 -5))
; (barrier '(15 3) '(15 20))
; (hstest 0.9)

; (bye)      ; to quit

(defmacro while (test &rest forms)
  `(loop (unless ,test (return)) ,@forms) )


(DEFVAR *HS-WINDOW*)
(SETF (GET '*HS-WINDOW* 'GLISPGLOBALVAR) T)
(SETF (GET '*HS-WINDOW* 'GLISPGLOBALVARTYPE) 'WINDOW)
(DEFVAR *GOAL*)
(SETF (GET '*GOAL* 'GLISPGLOBALVAR) T)
(SETF (GET '*GOAL* 'GLISPGLOBALVARTYPE) 'VECTOR)
(DEFVAR *OFFSETX*)
(SETF (GET '*OFFSETX* 'GLISPGLOBALVAR) T)
(SETF (GET '*OFFSETX* 'GLISPGLOBALVARTYPE) 'INTEGER)
(DEFVAR *OFFSETY*)
(SETF (GET '*OFFSETY* 'GLISPGLOBALVAR) T)
(SETF (GET '*OFFSETY* 'GLISPGLOBALVARTYPE) 'INTEGER)
(DEFVAR *HS-DONE*)
(SETF (GET '*HS-DONE* 'GLISPGLOBALVAR) T)
(SETF (GET '*HS-DONE* 'GLISPGLOBALVARTYPE) 'BOOLEAN)
(DEFVAR *HS-NODES*)
(SETF (GET '*HS-NODES* 'GLISPGLOBALVAR) T)
(SETF (GET '*HS-NODES* 'GLISPGLOBALVARTYPE) 'INTEGER)
(DEFVAR *HS-BARRIERS*)
(SETF (GET '*HS-BARRIERS* 'GLISPGLOBALVAR) T)
(SETF (GET '*HS-BARRIERS* 'GLISPGLOBALVARTYPE) '(LISTOF LINE-SEGMENT))


(DEFVAR *HSSLEEP*)

(SETQ *HSSLEEP* 0.01)

(DEFVAR *HS-WINDOW* NIL)

(DEFVAR *GOAL*)

(DEFVAR *OFFSETX* 200)

(DEFVAR *OFFSETY* 200)

(DEFVAR *HS-DONE*)

(DEFVAR *HS-COST* (MAKE-ARRAY '(100 100) :INITIAL-ELEMENT 0))

(DEFVAR *HS-START* (MAKE-ARRAY 300 :INITIAL-ELEMENT NIL))

(DEFVAR *HS-END* (MAKE-ARRAY 300 :INITIAL-ELEMENT NIL))

(DEFVAR *HS-MIN-COST* 0)

(DEFVAR *HS-HSHFN*)

(DEFVAR *HFACTOR* 1.0)

(DEFVAR *HS-NODES* 0)

(DEFVAR *HS-BARRIERS* NIL)

(DEFUN FIX (X) (IF (>= X 0) (FLOOR X) (CEILING X)))

(DEFUN HSTEST (FACTOR)
  (SETQ *HFACTOR* FACTOR)
  (HSEARCH '(0 0) '(30 5) 'HSHFN6))

(DEFUN HSHFN0 (P) 0)
(SETF (GET 'HSHFN0 'GLARGUMENTS) '((P VECTOR)))
(SETF (GET 'HSHFN0 'GLFNRESULTTYPE) 'INTEGER)


(DEFUN HSHFN1 (P) (FIX (VECTOR-DISTANCE P *GOAL*)))

(DEFUN HSHFN2 (P)
  (LET (DX DY)
    (SETQ DX (ABS (- (CAR P) (CAR *GOAL*))))
    (SETQ DY (ABS (- (CADR P) (CADR *GOAL*))))
    (FIX (* 0.7 (+ DX DY)))))

(DEFUN HSHFN3 (P) (FIX (* 0.8 (VECTOR-DISTANCE P *GOAL*))))

(DEFUN HSHFN4 (P) (FIX (* *HFACTOR* (VECTOR-DISTANCE P *GOAL*))))

(DEFUN HSHFN5 (P)
  (LET (DX DY)
    (SETQ DX (ABS (- (CAR P) (CAR *GOAL*))))
    (SETQ DY (ABS (- (CADR P) (CADR *GOAL*))))
    (- (+ DX DY) (* 0.5857864376269048 (MIN DX DY)))))
(SETF (GET 'HSHFN5 'GLARGUMENTS) '((P VECTOR)))
(SETF (GET 'HSHFN5 'GLFNRESULTTYPE) 'REAL)


(DEFUN HSHFN6 (P) (FIX (* *HFACTOR* (HSHFN5 P))))

(DEFUN HSCLEAR ()
  (LET (I J)
    (SETQ *HS-DONE* NIL)
    (SETQ *HS-NODES* 0)
    (FILL *HS-START* NIL)
    (FILL *HS-END* NIL)
    (SETQ I 0)
    (WHILE (< I 100) (SETQ J 0)
           (WHILE (< J 100) (SETF (AREF *HS-COST* I J) 0) (INCF J))
           (INCF I))))

(DEFUN HSDRAW (FROM TO)
  (WINDOW-DRAW-LINE-XY *HS-WINDOW* (+ *OFFSETX* (* 5 (CAR FROM)))
      (+ *OFFSETY* (* 5 (CADR FROM))) (+ *OFFSETX* (* 5 (CAR TO)))
      (+ *OFFSETY* (* 5 (CADR TO))) 1 'PAINT)
  (WINDOW-FORCE-OUTPUT *HS-WINDOW*))

(DEFUN HSEARCHF (START GOAL HFACTOR)
  (SETQ *HFACTOR* HFACTOR)
  (HSEARCH START GOAL #'HSHFN4))

(DEFUN HSEARCHB (START GOAL HFACTOR)
  (SETQ *HFACTOR* HFACTOR)
  (HSEARCH START GOAL #'HSHFN6))

(DEFUN HSEARCH (START GOAL HSHFN)
  (LET (NODE)
    (OR *HS-WINDOW* (HSINIT))
    (HSCLEAR)
    (WINDOW-CLEAR *HS-WINDOW*)
    (HS-DRAW-BARRIERS)
    (SETQ *GOAL* GOAL)
    (SETQ *HS-HSHFN* HSHFN)
    (WINDOW-DRAW-CIRCLE-XY *HS-WINDOW* (+ *OFFSETX* (* 5 (CAR START)))
        (+ *OFFSETY* (* 5 (CADR START))) 6 2)
    (WINDOW-DRAW-CIRCLE-XY *HS-WINDOW* (+ *OFFSETX* (* 5 (CAR GOAL)))
        (+ *OFFSETY* (* 5 (CADR GOAL))) 6 2)
    (SETQ *HS-MIN-COST* 0)
    (HSINSERT 0 START)
    (WHILE (AND (NOT *HS-DONE*) (< *HS-MIN-COST* 100))
           (IF (> *HSSLEEP* 0.0) (SLEEP *HSSLEEP*))
           (IF (SETQ NODE (HSREMOVE *HS-MIN-COST*)) (HSEXPAND NODE)
               (INCF *HS-MIN-COST*)))
    (FORMAT T "Nodes = ~A  Depth = ~A  Effective Branching b* = ~A~%"
            *HS-NODES* *HS-MIN-COST* (FINDB* *HS-NODES* *HS-MIN-COST*))))

(DEFUN HSEXPAND (NODE)
  (LET (COST)
    (SETQ COST (HSGETCOST NODE))
    (HSTRYX NODE (1+ (CAR NODE)) (CADR NODE) (1+ COST))
    (HSTRYX NODE (CAR NODE) (1+ (CADR NODE)) (1+ COST))
    (HSTRYX NODE (1- (CAR NODE)) (CADR NODE) (1+ COST))
    (HSTRYX NODE (CAR NODE) (1- (CADR NODE)) (1+ COST))
    (HSTRYX NODE (1- (CAR NODE)) (1+ (CADR NODE)) (+ 1.4142135 COST))
    (HSTRYX NODE (1+ (CAR NODE)) (1+ (CADR NODE)) (+ 1.4142135 COST))
    (HSTRYX NODE (1- (CAR NODE)) (1- (CADR NODE)) (+ 1.4142135 COST))
    (HSTRYX NODE (1+ (CAR NODE)) (1- (CADR NODE)) (+ 1.4142135 COST))))
(SETF (GET 'HSEXPAND 'GLARGUMENTS) '((NODE VECTOR)))
(SETF (GET 'HSEXPAND 'GLFNRESULTTYPE) 'BOOLEAN)


(DEFUN HSGETCOST (P) (AREF *HS-COST* (+ 50 (CAR P)) (+ 50 (CADR P))))

(DEFUN HSINIT ()
  (LET ((WIDTH 400) (HEIGHT 400) (OVER 50) (DOWN 50))
    (SETQ *HS-WINDOW* (WINDOW-CREATE WIDTH HEIGHT "Heuristic Search"))))

(DEFUN HSINSERT (I P)
  (LET (NEW)
    (SETQ NEW (LIST P))
    (IF (AREF *HS-END* I) (SETF (CDR (AREF *HS-END* I)) NEW))
    (SETF (AREF *HS-END* I) NEW)
    (IF (NOT (AREF *HS-START* I)) (SETF (AREF *HS-START* I) NEW))
    (SETQ *HS-MIN-COST* (MIN *HS-MIN-COST* I))))
(SETF (GET 'HSINSERT 'GLARGUMENTS) '((I INTEGER) (P VECTOR)))
(SETF (GET 'HSINSERT 'GLFNRESULTTYPE) 'INTEGER)


(DEFUN HSREMOVE (I)
  (LET (TOP)
    (SETQ TOP (AREF *HS-START* I))
    (SETF (AREF *HS-START* I) (CDR TOP))
    (IF (NOT (CDR TOP)) (SETF (AREF *HS-END* I) NIL))
    (CAR TOP)))

(DEFUN HSSETCOST (P C)
  (SETF (AREF *HS-COST* (+ 50 (CAR P)) (+ 50 (CADR P))) C))
(SETF (GET 'HSSETCOST 'GLARGUMENTS) '((P VECTOR) (C NUMBER)))
(SETF (GET 'HSSETCOST 'GLFNRESULTTYPE) 'NUMBER)


(DEFUN HSTRYX (OLD NEWX NEWY COST)
  (LET (NEW)
    (SETQ NEW (LIST NEWX NEWY))
    (WHEN (NOT (OR (< (CAR NEW) -49) (> (CAR NEW) 49)
                   (< (CADR NEW) -49) (> (CADR NEW) 49)
                   (AND (PLUSP (HSGETCOST NEW))
                        (<= (HSGETCOST NEW) COST))
                   (HITSBARRIER NEW)))
      (HSSETCOST NEW COST)
      (HSINSERT
          (FIX (+ COST (EVAL (LIST *HS-HSHFN* (LIST 'QUOTE NEW)))))
          NEW)
      (INCF *HS-NODES*)
      (HSDRAW OLD NEW)
      (IF (AND (= (CAR NEW) (CAR *GOAL*)) (= (CADR NEW) (CADR *GOAL*)))
          (SETQ *HS-DONE* T)))))
(SETF (GET 'HSTRYX 'GLARGUMENTS)
      '((OLD VECTOR) (NEWX INTEGER) (NEWY INTEGER) (COST NUMBER)))
(SETF (GET 'HSTRYX 'GLFNRESULTTYPE) 'BOOLEAN)


(DEFUN HITSBARRIER (NEW)
  (LET (BAR HIT MINX MINY MAXX MAXY)
    (DOLIST (BAR *HS-BARRIERS*)
      (SETQ MINX (MIN (CAAR BAR) (CAADR BAR)))
      (SETQ MAXX (MAX (CAAR BAR) (CAADR BAR)))
      (SETQ MINY (MIN (CADAR BAR) (CADADR BAR)))
      (SETQ MAXY (MAX (CADAR BAR) (CADADR BAR)))
      (IF (AND (<= (CADR NEW) (1+ MAXY)) (>= (CADR NEW) (1- MINY))
               (<= (CAR NEW) (1+ MAXX)) (>= (CAR NEW) (1- MINX))
               (< (ABS (/ (- (* (- (CAADR BAR) (CAAR BAR))
                                (- (CADR NEW) (CADAR BAR)))
                             (* (- (CADADR BAR) (CADAR BAR))
                                (- (CAR NEW) (CAAR BAR))))
                          (SQRT (+ (EXPT (- (CAADR BAR) (CAAR BAR)) 2)
                                   (EXPT (- (CADADR BAR) (CADAR BAR))
                                    2)))))
                  1))
          (SETQ HIT T)))
    HIT))
(SETF (GET 'HITSBARRIER 'GLARGUMENTS) '((NEW VECTOR)))
(SETF (GET 'HITSBARRIER 'GLFNRESULTTYPE) 'BOOLEAN)


(DEFUN BARRIER (FROM TO)
  (PUSH (IF (>= (CADR FROM) (CADR TO)) (LIST FROM TO) (LIST TO FROM))
        *HS-BARRIERS*)
  (HS-DRAW-BARRIERS))

(DEFUN NOBARRIERS () (SETQ *HS-BARRIERS* NIL))

(DEFUN SETBARRIERS ()
  (BARRIER '(20 10) '(25 -5))
  (BARRIER '(15 3) '(15 20)))

(DEFUN SETBARRIER () (BARRIER '(20 10) '(25 -5)))

(DEFUN HS-DRAW-BARRIERS ()
  (LET (BAR FROM TO)
    (DOLIST (BAR *HS-BARRIERS*)
      (SETQ FROM (CAR BAR))
      (SETQ TO (CADR BAR))
      (WINDOW-DRAW-LINE-XY *HS-WINDOW* (+ *OFFSETX* (* 5 (CAR FROM)))
          (+ *OFFSETY* (* 5 (CADR FROM))) (+ *OFFSETX* (* 5 (CAR TO)))
          (+ *OFFSETY* (* 5 (CADR TO))) 3 'PAINT))
    (WINDOW-FORCE-OUTPUT *HS-WINDOW*)))

(DEFUN REGULA-FALSI (FN VAL &OPTIONAL (INIT 1.0) (EPSILON 1.0E-8))
  (LET ((DELTA (/ (ABS INIT) 100.0)) (X INIT) FX SLOPE)
    (WHILE (> (ABS (SETQ FX (- (FUNCALL FN X) VAL))) EPSILON)
           (SETQ SLOPE
                 (/ (- (- (FUNCALL FN (+ X DELTA)) VAL) FX) DELTA))
           (SETQ X (- X (/ FX SLOPE))))
    X))

(DEFUN SUMBD (B D)
  (LET ((SUM 0)) (DOTIMES (I (1+ D) SUM) (INCF SUM (EXPT B I)))))

(DEFUN FINDB* (NODES DEPTH)
  (REGULA-FALSI (SUBST DEPTH '?D #'(LAMBDA (B) (SUMBD B ?D))) NODES))

(DEFUN COMPILE-HS ()
  (GLCOMPFILES *GLDIRECTORY* 'NIL '("hs.lsp") "hstrans.lsp"
      "hshead.lsp"))
