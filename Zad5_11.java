import java.util.*;
import static myutils.Skrocenie_Print.print;
class Zad5_11 {
    protected void finalize() {
        print("finalize() called");
    }
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1_000_000; i++)   // 1 dużo obiektów
            new Zad5_11();
        System.gc();                         // 2 prośba o GC
        System.runFinalization();            // 3 prośba o finalizację
        Thread.sleep(200);                   // 4 daj czas wątkowi finalizera
    }
}


//               ODPALAJ Z FLAGA TYPU:            java -Xmx10m Zad5_11         czyli limit 10mb