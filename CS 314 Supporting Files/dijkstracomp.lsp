; 20 Nov 2014 16:56:00 CST

; Animation of Dijkstra, Prim, A* algorithms

; To run (on a CS Linux machine):
; %lisp
; (xgcl)
; (load "/projects/cs314/dijkstracomp.lsp")
; (load "/projects/cs314/heap69.lsp")
; (load "/projects/cs314/gcdistcomp.lsp")

; (dtest 'austin)    ; test of Dijkstra from Austin
; (ptest 'austin)    ; test of Prim from Austin
; (atest 'austin 'muleshoe #'hf1)  ; Austin to Muleshoe using hf1

; (setq *sleep* 0.1)  ; to speed it up

(DEFVAR *RED* '(65535 10000 10000))

(DEFVAR *ORANGE* '(65535 30000 30000))

(DEFVAR *GREEN* '(10000 65535 30000))

(DEFVAR *BLUE* '(10000 10000 65535))

(DEFVAR *WHITE* '(65535 65535 65535))

(DEFVAR *BLACK* '(0 0 0))

(DEFVAR *MYW* NIL)

(DEFUN WTESTAA ()
  (SETQ MYW (SETQ *MYW* (WINDOW-CREATE 630 600 "test window"))))

(LET ((G24695 *MYW*)) (IF G24695 G24695 (WTESTAA)))

(DEFUN DIJKSTRA (S)
  (LET (C FRINGE V NEWCOST)
    (DOLIST (Z *TXCITIES*)
      (SETQ C (CAR Z))
      (SETF (GET C 'VISITED) *GLFALSE*)
      (SETF (GET C 'PARENT) NIL)
      (SETF (GET C 'COST) 999999))
    (SETF (GET S 'COST) 0)
    (SETQ FRINGE (LIST (MAKE-ARRAY 100) 0))
    (HEAP-INSERT-ITEM69 FRINGE S)
    (WHILE (PLUSP (CADR FRINGE)) (SETQ V (HEAP-REMOVE-ITEM72 FRINGE))
           (IF (GET V 'PARENT)
               (DRAWCITYLINK *MYW* V (GET V 'PARENT) 3 *RED*))
           (WHEN (NOT (GET V 'VISITED))
             (SETF (GET V 'VISITED) T)
             (DOLIST (E (GET V 'NEIGHBORS))
               (SETQ NEWCOST (+ (GET V 'COST) (CADR E)))
               (WHEN (< NEWCOST (GET (CAR E) 'COST))
                 (SETF (GET (CAR E) 'COST) NEWCOST)
                 (WHEN (GET (CAR E) 'PARENT)
                   (DRAWCITYLINK *MYW* (GET (CAR E) 'PARENT) (CAR E) 3
                       *WHITE*)
                   (DRAWCITYLINK *MYW* (GET (CAR E) 'PARENT) (CAR E) 1
                       *BLACK*))
                 (SETF (GET (CAR E) 'PARENT) V)
                 (HEAP-INSERT-ITEM69 FRINGE (CAR E))
                 (DRAWCITYLINK *MYW* V (CAR E) 3 *BLUE*)))))))

(DEFUN PRIM (S)
  (LET (C FRINGE V NEWCOST)
    (DOLIST (Z *TXCITIES*)
      (SETQ C (CAR Z))
      (SETF (GET C 'VISITED) *GLFALSE*)
      (SETF (GET C 'PARENT) NIL)
      (SETF (GET C 'COST) 999999))
    (SETF (GET S 'COST) 0)
    (SETQ FRINGE (LIST (MAKE-ARRAY 100) 0))
    (HEAP-INSERT-ITEM69 FRINGE S)
    (WHILE (PLUSP (CADR FRINGE)) (SETQ V (HEAP-REMOVE-ITEM72 FRINGE))
           (WHEN (NOT (GET V 'VISITED))
             (IF (GET V 'PARENT)
                 (DRAWCITYLINK *MYW* V (GET V 'PARENT) 3 *RED*))
             (SETF (GET V 'VISITED) T)
             (DOLIST (E (GET V 'NEIGHBORS))
               (SETQ NEWCOST (CADR E))
               (WHEN (AND (NOT (GET (CAR E) 'VISITED))
                          (< NEWCOST (GET (CAR E) 'COST)))
                 (SETF (GET (CAR E) 'COST) NEWCOST)
                 (SETF (GET (CAR E) 'PARENT) V)
                 (HEAP-INSERT-ITEM69 FRINGE (CAR E))))))))

(DEFUN ASTAR (S GOAL HFN)
  (LET (C FRINGE V DONE NEWG)
    (DOLIST (Z *TXCITIES*)
      (SETQ C (CAR Z))
      (SETF (GET C 'VISITED) *GLFALSE*)
      (SETF (GET C 'PARENT) NIL)
      (SETF (GET C 'G) 999999)
      (SETF (GET C 'COST) 999999))
    (SETF (GET S 'G) 0)
    (SETF (GET S 'H) (FUNCALL HFN S GOAL))
    (SETF (GET S 'COST) (GET S 'H))
    (SETQ FRINGE (LIST (MAKE-ARRAY 100) 0))
    (HEAP-INSERT-ITEM69 FRINGE S)
    (WHILE (AND (NOT DONE) (PLUSP (CADR FRINGE)))
           (SETQ V (HEAP-REMOVE-ITEM72 FRINGE))
           (IF (GET V 'PARENT)
               (DRAWCITYLINK *MYW* V (GET V 'PARENT) 3 *RED*))
           (IF (EQ V GOAL) (SETQ DONE T)
               (DOLIST (E (GET V 'NEIGHBORS))
                 (SETQ NEWG (+ (GET V 'G) (CADR E)))
                 (WHEN (< NEWG (GET (CAR E) 'G))
                   (SETF (GET (CAR E) 'G) NEWG)
                   (SETF (GET (CAR E) 'PARENT) V)
                   (SETF (GET (CAR E) 'COST)
                         (+ NEWG (FUNCALL HFN (CAR E) GOAL)))
                   (HEAP-INSERT-ITEM69 FRINGE (CAR E))
                   (DRAWCITYLINK *MYW* V (CAR E) 3 *BLUE*)))))))

(DEFUN HF1 (S GOAL)
  (* 0.621371192237334
     (GCDIST (GET S 'LATITUDE) (GET S 'LONGITUDE) (GET GOAL 'LATITUDE)
             (GET GOAL 'LONGITUDE))))
(SETF (GET 'HF1 'GLARGUMENTS) '((S CITY) (GOAL CITY)))
(SETF (GET 'HF1 'GLFNRESULTTYPE) '(UNITS REAL MILE))


; (GLCC 'HF1)

(DEFUN HF2 (X Y) (* 0.5 (HF1 X Y)))

(DEFUN HF3 (X Y) (* 2 (HF1 X Y)))

(DEFUN PATH (C)
  (LET (RES) (WHILE C (PUSH C RES) (SETQ C (GET C 'PARENT))) RES))
(SETF (GET 'PATH 'GLARGUMENTS) '((C CITY)))
(SETF (GET 'PATH 'GLFNRESULTTYPE) '(LISTOF CITY))


(DEFUN HF0 (X Y) 0)

(DEFVAR *SLEEP* 0.5)

(SETF (GET 'CITY 'GLSTRUCTURE)
      '((SYMBOL (PROPLIST (LATITUDE (UNITS REAL DEGREES))
                    (LONGITUDE (UNITS REAL DEGREES))
                    (NEIGHBORS
                        (LISTOF (LIST (TO CITY) (DISTANCE INTEGER))))
                    (COST INTEGER) (G INTEGER) (H INTEGER)
                    (PARENT CITY) (VISITED BOOLEAN)))
        MSG
        ((GCDIST (GLAMBDA ((SELF CITY) (OTHER CITY))
                          (LET ((M (UNITS REAL MILES)))
                            (M =
                               (GCDIST (LATITUDE SELF) (LONGITUDE SELF)
                                       (LATITUDE OTHER)
                                       (LONGITUDE OTHER)))
                            (TRUNCATE M))))
         (APPROX (GLAMBDA ((SELF CITY) (OTHER CITY))
                          (LET ((M (UNITS REAL MILES)))
                            (M =
                               (SGCDIST (LATITUDE SELF)
                                        (LONGITUDE SELF)
                                        (LATITUDE OTHER)
                                        (LONGITUDE OTHER)))
                            (TRUNCATE M))))
         (ROADDIST
             (GLAMBDA ((SELF CITY) (OTHER CITY))
                      (DISTANCE
                          (THAT (NEIGHBORS SELF) WITH (TO == SELF))))))))


(DEFUN DTEST (&OPTIONAL CITY)
  (LET (SAVE)
    (WINDOW-CLEAR *MYW*)
    (SETQ SAVE *SLEEP*)
    (SETQ *SLEEP* 0)
    (WINDOW-SET-COLOR *MYW* '(0 0 0))
    (DRAWTEXAS)
    (DOLIST (C *TXCITIES*) (DRAWCITY MYW (CAR C)))
    (WINDOW-FORCE-OUTPUT *MYW*)
    (SETQ *SLEEP* SAVE)
    (DIJKSTRA (OR CITY 'AUSTIN))))

(DEFUN PTEST (&OPTIONAL CITY)
  (LET (SAVE)
    (WINDOW-CLEAR *MYW*)
    (SETQ SAVE *SLEEP*)
    (SETQ *SLEEP* 0)
    (WINDOW-SET-COLOR *MYW* '(0 0 0))
    (DRAWTEXAS)
    (DOLIST (C *TXCITIES*) (DRAWCITY MYW (CAR C)))
    (WINDOW-FORCE-OUTPUT *MYW*)
    (SETQ *SLEEP* SAVE)
    (PRIM (OR CITY 'AUSTIN))))

(DEFUN ATEST (&OPTIONAL START GOAL HFN)
  (LET (SAVE)
    (WINDOW-CLEAR *MYW*)
    (SETQ SAVE *SLEEP*)
    (SETQ *SLEEP* 0)
    (WINDOW-SET-COLOR *MYW* '(0 0 0))
    (DRAWTEXAS)
    (DOLIST (C *TXCITIES*) (DRAWCITY MYW (CAR C)))
    (WINDOW-FORCE-OUTPUT *MYW*)
    (SETQ *SLEEP* SAVE)
    (ASTAR (OR START 'AUSTIN) (OR GOAL 'MULESHOE) (OR HFN #'HF1))))

(DEFUN DRAWTEXAS ()
  (LET (LST)
    (OR (BOUNDP '*TEXASLL*) (LOAD "/u/novak/glisp/texasll.lsp"))
    (SETQ LST *TEXASLL*)
    (WHILE (AND LST (CDR LST))
           (WINDOW-DRAW-LINE-XY *MYW* (LONGSCALE (CAAR LST))
               (LATSCALE (CADAR LST)) (LONGSCALE (CAADR LST))
               (LATSCALE (CADADR LST)) 1)
           (POP LST))
    (WINDOW-FORCE-OUTPUT *MYW*)))

(DEFUN LATSCALE (Y) (ROUND (* (- Y 25.5) 50.0)))

(DEFUN LONGSCALE (X) (ROUND (* (+ X 107.0) 43.3)))

(DEFUN CITYPOINT (CITY)
  (LIST (LONGSCALE (GET CITY 'LONGITUDE))
        (LATSCALE (GET CITY 'LATITUDE))))

(DEFUN DRAWCITYPOINT (W CITY)
  (LET ((LL (CITYPOINT CITY)))
    (WINDOW-DRAW-DOT-XY W (CAR LL) (CADR LL))))

(DEFUN DRAWCITYLINK (W CITY1 CITY2 &OPTIONAL LINEWIDTH COLOR)
  (LET ((LL1 (CITYPOINT CITY1)) (LL2 (CITYPOINT CITY2)))
    (WINDOW-SET-COLOR *MYW* (OR COLOR '(0 0 0)))
    (WINDOW-DRAW-LINE-XY W (CAR LL1) (CADR LL1) (CAR LL2) (CADR LL2)
        (OR LINEWIDTH 1))
    (WINDOW-FORCE-OUTPUT W)
    (SLEEP *SLEEP*)))

(DEFUN DRAWCITY (W CITY)
  (DRAWCITYPOINT W CITY)
  (DOLIST (DEST (GET CITY 'NEIGHBORS))
    (DRAWCITYLINK W CITY (CAR DEST))))

(DEFVAR *TXCITIES*)

(SETQ *TXCITIES*
      '((ABILENE 32.449 -99.732
                 ((ROSCOE 47) (HASKELL 54) (SAN-ANGELO 88)
                  (BROWNWOOD 85) (FORT-WORTH 158)))
        (ALICE 27.752 -98.068 ((CORPUS-CHRISTI 39) (LAREDO 94)))
        (AMARILLO 35.222 -101.83
            ((DUMAS 47) (PLAINVIEW 76) (HEREFORD 43) (PAMPA 54)
             (CHILDRESS 116)))
        (AUSTIN 30.267 -97.742
                ((SAN-ANTONIO 76) (TEMPLE 62) (BRYAN 86) (LAMPASAS 61)
                 (JUNCTION 122)))
        (BAY-CITY 28.983 -95.968 ((HOUSTON 88)))
        (BEAUMONT 30.086 -94.101 ((PORT-ARTHUR 18)))
        (BEEVILLE 28.401 -97.747
            ((CORPUS-CHRISTI 60) (SAN-ANTONIO 93)))
        (BIG-SPRING 32.25 -101.477 ((ROSCOE 59) (SAN-ANGELO 86)))
        (BROWNSVILLE 25.901 -97.496 NIL)
        (BROWNWOOD 31.709 -98.99 ((LAMPASAS 71) (SAN-ANGELO 96)))
        (BRYAN 30.674 -96.369 NIL) (CHILDRESS 34.426 -100.203 NIL)
        (COLUMBUS 29.706 -96.538 ((AUSTIN 88) (SAN-ANTONIO 119)))
        (CORPUS-CHRISTI 27.8 -97.395 ((HARLINGEN 107)))
        (CORSICANA 32.095 -96.468
            ((HUNTSVILLE 117) (TYLER 71) (WACO 55)))
        (DALLAS 32.794 -96.799
                ((CORSICANA 57) (TYLER 96) (DENTON 39) (HILLSBORO 58)))
        (DEL-RIO 29.363 -100.895
                 ((SONORA 90) (SAN-ANTONIO 151) (VAN-HORN 304)))
        (DENTON 33.215 -97.132 ((FORT-WORTH 30)))
        (DUMAS 35.866 -101.972 NIL)
        (EAGLE-PASS 28.709 -100.498 ((DEL-RIO 56) (LAREDO 126)))
        (EL-PASO 31.759 -106.485 NIL)
        (FORT-WORTH 32.725 -97.32 ((DALLAS 31) (HILLSBORO 51)))
        (GALVESTON 29.301 -94.797 NIL)
        (GREENVILLE 33.138 -96.11
            ((DALLAS 47) (SHERMAN 52) (TYLER 79)))
        (HARLINGEN 26.19 -97.695 ((MCALLEN 31) (BROWNSVILLE 24)))
        (HASKELL 33.158 -99.743 ((WICHITA-FALLS 95)))
        (HEREFORD 34.815 -102.396 ((MULESHOE 45)))
        (HILLSBORO 32.011 -97.129 NIL)
        (HOUSTON 29.763 -95.362
                 ((GALVESTON 47) (BEAUMONT 85) (VICTORIA 124)
                  (COLUMBUS 74) (HUNTSVILLE 70) (BRYAN 99)))
        (HUNTSVILLE 30.723 -95.55 NIL)
        (JUNCTION 30.489 -99.771 ((SAN-ANTONIO 116)))
        (KILLEEN 31.117 -97.727 ((TEMPLE 30) (LAMPASAS 28)))
        (LAMESA 32.737 -101.95 ((MIDLAND 52) (BIG-SPRING 44)))
        (LAMPASAS 31.064 -98.18 NIL) (LAREDO 27.506 -99.506 NIL)
        (LONGVIEW 32.501 -94.739 NIL)
        (LUBBOCK 33.578 -101.854
                 ((PLAINVIEW 46) (MULESHOE 70) (SEMINOLE 79)
                  (LAMESA 64) (SNYDER 88)))
        (LUFKIN 31.338 -94.728
                ((NACOGDOCHES 19) (HOUSTON 123) (BEAUMONT 108)))
        (MCALLEN 26.203 -98.229 ((BROWNSVILLE 55) (LAREDO 146)))
        (MIDLAND 31.997 -102.077 ((BIG-SPRING 39)))
        (MINERAL-WELLS 32.808 -98.112
            ((FORT-WORTH 60) (ABILENE 130) (WICHITA-FALLS 89)))
        (MULESHOE 34.226 -102.722 NIL)
        (NACOGDOCHES 31.603 -94.654 ((LONGVIEW 80)))
        (ODESSA 31.846 -102.366 ((MIDLAND 20) (PECOS 76)))
        (PALESTINE 31.762 -95.63
            ((CORSICANA 54) (TYLER 50) (NACOGDOCHES 56)))
        (PAMPA 35.536 -100.958 NIL)
        (PECOS 31.423 -103.492 ((VAN-HORN 88)))
        (PLAINVIEW 34.185 -101.705 ((VERNON 159)))
        (PORT-ARTHUR 29.899 -93.928 NIL) (ROSCOE 32.446 -100.537 NIL)
        (SAN-ANGELO 31.464 -100.436 NIL)
        (SAN-ANTONIO 29.424 -98.492 ((LAREDO 154)))
        (SEMINOLE 32.719 -102.643 ((ODESSA 64) (LAMESA 40)))
        (SHERMAN 33.636 -96.608 ((DALLAS 65) (WICHITA-FALLS 113)))
        (SNYDER 32.718 -100.916 ((LAMESA 67) (ROSCOE 30)))
        (SONORA 30.578 -100.642 ((JUNCTION 59) (SAN-ANGELO 68)))
        (TEMPLE 31.098 -97.342 ((WACO 37)))
        (TYLER 32.351 -95.3 ((LONGVIEW 34) (LUFKIN 86)))
        (VAN-HORN 31.04 -104.829 ((EL-PASO 156) (SONORA 254)))
        (VERNON 34.154 -99.264 ((CHILDRESS 68)))
        (VICTORIA 28.805 -97.002 ((CORPUS-CHRISTI 78) (BEEVILLE 55)))
        (WACO 31.549 -97.145 ((HILLSBORO 32) (BRYAN 87)))
        (WICHITA-FALLS 33.914 -98.492 ((VERNON 42) (FORT-WORTH 106)))))

(DEFUN READCITIES (LST)
  (LET (C)
    (DOLIST (ITEM LST)
      (SETQ C (FIRST ITEM))
      (SETF (GET C 'LATITUDE) (SECOND ITEM))
      (SETF (GET C 'LONGITUDE) (THIRD ITEM))
      (DOLIST (DEST (FOURTH ITEM))
        (PUSHNEW DEST (GET C 'NEIGHBORS) :TEST #'EQUAL)
        (PUSHNEW (LIST C (SECOND DEST)) (GET (FIRST DEST) 'NEIGHBORS)
                 :TEST #'EQUAL)))))

(BLOCK ()
  (LET* ((G25859 *TXCITIES*) (X (CAR G25859)))
    (TAGBODY
      G25860
      (IF (ENDP G25859) (RETURN (PROGN NIL)))
      (TAGBODY (SETF (GET (CAR X) 'NEIGHBORS) NIL))
      (SETQ G25859 (CDR G25859) X (CAR G25859))
      (GO G25860))))

(READCITIES *TXCITIES*)
(setq *glfalse* nil)
(setq *glnull* nil)
