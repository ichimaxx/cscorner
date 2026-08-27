/*
Exercise 10: (2) Create a class with a finalize( ) method that prints a message. In
main( ), create an object of your class. Explain the behavior of your program.
*/
public class Zad5_10 {
    protected void finalize() {
        System.out.println("finalize() called");
        // super.finalize(); // wywołałbyś w realnym kodzie, gdy już poznasz wyjątki
    }
    public static void main(String[] args) {
        new Zad5_10();   // obiekt bez referencji
        System.gc();              // prośba o uruchomienie GC
        System.runFinalization(); // prośba o uruchomienie finalizacji
    }
}