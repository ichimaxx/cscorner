import myutils.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 37: (2) Modify SimpleHashMap to use ArrayLists instead of LinkedLists.
Modify MapPerformance.java to compare the performance of the two implementations.
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
class SimpleHashMapArrayList<K,V> extends AbstractMap<K,V> {
    static final int SIZE = 997;
    @SuppressWarnings("unchecked")
    ArrayList<MapEntry<K,V>>[] buckets =
            new ArrayList[SIZE];
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            buckets[index] = new ArrayList<MapEntry<K,V>>();
        ArrayList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K,V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while(it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if(iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if(!found)
            buckets[index].add(pair);
        return oldValue;
    }
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null) return null;
        for(MapEntry<K,V> iPair : buckets[index])
            if(iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set= new HashSet<Map.Entry<K,V>>();
        for(ArrayList<MapEntry<K,V>> bucket : buckets) {
            if(bucket == null) continue;
            for(MapEntry<K,V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }
    public boolean containsKey(Object key) {
        boolean flag = false;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] != null) {
            for (MapEntry<K, V> k : buckets[index]) {
                //pętla po bucket z index
                if (k.getKey().equals(key))
                    //porównanie key z pętli do key z argumentu
                    flag = true;
                //zmiana flagi na true
            }
        }
        return flag;
    }
    public boolean containsValue(Object value) {
        boolean flag = false;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for (MapEntry<K, V> k : buckets[i]) {
                    //przechodzący po wszystkich bucketach, bo value nie da się znaleźć przez hashCode klucza(index)
                    if (k.getValue().equals(value)) {
                        //porównanie value z pętli do value z argumentu
                        flag = true;
                        //zmiana flagi na true
                    }
                }
            }
        }

        return flag;
    }
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        //jeżeli pod tym indeksem nie ma listy, klucza nie ma na mapie (zwraca null)
        if (buckets[index] == null)
            return null;
        ArrayList<MapEntry<K, V>> bucket = buckets[index];
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        // iterator do usuwania par
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                //zapis w pamięci starej wartości klucza
                V oldValue = iPair.getValue();
                it.remove();
                //metoda map.remove(key) powinna zwrócić starą wartość usunietego klucza
                return oldValue;
            }
        }
        return null;
    }
    public int size() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for(MapEntry<K,V> pair : buckets[i])
                    z++;
            }
        }
        return z;
    }
    public boolean isEmpty() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for(MapEntry<K,V> pair : buckets[i])
                    z++;
            }
        }
        return (z == 0 ? true : false);
    }
    public void clear() {
        for (int i = 0; i < SIZE; i++)
            buckets[i] = null;
        //czyści mapę bucket po buckecie ustawiając je na null
    }
}
class SimpleHashMapLinkedList<K,V> extends AbstractMap<K,V> {
    static final int SIZE = 997;
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets =
            new LinkedList[SIZE];
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K,V>>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K,V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while(it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if(iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if(!found)
            buckets[index].add(pair);
        return oldValue;
    }
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null) return null;
        for(MapEntry<K,V> iPair : buckets[index])
            if(iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set= new HashSet<Map.Entry<K,V>>();
        for(LinkedList<MapEntry<K,V>> bucket : buckets) {
            if(bucket == null) continue;
            for(MapEntry<K,V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }
    public boolean containsKey(Object key) {
        boolean flag = false;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] != null) {
            for (MapEntry<K, V> k : buckets[index]) {
                //pętla po bucket z index
                if (k.getKey().equals(key))
                    //porównanie key z pętli do key z argumentu
                    flag = true;
                //zmiana flagi na true
            }
        }
        return flag;
    }
    public boolean containsValue(Object value) {
        boolean flag = false;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for (MapEntry<K, V> k : buckets[i]) {
                    //przechodzący po wszystkich bucketach, bo value nie da się znaleźć przez hashCode klucza(index)
                    if (k.getValue().equals(value)) {
                        //porównanie value z pętli do value z argumentu
                        flag = true;
                        //zmiana flagi na true
                    }
                }
            }
        }

        return flag;
    }
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        //jeżeli pod tym indeksem nie ma listy, klucza nie ma na mapie (zwraca null)
        if (buckets[index] == null)
            return null;
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        // iterator do usuwania par
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                //zapis w pamięci starej wartości klucza
                V oldValue = iPair.getValue();
                it.remove();
                //metoda map.remove(key) powinna zwrócić starą wartość usunietego klucza
                return oldValue;
            }
        }
        return null;
    }
    public int size() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for(MapEntry<K,V> pair : buckets[i])
                    z++;
            }
        }
        return z;
    }
    public boolean isEmpty() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for(MapEntry<K,V> pair : buckets[i])
                    z++;
            }
        }
        return (z == 0 ? true : false);
    }
    public void clear() {
        for (int i = 0; i < SIZE; i++)
            buckets[i] = null;
        //czyści mapę bucket po buckecie ustawiając je na null
    }
}
        public class Zad17_37 {
            static List<Test<Map<String,String>>> tests =
                    new ArrayList<Test<Map<String,String>>>();
            static {
                tests.add(new Test<Map<String,String>>("put") {
                    int test(Map<String,String> map, TestParam tp) {
                        int loops = tp.loops;
                        int size = tp.size;
                        String[] arrayes = Generated.array(String.class, new CountingGenerator.String(), size);
                        for(int i = 0; i < loops; i++) {
                            map.clear();
                            for(int j = 0; j < size; j++)
                                map.put(arrayes[j], arrayes[j]);
                        }
                        return loops * size;
                    }
                });
                tests.add(new Test<Map<String,String>>("get") {
                    int test(Map<String,String> map, TestParam tp) {
                        int loops = tp.loops;
                        int span = tp.size * 2;
                        String[] arrayes = Generated.array(String.class, new CountingGenerator.String(), span);
                        for(int i = 0; i < loops; i++)
                            for(int j = 0; j < span; j++)
                                map.get(arrayes[j % span]);
                        return loops * span;
                    }
                });
                tests.add(new Test<Map<String,String>>("iterate") {
                    int test(Map<String,String> map, TestParam tp) {
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
                Tester.run(new SimpleHashMapArrayList<String,String>(), tests);
                Tester.run(new SimpleHashMapLinkedList<String,String>(), tests);
            }
}
/*
Obie implementacje HashMap działają logicznie tak samo. Różnią się tylko typem listy używanej w bucketach:
SimpleHashMapArrayList używa ArrayList, a SimpleHashMapLinkedList używa LinkedList.

Przy dobrym rozłożeniu hashCode() różnica wydajności nie będzie duża, bo większośc bucketów ma mało elementów.
ArrayList może być lepszy przy przeszukiwaniu sekwencyjnym bucketa, ponieważ elementy są przechowywane bliżej siebie
w pamięci. LinkedList nie daje tutaj dużej przewagi, bo mapa przeszukuje bucket i dodaje elementy na końcu.
Największy wpływ na szybkość ma liczba kolizji, czyli ile różnych kluczy trafia do tego samego bucketa.

--- SimpleHashMapArrayList ---
 size     put     get iterate
   10     142      30     100
  100      25       7     112
 1000       7       6     104
10000      11       6      98
-- SimpleHashMapLinkedList --
 size     put     get iterate
   10     172      33      93
  100      31       7     100
 1000      11       7     101
10000      10       7      98
*/