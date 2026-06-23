import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import static myutils.Skrocenie_Print.println;

/*
Exercise 10: (2) Modify Exercise 8 to take additional command-line arguments of words
to find in the file. Print all lines in which any of the words match.
*/
public class Zad18_10 {
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
        //jeśli args mniejsze od 2 wypisuje instrukcję
        if (args.length < 2){
            println("USAGE: java Zad18_8 filename slowo1 slowo2 ....");
            return;
        }
        //drukowanie wszystkich linii po kolei jedna po drugiej
        //czytanie po argumencie z command-line
        for(String line : read(args[0]))
            //od args[1] do tylu ile zostanie wypisane, będzie iść pętla for(),
            //sprawdza czy linia zawiera któryś z argumentów, jeśli tak, printuje daną linię i robi break,
            //dzięki czemu idzie do następnej linii, i nie wypisuje tej samej, jeśli drugie słowo
            //będzie pasować do danej linii
            for(int i = 1; i < args.length; i++) {
                if (line.contains(args[i])) {
                    println(line);
                    break;
                }
            }
    }
}
