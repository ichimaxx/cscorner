import java.util.*;

/*
Exercise 25: (6) Instead of using a Listlterator for each bucket, modify MapEntry so
that it is a self-contained singly linked list (each MapEntry should have a forward link to the
next MapEntry). Modify the rest of the code in SimpleHashMap.java so that this new
approach works correctly.
*/
class MapEntryx<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    public MapEntryx(K key, V value) {
        this.key = key;
        this.value = value;
    }
    //link do nastepnego wpisu w tym samym buckecie.
    //dzięki czemu MapEntryx działa jak element listy jednokierunkowej.
    MapEntryx<K,V> next;

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
        if(!(o instanceof MapEntryx)) return false;
        MapEntryx me = (MapEntryx)o;
        return
                (key == null ?
                        me.getKey() == null : key.equals(me.getKey())) &&
                        (value == null ?
                                me.getValue() == null : value.equals(me.getValue()));
    }
    public String toString() { return key + "=" + value; }
}

public class Zad17_25<K,V> extends AbstractMap<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can’t have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    MapEntryx<K,V>[] buckets = new MapEntryx[SIZE];
    public V put(K key, V value) {
        V oldValue = null;
        //nowy wpis key,value
        MapEntryx<K, V> news = new MapEntryx<K, V>(key, value);
        int index = Math.abs(key.hashCode()) % SIZE;
        //jeżeli bucket jest pusty, nowy bucket staje się pierwszym elementem
        if (buckets[index] == null) {
            buckets[index] = news;
            return null;
        }
        MapEntryx<K, V> current = buckets[index];
        //jeżeli bucket nie jest pusty, zaczyna od pierwszego elementu i idzie po kolejnych przez current.next
        while (current != null) {
            //jeżeli w bucket istnieje już wpis z tym samym kluczem, nie dodaje nowego elementu,
            //tylko podmienia value w istniejącym wpisie.
            if (current.getKey().equals(key)) {
                oldValue = current.getValue();
                current.setValue(value); // Replace old with new
                return oldValue;
            }
            //jeżeli current.next == null to znaczy że jest na końcu listy w tym buckecie
            //dodane wpis na koniec łańcucha
            if (current.next == null) {
                current.next = news;
                return null;
            }
            current = current.next;
        }
        return oldValue;
    }
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        MapEntryx<K, V> current = buckets[index];
        if (buckets[index] == null)
            return null;
        while (current != null) {
            if (current.getKey().equals(key))
                return current.getValue();
            current = current.next;
        }
        return null;
    }
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        for (MapEntryx<K, V> bucket : buckets) {
            MapEntryx<K, V> current = bucket;
            //przejście po całym łańcuchu wpisów,
            //current > current.next > current.next > null
            while (current != null) {
                set.add(current);
                current = current.next;
            }
        }
        return set;
    }

    public static void main(String[] args) {
        Zad17_25<String,String> m =
                new Zad17_25<String,String>();
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        System.out.println(m.get("ERITREA"));
        System.out.println(m.entrySet());
    }
}

//bucket już nie jest LinkedList,
//jest teraz początkiem własnej listy zbudowanej z MapEntryx.next.
