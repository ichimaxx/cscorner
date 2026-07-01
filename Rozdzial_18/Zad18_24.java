import java.io.*;
import java.nio.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 24: (1) Modify IntBufferDemo.java to use doubles.
*/
public class Zad18_24 {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        DoubleBuffer ib = bb.asDoubleBuffer();
        ib.put(new double[]{ 11, 42, 47, 99, 143, 811, 1016 });
        //print elementu pod indeksem 3
        System.out.println(ib.get(3));
        //zapis absolutny, pod indeksem 3 zmiana 99 na 1811
        ib.put(3, 1811);
        //ustawia nowy limit na aktualną pozycję
        //a pozycję cofa na początek, dzięki czemu pętla odczyta tylko wpisane wartości
        ib.flip();
        //wypisz wszystkie wartości od pozycji 0 do limitu
        while(ib.hasRemaining()) {
            double i = ib.get();
            System.out.println(i);
        }
    }
}
