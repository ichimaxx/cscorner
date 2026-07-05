import java.io.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 27: (1) Create a Serializable class containing a reference to an object of a
second Serializable class. Create an instance of your class, serialize it to disk, then restore it
and verify that the process worked correctly.
*/
//druga klasa z Serializable
class Drugaklasa implements Serializable {
    private int n;
    public Drugaklasa(int n) { this.n = n; }
    public String toString() { return Integer.toString(n); }
}

public class Zad18_27 implements Serializable {
    private static Random rand = new Random(47);
    //referencja do obiektu Drugaklasa
    private Drugaklasa[] d = {
            new Drugaklasa(rand.nextInt(10)),
            new Drugaklasa(rand.nextInt(10)),
            new Drugaklasa(rand.nextInt(10))
    };
    private char c;
    // Value of i == number of segments
    public Zad18_27(char x) {
        println("Zad18_27 constructor");
        c = x;
    }
    public Zad18_27() {
        println("Default constructor");
    }
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for(Drugaklasa dat : d)
            result.append(dat);
        result.append(")");
        return result.toString();
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Zad18_27 w = new Zad18_27('a');
        println("Zad18_27 przed serializacją = " + w);
        //zapis do pliku
        //obiekt -> writeObject() -> plik
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("Zad18_27.out"));
        out.writeObject("ZAPIS(Serializacja) Zad18_27\n");
        out.writeObject(w);
        //flush outputu
        out.close();
        //wczytanie pliku
        //plik -> readObject() -> obiekt
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Zad18_27.out"));
        String s = (String)in.readObject();
        Zad18_27 w2 = (Zad18_27)in.readObject();
        //test czy obiekt został odtworzony
        println(s + "Odczyt Zad18_27 po serializacji = " + w2);
        in.close();
    }
}
