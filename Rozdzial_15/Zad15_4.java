import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (3) "Generify" innerclasses/Sequence.java
*/

interface Selector2<T> { //generic interface
    boolean end();
    T current();
    void next();
}

public class Zad15_4<A> {
    private Object[] items; // zostawione Object, bo jeszcze nie było o generic arrays
    private int next = 0;
    public Zad15_4(int size) { items = new Object[size]; }
    public void add(A x) { // ugenerycznione add
        if(next < items.length)
            items[next++] = x;
    }
    private class SequenceSelector2 implements Selector2<A> {
        private int i = 0;
        public boolean end() { return i == items.length; }
        public A current() { return (A) items[i]; } // items przechowuje Object, ale na zewnatrz trzeba zwrocic typ A wiec potrzebne jest rzutowanie, czyli (A) items[i]
        public void next() { if(i < items.length) i++; }
    }
    public Selector2<A> selector2() {
        return new SequenceSelector2();
    }
    public static void main(String[] args) {
        Zad15_4<String> sequence = new Zad15_4<String>(10); // wybieramy typ String pomimo tego ze size jest intem poniewaz na koncu do tablicy sequence sa insertowane Strings
        for(int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i)); // to powoduje zamiane int na String wtedy będzie pasować do deklaracji typu <String>
        Selector2<String> selector = sequence.selector2();
        while(!selector.end()) {
            print(selector.current() + " ");
            selector.next();
        }
    }
}
