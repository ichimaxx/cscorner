import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (1) Write a method that takes two int arguments, indicating the two sizes of
a 2-D array. The method should create and fill a 2-D array of BerylliumSphere according
to the size arguments.
*/
class BerylliumSphere2 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_6 {
        public BerylliumSphere2[][] tworcatablic(int a, int b) {
            BerylliumSphere2[][] g;
            g = new BerylliumSphere2[a][b];
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++)
                    g[i][j] = new BerylliumSphere2();
            }
            return g;
        }
        public void printeks(BerylliumSphere2[][] f) {
            println(Arrays.deepToString(f));
        }
        public static void main (String[] args) {
            Zad16_6 d = new Zad16_6();
            d.printeks(d.tworcatablic(4,5));
        }
}
