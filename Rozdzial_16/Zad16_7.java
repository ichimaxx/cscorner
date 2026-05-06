import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (1) Repeat the previous exercise for a 3-D array.
*/
class BerylliumSphere3 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_7 {
    public BerylliumSphere3[][][] tworcatablic(int a, int b, int c) {
        BerylliumSphere3[][][] g;
        g = new BerylliumSphere3[a][b][c]; // tworzy tablice referencji, ale z elementami null
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                for (int k = 0; k < c; k++)
                    g[i][j][k] = new BerylliumSphere3(); // ta potrójna pętla wypełnia tablice po kolei obiektami
            }
        }
        return g;
    }
    public void printeks(BerylliumSphere3[][][] f) {
        println(Arrays.deepToString(f));
    }
    public static void main (String[] args) {
        Zad16_7 d = new Zad16_7();
        d.printeks(d.tworcatablic(2,5,4)); // a to ilość tablic, b to ilość wierszy, c to ilość elementów w wierszu
    }
}
