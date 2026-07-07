import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 5: (4) Modify control/VowelsAndConsonants.java so that it uses three
enum types: VOWEL, SOMETIMES_A_VOWEL, and CONSONANT. The enum
constructor should take the various letters that describe that particular category. Hint: Use
varargs, and remember that varargs automatically creates an array for you.
*/
public enum Zad19_5 {
    VOWEL('a', 'e', 'i', 'o', 'u'),
    SOMETIMES_A_VOWEL('y', 'w'),
    CONSONANT('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','x','v','z');
    private char[] litery;
    //konstruktor varagrs dla enum
    Zad19_5(char... litery) {
        this.litery = litery;
    }
    //metoda, która sprawdza, czy podana litera jest w tablicy liter
    public boolean contains (char c) {
        for(char litera : litery)
            if(litera == c)
                return true;
        return false;
    }
    //metoda definiująca, który enum zostanie wybrany do danej litery
    public static Zad19_5 miniSwitch(char c) {
        for(Zad19_5 swit : values())
            if(swit.contains(c))
                return swit;
        //jeżeli litery nie ma w którymś z enum, wyrzuca wyjątek
        throw new IllegalArgumentException("Nieznana litera: " + c);
    }
    public static void main(String[] args) {
        Random rand = new Random(47);
        for(int i = 0; i < 100; i++) {
            char c = (char)(rand.nextInt(26) + 'a');
            println(c + ", " + (int)c + ": " + miniSwitch(c));
        }
    }
}

