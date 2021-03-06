/**
 * this class Cons implements a Lisp-like Cons cell
 * 
 * @author  Gordon S. Novak Jr.
 * @version 29 Nov 01; 25 Aug 08; 05 Sep 08; 08 Sep 08; 12 Sep 08; 24 Sep 08
 *          02 Oct 09; 12 Feb 10; 04 Oct 12
 */

import java.util.Arrays;

interface Functor { Object fn(Object x); }

interface Predicate { boolean pred(Object x); }

public class Cons
{
    // instance variables
    private Object car;
    private Cons cdr;
    private Cons(Object first, Cons rest)
       { car = first;
         cdr = rest; }
    public static Cons cons(Object first, Cons rest)
      { return new Cons(first, rest); }
    public static boolean consp (Object x)
       { return ( (x != null) && (x instanceof Cons) ); }
// safe car, returns null if lst is null
    public static Object first(Cons lst) {
        return ( (lst == null) ? null : lst.car  ); }
// safe cdr, returns null if lst is null
    public static Cons rest(Cons lst) {
      return ( (lst == null) ? null : lst.cdr  ); }
    public static Object second (Cons x) { return first(rest(x)); }
    public static Object third (Cons x) { return first(rest(rest(x))); }
    public static void setfirst (Cons x, Object i) { x.car = i; }
    public static void setrest  (Cons x, Cons y) { x.cdr = y; }
   public static Cons list(Object ... elements) {
       Cons list = null;
       for (int i = elements.length-1; i >= 0; i--) {
           list = cons(elements[i], list);
       }
       return list;
   }

    // convert a list to a string for printing
    public String toString() {
       return ( "(" + toStringb(this) ); }
    public static String toString(Cons lst) {
       return ( "(" + toStringb(lst) ); }
    private static String toStringb(Cons lst) {
       return ( (lst == null) ?  ")"
                : ( first(lst) == null ? "()" : first(lst).toString() )
                  + ((rest(lst) == null) ? ")" 
                     : " " + toStringb(rest(lst)) ) ); }

    public static int square(int x) { return x*x; }

    // iterative destructive merge using compareTo
public static Cons dmerj (Cons x, Cons y) {
  if ( x == null ) return y;
   else if ( y == null ) return x;
   else { Cons front = x;
          if ( ((Comparable) first(x)).compareTo(first(y)) < 0)
             x = rest(x);
            else { front = y;
                   y = rest(y); };
          Cons end = front;
          while ( x != null )
            { if ( y == null ||
                   ((Comparable) first(x)).compareTo(first(y)) < 0)
                 { setrest(end, x);
                   x = rest(x); }
               else { setrest(end, y);
                      y = rest(y); };
              end = rest(end); }
          setrest(end, y);
          return front; } }

public static Cons midpoint (Cons lst) {
  Cons current = lst;
  Cons prev = current;
  while ( lst != null && rest(lst) != null) {
    lst = rest(rest(lst));
    prev = current;
    current = rest(current); };
  return prev; }

    // Destructive merge sort of a linked list, Ascending order.
    // Assumes that each list element implements the Comparable interface.
    // This function will rearrange the order (but not location)
    // of list elements.  Therefore, you must save the result of
    // this function as the pointer to the new head of the list, e.g.
    //    mylist = llmergesort(mylist);
public static Cons llmergesort (Cons lst) {
  if ( lst == null || rest(lst) == null)
     return lst;
   else { Cons mid = midpoint(lst);
          Cons half = rest(mid);
          setrest(mid, null);
          return dmerj( llmergesort(lst),
                        llmergesort(half)); } }


    // ****** your code starts here ******
    // add other functions as you wish.

// Get the union of two sets.
public static Cons union (Cons x, Cons y) {
    return mergeunion(llmergesort(x), llmergesort(y));
}

