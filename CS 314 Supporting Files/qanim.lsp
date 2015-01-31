; qanim.lsp             Gordon S. Novak Jr.         ; 06 Mar 12

; Copyright (c) 2012 Gordon S. Novak Jr. and The University of Texas at Austin.

; These programs create animations of a stack and a queue.
; Initially, the structure contains 10 entries; then insertions or
; removals are randomly generated.

; To use:
; gcl            ; must use Gnu Common Lisp.  Enter gcl at command prompt.
; (xgcl)         ; this command to Lisp enables the drawing functions.
; (load "qanim.lsp")  ; load this code
; (animstack)    ; to animate a Stack
; (animqueue)    ; to animate a queue
; ...            ; you can run the animations repeatedly
; (bye)          ; to exit Gnu Common Lisp

(defvar *red* '(65535 10000 10000))
(defvar *orange* '(65535 30000 30000))
(defvar *green* '(10000 65535 30000))
(defvar *blue* '(10000 10000 65535))
(defvar *white* '(65535 65535 65535))
(defvar *black* '(0 0 0))

(defvar myw nil)
(defvar *myw* nil)

; Make a window to play in.
(or *myw* (setq myw (setq *myw* (window-create 400 700 "test window"))))

(defvar *arr* (make-array 30))

; init with 10 elements at top
(defun initarray ()
  (dotimes (i 10) (setf (aref *arr* i) t))
  (dotimes (i 20) (setf (aref *arr* (+ i 10)) nil)) )

(defun drawarray ()
  (window-clear *myw*)
  (window-set-color *myw* *black*)
  (window-draw-box-xy *myw* 98 48 204 604 1)
  (window-set-color *myw* *red*)
  (dotimes (i 30)
    (if (aref *arr* i)
        (window-draw-line-xy *myw* 100 (- 640 (* 20 i)) 300 (- 640 (* 20 i))
                             18) ))
  (window-force-output *myw*)
  (window-reset-color *myw*)
  )

; animate random insertion and removal from stack
(defun animstack ()
  (let (r qtop)
    (initarray)
    (setq qtop 10)
    (dotimes (i 200)
      (setq r (random 1.0))
      (if (>= r 0.5)
          (if (< qtop 30)
              (progn (setf (aref *arr* qtop) t)         ; push if not full
                     (incf qtop) ) )
          (if (> qtop 0)
              (progn (decf qtop)
                     (setf (aref *arr* qtop) nil) ) ) ) ; pop if not empty
      (drawarray)
      (sleep 0.1) )
    ))


; animate random insertion and removal from circular queue
(defun animqueue ()
  (let (r front end next)
    (initarray)
    (setq front 0)
    (setq end 10)
    (dotimes (i 200)
      (setq r (random 1.0))
      (if (>= r 0.5)
          (progn (setq next (mod (1+ end) 30))
                 (if (not (eql next front))     ; reject insert if full
                     (progn
                       (setf (aref *arr* end) t) ; insert
                       (setq end next) )) )
          (if (not (eql front end))             ; reject remove if empty
              (progn
                (setf (aref *arr* front) nil)   ; remove
                (setq front (mod (1+ front) 30)) ) ) )
      (drawarray)
      (sleep 0.1) )
    ))
