import myutils.Directory;

import java.io.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 20: (4) Using Directory.walk( ) and BinaryFile(Zad18_20), verify that all .class files
in a directory tree begin with the hex characters ‘CAFEBABE’.
*/
public class Zad18_20 {
    public static byte[] read(File bFile) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } finally {
            bf.close();
        }
    }
    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }
    public static void main(String[] args) throws IOException {
        //regex szuka wszystkich plików, które mają rozszerzenie .class
        for(File file : Directory.walk(".",".*\\.class")) {
            byte[] bytes = Zad18_20.read(file);
            if(bytes.length >= 4 &&
                //sprawdzenie, czy pierwsze 4 bajty mają wartość CA FE BA BE
                //0xCA bez castu, jest intem o wartości 202,
                //a byte w javie jest signed, więc odczytany bajt może mieć wartość ujemną.
                //dlatego porównano byte z byte przez cast(rzutowanie): (byte)0xCA
                bytes[0] == (byte)0xCA &&
                bytes[1] == (byte)0xFE &&
                bytes[2] == (byte)0xBA &&
                bytes[3] == (byte)0xBE) {
                println(file + " ZACZYNA SIĘ OD \"CAFEBABE\"");
            } else {
                println(file + " NIE ZACZYNA SIĘ OD \"CAFEBABE\"");
            }
        }
    }
}
