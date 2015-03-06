// libtest.java      GSN    03 Oct 08; 21 Feb 12; 26 Dec 13
// 

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

interface Functor { Object fn(Object x); }

interface Predicate { boolean pred(Object x); }

@SuppressWarnings("unchecked")
public class libtest {

    // ****** your code starts here ******


public static Integer sumlist(LinkedList<Integer> lst) {
    Integer sum = new Integer(0);
    for (Integer num : lst)
        sum += num;
    return sum;
}

public static Integer sumarrlist(ArrayList<Integer> lst) {
    Integer sum = new Integer(0);
    for (Integer num : lst)
        sum += num;
    return sum;
}

public static LinkedList<Object> subset (Predicate p, LinkedList<Object> lst) {
    LinkedList<Object> subsetList = new LinkedList<Object>();
    for (Object obj : lst)
        if (p.pred(obj)) subsetList.add(obj);
    return subsetList;
}

public static LinkedList<Object> dsubset (Predicate p, LinkedList<Object> lst) {
    ListIterator<Object> itr = lst.listIterator();
    while (itr.hasNext())
        if (p.pred(itr.next())) itr.remove();
    return lst;
}

public static Object some (Predicate p, LinkedList<Object> lst) {
    for (Object obj : lst)
        if (p.pred(obj)) return obj;
    return null;
}

public static LinkedList<Object> mapcar (Functor f, LinkedList<Object> lst) {
    LinkedList<Object> mapList = new LinkedList<Object>();
    for (Object obj : lst)
        mapList.add(f.fn(obj));
    return mapList;
}

public static LinkedList<Object> merge (LinkedList<Object> lsta, LinkedList<Object> lstb) {
    if (lsta.isEmpty()) return lstb;
    else if (lstb.isEmpty()) return lsta;
    else {
        LinkedList<Object> mergedList = new LinkedList<Object>();
        ListIterator<Object> itra = lsta.listIterator();
        ListIterator<Object> itrb = lstb.listIterator();
        while (itra.hasNext() && itrb.hasNext()) {
            Object obja = itra.next();
            Object objb = itrb.next();
            if (((Comparable) obja).compareTo(objb) < 0) { 
                mergedList.add(obja);
                itrb.previous(); }
            else if (((Comparable) obja).compareTo(objb) > 0) {
                mergedList.add(objb);
                itra.previous(); }
            else {
                mergedList.add(obja);
                itrb.previous(); } }
        if (itra.hasNext() && !(itrb.hasNext())) {
            while (itra.hasNext()) {
                Object obja = itra.next();
                mergedList.add(obja); } }
        else if (!(itra.hasNext()) && itrb.hasNext()) {
            while (itrb.hasNext()) {
                Object objb = itrb.next();
                mergedList.add(objb); } }
    return mergedList; } 
}

public static LinkedList<Object> sort (LinkedList<Object> lst) {
    int size = lst.size();
    if (size == 0) return null;
    else if (size == 1) return lst;
    else {
        int midIndex = (size) / 2;
        return  merge(sort(new LinkedList<Object>(lst.subList(0, midIndex))), sort(new LinkedList<Object>(lst.subList(midIndex, size)))); }
}

public static LinkedList<Object> intersection (LinkedList<Object> lsta, LinkedList<Object> lstb) {
    if (lsta == null || lstb == null) return null;
    LinkedList<Object> aSorted = sort(lsta);
    LinkedList<Object> bSorted = sort(lstb);
    LinkedList<Object> intersection = new LinkedList<Object>();
    ListIterator<Object> itrA = aSorted.listIterator();
    ListIterator<Object> itrB = bSorted.listIterator();
    while (itrA.hasNext()) {
        Object objA = itrA.next();
        Object objB = itrB.next();
        if (((Comparable) objA).compareTo(objB) < 0)
            itrB.previous();
        else if (((Comparable) objA).compareTo(objB) > 0)
            itrA.previous();
        else intersection.add(objA); }
    return intersection;
}

public static LinkedList<Object> reverse (LinkedList<Object> lst) {
    LinkedList<Object> reversedList = new LinkedList<Object>();
    for (Object obj : lst)
        reversedList.addFirst(obj);
    return reversedList;
}

