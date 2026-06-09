import static myutils.Skrocenie_Print.*;
import java.util.*;

/*
Exercise 22: (4) Implement the clear( ) and remove( ) methods for SimpleHashMap(Zad17_22).
*/
public class Zad17_22<K,V> extends AbstractMap<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    private static int probes = 0;
    // You can’t have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets =
            new LinkedList[SIZE];
    public V put(K key, V value) {
        int probesForCollision = 0; // pole lokalne zliczające pojedyncze it.next dla każdej nowo dodanej kolizji
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K,V>>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K,V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while(it.hasNext()) {
            probesForCollision++;
            probes++;
            MapEntry<K,V> iPair = it.next();
            if(iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                println("COLLISION at bucket " + index + ": "
                        + iPair.getKey() + " and " + key + ", probes: " + probesForCollision);
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
    public void clear() {
        for (int i = 0; i < SIZE; i++)
            buckets[i] = null;
        //czyści mapę bucket po buckecie ustawiając je na null
    }
    public static void main(String[] args) {
        Zad17_22<String,String> m =
                new Zad17_22<String,String>();
        m.putAll(Countries.capitals(25));
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        println("\nPROBES: " + probes);

        m.remove("LESOTHO");
        println("\nMAPA PO UZYCIU m.remove(\"LESOTHO\")\n");
        System.out.println(m);

        m.clear();
        println("\nMAPA PO UZYCIU m.clear()\n");
        System.out.println(m);
    }
}
