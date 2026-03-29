import static myutils.Skrocenie_Print.*;
/*
Exercise 3 : (1) Create and test a SixTuple generic.
*/
class TwoTuple<A,B> {
    public final A first;
    public final B second;
    public TwoTuple(A a, B b) { first = a; second = b; }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}
class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
    public final D fourth;
    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ")";
    }
}
class FiveTuple<A,B,C,D,E> extends FourTuple<A,B,C,D> {
    public final E fifth;
    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ")";
    }
}
class SixTuple<A,B,C,D,E,F> extends FiveTuple<A,B,C,D,E> {
    public final F sixth;
    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        sixth = f;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ", " + sixth + ")";
    }
}
public class Zad15_3 {
    static SixTuple<String, Integer, Integer, Integer, Long, Float> p() {
        return new SixTuple<String, Integer, Integer, Integer, Long, Float>("sikstuple", 5, 60, 70, 122332L, 0.2f); //metoda statyczna z wybranymi wartosciami
    }
    public static void main (String[] args) {
        println(p()); // wynik metody
    }
}

/*
* To jest Tuple, czyli klasa generyczna która może przyjąć różne typy za każdym razem jak ją będziemy definiować, w tym przypadku stworzona została klasa z 6 polami, kazde z tych pól może mieć inny typ, definiujemy to dopiero przy deklaracji obiektu, po zdefiniowaniu metoda p() automatycznie zwraca określone wartości
*
*
* */