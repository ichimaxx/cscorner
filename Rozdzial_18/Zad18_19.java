import java.io.*;
import java.util.*;

import static myutils.Skrocenie_Print.println;
/*
Exercise 19: (2) Using BinaryFile and a Map<Byte,Integer>, create a program that
counts the occurrence of all the different bytes in a file.
*/
public class Zad18_19 {
    public static byte[] read(File bFile) throws IOException{
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
        Map<Byte, Integer> ok = new TreeMap<>();
        byte[] bytes = Zad18_19.read("Zad18_17.java");
        for(int i = 0; i < bytes.length ; i++) {
            //pobranie kolejnego bajtu z tekstu pliku
            byte g = bytes[i];
            //jeżeli mapa zawiera już ten byte, to dodaje value + 1
            if (ok.containsKey(g)) {
                int k = ok.get(g);
                ok.put(g, k + 1);
            } else {
                ok.put(g, 1);
            }
        }
        println(ok);
    }
}
