import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 28: (4) Modify net/mindview/util/Tuple.java to make it a general-purpose
class by adding hashCode( ), equals( ), and implementing Comparable for each type of
Tuple.
*/

/*
equals() - sprawdza, czy dwa Tuple mają takie same wartości pól.
hashCode() - pozwala używać Tuple jako kluczy w HashMap/elementów w HashSet.
compareTo() - pozwala porównywać/sortować Tuple według pól po kolei.
*/
class TwoTuple_1<A extends Comparable<A>,B extends Comparable<B>> implements Comparable<TwoTuple_1<A,B>>{
    //first i second są public final bo Tuple jest prostym obiektem do trzymania danych
    public final A first;
    public final B second;
    public TwoTuple_1(A a, B b) {
        first = a;
        second = b;
    }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
    public int hashCode() {
        //hashCode() używa tych samych pól co equals() first i second
        //dzięki temu TwoTuple może poprawnie działać jako klucz w hashMap
        int result = 17;
        result = 37 * result + first.hashCode();
        result = 37 * result + second.hashCode();
        return result;
    }
    public boolean equals(Object o) {
        //getClass() sprawia,że TwoTuple porównuje się tylko z TwoTuple,
        //a nie np z threeTuple mimo że dziedziczy po TwoTuple
        return o != null && getClass() == o.getClass()&&
            first.equals(((TwoTuple_1)o).first) &&
                second.equals(((TwoTuple_1)o).second);
    }
    @Override
    public int compareTo(TwoTuple_1<A,B> a) {
        //porównanie argumentów pomiędzy sobą, jeżeli first są równe to sprawdza second
        int firstComp = first.compareTo(a.first);
           if(firstComp != 0)
               return firstComp;
        return second.compareTo(a.second);
    }
}
class ThreeTuple_1<A extends Comparable<A>,B extends Comparable<B>,C extends Comparable<C>> extends TwoTuple_1<A,B>{
    public final C third;
    public int hashCode() {
        //third ma trzy pola więc hashCode uwzględnia first, second i third
        int result = 17;
        result = 37 * result + first.hashCode();
        result = 37 * result + second.hashCode();
        result = 37 * result + third.hashCode();
        return result;
    }
    public boolean equals(Object o) {
        //porównuje tylko z ta samą klasą, czyli z ThreeTuple
        return o != null && getClass() == o.getClass()&&
                first.equals(((ThreeTuple_1)o).first) &&
                second.equals(((ThreeTuple_1)o).second)&&
                third.equals(((ThreeTuple_1)o).third);
    }
    public ThreeTuple_1(A a, B b, C c) {
        super(a, b); // first i second ustawia konstruktor TwoTuple
        third = c; // dodano third tuple
    }
    public int compareTo(ThreeTuple_1<A,B,C> a) {
        int firstComp = first.compareTo(a.first);
        int secComp = second.compareTo(a.second);
        if(firstComp != 0)
            return firstComp;
        if(secComp != 0)
            return secComp;
        return third.compareTo(a.third);
    }
    public String toString() {
        return "(" + first + ", " + second + ", " + third +")";
    }
}
class FourTuple_1<A extends Comparable<A>,B extends Comparable<B>,C extends Comparable<C>,D extends Comparable<D>> extends ThreeTuple_1<A,B,C> {
    public final D fourth;
    public FourTuple_1(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }
    public int hashCode() {
        int result = 17;
        result = 37 * result + first.hashCode();
        result = 37 * result + second.hashCode();
        result = 37 * result + third.hashCode();
        result = 37 * result + fourth.hashCode();
        return result;
    }
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() &&
                first.equals(((FourTuple_1)o).first) &&
                second.equals(((FourTuple_1)o).second) &&
                third.equals(((FourTuple_1)o).third) &&
                fourth.equals(((FourTuple_1)o).fourth);
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ")";
    }
    public int compareTo(FourTuple_1<A,B,C,D> a) {
        int firstComp = first.compareTo(a.first);
        int secComp = second.compareTo(a.second);
        int thiComp = third.compareTo(a.third);
        if(firstComp != 0)
            return firstComp;
        if(secComp != 0)
            return secComp;
        if(thiComp != 0)
            return thiComp;
        return fourth.compareTo(a.fourth);
    }
}
class FiveTuple_1<A extends Comparable<A>,B extends Comparable<B>,C extends Comparable<C>,D extends Comparable<D>,E extends Comparable<E>> extends FourTuple_1<A,B,C,D> {
    public final E fifth;
    public FiveTuple_1(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }
    public int hashCode() {
        int result = 17;
        result = 37 * result + first.hashCode();
        result = 37 * result + second.hashCode();
        result = 37 * result + third.hashCode();
        result = 37 * result + fourth.hashCode();
        result = 37 * result + fifth.hashCode();
        return result;
    }
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() &&
                first.equals(((FiveTuple_1)o).first) &&
                second.equals(((FiveTuple_1)o).second) &&
                third.equals(((FiveTuple_1)o).third) &&
                fourth.equals(((FiveTuple_1)o).fourth) &&
                fifth.equals(((FiveTuple_1)o).fifth);
    }
    public int compareTo(FiveTuple_1<A,B,C,D,E> a) {
        int firstComp = first.compareTo(a.first);
        int secComp = second.compareTo(a.second);
        int thiComp = third.compareTo(a.third);
        int fourComp = fourth.compareTo(a.fourth);
        if(firstComp != 0)
            return firstComp;
        if(secComp != 0)
            return secComp;
        if(thiComp != 0)
            return thiComp;
        if(fourComp != 0)
            return fourComp;
        return fifth.compareTo(a.fifth);
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ")";
    }

}
public class Zad17_28 {

