import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 10: (2) Modify ArrayOfGenerics.Java to use containers instead of arrays.
Show that you can eliminate the compile-time warnings.
*/
class BerylliumSphere5 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_10 {
    public static void main(String[] args) {
        List<List<String>> ls = new ArrayList<List<String>>();
        // Zamiast tablicy List<String>[] używamy kontenera List<List<String>>
        // Dzięki temu nie trzeba rzutować tablicy i znika unchecked warning.
        ls.add(new ArrayList<String>());
        ls.get(0).add("test");
        ls.get(0).add("test2");
        List<String> z = ls.get(0);
        println(z);
        //ls.add(new ArrayList<Integer>()); to nie działa, listy z Integer nie dodamy do List zdefiniowanej do String

        //List<List<Object>> objects = ls; // nie skompiluje się, kontenery generyczne nie są kowariantne tak jak tablice,
        //więc List<List<String>> nie można przypisać do List<List<Object>>
        //Dzięki temu kompilator blokuje pomieszanie List<String> z List<Integer>
        //objects.add(new ArrayList<Integer>())

        ArrayList<List<BerylliumSphere5>> spheres = new ArrayList<List<BerylliumSphere5>>(); // kontener list obiektów BerylliumSphere5
        spheres.add(new ArrayList<BerylliumSphere5>()); // dodaje wewnętrzną listę, do niej wrzucamy obiekty
        for(int i = 0; i < 7; i++)
            spheres.get(0).add(new BerylliumSphere5()); // dodawanie obiektów do listy
        println(spheres);
    }
}