    // ****** your code ends here ******

    public static void main(String args[]) {
        LinkedList<Integer> lst = new LinkedList<Integer>();
        lst.add(new Integer(3));
        lst.add(new Integer(17));
        lst.add(new Integer(2));
        lst.add(new Integer(5));
        System.out.println("lst = " + lst);
        System.out.println("sum = " + sumlist(lst));

        ArrayList<Integer> lstb = new ArrayList<Integer>();
        lstb.add(new Integer(3));
        lstb.add(new Integer(17));
        lstb.add(new Integer(2));
        lstb.add(new Integer(5));
        System.out.println("lstb = " + lstb);
        System.out.println("sum = " + sumarrlist(lstb));

        final Predicate myp = new Predicate()
            { public boolean pred (Object x)
                { return ( (Integer) x > 3); }};

        LinkedList<Object> lstc = new LinkedList<Object>();
        lstc.add(new Integer(3));
        lstc.add(new Integer(17));
        lstc.add(new Integer(2));
        lstc.add(new Integer(5));
        System.out.println("lstc = " + lstc);
        System.out.println("subset = " + subset(myp, lstc));

        System.out.println("lstc     = " + lstc);
        System.out.println("dsubset  = " + dsubset(myp, lstc));
        System.out.println("now lstc = " + lstc);

        LinkedList<Object> lstd = new LinkedList<Object>();
        lstd.add(new Integer(3));
        lstd.add(new Integer(17));
        lstd.add(new Integer(2));
        lstd.add(new Integer(5));
        System.out.println("lstd = " + lstd);
        System.out.println("some = " + some(myp, lstd));

        final Functor myf = new Functor()
            { public Integer fn (Object x)
                { return new Integer( (Integer) x + 2); }};

        System.out.println("mapcar = " + mapcar(myf, lstd));

        LinkedList<Object> lste = new LinkedList<Object>();
        lste.add(new Integer(1));
        lste.add(new Integer(3));
        lste.add(new Integer(5));
        lste.add(new Integer(6));
        lste.add(new Integer(9));
        lste.add(new Integer(11));
        lste.add(new Integer(23));
        System.out.println("lste = " + lste);
        LinkedList<Object> lstf = new LinkedList<Object>();
        lstf.add(new Integer(2));
        lstf.add(new Integer(3));
        lstf.add(new Integer(6));
        lstf.add(new Integer(7));
        System.out.println("lstf = " + lstf);
        System.out.println("merge = " + merge(lste, lstf));

        lste = new LinkedList<Object>();
        lste.add(new Integer(1));
        lste.add(new Integer(3));
        lste.add(new Integer(5));
        lste.add(new Integer(7));
        System.out.println("lste = " + lste);
        lstf = new LinkedList<Object>();
        lstf.add(new Integer(2));
        lstf.add(new Integer(3));
        lstf.add(new Integer(6));
        lstf.add(new Integer(6));
        lstf.add(new Integer(7));
        lstf.add(new Integer(10));
        lstf.add(new Integer(12));
        lstf.add(new Integer(17));
        System.out.println("lstf = " + lstf);
        System.out.println("merge = " + merge(lste, lstf));

        LinkedList<Object> lstg = new LinkedList<Object>();
        lstg.add(new Integer(39));
        lstg.add(new Integer(84));
        lstg.add(new Integer(5));
        lstg.add(new Integer(59));
        lstg.add(new Integer(86));
        lstg.add(new Integer(17));
        System.out.println("lstg = " + lstg);

        System.out.println("intersection(lstd, lstg) = "
                           + intersection(lstd, lstg));
        System.out.println("reverse lste = " + reverse(lste));

        System.out.println("sort(lstg) = " + sort(lstg));

   }
}
