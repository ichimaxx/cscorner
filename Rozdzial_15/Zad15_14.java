import net.mindview.util.*;
import java.util.*;
/*
Exercise 14: (1) Modify BasicGeneratorDemo.java to use the explicit form of
creation for the Generator (that is, use the explicit constructor instead of the generic
create( ) method).
*/
class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;
    public BasicGenerator(Class<T> type){ this.type = type; }
    public T next() {
        try {
            // Assumes type is a public class:
            return type.newInstance();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
// Produce a Default generator given a type token:
public static <T> Generator<T> create(Class<T> type) {
    return new BasicGenerator<T>(type);
}
}
class CountedObject {
    private static long counter = 0;
    private final long id = counter++;
    public long id() { return id; }
    public String toString() { return "CountedObject " + id;}
}

public class Zad15_14 {
    public static void main(String[] args) {
        Generator<CountedObject> gen = new BasicGenerator<CountedObject>(CountedObject.class); // zamieniono create() na explicit form of creation czyli uzycie konstruktora
        for(int i = 0; i < 5; i++)
            System.out.println(gen.next());
    }
}

/*
w zadaniu zamieniono
Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
na
Generator<CountedObject> gen = new BasicGenerator<CountedObject>(CountedObject.class);
czyli zamiast uzycia bezposrednio metody pomocniczej create() z BasicGenerator, tworzymy obiekt BasicGenerator przez wywołanie konstruktora (new BasicGenerator) i określamy jego typ
*/