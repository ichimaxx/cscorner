import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (1) Demonstrate that multidimensional arrays of nonprimitive types are
automatically initialized to null.
*/
public class Zad16_5 {
    public Double[][][] tworcatablic(int a, int b, int e) {
        Double[][][] g;
        g = new Double[a][b][e];

        return g;
    }
    public void printeks(Double[][][] f) {
        println(Arrays.deepToString(f));
    }
    public static void main (String[] args) {
        Zad16_5 d = new Zad16_5();
        println("TABLICA 1:");
        d.printeks(d.tworcatablic(4,5,3));
        println("\nTABLICA 2:");
        d.printeks(d.tworcatablic(1,3,2));
        println("\nTABLICA 3:");
        d.printeks(d.tworcatablic(6,5,1));
        println("\nTABLICA 4:");
        d.printeks(d.tworcatablic(7,4,2));
        println("\nTABLICA 5:");
        d.printeks(d.tworcatablic(3,5,1));
    }
}
/*
Jeśli do tablicy używamy typu obiektowego (Double[][][]), czyli nonprimitive type java automatycznie ustawia elementy na null, ponieważ nie przypisujemy do nich żadnych obiektów (wartość domyślnie jest ustawiony na null).
W przypadku użycia typu prymitywnego (double[][][]) java automatycznie przypisze do elementów wartości 0.0 (wartość domyślnie jest ustawiona na 0.0)
*/