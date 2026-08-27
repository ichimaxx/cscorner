import myutils.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 36: (5) Modify SlowMap so that instead of two ArrayLists, it holds a single
ArrayList of MapEntry objects. Verify that the modified version works correctly. Using
MapPerformance.java, test the speed of your new Map. Now change the put( ) method
so that it performs a sort( ) after each pair is entered, and modify get( ) to use
Collections.binarySearch( ) to look up the key. Compare the performance of the new
version with the old ones.
*/
class MapEntry_6<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    public MapEntry_6(K key, V value) {
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
        if(!(o instanceof MapEntry_6)) return false;
        MapEntry_6 me = (MapEntry_6)o;
        return
                (key == null ?
                        me.getKey() == null : key.equals(me.getKey())) &&
                        (value == null ?
                                me.getValue()== null : value.equals(me.getValue()));
    }
    public String toString() { return key + "=" + value; }
}
class SlowMapSingleArray<K extends Comparable<K>,V> extends AbstractMap<K, V> {
    private List<Map.Entry<K ,V>> wejscia = new ArrayList<Map.Entry<K,V>>();
    private Comparator<Map.Entry<K,V>> entryComparator = new Comparator<Map.Entry<K,V>>() {
        public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
            return e1.getKey().compareTo(e2.getKey());
        }
    };
    public V put(K key, V value) {
        V oldValue = get(key);// The old value or null
        for (int i = 0; i < wejscia.size(); i++) {
            Map.Entry<K,V> raz = wejscia.get(i);
            if (raz.getKey().equals(key)) {
                wejscia.set(i, new MapEntry_6<K,V>(key,value));
                Collections.sort(wejscia, entryComparator);
                return oldValue;
            }
        }
        wejscia.add(new MapEntry_6<K,V>(key, value));
        Collections.sort(wejscia, entryComparator);
        return oldValue;
    }

    public V get(Object key) {
        //generuje tymczasowy Entry z szukanym kluczem, value nie ma znaczenia, bo comparator porównuje tylko klucze
        //get() w interfejsie Map przyjmuje Object, dlatego tutaj rzutuje się key na K
        Map.Entry<K,V> s = new MapEntry_6<K,V>((K) key, null);// The old value or null
        //szuka binarnie po posortowanej liście,
        int index = Collections.binarySearch(wejscia, s, entryComparator);
        if (index < 0) {
            return null;
        }
        return wejscia.get(index).getValue();
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
    public Set<Entry<K, V>> entrySet() {
        //entrySet() zwraca AbstractSet jako widok na prawdziwe dane mapy,
        //czyli listy keys i values, dzięki czemu usuwanie przez iterator zmienia mapę
        return new AbstractSet<Map.Entry<K, V>>() {
            public int size() {
                return wejscia.size();
            }

            public Iterator<Map.Entry<K, V>> iterator() {
                return new Iterator<Map.Entry<K, V>>() {
                    int index = 0;
                    int last = -1;

                    public boolean hasNext() {
                        return index < wejscia.size();
                    }

                    public Map.Entry<K, V> next() {
                        last = index;
                        return wejscia.get(index++);
                    }

                    public void remove() {
                        if (last == -1) // jeżeli last = -1 to oznacza, że nie ma aktualnego elementu do usunięcia
                            throw new IllegalStateException();
                        wejscia.remove(last);
                        index = last;
                        last = -1;
                    }
                };
            }
        };
    }
}
class SlowMapFixed_1<K,V> extends AbstractMap<K,V> {
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
                        MapEntry_6<K, V> k = new MapEntry_6<K, V>(keys.get(index), values.get(index));
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
class SlowMapNotFixed_1<K,V> extends AbstractMap<K,V> {
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
            set.add(new MapEntry_6<K,V>(ki.next(), vi.next()));
        return set;
    }
}
public class Zad17_36 {
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
        Tester.run(new SlowMapSingleArray<Integer,Integer>(), tests);
        Tester.run(new SlowMapFixed_1<Integer,Integer>(), tests); // naprawiona SlowMap
        Tester.run(new SlowMapNotFixed_1<Integer,Integer>(), tests); // nie naprawiona SlowMap
    }
}

/*
SlowMapSingleArray przyspiesza głównie operacje get(), ponieważ zamiast liniowego wyszukiwania używa binarySearch()
na posortowanej liście Map.Entry.

Nie jest to porównywalne z HashMap, ponieważ put() nadal przeszukuje listę, oraz po każdym dodaniu sortuje całą listę.
Przez co koszt dodawania zostaje duży.
---------- TreeMap ----------
 size     put     get iterate
   10     103      34      10
  100      21       6       2
 1000      24       9       2
10000      31      24       2
---------- HashMap ----------
 size     put     get iterate
   10      59      35      11
  100       4       0       5
 1000       6       1       3
10000       5       1       2
------- LinkedHashMap -------
 size     put     get iterate
   10      28      30      17
  100      12       2       2
 1000       9       3       2
10000       9       3       2
------ IdentityHashMap ------
 size     put     get iterate
   10      31       7      12
  100       9       8       4
 1000      19      18       4
10000      21      21       6
-------- WeakHashMap --------
 size     put     get iterate
   10      37      18      11
  100      15       2       5
 1000       8       2       5
10000       9       2      39
--------- Hashtable ---------
 size     put     get iterate
   10      30      12       9
  100      12       9       5
 1000      12       9       5
10000      11       9       5
----- SlowMapSingleArray -----
 size     put     get iterate
   10     235      33       7
  100     138       8       3
 1000     984      12       3
10000   12402      28       3
-------- SlowMapFixed --------
 size     put     get iterate
   10     130      44      11
  100      67      30       5
 1000     356     291       5
10000    3341    3094       5
------ SlowMapNotFixed ------
 size     put     get iterate
   10     128      14      64
  100     328      32     182
 1000    1587     278     923
10000   17047    3831
*/