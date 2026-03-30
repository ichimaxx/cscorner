import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (1) Use RandomList(Zad15_6) with two more types in addition to the one shown in
main( ).
*/
public class Zad15_6<T> {
    private ArrayList<T> storage = new ArrayList<T>();
    private Random rand = new Random(47);
    public void add(T item) { storage.add(item); }
    public T select() {
        return storage.get(rand.nextInt(storage.size()));
    }
    public static void main(String[] args) {
        Zad15_6<String> rs = new Zad15_6<String>();
        Integer kd[] = {45, 6563, 6356, 6536, 66}; // array do Integera
        Character of[] = {'k', 'd', 'h', 'f', 'a', 'h'}; // array do characters
        Zad15_6<Integer> rs7 = new Zad15_6<Integer>(); //nowy obiekt pod typ Integer
        Zad15_6<Character> rs9 = new Zad15_6<Character>(); //nowy obiekt pod typ Character
        for(String s: ("The quick brown fox jumped over " +
                "the lazy brown dog ").split(" "))
            rs.add(s);
        for(Integer j: (kd))
            rs7.add(j);
        for(Character z: (of))
            rs9.add(z);
        for(int i = 0; i < 11; i++)
            print(rs.select() + " ");
        println("");
        for(int i = 0; i < 11; i++)
            print(rs7.select() + " ");
        println("");
        for(int i = 0; i < 11; i++)
            print(rs9.select() + " ");
    }
}

// Dzieki temu, że uzyty jest typ generyczny w klasie Zad15_6 mozna tworzyc obiekty klasy Zad15_6 np dla string, Integer albo Character. Bez generyków typ musi być predefiniowany.