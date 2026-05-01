import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 1: (2) Create a method that takes an array of BerylliumSphere as an
argument. Call the method, creating the argument dynamically. Demonstrate that ordinary
aggregate array initialization doesn’t work in this case. Discover the only situations where
ordinary aggregate array initialization works, and where dynamic aggregate initialization is
redundant.
*/
class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_1 {
    public BerylliumSphere[] dynamic(BerylliumSphere[] s) {
        return s;
    }
    public static void main(String[] args){
        Zad16_1 g = new Zad16_1();
        // NIE DZIAŁA:
        // println(Arrays.toString(g.dynamic({new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()}))); // aggregate initialization czyli { ... } nie może być użyta jako samodzielny argument metody
        //TO JUZ ZADZIAŁA:
        println(Arrays.toString(g.dynamic(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere() }))); //dynamic aggregate initialization(dynamiczna inicjalizacja)
        // to też zadziała
        BerylliumSphere[] agi =  {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()}; // w tym przypadku agregatowa inicjalizacja zadziała ponieważ deklarowana tablica jest od razu inicjalizowana
        // i to też
        BerylliumSphere[] dagi = new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()}; // tak jak zadanie mówi tutaj dynamiczna inicjalizacja jest redundantna(zbędna), wystarczy krótszy zapis tak jak w agi
        println("agi:" + Arrays.toString(agi));
        println("dagi:" + Arrays.toString(dagi));
    }
}
