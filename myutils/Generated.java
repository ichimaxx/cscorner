package myutils;
import java.util.*;

class CollectionData<T> extends ArrayList<T> {
    public CollectionData(myutils.Generator<T> gen, int quantity) {
        for(int i = 0; i < quantity; i++)
            add(gen.next());
    }
    // A generic convenience method:
    public static <T> myutils.CollectionData<T>
    list(myutils.Generator<T> gen, int quantity) {
        return new myutils.CollectionData<T>(gen, quantity);
    }
}
public class Generated {
    // Fill an existing array:
    public static <T> T[] array(T[] a, Generator<T> gen) {
        return new CollectionData<T>(gen, a.length).toArray(a);
    }
    // Create a new array:
    @SuppressWarnings("unchecked")
    public static <T> T[] array(Class<T> type,
                                Generator<T> gen, int size) {
        T[] a =
                (T[])java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<T>(gen, size).toArray(a);
    }
}