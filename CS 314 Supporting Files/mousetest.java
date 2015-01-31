// mousetest.java                06 Oct 09; 28 Feb 12

// You can use Cons.drjava.java to go with this file (rename it Cons.java).

import java.util.*;

public class mousetest
{


// member returns null if requested item not found
    public static Cons memberv(Cons i, Cons lst) {
      return ( Cons.consp(lst)
               ? (( Cons.first((Cons)Cons.first(lst)).equals(Cons.first(i))
                    && Cons.second((Cons)Cons.first(lst)).equals(Cons.second(i)) )
                  ? lst : memberv(i, Cons.rest(lst)) )
               : lst ); }


public static Cons mouse (String[][] maze, int x, int y, Cons prev)
  {
    Cons path;
    if ( maze[y][x].equals("*") ) return null;
    Cons here = Cons.list(x, y);
    if ( memberv(here, prev) != null )
        return null;
    else if ( maze[y][x].equals("C") )
        return Cons.list("C");
      else if ((path = mouse(maze, x - 1, y,
                             Cons.cons(here,prev)))
               != null )
          return Cons.cons("W", path);
        else if ((path = mouse(maze, x, y - 1,
                               Cons.cons(here,prev)))
                 != null )
            return Cons.cons("N", path);
          else if ((path = mouse(maze, x + 1, y,
                                 Cons.cons(here,prev)))
                   != null )
              return Cons.cons("E", path);
            else if ((path = mouse(maze, x, y + 1,
                                   Cons.cons(here,prev)))
                     != null )
                return Cons.cons("S", path);
              else return null;
  }   /* mouse */

   public static void main(String args[]) 
   {
       String mazea[][] = { { "*","*","*","*","*","*","*","*","*","*" },
                            { "*","0","0","*","*","*","*","*","*","*" },
                            { "*","0","*","*","*","*","*","*","*","*" },
                            { "*","0","*","*","*","*","*","*","*","*" },
                            { "*","0","0","0","0","0","0","*","*","*" },
                            { "*","*","*","*","0","*","0","*","*","*" },
                            { "*","*","*","*","0","*","0","*","C","*" },
                            { "*","*","*","*","0","*","0","*","0","*" },
                            { "*","*","*","*","0","*","0","0","0","*" },
                            { "*","*","*","*","0","*","*","*","*","*" } };
     System.out.println("Test maze:");
     for (int i =0; i<10; i++) {
         for (int j =0; j<10; j++) System.out.print(mazea[i][j] + " ");
         System.out.println(); }
     Cons patha = mouse(mazea, 4, 9, null);
     System.out.println("Path:" + patha);
     System.out.println();
     System.out.println();
       String mazeb[][] = { { "*","*","*","*","*","*","*","*","*","*" },
                            { "*","0","0","0","0","*","*","*","0","*" },
                            { "*","0","*","*","0","0","0","0","0","*" },
                            { "*","0","*","*","0","*","*","0","*","*" },
                            { "*","0","0","0","0","0","0","0","0","*" },
                            { "*","*","*","*","0","*","0","*","*","*" },
                            { "*","0","0","*","0","*","0","*","C","*" },
                            { "*","0","*","*","0","*","0","*","0","*" },
                            { "*","0","0","0","0","*","0","0","0","*" },
                            { "*","*","*","*","0","*","*","*","*","*" } };
     System.out.println("Test maze:");
     for (int i =0; i<10; i++) {
         for (int j =0; j<10; j++) System.out.print(mazeb[i][j] + " ");
         System.out.println(); }
     Cons pathb = mouse(mazeb, 4, 9, null);
     System.out.println("Path:" + pathb);
     System.out.println();
   }
}   