    // following is a helper function for union
public static Cons mergeunion (Cons x, Cons y) {
    if (x == null && y == null) return null;
    else if (x == null) return y;
    else if (y == null) return x;
    if (((Comparable) first(x)).compareTo(first(y)) < 0) return cons(first(x), mergeunion(rest(x), y));
    else if (((Comparable) first(x)).compareTo(first(y)) > 0) return cons(first(y), mergeunion(x, rest(y)));
    else return cons(first(x), mergeunion(rest(x), rest(y)));
}    

// Get the set difference of set x minus set y
public static Cons setDifference (Cons x, Cons y) {
    return mergediff(llmergesort(x), llmergesort(y));    
}

    // following is a helper function for setDifference
public static Cons mergediff (Cons x, Cons y) {
    if (x == null) return null;
    else if (y == null) return x;
    if (first(x).equals(first(y))) return mergediff(rest(x), rest(y));
    else if (((Comparable) first(x)).compareTo(first(y)) > 0) return mergediff(x, rest(y));
    else return cons(first(x), mergediff(rest(x), y));
}

// Be an evil bank.
public static Cons bank(Cons accounts, Cons updates) {
    return llmergesort(bankHelper(accounts, llmergesort(updates), list()));
}

// Change ups == null to reflect the negative thing.
public static Cons bankHelper(Cons acts, Cons ups, Cons newAccounts) {
    if (ups == null) return union(acts, newAccounts);
    else if (acts == null) {
        if (((Account) first(ups)).amount() > 0) return bankHelper(acts, rest(ups), cons(first(ups), newAccounts));
        else return bankHelper(acts, rest(ups), newAccounts);
    }
    else if (((Account) first(acts)).equals((Account) first(ups))) {
        Account act = new Account(((Account) first(acts)).name(), ((Account) first(acts)).amount());
        while (((Account) first(acts)).equals((Account) first(ups)) && ups != null) {
            int newBalance = act.amount() + ((Account) first(ups)).amount();
            if (newBalance < 0) {
                System.out.println(act.name() + " account overdrawn $" + newBalance + ". Charged overdraft fee of $30. New balance: $" + (newBalance - 30));
                newBalance += -30;
            }
            act = new Account(((Account) first(acts)).name(), newBalance); 
            ups = rest(ups);
        } 
        return bankHelper(rest(acts), ups, cons(act, newAccounts));
    }
    else if (((Account) first(acts)).name().compareToIgnoreCase(((Account) first(ups)).name()) < 0) {
        return bankHelper(rest(acts), ups, cons(first(acts), newAccounts));
    }
    else {    
        if (((Account) first(ups)).amount() > 0) {
            System.out.println(((Account) first(ups)).name() + " account added with $" + ((Account) first(ups)).amount() + " balance.");
            return bankHelper(acts, rest(ups), cons(first(ups), newAccounts));
        }
        else {
            System.out.println("Cannot update " + ((Account) first(ups)).name() + " account for $" + ((Account) first(ups)).amount() + ". Account does not exist.");
            return bankHelper(acts, rest(ups), newAccounts);
        }
    }
}

// Merge two arrays of strings
public static String [] mergearr(String [] x, String [] y) {
    String [] answer = new String[x.length + y.length];
    return mergearrHelper(x, y, 0, 0, answer);
}

// Helper method for string array merging
public static String [] mergearrHelper (String [] x, String [] y, int m, int n, String [] answer) {
    if (m == x.length && n == y.length) return answer;
    else if (!(m == x.length) && (n == y.length || x[m].compareTo(y[n]) < 0)) {
        answer[m + n] = x[m];
        System.out.println(Arrays.toString(answer));
        System.out.println("m: " + m + " n: " + n);
        return mergearrHelper(x, y, ++m, n, answer);
    }
    else if (!(n == y.length) && (m == x.length || x[m].compareTo(y[n]) > 0)) { 
        answer[m + n] = y[n]; 
        System.out.println(Arrays.toString(answer));
        System.out.println("m: " + m + " n: " + n);
        return mergearrHelper(x, y, m, ++n, answer);
    }
    else {
        answer[m + n] = x[m];
        answer[m + n + 1] = y[n];
        return mergearrHelper(x, y, ++m, ++n, answer);
    }
}

// Check to see if some markup is well formed, with each tag or string input as a member of a list.
public static boolean markup(Cons text) {
   return markupHelper(text, 0, list(), 0, 0, 0, list()); 
}

// Helper method to check markup
public static boolean markupHelper(Cons text, int position, Cons lastOpenPosition, int opens, int closes, int stackLength, Cons stack) {
    if (text == null) {
        if (stack == null) return true;
        else {
            String tag = "<" + ((String) first(stack)) + ">";
            System.out.println("You didn't close your tags! The most recent unfulfilled tag is poor " + tag + " at position " + first(lastOpenPosition) + ".");
            return false;
        }
    }
    else if (((String) first(text)).matches("<\\w*>")) {
        String tag = ((String) first(text)).replaceAll("<|>", "");
        return markupHelper(rest(text), ++position, cons(position - 1, lastOpenPosition), ++opens, closes, ++stackLength, cons(tag, stack));
    }
    else if (((String) first(text)).matches("</\\w*>")) {
        String tag = ((String) first(text)).replaceAll("<|>|/", "");
        if (tag.equals((String) first(stack))) { return markupHelper(rest(text), ++position, rest(lastOpenPosition), opens, ++closes, --stackLength, rest(stack)); }
        else if (tag.equalsIgnoreCase((String) first(stack))) {
            System.out.println("Style warning: tag cases do not match! Consider writing better code. We'll ignore the error, this time.");
            return markupHelper(rest(text), ++position, rest(lastOpenPosition), opens, ++closes, --stackLength, rest(stack));
        }
        else {
            closes++;
            if (closes > opens) System.out.println("Problem with tag </" + tag + "> at position " + (position - 1) + ". You closed before you opened!");
            else {
                String openTag = ((String) first(stack));
                System.out.println("Problem with tag </" + tag + "> at position " + position + ". Expected </" + openTag + "> to match <" + openTag + "> at position " + first(lastOpenPosition) + ".");
            }
            return false;
        }
    }
    else return markupHelper(rest(text), ++position, lastOpenPosition, opens, closes, stackLength, stack);
}

