import java.io.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 7: (2) Open a text file so that you can read the file one line at a time. Read each
line as a String and place that String object into a LinkedList. Print all of the lines in the
LinkedList in reverse order.
*/
public class Zad18_7 {
    //metoda zmieniona pod LinkedList<String>
    public static LinkedList<String> read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        LinkedList<String> zz = new LinkedList<>();
        while((s = in.readLine())!= null)
            //dodaje za każdym razem jako pierwszy w kontenerze(reverse order)
            zz.addFirst(s);
        in.close();
        return zz;
    }
    public static void main(String[] args) throws IOException {
        //drukowanie wszystkich linii po kolei jedna po drugiej
        for(String line : read("Zad18_4.java"))
            println(line);
    }
}
