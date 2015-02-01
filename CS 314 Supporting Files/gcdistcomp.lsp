; 20 Nov 2014 17:12:33 CST

(SETF (GET 'LAT-LONG 'GLSTRUCTURE)
      '((LIST (LATITUDE NUMBER) (LONGITUDE NUMBER)) MSG
        ((DISTANCE
             (GLAMBDA (SELF OTHER)
                      (GCDIST (LATITUDE SELF) (LONGITUDE SELF)
                              (LATITUDE OTHER) (LONGITUDE OTHER)))
             ARGTYPES (LAT-LONG)))))


(DEFUN GCDIST (LATA LONGA LATB LONGB)
  (LET (CLATA CLATB D PSI DIST)
    (SETQ CLATA (COS (* 0.017453292519943295 LATA)))
    (SETQ CLATB (COS (* 0.017453292519943295 LATB)))
    (SETQ D
          (SQRT (+ (EXPT (- (* CLATA
                               (SIN (* 0.017453292519943295 LONGA)))
                            (* CLATB
                               (SIN (* 0.017453292519943295 LONGB))))
                         2)
                   (EXPT (- (* CLATA
                               (COS (* 0.017453292519943295 LONGA)))
                            (* CLATB
                               (COS (* 0.017453292519943295 LONGB))))
                         2)
                   (EXPT (- (SIN (* 0.017453292519943295 LATA))
                            (SIN (* 0.017453292519943295 LATB)))
                         2))))
    (SETQ PSI (* 2.0 (ASIN (* 0.5 D))))
    (SETQ DIST (* 6372.640112053847 PSI))
    DIST))
(SETF (GET 'GCDIST 'GLARGUMENTS)
      '((LATA (UNITS REAL DEGREES)) (LONGA (UNITS REAL DEGREES))
        (LATB (UNITS REAL DEGREES)) (LONGB (UNITS REAL DEGREES))))
(SETF (GET 'GCDIST 'GLFNRESULTTYPE) '(UNITS REAL KILOMETER))


(DEFUN SGCDIST (LATA LONGA LATB LONGB)
  (LET (MIDLAT PSI DIST)
    (SETQ MIDLAT (* 0.008726646259971647 (+ LATA LATB)))
    (SETQ PSI
          (* 0.017453292519943295
             (SQRT (+ (EXPT (- LATA LATB) 2)
                      (EXPT (* (- LONGA LONGB) (COS MIDLAT)) 2)))))
    (SETQ DIST (* 6372.640112053847 PSI))
    DIST))
(SETF (GET 'SGCDIST 'GLARGUMENTS)
      '((LATA (UNITS REAL DEGREES)) (LONGA (UNITS REAL DEGREES))
        (LATB (UNITS REAL DEGREES)) (LONGB (UNITS REAL DEGREES))))
(SETF (GET 'SGCDIST 'GLFNRESULTTYPE) '(UNITS REAL KILOMETER))


(DEFUN GCDISTB (LATA LONGA LATB LONGB)
  (LET (PSI)
    (SETQ PSI
          (ACOS (+ (* (COS (* 0.017453292519943295 LATA))
                      (COS (* 0.017453292519943295 LATB))
                      (COS (* 0.017453292519943295 (- LONGA LONGB))))
                   (* (SIN (* 0.017453292519943295 LATA))
                      (SIN (* 0.017453292519943295 LATB))))))
    (* 6372.640112053847 PSI)))
(SETF (GET 'GCDISTB 'GLARGUMENTS)
      '((LATA (UNITS REAL DEGREES)) (LONGA (UNITS REAL DEGREES))
        (LATB (UNITS REAL DEGREES)) (LONGB (UNITS REAL DEGREES))))
(SETF (GET 'GCDISTB 'GLFNRESULTTYPE) '(UNITS REAL KILOMETER))
