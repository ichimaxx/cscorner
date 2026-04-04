/*
Exercise 16: (2) Add a SixTuple to Tuple.java, and test it in TupleTest2.java.
*/
class Amphibians2 {}
class Vehicles2 {}

class TwoTuples2<A,B> {
    public final A first;
    public final B second;
    public TwoTuples2(A a, B b) { first = a; second = b; }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
class ThreeTuples2<A,B,C> extends TwoTuples2<A,B> {
    public final C third;
    public ThreeTuples2(A a, B b, C c) {
        super(a, b);
        third = c;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " + third +")";
    }
}
class FourTuples2<A,B,C,D> extends ThreeTuples2<A,B,C> {
    public final D fourth;
    public FourTuples2(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ")";
    }
}
class FiveTuples2<A,B,C,D,E> extends FourTuples2<A,B,C,D> {
    public final E fifth;
    public FiveTuples2(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ")";
    }
}
class SixTuples2<A,B,C,D,E,F> extends FiveTuples2<A,B,C,D,E> {
    public final F sixth;
    public SixTuples2(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        sixth = f;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ", " + sixth + ")";
    }
}
class Tuples2 {
    public static <A,B> TwoTuples2<A,B> tuples2(A a, B b) {
        return new TwoTuples2<A,B>(a, b);
    }
    public static <A,B,C> ThreeTuples2<A,B,C>
    tuples2(A a, B b, C c) {
        return new ThreeTuples2<A,B,C>(a, b, c);
    }
    public static <A,B,C,D> FourTuples2<A,B,C,D>
    tuples2(A a, B b, C c, D d) {
        return new FourTuples2<A,B,C,D>(a, b, c, d);
    }
    public static <A,B,C,D,E>
    FiveTuples2<A,B,C,D,E> tuples2(A a, B b, C c, D d, E e) {
        return new FiveTuples2<A,B,C,D,E>(a, b, c, d, e);
    }
    public static <A,B,C,D,E,F>
    SixTuples2<A,B,C,D,E,F> tuples2(A a, B b, C c, D d, E e, F f ){
        return new SixTuples2<A,B,C,D,E,F>(a, b, c, d, e, f);
    }
}
public class Zad15_16 extends Tuples2 {
    static TwoTuples2<String,Integer> f() {
        return tuples2("hi", 47);
    }
    static TwoTuples2 f2() { return tuples2("hi", 47); }
    static ThreeTuples2<Amphibians2,String,Integer> ui() {
        return tuples2(new Amphibians2(), "hi", 47);
    }
    static
    FourTuples2<Vehicles2,Amphibians2,String,Integer> h() {
        return tuples2(new Vehicles2(), new Amphibians2(), "hi", 47);
    }
    static
    FiveTuples2<Vehicles2,Amphibians2,String,Integer,Double> k() {
        return tuples2(new Vehicles2(), new Amphibians2(),
                "hi", 47, 11.1);
    }
    static
    SixTuples2<Vehicles2,Amphibians2,String,Integer,Double,Float> w() {
        return tuples2(new Vehicles2(), new Amphibians2(),
                "hi", 47, 11.1, 3.0f);
    }
    public static void main(String[] args) {
        TwoTuples2<String,Integer> ttsi = f();
        System.out.println(f2());
        System.out.println(ttsi);
        System.out.println(ui());
        System.out.println(h());
        System.out.println(k());
        System.out.println(w()); // test SixTuple dodanego w zadaniu
    }
}