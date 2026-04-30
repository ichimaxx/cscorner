import java.math.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 42: (5) Create two separate classes, with nothing in common. Each class should
hold a value, and at least have methods that produce that value and perform a modification
upon that value. Modify Functional.java so that it performs functional operations on
collections of your classes (these operations do not have to be arithmetic as they are in
Functional.java(Zad15_42)).
*/
// Different types of function objects:
interface Combiner<T> { T combine(T x, T y); }
interface UnaryFunction<R,T> { R function(T x); }
interface Collector<T> extends UnaryFunction<T,T> {
    T result(); // Extract result of collecting parameter
}
interface UnaryPredicate<T> { boolean test(T x); }
class Klasa1 {
    private String value;
    Klasa1(String value) {
        this.value = value;
    }
    String getValue(){
        return value;
    }
    void modify(String x){
        value += x; // to inaczej:  value = value + x
    }
    public String toString() {
        return value;
    }
}

class Klasa2 {
    private Integer value;
    Klasa2(Integer value) {
        this.value = value;
    }
    Integer getValue(){
        return value;
    }
    void modify(Integer x){
        value *= x; // to inaczej: value = value * x
    }
    public String toString() {
        return String.valueOf(value); // konwersja na string
    }
}
public class Zad15_42 {
    // Calls the Combiner object on each element to combine
    // it with a running result, which is finally returned:
    public static <T> T
    reduce(Iterable<T> seq, Combiner<T> combiner) {
        Iterator<T> it = seq.iterator();
        if(it.hasNext()) {
            T result = it.next();
            while(it.hasNext())
                result = combiner.combine(result, it.next());
            return result;
        }
        // If seq is the empty list:
        return null; // Or throw exception
    }
    // Take a function object and call it on each object in
    // the list, ignoring the return value. The function
    // object may act as a collecting parameter, so it is
    // returned at the end.
    public static <T> Collector<T>
    forEach(Iterable<T> seq, Collector<T> func) {
        for(T t : seq)
            func.function(t);
        return func;
    }
    // Creates a list of results by calling a
    // function object for each object in the list:
    public static <R,T> List<R>
    transform(Iterable<T> seq, UnaryFunction<R,T> func) {
        List<R> result = new ArrayList<R>();
        for(T t : seq)
            result.add(func.function(t));
        return result;
    }
    // Applies a unary predicate to each item in a sequence,
    // and returns a list of items that produced "true":
    public static <T> List<T>
    filter(Iterable<T> seq, UnaryPredicate<T> pred) {
        List<T> result = new ArrayList<T>();
        for(T t : seq)
            if(pred.test(t))
                result.add(t);
        return result;
    }
    // To use the above generic methods, we need to create
    // function objects to adapt to our particular needs:
    static class IntegerAdder implements Combiner<Integer> {
        public Integer combine(Integer x, Integer y) {
            return x + y;
        }
    }
    static class
    IntegerSubtracter implements Combiner<Integer> {
        public Integer combine(Integer x, Integer y) {
            return x - y;
        }
    }
    static class
    BigDecimalAdder implements Combiner<BigDecimal> {
        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }
    static class
    BigIntegerAdder implements Combiner<BigInteger> {
        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }
    static class
    AtomicLongAdder implements Combiner<AtomicLong> {
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            // Not clear whether this is meaningful:
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }
    // We can even make a UnaryFunction with an "ulp"
    // (Units in the last place):
    static class BigDecimalUlp
            implements UnaryFunction<BigDecimal,BigDecimal> {
        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }
    static class GreaterThan<T extends Comparable<T>>
            implements UnaryPredicate<T> {
        private T bound;
        public GreaterThan(T bound) { this.bound = bound; }
        public boolean test(T x) {
            return x.compareTo(bound) > 0;
        }
    }
    static class ModifierKlasa1 implements UnaryFunction<Klasa1,Klasa1> { // klasa wewnętrzna stworzona do umożliwienia operacji na Klasa1
        public Klasa1 function(Klasa1 x) {
            x.modify("WEEEE"); // dodaje String WEEEE do wartości podanych w main
            return x;
        }
    }
    static class ModifierKlasa2 implements UnaryFunction<Klasa2,Klasa2> { // klasa wewnętrzna stworzona do umożliwienia operacji na Klasa2
        public Klasa2 function(Klasa2 x) {
            x.modify(5); // mnoży wartość przechowywaną w Klasa2 przez 5
            return x;
        }
    }
    static class MultiplyingIntegerCollector
            implements Collector<Integer> {
        private Integer val = 1;
        public Integer function(Integer x) {
            val *= x;
            return val;
        }
        public Integer result() { return val; }
    }
    public static void main(String[] args) {
        // Generics, varargs & boxing working together:
        List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer result = reduce(li, new IntegerAdder());
        println(result);
        result = reduce(li, new IntegerSubtracter());
        println(result);
        println(filter(li, new GreaterThan<Integer>(4)));
        println(forEach(li,
                new MultiplyingIntegerCollector()).result());
        println(forEach(filter(li, new GreaterThan<Integer>(4)),
                new MultiplyingIntegerCollector()).result());
        MathContext mc = new MathContext(7);
        List<BigDecimal> lbd = Arrays.asList(
                new BigDecimal(1.1, mc), new BigDecimal(2.2, mc),
                new BigDecimal(3.3, mc), new BigDecimal(4.4, mc));
        BigDecimal rbd = reduce(lbd, new BigDecimalAdder());
        println(rbd);
        println(filter(lbd,
                new GreaterThan<BigDecimal>(new BigDecimal(3))));
        // Use the prime-generation facility of BigInteger:
        List<BigInteger> lbi = new ArrayList<BigInteger>();
        BigInteger bi = BigInteger.valueOf(11);
        for(int i = 0; i < 11; i++) {
            lbi.add(bi);
            bi = bi.nextProbablePrime();
        }
        println(lbi);
        BigInteger rbi = reduce(lbi, new BigIntegerAdder());
        println(rbi);
        // The sum of this list of primes is also prime:
        println(rbi.isProbablePrime(5));
        List<AtomicLong> lal = Arrays.asList(
                new AtomicLong(11), new AtomicLong(47),
                new AtomicLong(74), new AtomicLong(133));
        AtomicLong ral = reduce(lal, new AtomicLongAdder());
        println(ral);
        println(transform(lbd,new BigDecimalUlp()));

        // ROZWIAZANIE DO Zad15_42:
        List<Klasa1> lis = Arrays.asList(new Klasa1("siema"), new Klasa1("hi"));
        List<Klasa1> oki = transform(lis, new ModifierKlasa1()); // transform stosuje ModifierKlasa1 do każdego obiektu Klasa1(Lista lis)
        println(oki);

        List<Klasa2> lis1 = Arrays.asList(new Klasa2(7674), new Klasa2(4545)); // lista z nowymi obiektami
        List<Klasa2> oki1 = transform(lis1, new ModifierKlasa2()); // transform tworzy listę wyników po zastosowaniu funkcji do każdego obiektu Klasa2(Lista lis1)
        println(oki1);
    }
}
/*
W zadaniu dodano dwie niezależne klasy Klasa1 i Klasa2.
Nie mają wspólnych interfejsów ale obie trzymają wartości, mogą je zwrócić(getValue()) i mają metodę która je modyfikuje(modify()).

Functional.java(Zad15_42) został użyty do operacji na tych klasach(Klasa1, Klasa2). Użyto transform(), który stosuje ModifierKlasa1 i ModifierKlasa2 do każdego obiektu z listy (lis, lis1)
 */