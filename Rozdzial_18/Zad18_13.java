import java.io.*;
import myutils.*;
/*
Exercise 13: (3) Modify BasicFileOutput.java so that it uses LineNumberReader
to keep track of the line count. Note that it’s much easier to just keep track programmatically.
*/
public class Zad18_13 {
    static String file = "Zad18_13.out";
    public static void main(String[] args)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("Zad18_13.java")));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        //dodana warstwa LineNumberReader do bufora
        LineNumberReader zo = new LineNumberReader(in);
        String s;
        while((s = zo.readLine()) != null ) {
            //println po getLineNumber() a nie manualne inkrementowanie int ++
            out.println(zo.getLineNumber() + ": " + s);
        }
        //strumienie są zamykane, ponieważ to często trzyma zasób systemowy, np. otwarty plik na dysku.
        out.close();
        zo.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}
