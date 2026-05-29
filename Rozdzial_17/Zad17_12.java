import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 12: (1) Substitute a HashMap, a TreeMap and a LinkedHashMap in
AssociativeArray .Java’s main( ).
*/

public class Zad17_12<K,V> {
    private Object[][] pairs;
    private int index;
    public Zad17_12(int length) {
        pairs = new Object[length][2];
    }
    public void put(K key, V value) {
        if(index >= pairs.length)
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[]{ key, value };
    }
    @SuppressWarnings("unchecked")
    public V get(K key) {
        for(int i = 0; i < index; i++)
            if(key.equals(pairs[i][0]))
                return (V)pairs[i][1];
        return null; // Did not find key
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if(i < index - 1)
                result.append("\n");
        }
        return result.toString();
    }
    public static void main(String[] args) {
        Zad17_12<String,String> map = new Zad17_12<String,String>(6);
        HashMap<String,String> map1 = new HashMap<>();
        TreeMap<String,String> map2 = new TreeMap<String,String>();
        LinkedHashMap<String,String> map3 = new LinkedHashMap<>();
        println("\nZad17_12 map:\n");
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object"); // Past the end
        } catch(ArrayIndexOutOfBoundsException e) {
            println("Too many objects!");
        }
        println(map);
        println(map.get("ocean"));
        println("\nHashMap map:\n");
        map1.put("sky", "blue");
        map1.put("grass", "green");
        map1.put("ocean", "dancing");
        map1.put("tree", "tall");
        map1.put("earth", "brown");
        map1.put("sun", "warm");
        try {// catch się tutaj nie wykona, bo HashMap nie ma limitu 6 elementów jak Zad17_12
            map1.put("extra", "object");
        } catch(ArrayIndexOutOfBoundsException e) {
            println("Too many objects!");
        }
        println(map1);
        println(map1.get("ocean"));
        println("\nTreeMap map:\n");
        map2.put("sky", "blue");
        map2.put("grass", "green");
        map2.put("ocean", "dancing");
        map2.put("tree", "tall");
        map2.put("earth", "brown");
        map2.put("sun", "warm");
        try {// catch się tutaj nie wykona, bo TreeMap nie ma limitu 6 elementów jak Zad17_12
            map2.put("extra", "object");
        } catch(ArrayIndexOutOfBoundsException e) {
            println("Too many objects!");
        }
        println(map2);
        println(map2.get("ocean"));
        println("\nLinkedHashMap map:\n");
        map3.put("sky", "blue");
        map3.put("grass", "green");
        map3.put("ocean", "dancing");
        map3.put("tree", "tall");
        map3.put("earth", "brown");
        map3.put("sun", "warm");
        try {  // catch się tutaj nie wykona, bo LinkedHashMap nie ma limitu 6 elementów jak Zad17_12
            map3.put("extra", "object");
        } catch(ArrayIndexOutOfBoundsException e) {
            println("Too many objects!");
        }
        println(map3);
        println(map3.get("ocean"));
    }
}

/*
Try/catch przy Zad17_12 ma sens, ponieważ ta klasa ma tablicę o stałym rozmiarze.
Przy HashMap, TreeMap, i LinkedHashMap ten sam try/catch nie złapie wyjątku,
ponieważ domyślne mapy nie mają limitu elementów.
Ich rozmiar może rosnąć w miarę dodawania elementów, ograniczenie to przydzielona pamięć.

Właściwości względem danych map:

Zad17_12 -- kolejność dodawania, limit 6 par
HashMap -- szybka mapa, kolejność niegwarantowana
TreeMap -- sortowanie po kluczach
LinkedHashMap -- kolejność dodania, brak sztywnego limitu
*/