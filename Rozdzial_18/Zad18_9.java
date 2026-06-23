import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import static myutils.Skrocenie_Print.println;

/*
Exercise 9: (1) Modify Exercise 8 to force all the lines in the LinkedList to uppercase
and send the results to System.out.
*/
public class Zad18_9 {
    //metoda zmieniona pod LinkedList<String>
    public static LinkedList<String> read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        LinkedList<String> zz = new LinkedList<>();
        while((s = in.readLine())!= null)
            //dodaje za każdym razem jako pierwszy w kontenerze(reverse order)
            //tekst w LinkedList jest przechowywany w uppercase
            zz.addFirst(s.toUpperCase());
        in.close();
        return zz;
    }
    public static void main(String[] args) throws IOException {
        //jeśli nie ma args to wypisuje instrukcję co trzeba zrobić
        if (args.length == 0){
            println("USAGE: java Zad18_9 filename");
            return;
        }
        //drukowanie wszystkich linii po kolei jedna po drugiej
        //czytanie po argumencie z command-line
        for(String line : read(args[0]))
            //result w System.out.println()
            println(line);
    }
}
