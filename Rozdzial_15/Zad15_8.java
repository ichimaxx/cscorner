import java.util.*;
import net.mindview.util.*;
/*
Exercise 8: (2) Following the form of the Coffee example, create a hierarchy of
StoryCharacters from your favorite movie, dividing them into GoodGuys and BadGuys.
Create a generator for StoryCharacters, following the form of CoffeeGenerator.
*/

class StoryCharacters {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
class GoodGuys extends StoryCharacters {}
class BadGuys extends StoryCharacters {}
class Deadpool extends GoodGuys {}
class Wolverine extends GoodGuys {}
class Hulk extends GoodGuys {}
class Thanos extends BadGuys {}
class Guillotine extends BadGuys {}
class Kang extends BadGuys {}

public class Zad15_8
        implements Generator<StoryCharacters>, Iterable<StoryCharacters> {
    private Class[] types = { Deadpool.class, Wolverine.class,
            Guillotine.class, Thanos.class, Hulk.class, Kang.class, };
    private static Random rand = new Random(47);
    public Zad15_8() {}
    // For iteration:
    private int size = 0;
    public Zad15_8(int sz) { size = sz; }
    public StoryCharacters next() {
        try {
            return (StoryCharacters)
                    types[rand.nextInt(types.length)].newInstance();
            // Report programmer errors at run time:
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    class StoryIterator implements Iterator<StoryCharacters> {
        int count = size;
        public boolean hasNext() { return count > 0; }
        public StoryCharacters next() {
            count--;
            return Zad15_8.this.next();
        }
        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    };
    public Iterator<StoryCharacters> iterator() {
        return new StoryIterator();
    }
    public static void main(String[] args) {
        Zad15_8 gen = new Zad15_8();
        for(int i = 0; i < 5; i++)
            System.out.println(gen.next());
        for(StoryCharacters c : new Zad15_8(5))
            System.out.println(c);
    }
}

// CoffeeGenerator z ksiazki przerobiony na Storycharacters generator