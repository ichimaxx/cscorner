import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 21: (3) Try to sort an array of the objects in Exercise 18. Implement
Comparable to fix the problem. Now create a Comparator to sort the objects into reverse
order.
*/
class CompTypeComparator implements Comparator<BerylliumSphere8> {// interfejs potrzebny, aby użyć Arrays.sort(T[], Comparator).
    // Comparator pozwala przekazać własny sposób sortowania po Arrays.sort()
    @Override
    public int compare(BerylliumSphere8 o1, BerylliumSphere8 o2) { // nadpisane compare() na potrzeby sortowania tablicy za pomocą id obiektu
        return (o1.id > o2.id ? -1 : (o1.id == o2.id ? 0 : 1));
    }
}
class BerylliumSphere8 implements Comparable<BerylliumSphere8>{ // interfejs potrzebny, aby użyć Arrays.sort(T[])
    private static long counter;
    protected final long id = counter++;
    public String toString() { return "Sphere " + id; }
    @Override
    public int compareTo(BerylliumSphere8 rv) { // nadpisane compareTo() na potrzeby sortowania tablicy za pomocą id obiektu
        return (id < rv.id ? -1 : (id == rv.id ? 0 : 1));
    }
}
// idea Zadania 18 została przeniesiona
public class Zad16_21 {
    public static void main(String[] args) {
        int size = 6;
        BerylliumSphere8[] k = new BerylliumSphere8[size];
        for (int i = 0; i < size; i++)
            k[i] = new BerylliumSphere8();
        println("\nBez sortowania, tablica z obiektami wytworzona za pomocą pętli for:\n" + Arrays.toString(k));
        Arrays.sort(k, new CompTypeComparator());
        println("\nPo sortowaniu odwrotnym(reverse order) z użyciem Comparator:\n" + Arrays.toString(k));
        Arrays.sort(k);
        println("\nPo sortowaniu z użyciem Comparable:\n" + Arrays.toString(k));
    }
}
