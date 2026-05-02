import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 2: (1) Write a method that takes an int argument and returns an array of that
size, filled with BerylliumSphere objects.
*/
class BerylliumSphere1 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_2 {
    public static BerylliumSphere1[] tablica(int n) {
        BerylliumSphere1[] agg = new BerylliumSphere1[n]; // tablica n wielkosci

        for (int i = 0; i < n; i++)
            agg[i] = new BerylliumSphere1(); // do każdego elementu tablicy przypisano nowy obiekt BerylliumSphere1()

        return agg; // zwrot wypełnionej tablicy
    }
    public static void main (String[] args){
        println(Arrays.toString(tablica(3))); // Arrays.toString() zamienia tablicę na tekst do wypisania
    }
}
