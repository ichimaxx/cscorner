/*
Exercise 15: (1) Verify the previous statement.
*/
class Amphibians {}
class Vehicles {}

class TwoTuples<A,B> {
    public final A first;
    public final B second;
    public TwoTuples(A a, B b) { first = a; second = b; }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
class ThreeTuples<A,B,C> extends TwoTuples<A,B> {
    public final C third;
    public ThreeTuples(A a, B b, C c) {
        super(a, b);
        third = c;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " + third +")";
    }
}
class FourTuples<A,B,C,D> extends ThreeTuples<A,B,C> {
    public final D fourth;
    public FourTuples(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ")";
    }
}
class FiveTuples<A,B,C,D,E>
        extends FourTuples<A,B,C,D> {
    public final E fifth;
    public FiveTuples(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ")";
    }
}
class Tuples {
    public static <A,B> TwoTuples<A,B> tuples(A a, B b) {
        return new TwoTuples<A,B>(a, b);
    }
    public static <A,B,C> ThreeTuples<A,B,C>
    tuples(A a, B b, C c) {
        return new ThreeTuples<A,B,C>(a, b, c);
    }
    public static <A,B,C,D> FourTuples<A,B,C,D>
    tuples(A a, B b, C c, D d) {
        return new FourTuples<A,B,C,D>(a, b, c, d);
    }
    public static <A,B,C,D,E>
    FiveTuples<A,B,C,D,E> tuples(A a, B b, C c, D d, E e) {
        return new FiveTuples<A,B,C,D,E>(a, b, c, d, e);
    }
}
public class Zad15_15 extends Tuples {
    static TwoTuples<String,Integer> f() {
        return tuples("hi", 47);
    }
    static TwoTuples f2() { return tuples("hi", 47); }
    static ThreeTuples<Amphibians,String,Integer> g() {
        return tuples(new Amphibians(), "hi", 47);
    }
    static
    FourTuples<Vehicles,Amphibians,String,Integer> h() {
        return tuples(new Vehicles(), new Amphibians(), "hi", 47);
    }
    static
    FiveTuples<Vehicles,Amphibians,String,Integer,Double> k() {
        return tuples(new Vehicles(), new Amphibians(),
                "hi", 47, 11.1);
    }
    public static void main(String[] args) {
        TwoTuples<String,Integer> ttsi = f();
        System.out.println(f2());
        TwoTuples<String,Integer> tfp = f2(); // dodanie wyniku w sposób sparametryzowany tak jak f()
        System.out.println(ttsi);
        System.out.println(tfp);
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}

/*
Kompilacja bez parametryzacji f2:
PS C:\Users\ichim\Desktop\cscorner\Rozdzial_15> javac Zad15_15.java
PS C:\Users\ichim\Desktop\cscorner\Rozdzial_15> java Zad15_15
(hi, 47)
(hi, 47)
(Amphibian@65ab7765, hi, 47)
(Vehicles@448139f0, Amphibian@7cca494b, hi, 47)
(Vehicles@27d6c5e0, Amphibian@4f3f5b24, hi, 47, 11.1)

Statement:
"Notice that f( ) returns a parameterized TwoTuple object, while f2( ) returns an
unparameterized TwoTuple object. The compiler doesn’t warn about f2( ) in this case
because the return value is not being used in a parameterized fashion; in a sense, it is being
"upcast" to an unparameterized TwoTuple. However, if you were to try to capture the result
of f2( ) into a parameterized TwoTuple, the compiler would issue a warning. "

Odpowiedź:
Jak widać przy kompilacji nie było żadnego ostrzeżenia, że metoda f2 jest bez parametrów i ma odpowiedzi wypisane w return.
Natomiast po przypisaniu wyniku do TwoTuples<String,Integer> czyli do zparametryzowanego obiektu, dostajemy warning:

PS C:\Users\ichim\Desktop\cscorner\Rozdzial_15> javac Zad15_15.java -Xlint:unchecked
Zad15_15.java:85: warning: [unchecked] unchecked conversion
        TwoTuples<String,Integer> tfp = f2(); // dodanie wyniku w sposób sparametryzowany tak jak f()
                                          ^
  required: TwoTuples<String,Integer>
  found:    TwoTuples
1 warning

Jest tak dlatego bo f2() zwraca "raw type" TwoTuples. Gdy przypisujemy wynik f2() do TwoTuples<String,Integer> powstaje tzw. "unchecked conversion" z raw type do typu sparametryzowanego.
Kod się kompiluje ale kompilator ostrzega bo Java dopuszcza raw types dla zgodności wstecznej i oznacza to jako operację niebezpieczną.
*/