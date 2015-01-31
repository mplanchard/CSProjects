; mouse.lsp             Gordon S. Novak Jr.         ; 11 Aug 08

(defvar *min* 0)
(defvar *max* 9)

; find path for mouse in maze
; (mouse maze 4 9 '())
(defun mouse (maze x y prev)
  (let (path here)
    (if (or (< x *min*) (> x *max*) (< y *min*) (> y *max*)
            (eq (aref maze y x) '*)
            (member (setq here (list x y))
                    prev :test 'equal))
        nil                         ; fail
        (if (eq (aref maze y x) 'c)
            '(cheese)               ; success
            (if (setq path
                  (mouse maze (- x 1) y
                         (cons here prev)))
                (cons 'w path)
            (if (setq path
                  (mouse maze x (- y 1) (cons here prev)))
                (cons 'n path)
            (if (setq path
                  (mouse maze (+ x 1) y (cons here prev)))
                (cons 'e path)
            (if (setq path
                  (mouse maze x (+ y 1) (cons here prev)))
                (cons 's path)
                nil)))))) ))


(setq maze (make-array '(10 10)   ; maze with a dead end
 :initial-contents
;  0 1 2 3 4 5 6 7 8 9
'((* * * * * * * * * *)    ; 0
  (* 0 0 * * * * * * *)    ; 1
  (* 0 * * * * * * * *)    ; 2
  (* 0 * * * * * * * *)    ; 3
  (* 0 0 0 0 0 0 * * *)    ; 4
  (* * * * 0 * 0 * * *)    ; 5
  (* * * * 0 * 0 * C *)    ; 6
  (* * * * 0 * 0 * 0 *)    ; 7
  (* * * * 0 * 0 0 0 *)    ; 8
  (* * * * 0 * * * * *)))) ; 9

(setq mazeb (make-array '(10 10)   ; maze with a loop
 :initial-contents
;  0 1 2 3 4 5 6 7 8 9
'((* * * * * * * * * *)    ; 0
  (* 0 0 0 * * * * * *)    ; 1
  (* 0 * 0 * * * * * *)    ; 2
  (* 0 * 0 * * * * * *)    ; 3
  (* 0 0 0 0 0 0 * * *)    ; 4
  (* * * * 0 * 0 * * *)    ; 5
  (* * * * 0 * 0 * C *)    ; 6
  (* * * * 0 * 0 * 0 *)    ; 7
  (* * * * 0 * 0 0 0 *)    ; 8
  (* * * * 0 * * * * *)))) ; 9

(setq mazec (make-array '(10 10)   ; maze with max depth
 :initial-contents
;  0 1 2 3 4 5 6 7 8 9
'((0 0 0 0 0 0 0 0 0 0)    ; 0
  (0 0 0 0 0 0 0 0 0 0)    ; 1
  (0 0 0 0 0 0 0 0 0 0)    ; 2
  (0 0 0 0 0 0 0 0 0 0)    ; 3
  (0 0 0 0 0 0 0 0 0 0)    ; 4
  (0 0 0 0 0 0 0 0 0 0)    ; 5
  (0 0 0 0 0 0 0 0 0 0)    ; 6
  (0 0 0 0 0 0 0 0 0 0)    ; 7
  (0 0 0 0 0 0 0 0 0 0)    ; 8
  (0 0 0 0 0 C 0 0 0 0)))) ; 9
