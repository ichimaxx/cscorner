import java.io.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 8: (1) Modify Exercise 7 so that the name of the file you read is provided as a
command-line argument.
*/
public class Zad18_8 {
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
        //jeśli nie ma args to wypisuje instrukcję co trzeba zrobić
        if (args.length == 0){
            println("USAGE: java Zad18_8 filename");
            return;
        }
        //drukowanie wszystkich linii po kolei jedna po drugiej
        //czytanie po argumencie z command-line
        for(String line : read(args[0]))
            println(line);
    }
}
