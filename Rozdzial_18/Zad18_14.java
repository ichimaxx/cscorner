import java.io.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 14: (2) Starting with BasicFileOutput.java, write a program that compares
the performance of writing to a file when using buffered and unbuffered I/O.
*/
public class Zad18_14 {
    //dwa pliki, jeden z dla write to file z buforem a drugi bez
    static String file = "Zad18_14Buff.out";
    static String file2 = "Zad18_14NoBuff.out";
    public static void main(String[] args)
            throws IOException {
        //czytanie pliku źródłowego
        String input = BufferedInputFile.read("Zad18_11.java");
        //string reader potrzebne są dwa, bo w programie są dwie osobne pętle,
        //a każdy string reader po przeczytaniu tekstu dochodzi do końca
        BufferedReader in = new BufferedReader(
                new StringReader(input));
        BufferedReader in1 = new BufferedReader(
                new StringReader(input));
        //start pomiaru Buffered
        long start = System.nanoTime();
        //Writing to file z buforem
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        //zamykanie strumieni
        out.close();
        in.close();
        long duration = System.nanoTime() - start;
        //rezultat
        System.out.println("\nBuffered File:\n\n " + BufferedInputFile.read(file));
        long start1 = System.nanoTime();
        //Writing to file bez bufora
        PrintWriter out2 = new PrintWriter(new FileWriter(file2));
        int lineCount1 = 1;
        String s1;
        while((s1 = in1.readLine()) != null )
            out2.println(lineCount1++ + ": " + s1);
        out2.close();
        in1.close();
        long duration1= System.nanoTime() - start1;
        //rezultat
        System.out.println("\nNonBuffered File:\n\n " +BufferedInputFile.read(file2));
        println("\nBuffered File creation duration: " + duration + "\nNonBuffered File creation duration: " + duration1);
    }
}

/*
przy małej próbce wynik może być losowy, jednak przy dużej ilości danych buffered będzie szybszy
dodatkowo buffered ma dodatkową warstwę przez co przy małych ilościach danych może to dawać więcej narzutu niż korzyści
 */