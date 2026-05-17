import java.util.*;
import static myutils.Skrocenie_Print.*;
import myutils.*;
/*
Exercise 22: (2) Show that the results of performing a binarySearch( ) on an unsorted
array are unpredictable.
*/
public class Zad16_22 {
    public static void main(String[] args) {
        String[] sa = Generated.array(new String[30],
                new myutils.RandomGenerator.String(5));
        println(Arrays.toString(sa));
        int index = Arrays.binarySearch(sa, sa[10]);
        println("\n1");
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + sa[index]);
        } else {
            println("Nie można znaleźć elementu, sa[index] jest ujemny: sa[" + index + "]");
        }
        println("\n2");
        index = Arrays.binarySearch(sa, sa[21]);
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + sa[index]);
        } else {
            println("Nie można znaleźć elementu, sa[index] jest ujemny: sa[" + index + "]");
        }
        println("\n3");
        index = Arrays.binarySearch(sa, sa[7]);
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + sa[index]);
        } else {
            println("Nie można znaleźć elementu, sa[index] jest ujemny: sa[" + index + "]");
        }
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        index = Arrays.binarySearch(sa, sa[10], String.CASE_INSENSITIVE_ORDER);
        println("\n1 NA POSORTOWANEJ TABLICY");
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + sa[index]);
        } else {
            println("Nie można znaleźć elementu, sa[index] jest ujemny: sa[" + index + "]");
        }
        println("\n2 NA POSORTOWANEJ TABLICY");
        index = Arrays.binarySearch(sa, sa[21], String.CASE_INSENSITIVE_ORDER);
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + sa[index]);
        } else {
            println("Nie można znaleźć elementu, sa[index] jest ujemny: sa[" + index + "]");
        }
        println("\n3 NA POSORTOWANEJ TABLICY");
        index = Arrays.binarySearch(sa, sa[7], String.CASE_INSENSITIVE_ORDER);
        if(index >= 0) {
            println("Znaleziony element: " + index + "\n" + sa[index]);
        } else {
            println("Nie można znaleźć elementu, sa[index] jest ujemny: sa[" + index + "]");
        }
    }
}
/*
Jak można zauważyć na nieposortowanej tablicy binarySearch() czasem znajduje element,
a czasem zwraca wynik ujemny, mimo że szukany element pochodzi z tej samej tablicy.
Jest to nieprzewidywalne, ponieważ binarySearch() zakłada, że dane są już posortowane.

Algorytm działa trochę jak szukanie w książce telefonicznej lub słowniku.
Nie sprawdza wszystkich elementów po kolei, tylko zaczyna od środka tablicy i na podstawie porównania odrzuca połowę zakresu.

Jeżeli tablica nie jest posortowana, algorytm może odrzucić tę część tablicy, w której faktycznie znajdował się poszukiwany element.
*/