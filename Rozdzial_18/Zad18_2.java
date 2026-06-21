import java.util.regex.*;
import java.io.*;
import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 2: (2) Create a class called SortedDirList with a constructor that takes a File
object and builds a sorted directory list from the files at that File. Add to this class two
overloaded list( ) methods: the first produces the whole list, and the second produces the
subset of the list that matches its argument (which is a regular expression).
*/
public class Zad18_2{
    File file;
    private String[] listt;
    public Zad18_2(File file) {
        this.file = file;
        listt = file.list();
        Arrays.sort(listt, String.CASE_INSENSITIVE_ORDER);
    }
    public String[] list() {
        //zwraca całą posortowaną listę nazw plików
        return listt;
    }
    public String[] list(String regex) {
        Pattern pattern = Pattern.compile(regex);
        List<String> wyniki = new ArrayList<String>();
        //bierze listę klasy i ją porównuje z regexem
        for (String name : listt)
            if(pattern.matcher(name).matches())
                wyniki.add(name);
        // metoda zwraca tylko nazwy plików, które są matching z regexem
        return wyniki.toArray(new String[0]);
    }
    public static void main(String[] args) {
        Zad18_2 z = new Zad18_2(new File("."));
        println("\nCała zawartość folderu: \n");
        for (String s : z.list()) {
            System.out.println(s);
        }
        println("\nTylko pliki .java:\n");
        for(String k : z.list(".*\\.java"))
            println(k);
    }
}
