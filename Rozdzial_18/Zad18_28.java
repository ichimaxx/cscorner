import java.io.*;

import static myutils.Skrocenie_Print.*;
/*
Exercise 28 : (2) In Blips.java, copy the file and rename it to BlipCheck.java and
rename the class Blip2 to BlipCheck (making it public and removing the public scope
from the class Blips in the process). Remove the //! marks in the file and execute the
*/

class Blip1 implements Externalizable {
    public Blip1() {
        println("Blip1 Constructor");
    }
    public void writeExternal(ObjectOutput out)
            throws IOException {
        println("Blip1.writeExternal");
    }
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        println("Blip1.readExternal");
    }
}
public class Zad18_28 implements Externalizable {
    //konstruktor jest zakomentowany, więc java dodaje coś w stylu:
    //public Zad18_28 {
    //super();
    //}

    //public Zad18_28() {
    //    println("Blip2 Constructor");
    //}

    public void writeExternal(ObjectOutput out)
            throws IOException {
        println("Blip2.writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        println("Blip2.readExternal");
    }
    public static void main(String[] args) {
        println("Usage: java Blips");
    }
}
class Blips {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        println("Constructing objects:");
        Blip1 b1 = new Blip1();
        Zad18_28 b2 = new Zad18_28();
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Zad18_28.out"));
        println("Saving objects:");
        o.writeObject(b1); o.writeObject(b2);
        o.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Zad18_28.out"));
        println("Recovering b1:");
        b1 = (Blip1)in.readObject();
        // OOPS! Throws an exception:
        //Przy Externalizable podczas odczytu wymagany jest publiczny konstruktor bezargumentowy.
        //Program nadal działa mimo zakomentowanego konstruktora, ponieważ kompilator automatycznie tworzy
        //publiczny konstruktor domyślny.
        //Wyjątek byłby, gdyby klasa nie miała dostępnego publicznego konstruktora bezargumentowego.
        println("Recovering b2:");
        b2 = (Zad18_28)in.readObject();
    }
}
