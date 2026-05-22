import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 4: (2) Create a Collection initializer that opens a file and breaks it into words
using TextFile, and then uses the words as the source of data for the resulting Collection.
Demonstrate that it works.
*/
public class Zad17_4 {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>(new TextFile("Zad17_3.java", "\\W+"));
        // Używa klasy TextFile do odczytania pliku Zad17_3.java i rozbija go na słowa.
        println("\nArrayList TextFile Zad17_3:\n");
        println(words);
        println("\nArrayList TextFile Zad17_3 sorted:\n");
        Collections.sort(words);
        println(words);
        // String są następnie użyte jako dane początkowe dla ArrayList,
        // na której pokazano metody Collection.sort() i shuffle()
        println("\nArrayList TextFile Zad17_3 shuffled:\n");
        Collections.shuffle(words);
        println(words);
    }
}

