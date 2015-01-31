(defun HEAP-INSERT-ITEM69 (SELF NEW)
  (LET (I)
    (INCF (CADR SELF))
    (SETQ I (CADR SELF))
    (EXPARR-EXPAND?70 SELF)
    (WHILE (AND (> I 1)
                (< (get NEW 'cost)
                   (get (AREF (CAR SELF) (TRUNCATE I 2)) 'cost)))
           (SETF (AREF (CAR SELF) I) (AREF (CAR SELF) (TRUNCATE I 2)))
           (SETQ I (TRUNCATE I 2)))
    (SETF (AREF (CAR SELF) I) NEW)
    SELF))

(defun EXPARR-EXPAND?70  (SELF)
  (IF (>= (CADR SELF)
          (IF (NULL (CAR SELF)) 0 (ARRAY-TOTAL-SIZE (CAR SELF))))
      (EXPARR-EXPAND71 SELF
          (+ 2
             (TRUNCATE
                 (* 3
                    (IF (NULL (CAR SELF)) 0
                        (ARRAY-TOTAL-SIZE (CAR SELF))))
                 2)))))

(defun EXPARR-EXPAND71 (SELF N)
  (LET (NEWARR OLDSIZE NEWSIZE)
    (SETQ OLDSIZE
          (IF (NULL (CAR SELF)) 0 (ARRAY-TOTAL-SIZE (CAR SELF))))
    (SETQ NEWSIZE (MAX 10 N))
    (WHEN (> NEWSIZE OLDSIZE)
      (SETQ NEWARR (MAKE-ARRAY NEWSIZE))
      (DOTIMES (I OLDSIZE) (SETF (AREF NEWARR I) (AREF (CAR SELF) I)))
      (FREE-MEMORY (CAR SELF) OLDSIZE)
      (SETF (CAR SELF) NEWARR))
    SELF))

(defun HEAP-REMOVE-ITEM72 (SELF)
  (LET (THEMAX I)
    (WHEN (PLUSP (CADR SELF))
      (SETQ THEMAX (AREF (CAR SELF) 1))
      (SETF (AREF (CAR SELF) 1) (AREF (CAR SELF) (CADR SELF)))
      (SETF (AREF (CAR SELF) (CADR SELF)) *GLNULL*)
      (DECF (CADR SELF))
      (SETQ I 1)
      (HEAP-HEAPIFY73 SELF 1)
      THEMAX)))

(defun HEAP-HEAPIFY73 (SELF I)
  (LET (L R LARGEST)
    (SETQ L (* 2 I))
    (SETQ R (1+ (* 2 I)))
    (IF (AND (<= L (CADR SELF))
             (< (get (AREF (CAR SELF) L) 'cost)
                (get (AREF (CAR SELF) I) 'cost)))
        (SETQ LARGEST L) (SETQ LARGEST I))
    (IF (AND (<= R (CADR SELF))
             (< (get (AREF (CAR SELF) R) 'cost)
                (get (AREF (CAR SELF) LARGEST) 'cost)))
        (SETQ LARGEST R))
    (WHEN (/= LARGEST I)
      (LET (TMP)
        (SETQ TMP (AREF (CAR SELF) I))
        (SETF (AREF (CAR SELF) I) (AREF (CAR SELF) LARGEST))
        (SETF (AREF (CAR SELF) LARGEST) TMP))
      (HEAP-HEAPIFY73 SELF LARGEST))))
