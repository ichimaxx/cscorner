import java.io.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 29: (2) In Blip3.java, comment out the two lines after the phrases "You must
do this:" and run the program. Explain the result and why it differs from when the two lines
are in the program.
*/
public class Zad18_29 implements Externalizable {
    private int i;
    private String s; // No initialization
    public Zad18_29() {
        println("Blip3 Constructor");
        // s, i not initialized
    }
    public Zad18_29(String x, int a) {
        println("Blip3(String x, int a)");
        s = x;
        i = a;
        // s & i initialized only in non-default constructor.
    }
    public String toString() { return s + i; }
    public void writeExternal(ObjectOutput out)
            throws IOException { println("Blip3.writeExternal");
        // You must do this:
        //out.writeObject(s);
        //out.writeInt(i);
    }
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        println("Blip3.readExternal");
        // You must do this:
        //s = (String)in.readObject();
        //i = in.readInt();
    }
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        println("Constructing objects:");
        Zad18_29 b3 = new Zad18_29("A String ", 47);
        println(b3);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blip3.out"));
        println("Saving object:");
        o.writeObject(b3);
        o.close();
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blip3.out"));
        println("Recovering b3:");
        b3 = (Zad18_29)in.readObject();
        println(b3);
        in.close();
    }
}
/*
po wykomentowaniu linii w metodach readExternal() i writeExternal() Externalizable nie ma gdzie
zapisać pola podczas przenosin, ponieważ przy tego rodzaju serializacji zapis musi być zrobiony ręcznie
Zamiast wyniku A String 47 będzie null0

- java tworzy nowy obiekt przez publiczny konstruktor bezargumentowy
- pole string s ma domyślną wartość null
- pole int i ma domyślną wartość 0
- java wywołuje readExternal()
- readexternal() nie odczytuje do s oraz i
*/