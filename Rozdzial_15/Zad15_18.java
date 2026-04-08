import java.util.*;
import net.mindview.util.*;


/*
Exercise 18: (3) Following the form of BankTeller.java, create an example where
BigFish eat LittleFish in the Ocean.
*/

class LittleFish { // zastąpiono customer
    private static long counter = 1;
    private final long id = counter++;
    private LittleFish() {}
    public String toString() { return "LittleFish " + id; }
    // A method to produce Generator objects:
    public static Generator<LittleFish> generator() {
        return new Generator<LittleFish>() {
            public LittleFish next() { return new LittleFish(); }
        };
    }
}
class BigFish { // zastąpiono tellera
    private static long counter = 1;
    private final long id = counter++;
    private BigFish() {}
    public String toString() { return "BigFish " + id; }
    // A single Generator object:
    public static Generator<BigFish> generator =
            new Generator<BigFish>() {
                public BigFish next() { return new BigFish(); }
            };
}
public class Zad15_18 { // metody fill zaimplikowalem ponownie w tym zadaniu aby była możliwość kompilacji tylko tego jednego pliku bez potrzeby innych
    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static <T> Queue<T> fill(Queue<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    public static void serve(BigFish t, LittleFish c) {
        System.out.println(t + " eats " + c);
    }
    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<LittleFish> lfishs = new LinkedList<LittleFish>();
        Zad15_18.fill(lfishs, LittleFish.generator(), 15);
        List<BigFish> bfishs = new ArrayList<BigFish>();
        Zad15_18.fill(bfishs, BigFish.generator, 4);
        for(LittleFish c : lfishs)
            serve(bfishs.get(rand.nextInt(bfishs.size())), c);
    }
}

//przerobiono kod BankTeller.java zastąpiono tellerów i customers na LittleFishs i BigFishs, mechanika kodu pozostała taka sama