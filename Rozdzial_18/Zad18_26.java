import java.io.*;
import java.nio.*;
import java.nio.charset.Charset;
import java.util.*;
import java.nio.channels.*;
import java.util.regex.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 26: (3) Modify strings/JGrep.java to use Java nio memorymapped files.
*/
public class Zad18_26 {
    public static void main(String[] args) throws Exception {
        if(args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        int index = 0;
        Matcher m = p.matcher("");
        //RandomAccessFile -> FileChannel -> map() -> MappedByteBuffer -> decode -> String
        //Plik jest otwierany przez RandomAccessFile, i pobierany jest FileChannel.
        try(FileChannel fc = new RandomAccessFile(args[0], "r").getChannel()) {
            // plik -> FileChannel -> memory-mapped buffer
            //metoda map() mapuje cały plik do pamięci jako MappedByteBuffer.
            MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            //MappedByteBuffer ma bajty, a regex potrzebuje tekstu:
            //bufor jest dekodowany przez Charset.defaultCharset() do Stringa
            String text = Charset.defaultCharset().decode(out).toString();
            //tekst dzielony na linie i dla każdej linii matcher szuka dopasowania bazując na regex z args[1].
            String[] lines = text.split("\n");
            //pętla matchera po tekście
            for (String line : lines) {
                m.reset(line);
                while (m.find())
                    println(index++ + ": " + m.group() + ": " + m.start());
            }
        }
    }
}

/*
program działa jak JGrep, ale zamiast czytać przez TextFile, używa memorymapped file.
output:
numer znalezienia: znaleziony fragment: pozycja w linii
*/