    public static <A extends Comparable<A>,B extends Comparable<B>> TwoTuple_1<A,B> tuple(A a, B b) {
        return new TwoTuple_1<A,B>(a, b);
    }
    public static <A extends Comparable<A>,B extends Comparable<B>,C extends Comparable<C>> ThreeTuple_1<A,B,C> tuple(A a, B b, C c) {
        return new ThreeTuple_1<A,B,C>(a, b, c);
    }
    public static <A extends Comparable<A>,B extends Comparable<B>,C extends Comparable<C>,D extends Comparable<D>> FourTuple_1<A,B,C,D> tuple(A a, B b, C c, D d) {
        return new FourTuple_1<A,B,C,D>(a, b, c, d);
    }
    public static <A extends Comparable<A>,B extends Comparable<B>,C extends Comparable<C>,D extends Comparable<D>,E extends Comparable<E>> FiveTuple_1<A,B,C,D,E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple_1<A,B,C,D,E>(a, b, c, d, e);
    }


    public static void main(String[] args) {
        Random rand = new Random();
        Map<TwoTuple_1<Integer,Integer>,Integer> map =
                new HashMap<TwoTuple_1<Integer,Integer>,Integer>();
        Map<TwoTuple_1<Integer,Integer>,Integer> map2 =
                new HashMap<TwoTuple_1<Integer,Integer>,Integer>();
        TwoTuple_1<Integer,Integer>[] k = new TwoTuple_1[5];
        for(int i = 0; i < k.length; i++) {
            k[i] = new TwoTuple_1<Integer,Integer>(rand.nextInt(10),rand.nextInt(10));
            map.put(k[i], i);
        }
        println("\n5 losowych Tuple dodane do mapy: " + map);
        for(int i = 0; i < k.length; i++) {
            k[i] = new TwoTuple_1<Integer,Integer>(2,3);
            map2.put(k[i], i);
        }
        //map 2 pokazuje że takie same tuple nadpisują wartość, czyli equals i hashCode działa
        println("\n5 razy dodany ten sam tuple: " + map2);
    }
}