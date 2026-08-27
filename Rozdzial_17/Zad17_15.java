import java.util.*;
import static myutils.Skrocenie_Print.*;

/*
Exercise 15: (1) Repeat Exercise 13 using a SlowMap.
*/
class MapEntry_4<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    public MapEntry_4(K key, V value) {
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
        if(!(o instanceof MapEntry_4)) return false;
        MapEntry_4 me = (MapEntry_4)o;
        return
                (key == null ?
                        me.getKey() == null : key.equals(me.getKey())) &&
                        (value == null ?
                                me.getValue()== null : value.equals(me.getValue()));
    }
    public String toString() { return key + "=" + value; }
}

public class Zad17_15 <K,V> extends AbstractMap<K,V> {
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
        if(!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set= new HashSet<Map.Entry<K,V>>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while(ki.hasNext())
            set.add(new MapEntry_4<K,V>(ki.next(), vi.next()));
        return set;
    }
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>(new myutils.TextFile("Zad17_13.java", "\\W+"));
        Iterator<String> itt = words.iterator();
        Zad17_15<String,Integer> m = new Zad17_15<String,Integer>();
        try {
            while(itt.hasNext()) {
                String word = itt.next();
                Integer count = m.get(word);
                if (count == null) {
                    // jeżeli nie ma słowa takiego słowa w mapie, value ma być 1
                    m.put(word, 1);
                } else {
                    m.put(word, count + 1);
                    // jeżeli jest już słowo w mapie, zwiększa jego licznik o 1
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            println("Too many objects!");
        }
        println(m);
    }
}

/*
Do liczenia wystąpień słów z pliku użyto własnej mapy SlowMap,
opartej na dwóch listach: keys i values.
*/