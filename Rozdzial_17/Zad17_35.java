import java.util.*;
import myutils.*;

import static myutils.Skrocenie_Print.println;

/*
Exercise 35: (1) Modify MapPerformance.java to include tests of SlowMap.
*/
// SlowMap z naprawionym EntrySet, które to zwraca prawdziwą mapę
class SlowMapFixed<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();

    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }

    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    public static void printKeys(Map<Integer, String> map) {
        println("Size = " + map.size() + ", ");
        println("Keys: ");
        println(map.keySet()); // Produce a Set of the keys
    }
    //kompletna zmiana EntrySet, ponieważ Zad17_16(SlowMap) bazuje na dwóch listach: keys i values
    //poprzednia wersja entrySet() zwracała kopię tej mapy w formie setu, więc remove(), clear() i keySet().removeAll()
    //nie zmieniały prawdziwej mapy tylko kopię
    //entrySet() jest wymagane do większości metod z test(), aby prawidłowo działały
    public Set<Map.Entry<K, V>> entrySet() {
        //entrySet() zwraca AbstractSet jako widok na prawdziwe dane mapy,
        //czyli listy keys i values, dzięki czemu usuwanie przez iterator zmienia mapę
        return new AbstractSet<Map.Entry<K, V>>() {
            public int size() {
                return keys.size();
            }

            public Iterator<Map.Entry<K, V>> iterator() {
                return new Iterator<Map.Entry<K, V>>() {
                    int index = 0;
                    int last = -1;

                    public boolean hasNext() {
                        return index < keys.size();
                    }

                    public Map.Entry<K, V> next() {
                        last = index;
                        MapEntry<K, V> k = new MapEntry<K, V>(keys.get(index), values.get(index));
                        index++;
                        return k;
                    }

                    public void remove() {
                        if (last == -1) // jeżeli last = -1 to oznacza, że nie ma aktualnego elementu do usunięcia
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
}
// nienaprawiona SlowMap, która zwraca za każdym razem z użyciem entrySet kopię mapy
class SlowMapNotFixed<K,V> extends AbstractMap<K,V> {
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
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set= new HashSet<Map.Entry<K,V>>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while(ki.hasNext())
            set.add(new MapEntry<K,V>(ki.next(), vi.next()));
        return set;
    }
}
public class Zad17_35 {
    static List<Test<Map<Integer,Integer>>> tests =
            new ArrayList<Test<Map<Integer,Integer>>>();
    static {
        tests.add(new Test<Map<Integer,Integer>>("put") {
            int test(Map<Integer,Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    map.clear();
                    for(int j = 0; j < size; j++)
                        map.put(j, j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer,Integer>>("get") {
            int test(Map<Integer,Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for(int i = 0; i < loops; i++)
                    for(int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });
        tests.add(new Test<Map<Integer,Integer>>("iterate") {
            int test(Map<Integer,Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for(int i = 0; i < loops; i ++) {
                    Iterator it = map.entrySet().iterator();
                    while(it.hasNext())
                        it.next();
                }
                return loops * map.size();
            }
        });
    }
    public static void main(String[] args) {
        if(args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.run(new TreeMap<Integer,Integer>(), tests);
        Tester.run(new HashMap<Integer,Integer>(), tests);
        Tester.run(new LinkedHashMap<Integer,Integer>(),tests);
        Tester.run(
                new IdentityHashMap<Integer,Integer>(), tests);
        Tester.run(new WeakHashMap<Integer,Integer>(), tests);
        Tester.run(new Hashtable<Integer,Integer>(), tests);
        Tester.run(new SlowMapFixed<Integer,Integer>(), tests); // naprawiona SlowMap
        Tester.run(new SlowMapNotFixed<Integer,Integer>(), tests); // nie naprawiona SlowMap
    }
}

/*
SlowMapNotFixed.clear() nie czyści prawdziwych danych.
entrySet() pokazuje kopię stanu keys/values dlatego mapa może wyglądać jakby clear() nie działał.
 */