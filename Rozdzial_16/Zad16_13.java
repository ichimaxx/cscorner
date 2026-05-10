import myutils.*;
import java.lang.reflect.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;

/*
Exercise 13: (2) Fill a String using CountingGenerator.Character.
*/
public class Zad16_13 {
    public static void main(String[] args) {
        int size = 10;
        try {
            Character[] f = new Character[size];
            myutils.CountingGenerator.Character g = new myutils.CountingGenerator.Character(); //wywołanie CountingGenerator.Character
            for (int i = 0; i < size; i++)
                f[i] = g.next(); // wywołanie metody next() z CountingGenerator.Character tyle razy ile miejsc ma tablica Character[]
            char[] a3 = ConvertTo.primitive(f);
            // Tworzenie tablicy char[](array primitywów)
            // Za pomocą ConvertTo.primitive() kopiowanie tablicy Character[] wartość po wartości przy użyciu autounboxingu i przerzucenie ich do nowej tablicy char[]
            String z = new String(a3); // zamiana tablicy characters na pojedynczy String
            println(z);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}

