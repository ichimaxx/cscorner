import java.nio.channels.*;
import java.nio.*;
import java.io.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 23: (6) Create and test a utility method to print the contents of a CharBuffer
up to the point where the characters are no longer printable.
*/
public class Zad18_23 {
    //stała określająca rozmiar bufora
    //1024 bajty = miejsce na 512 znaków char, bo jeden znak to 2 bajty
    private static final int BSIZE = 1024;
    static void prints(CharBuffer cb) {
        //tak długo, jak w buforze są znaki odczytuje while
        while(cb.hasRemaining()) {
            //kolejny znak z CharBuffer
            //każde cb.get() przesuwa bufor o jeden znak dalej
            char c = cb.get();
            //jeżeli trafiono na znak o wartości 0, przerywa wpisywanie
            if (c == 0)
                break;
            print(c);
        }
    }
    public static void main(String[] args) {
        //stworzono ByteBuffer, czyli bufor bajtowy o rozmiarze 1024 bajtów
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        //CharBuffer jako "widok" na ten sam ByteBuffer
        //czyli pod spodem są bajty, ale można pracować na nich jak na znakach
        CharBuffer zz = buff.asCharBuffer();
        //wkłada tekst do CharBuffer
        //po tej operacji pozycja znajduje sięza ostatnim wpisanym znakiem
        zz.put("Some Text");
        //cofa pozycję bufora na początek, więc metoda prints() może czytać tekst od pierwszego znaku
        zz.rewind();
        prints(zz);
    }
    }
