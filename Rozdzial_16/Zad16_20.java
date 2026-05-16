import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 20: (4) Demonstrate deepEquals( ) for multidimensional arrays.
*/
public class Zad16_20 {
    private static int z;
    private final int liczenie;
    private static boolean status = false;
    public Zad16_20(int start) {
        if(!status) { // dodana flaga, ponieważ, aby zacząć od konkretnej liczby z konstruktora, trzeba stworzyć warunek, który zweryfikuje, czy 'z' już była wcześniej ustawiona (aby uniknąć powtarzania tej czynności).
            // Jeśli flaga nie była wcześniej ustawiana, liczba 'z' (początkowa) zostaje ustanowiona przez argument konstruktora(int start).
            // Po ustawieniu jednorazowo flagi(if(!status)) konstruktor od razu przechodzi do inkrementacji 'z' (wartości początkowej)
            // finalne pole liczenie zapamiętuje aktualną wartość 'z', a statyczny licznik 'z' jest podwyższany dla następnego obiektu.
            z = start;
            status = true;
        }
        liczenie = z++;
    }
    public String toString() { return "WARTOSC " + liczenie; }
    public static void main(String[] args){
        int rozmiar = 5;
        int wartosc = 3;
        Zad16_20[][] ow;
        ow = new Zad16_20[rozmiar][rozmiar];
        for (int i = 0; i < rozmiar; i++){
            for (int j = 0; j < rozmiar; j++)
                ow[i][j] = new Zad16_20(wartosc);
        }
        println("\nArray ow[][]:\n" + Arrays.deepToString(ow));
        Zad16_20[][] ok;
        ok = new Zad16_20[rozmiar][rozmiar];
        for (int i = 0; i < rozmiar; i++){
            for (int j = 0; j < rozmiar; j++)
                ok[i][j] = ow[i][j];
        }
        println("\nArray ok[][] przyrównana do array ow[][] (ok[i][j] = ow[i][j]):\n" + Arrays.deepToString(ok));
        println("\nCzy array ow[][] i array ok[][] są equals? : " + Arrays.equals(ow, ok)); // pokaże false, ponieważ Arrays.equals() porównuje tylko pierwszą warstwę tablicy, która jest dwuwymiarowa. A ow[0] i ok[0] to różne tablice.
        println("Czy array ow[][] i array ok[][] są deepEquals? : " + Arrays.deepEquals(ow, ok)); //Arrays.deepEquals() wchodzi głębiej i sprawdza elementy w zagnieżdżonych tablicach, nie skupiając się na zewnętrznej.
        for (int i = 0; i < rozmiar; i++){
            for (int j = 0; j < rozmiar; j++)
                ok[i][j] = new Zad16_20(wartosc);
        }
        println("\nArray ok[][] nowe wartości (ok[i][j] = new Zad16_20(wartosc))\n" + Arrays.deepToString(ok));
        println("\nCzy array ow[][] i array ok[][] są equals? : " + Arrays.equals(ow, ok));
        println("Czy array ow[][] i array ok[][] są teraz deepEquals? : " + Arrays.deepEquals(ow, ok)); // Arrays.deepEquals() wychodzi false, ponieważ tworzone są nowe obiekty, a klasa Zad16_20 nie ma własnej metody equals() więc porównywane są referencje tak jak w poprzednim zadaniu(Zad16_19).
    }
}

