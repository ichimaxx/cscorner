import java.util.*;
import static myutils.Skrocenie_Print.print;

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