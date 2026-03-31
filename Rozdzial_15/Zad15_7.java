import java.util.*;
import net.mindview.util.*;

/*
Exercise 7: (2) Use composition instead of inheritance to adapt Fibonacci to make it
Iterable.
*/

class Fibonaccis implements Generator<Integer> {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    public static void main(String[] args) {
        Fibonaccis gen = new Fibonaccis();
        for(int i = 0; i < 18; i++)
            System.out.print(gen.next() + " ");
    }
}

public class Zad15_7 implements Iterable<Integer> {
    private Fibonaccis fibon = new Fibonaccis(); // <-- composition zamiast inheritance czyli extends Fibonaccis
    private int n;
    public Zad15_7(int count) { n = count; }
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() { return n > 0; }
            public Integer next() {
                n--;
                return Zad15_7.this.fibon.next(); // wolanie metody przez composition, w tym momencie trzeba podac dodatkowo przed metodą obiekt z któego bierzemy metode czyli fibon.next();
            }
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args) {
        for(int i : new Zad15_7(18))
            System.out.print(i + " ");
    }
}

// zamniana inheritance na composition