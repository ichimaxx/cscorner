import static myutils.Skrocenie_Print.println;
/*
Exercise 2: (1) Create a holder class that holds three objects of the same type, along with
the methods to store and fetch those objects and a constructor to initialize all three.
*/
public class Zad15_2<T> {
    private T a;
    private T b;
    private T c;

    public Zad15_2(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;}
    public void setA(T a) { this.a = a; } // setA B I C zrobione, aby mozna było zmieniac kazdy pole jeśli potrzeba, nie uzywane w cwiczeniu
    public void setB(T b) { this.b = b; }
    public void setC(T c) { this.c = c; }
    public T getA() { return a; }
    public T getB() { return b; }
    public T getC() { return c; }
    public static void main(String[] args) {
        Zad15_2<String> h3 = new Zad15_2<String>("pierwszy arg", "drugi arg", "trzeci arg");
        String a = h3.getA();
        String b = h3.getB();
        String c = h3.getC();
        println(a);
        println(b);
        println(c);
    }
}
