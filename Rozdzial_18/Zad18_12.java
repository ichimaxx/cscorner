import java.io.*;
import java.util.LinkedList;
import static myutils.Skrocenie_Print.println;

/*
Exercise 12: (3) Modify Exercise 8 to also open a text file so you can write text into it.
Write the lines in the LinkedList, along with line numbers (do not attempt to use the
"LineNumber" classes), out to the file.
*/
class BufferedInputFile {
    // Throw exceptions to console:
    public static String
    read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = in.readLine())!= null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }
    public static void main(String[] args)
            throws IOException {
        System.out.print(read("BufferedInputFile.java"));
    }
}
public class Zad18_12 {
    static String file = "Zad18_12.out";
    //metoda zmieniona pod LinkedList<String>
    public static LinkedList<String> read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        LinkedList<String> zz = new LinkedList<>();
        //produkcja kontenera LinkedList tak jak w zad8
        while((s = in.readLine())!= null)
            zz.addFirst(s);
        in.close();
        //zapis linii z kontenera LinkedList do nowego pliku razem z numerami linii
        for(String line : zz)
            out.println(lineCount++ + ": " + line);
        out.close();
        //print zmodyfikowanego pliku
        System.out.println(BufferedInputFile.read(file));
        return zz;
    }
    public static void main(String[] args) throws IOException {
        //jeśli nie ma args to wypisuje instrukcję co trzeba zrobić
        if (args.length == 0){
            println("USAGE: java Zad18_12 filename");
            return;
        }
        //wywołuje metodę read na pliku podanym jako pierwszy argument(args[0]
        //czyta plik podany w args[0] i tworzy plik wynikowy oraz numeruje linie
        read(args[0]);
    }
}
