import Rozdzial_14.typeinfo.pets.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 1: (1) Use Holders with the typeinfo.pets library to show that a Holders
that is specified to hold a base type can also hold a derived type.
*/

public class Zad15_1<T> {
        private T a;
        public Zad15_1(T a) { this.a = a; }
        public void set(T a) { this.a = a; }
        public T get() { return a; }
        public static void main(String[] args) {
            Zad15_1<Cat> h3 = new Zad15_1<Cat>(new Cat()); // holder zadekladowany na typ bazowy Cat
            Cat a = h3.get(); // No cast needed
            println(a); // printowany Cat
            h3.set(new Manx()); //zmiana obiektu na Manx, da sie, bo manx jest derived klasa po Cat
            a = h3.get(); // robimy get dla h3
            println(a); // printowany jest Manx
    }
}