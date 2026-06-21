import java.util.regex.*;
import java.io.*;
import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 4: (2) Use Directory.walk( ) to sum the sizes of all files in a directory tree
whose names match a particular regular expression.
*/
public class Zad18_4 {
    public static void main(String[] args) {
        long sum = 0;
        //regex szuka wszystkie pliki, które zaczynają się na Z lub z i mają rozszerzenie .java
        for(File file : Directory.walk(".","[Zz].*\\.java")) {
            println(file);
            //dodawania bajtów do long, który będzie trzymać całkowity rozmiar directory tree
            sum += file.length();
        }
        println("SUMA PLIKÓW: " + sum);
    }
}
