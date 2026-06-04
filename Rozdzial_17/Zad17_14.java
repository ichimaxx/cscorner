import java.util.concurrent.*;
import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 14: (3) Show that java.util.Properties works in the above program.
*/
public class Zad17_14 {
    public static void printKeys(Map<Integer, String> map) {
        print("Size = " + map.size() + ", ");
        print("Keys: ");
        println(map.keySet()); // Produce a Set of the keys
    }
    public static void test(Map<Integer, String> map) {
        println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        // Map has ‘Set’ behavior for keys:
        map.putAll(new CountingMapData(25));
        printKeys(map);
        // Producing a Collection of the values:
        print("Values: ");
        println(map.values());
        println(map);
        println("map.containsKey(11): " + map.containsKey(11));
        println("map.get(11): " + map.get(11));
        println("map.containsValue(\"F0\"): "
                + map.containsValue("F0"));
        Integer key = map.keySet().iterator().next();
        println("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        println("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        // Operations on the Set change the Map:
        map.keySet().removeAll(map.keySet());
        println("map.isEmpty(): " + map.isEmpty());
    }
    //Rzutowanie Properties() do na surowe(raw type) Map wywołuje unchecked warning
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        test(new HashMap<Integer,String>());
        println();
        test(new TreeMap<Integer,String>());
        println();
        test(new LinkedHashMap<Integer,String>());
        println();
        test(new IdentityHashMap<Integer,String>());
        println();
        test(new ConcurrentHashMap<Integer,String>());
        println();
        test(new WeakHashMap<Integer,String>());
        println();
        // Properties jest starszą klasą, dziedziczącą po Hashtable,
        // więc można potraktować ją jako Map przez raw cast(bez typów).
        // Wywołuje unchecked warning dlatego używane jest @SuppressWarnings.
        test((Map) new Properties());
    }
}