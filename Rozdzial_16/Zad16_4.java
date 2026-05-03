import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (2) Repeat the previous exercise for a three-dimensional array.
*/
public class Zad16_4 {
    public double[][][] tworcatablic(int a, int b, int e, int c, int d) {
        double[][][] g;
        g = new double[a][b][e];
        for (int i = 0; i < a; i++) { //pętla która umożliwia przechodzenie po każdej TABLICY array trzywymiarowej (zewnętrzne tablice) -- dodatkowa pętla w porównaniu do tablic dwuwymiarowych
            for (int j = 0; j < b; j++) { //pętla która umożliwia przechodzenie po każdym WIERSZU tabeli trzywymiarowej (zewnętrzne tablice)
                for (int k = 0; k < e; k++) // pętla, która umożliwia przechodzenie po każdym ELEMENCIE z danych WIERSZY j. Uwarunkowana wielkością tablic wewnętrznych z pomoca parametru e (wewnętrzne tablice). Musi być uruchomiona wewnątrz pętli przechodzącej po zewnętrznych tablicach aby zadziałało.
                    g[i][j][k] = (double) ThreadLocalRandom.current().nextInt(c, d + 1); // losuje int z zakresu c-d i rzutuje go na double dzięki czemu wartości są 1.0, 3.0 itd
            }
        }
        return g;
    }
    public void printeks(double[][][] f) {
        println(Arrays.deepToString(f));
    }
    public static void main (String[] args) {
        Zad16_4 d = new Zad16_4();
        println("TABLICA 1:");
        d.printeks(d.tworcatablic(4,5,6,1,12));
        println("\nTABLICA 2:");
        d.printeks(d.tworcatablic(1,3,3,1,12));
        println("\nTABLICA 3:");
        d.printeks(d.tworcatablic(6,5,6,1,12));
        println("\nTABLICA 4:");
        d.printeks(d.tworcatablic(7,4,2,1,12));
        println("\nTABLICA 5:");
        d.printeks(d.tworcatablic(3,4,1,1,12));

    }
}

/*
podsumowując:
g[i][j][k] = (double) ThreadLocalRandom.current().nextInt(c, d + 1);

i wybiera "blok/tablicę"
j wybiera "wiersz" w danym bloku
k wybiera "element/kolumnę" w danym wierszu
c wybiera początek zakresu losowanej liczby
d wybiera koniec zakresu losowanej liczby

g = new double[a][b][e];

a - wybiera ilość bloków/tablicc
b - wybiera ilość wierszy w tablicy
e - wybiera ilość elementów w wierszu
*/