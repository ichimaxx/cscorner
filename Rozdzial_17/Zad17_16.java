import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.util.concurrent.*;

/*
Exercise 16: (7) Apply the tests in Maps.java to SlowMap to verify that it works. Fix
anything in SlowMap that doesn’t work correctly.
*/

class MapEntry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }
    public V setValue(V v) {
        V result = value;
        value = v;
        return result;
    }

    public int hashCode() {
        return (key==null ? 0 : key.hashCode()) ^
                (value==null ? 0 : value.hashCode());
    }
    public boolean equals(Object o) {
        if(!(o instanceof MapEntry)) return false;
        MapEntry me = (MapEntry)o;
        return
                (key == null ?
                        me.getKey() == null : key.equals(me.getKey())) &&
                        (value == null ?
                                me.getValue()== null : value.equals(me.getValue()));
    }
    public String toString() { return key + "=" + value; }
}

public class Zad17_16<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();
    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        if(!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }
    public V get(Object key) { // key is type Object, not K
        if(!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }
    public static void printKeys(Map<Integer,String> map) {
        println("Size = " + map.size() + ", ");
        println("Keys: ");
        println(map.keySet()); // Produce a Set of the keys
    }
    public static void test(Map<Integer,String> map) {
        println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        // Map has ‘Set’ behavior for keys:
        map.putAll(new CountingMapData(25));
        printKeys(map);
        // Producing a Collection of the values:
        println("Values: ");
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
    //kompletna zmiana EntrySet, ponieważ Zad17_16(SlowMap) bazuje na dwóch listach: keys i values
    //poprzednia wersja entrySet() zwracała kopię tej mapy w formie setu, więc remove(), clear() i keySet().removeAll()
    //nie zmieniały prawdziwej mapy tylko kopię
    //entrySet() jest wymagane do większości metod z test(), aby prawidłowo działały
    public Set<Map.Entry<K, V>> entrySet() {
        //entrySet() zwraca AbstractSet jako widok na prawdziwe dane mapy,
        //czyli listy keys i values dzięki czemu usuwanie przez iterator zmienia mapę
        return new AbstractSet<Map.Entry<K,V>>() {
            public int size() {
                return keys.size();
            }
            public Iterator<Map.Entry<K,V>> iterator() {
                return new Iterator<Map.Entry<K,V>>() {
                    int index = 0;
                    int last = -1;
                    public boolean hasNext() {
                        return index < keys.size();
                    }
                    public Map.Entry<K, V> next() {
                        last = index;
                        MapEntry<K,V> k = new MapEntry<K,V>(keys.get(index), values.get(index));
                        index++;
                        return k;
                    }
                    public void remove() {
                        if(last == -1) // jeżeli last = -1 to oznacza, że nie ma aktualnego elementu do usunięcia
                            throw new IllegalStateException();
                        values.remove(last);
                        keys.remove(last);
                        index = last;
                        last = -1;
                    }
                };
            }
        };
    }

    public static void main(String[] args) {
        Zad17_16<String,String> m= new Zad17_16<String,String>();
        m.putAll(Countries.capitals(15));
        System.out.println(m);
        System.out.println(m.get("BOTSWANA"));
        System.out.println(m.entrySet());
        println("\nHashMap TEST: \n");
        test(new HashMap<Integer,String>());
        println("\nZad17_16 MAP TEST: \n");
        test(new Zad17_16<Integer,String>());
        println("\nTreeMap MAP TEST: \n");
        test(new TreeMap<Integer,String>());
        println("\nLinkedHashMap MAP TEST: \n");
        test(new LinkedHashMap<Integer,String>());
        println("\nIdentityHashMap MAP TEST: \n");
        test(new IdentityHashMap<Integer,String>());
        println("\nConcurrentHashMap MAP TEST: \n");
        test(new ConcurrentHashMap<Integer,String>());
        println("\nWeakHashMap MAP TEST: \n");
        test(new WeakHashMap<Integer,String>());
    }
}
/*
metody używające entrySet()
map.size();                  -- używa entrySet().size()
map.keySet();                -- używa entrySet()
map.values();                -- używa entrySet()
println(map);                -- używa entrySet()
map.containsKey(11);         -- używa entrySet()
map.containsValue("F0");     -- używa entrySet()
map.remove(key);             -- używa entrySet().iterator().remove()
map.clear();                 -- używa entrySet().clear()
map.keySet().removeAll(...); -- używa entrySet().iterator().remove()
*/