    // ****** your code ends here ******

    public static void main ( String[] args )
      { 
        Cons set1 = list("d", "b", "c", "a");
        Cons set2 = list("f", "d", "b", "g", "h");
        System.out.println("set1 = " + Cons.toString(set1));
        System.out.println("set2 = " + Cons.toString(set2));
        System.out.println("union = " + Cons.toString(union(set1, set2)));

        Cons set3 = list("d", "b", "c", "a");
        Cons set4 = list("f", "d", "b", "g", "h");
        System.out.println("set3 = " + Cons.toString(set3));
        System.out.println("set4 = " + Cons.toString(set4));
        System.out.println("difference = " +
                           Cons.toString(setDifference(set3, set4)));
        Cons mySet0 = list("a", "b", "c", "d");
        Cons mySet1 = list("a", "b", "c", "d");
        Cons mySet2 = list("b", "c", "a", "e");
        Cons mySet3 = list("a", "z", "t", "r");
        Cons weirdSet1 = list("z", "t", "e", "u", "o");
        Cons weirdSet2 = list("z", "t", "e", "p", "m");
        System.out.println("mySet0 = " + Cons.toString(mySet0));
        System.out.println("mySet1 = " + Cons.toString(mySet1));
        System.out.println("mySet2 = " + Cons.toString(mySet2));
        System.out.println("mySet3 = " + mySet3);
        System.out.println("Differences: ");
        System.out.println("0 - 3 = " + setDifference(mySet0, mySet3));
        System.out.println("0-1 = " + Cons.toString(setDifference(mySet0, mySet1)));
        System.out.println("mySet1 = " + mySet1);
        System.out.println("0-2= " + Cons.toString(setDifference(mySet0, mySet2)));
        System.out.println("mySet2 = " + mySet2);
        System.out.println("1-2 = " + Cons.toString(setDifference(mySet1, mySet2)));
        System.out.println("mySet2 = " + mySet2);
        System.out.println("1-0 = " + Cons.toString(setDifference(mySet1, mySet0)));
        System.out.println("Weird difference = " + setDifference(weirdSet1, weirdSet2));
        Cons accounts = list(
               new Account("Arbiter", new Integer(498)),
               new Account("Flintstone", new Integer(102)),
               new Account("Foonly", new Integer(123)),
               new Account("Kenobi", new Integer(373)),
               new Account("Rubble", new Integer(514)),
               new Account("Tirebiter", new Integer(752)),
               new Account("Vader", new Integer(1024)) );

        Cons updates = list(
               new Account("Foonly", new Integer(100)),
               new Account("Flintstone", new Integer(-10)),
               new Account("Arbiter", new Integer(-600)),
               new Account("Garble", new Integer(-100)),
               new Account("Rabble", new Integer(100)),
               new Account("Flintstone", new Integer(-20)),
               new Account("Foonly", new Integer(10)),
               new Account("Tirebiter", new Integer(-200)),
               new Account("Flintstone", new Integer(10)),
               new Account("Flintstone", new Integer(-120))  );
        System.out.println("accounts = " + accounts.toString());
        System.out.println("updates = " + updates.toString());
        Cons newaccounts = bank(accounts, updates);
        System.out.println("result = " + newaccounts.toString());

        String[] arra = {"a", "big", "dog", "hippo"};
        System.out.println(Arrays.toString(arra));
        String[] arrb = {"canary", "cat", "fox", "turtle"};
        System.out.println(Arrays.toString(arrb));
        String[] resarr = mergearr(arra, arrb);
        for ( int i = 0; i < resarr.length; i++ )
          System.out.println(resarr[i]);

        Cons xmla = list( "<TT>", "foo", "</TT>");
        Cons xmlb = list( "<TABLE>", "<TR>", "<TD>", "foo", "</TD>",
                          "<TD>", "bar", "</TD>", "</TR>",
                          "<TR>", "<TD>", "fum", "</TD>", "<TD>",
                          "baz", "</TD>", "</TR>", "</TABLE>" );
        Cons xmlc = list( "<TABLE>", "<TR>", "<TD>", "foo", "</TD>",
                          "<TD>", "bar", "</TD>", "</TR>",
                          "<TR>", "<TD>", "fum", "</TD>", "<TD>",
                          "baz", "</TD>", "</WHAT>", "</TABLE>" );
        Cons xmld = list( "<TABLE>", "<TR>", "<TD>", "foo", "</TD>",
                          "<TD>", "bar", "</TD>", "", "</TR>",
                          "</TABLE>", "</NOW>" );
        Cons xmle = list( "<THIS>", "<CANT>", "<BE>", "foo", "<RIGHT>" );
        Cons xmlf = list( "<CATALOG>",
                          "<CD>",
                          "<TITLE>", "Empire", "Burlesque", "</TITLE>",
                          "<ARTIST>", "Bob", "Dylan", "</ARTIST>",
                          "<COUNTRY>", "USA", "</COUNTRY>",
                          "<COMPANY>", "Columbia", "</COMPANY>",
                          "<PRICE>", "10.90", "</PRICE>",
                          "<YEAR>", "1985", "</YEAR>",
                          "</CD>",
                          "<CD>",
                          "<TITLE>", "Hide", "your", "heart", "</TITLE>",
                          "<ARTIST>", "Bonnie", "Tyler", "</ARTIST>",
                          "<COUNTRY>", "UK", "</COUNTRY>",
                          "<COMPANY>", "CBS", "Records", "</COMPANY>",
                          "<PRICE>", "9.90", "</PRICE>",
                          "<YEAR>", "1988", "</YEAR>",
                          "</CD>", "</CATALOG>");
        System.out.println("xmla = " + xmla.toString());
        System.out.println("result = " + markup(xmla));
        System.out.println("xmlb = " + xmlb.toString());
        System.out.println("result = " + markup(xmlb));
        System.out.println("xmlc = " + xmlc.toString());
        System.out.println("result = " + markup(xmlc));
        System.out.println("xmld = " + xmld.toString());
        System.out.println("result = " + markup(xmld));
        System.out.println("xmle = " + xmle.toString());
        System.out.println("result = " + markup(xmle));
        System.out.println("xmlf = " + xmlf.toString());
        System.out.println("result = " + markup(xmlf));

      }

}
