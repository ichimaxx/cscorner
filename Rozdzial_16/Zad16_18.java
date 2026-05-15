import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 18: (3) Create and fill an array of BerylliumSphere. Copy this array to a new
array and show that it’s a shallow copy.
*/
class BerylliumSphere7 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_18 {
    public static void main(String[] args) {
        int size = 6;
        BerylliumSphere7[] k = new BerylliumSphere7[size];
        BerylliumSphere7[] f = new BerylliumSphere7[size];
        for(int i = 0; i < size; i++)
            k[i] = new BerylliumSphere7();
        println("BerylliumSphere7[] k ORYGINALNA = " + Arrays.toString(k));
        System.arraycopy(k, 0, f, 0, k.length); //kopiowanie elementów z tablicy k do f w ilości k.length
        println("BerylliumSphere7[] f KOPIOWANA(System.arraycopy()) = " + Arrays.toString(f));
        // sprawdzanie tablic
        println("\nTESTY:");
        println("Czy k i f to ta sama tablica? (k==f): " + (k == f));
        println("Czy k[0] i f[0] to ten sam obiekt? (k[0] == f[0]): " + (k[0] == f[0]));
        println("Czy k[1] i f[1] to ten sam obiekt? (k[1] == f[1]): " + (k[1] == f[1]));
    }
}
/*
Pomimo tego, że obiekty są te same, jak wskazuje:
k[0] == f[0]
k[1] == f[1]
To tablica jest różna (k == f FALSE), bo zdefiniowane zostały dwie osobne.
System.arraycopy() skopiowało referencje do obiektów, a nie stworzyło nowe obiekty BerylliumSphere7().
Jest to tak zwana shallow copy, czyli płytka kopia.
*/
