import myutils.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 39: (6) Add a private rehash( ) method to SimpleHashMap that is
invoked when the load factor exceeds 0.75. During rehashing, double the number of buckets,
then search for the first prime number greater than that to determine the new number of
buckets.
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
class Zad17_39<K,V> extends AbstractMap<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    private int SIZE = 997;
    // You can’t have a physical array of generics,
    // but you can upcast to one:
    private int zliczacz = 0;
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets =
            new LinkedList[SIZE];
    @SuppressWarnings("unchecked")
    // suppresed wyciszony, bo java nie pozwala bezpiecznie tworzyć tablic generycznych
    private void rehash() {
        //zapis starej tablicy bucketów, aby przenieść z niej elementy do nowej tablicy
        LinkedList<MapEntry<K,V>>[] starebucket = buckets;
        // podwaja liczbe bucketów tak jak w zadaniu, a potem szuka pierwszą większą liczbę pierwszą
        int newSize = nextPrime(SIZE * 2 + 1);
        //aktualizacja SIZE
        SIZE = newSize;
        //nowa, większa tablica bucketów
        buckets = new LinkedList[SIZE];
        //licznik elementów został wyzerowany, bo elementy będą na nowo dodawane przez put()
        zliczacz = 0;
        //pętla dodająca stare wpisy ponownie, ponieważ po zmianie SIZE każdy klucz może dostać nowy index
        for(LinkedList<MapEntry<K,V>> bucket : starebucket) {
            if (bucket == null) continue;
            for (MapEntry<K, V> entry : bucket)
                put(entry.getKey(), entry.getValue());
        }
    }
    //metoda sprawdzjąca liczbę pierwszą
    private boolean isPrime(int n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;

        for(int i = 3; i * i <= n; i += 2)
            if(n % i == 0)
                return false;

        return true;
    }

    private int nextPrime(int n) {
        while(!isPrime(n))
            n++;
        return n;
    }
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(pair);
            zliczacz++;
            if ((double)zliczacz / buckets.length > 0.75 ) {
                rehash();
            }
        }

        return oldValue;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }

    public boolean containsKey(Object key) {
        boolean flag = false;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] != null) {
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
                zliczacz--;
                //metoda map.remove(key) powinna zwrócić starą wartość usunietego klucza
                return oldValue;
            }
        }
        return null;
    }

    public int size() {
        return zliczacz;
    }

    public boolean isEmpty() {
        int z = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                for (MapEntry<K, V> pair : buckets[i])
                    z++;
            }
        }
        return (z == 0 ? true : false);
    }

    public void clear() {
        for (int i = 0; i < SIZE; i++)
            buckets[i] = null;
        zliczacz = 0;
        //czyści mapę bucket po buckecie ustawiając je na null
    }
    public static void main(String[] args){
        Zad17_39<String, String> kk = new Zad17_39<>();
        println("\nRozmiar mapy początkowo:");
        println("kk.size(): " + kk.size());
        println("kk.SIZE: " + kk.SIZE);
        kk.putAll(Countries.capitals(156));
        println("\nRozmiar mapy po dodaniu 156 wartości:");
        println("kk.size(): " +  kk.size());
        println("kk.SIZE: " + kk.SIZE);
        for(int i = 0; i < 80330; i++)
            kk.put(Integer.toString(i), Integer.toString(i));
        println("\nRozmiar mapy po dodaniu dodatkowych 80330 wartości:");
        println("kk.size(): " + kk.size());
        println("kk.SIZE: " + kk.SIZE);
    }
}
