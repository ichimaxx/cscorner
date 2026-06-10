import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 24: (5) Following the example in SimpleHashMap.java, create and test a
SimpleHashSet.
*/

public class Zad17_24<E> extends AbstractSet<E> {
    //Rozmiar tablicy bucketów,
    //liczba pierwsza 997
    static final int SIZE = 997;
    //Tablica bucketów,
    //każdy bucket to LinkedList przechowująca elementy typu E
    //Tablica nie trzyma bezpośrednio jednego elementu, tylko listę elementów
    //bo kilka elementów może trafić do tego samego indeksu - tak zwana collision z książki.
    @SuppressWarnings("unchecked")
    LinkedList<E>[] buckets = new LinkedList[SIZE];
    @Override
    public boolean add(E key) {
        if (key == null) { // jeśli key będzie null, wyrzuci wyjątek
            throw new NullPointerException();
        }
        //oblicza indeks bucketa
        //key.hashCode() daje liczbę hashująca element,
        //Math.abs() robi z niej liczbe dodatnią,
        //a % SIZE dopasowuje ją do zakresu indeksów tablicy 0-996 (reszta z dzielenia)
        int index = Math.abs(key.hashCode()) % SIZE;

        //jeżeli pod tym indeksem nie ma jeszcze listy, tworzy nowy bucket jako LinkedList
        if(buckets[index] == null)
            buckets[index] = new LinkedList<E>();

        //pobieranie bucket, listę elementów znajdującą się pod tym indeksem
        LinkedList<E> bucket = buckets[index];

        //flaga, która mówi, że znaleziono już taki element w buckecie
        boolean found = false;

        //listIterator dla konkretnego bucket, aby można było po niej przechodzić
        ListIterator<E> it = bucket.listIterator();

        //obsługa kolizji(collision)
        while(it.hasNext()) {
            E iPair = it.next();

            //sprawdza czy element istnieje
            //hashCode() tylko wskazał bucket, a equals sprawdza dokładnie element.
            if(iPair.equals(key)) {
                //flaga na true, bo element został znaleziony
                found = true;
                break;
            }
        }

        //jeżeli nie znaleziono elementu w bucket, dodaje nowy na koniec listy
        if(!found) {
            buckets[index].add(key);
            return true;
        }
        //zwraca false, jeżeli element już istniał w secie
        return false;
    }

    @Override

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            //indeks sprawdzanego aktualnie bucketa
            private int bucketIndex = 0;
            //iterator aktualnego bucketa, przechodzi po elementach, jednej LinkedList
            private Iterator<E> bucketIterator = null;

            //Przesuwa bucketIterator do następnego niepustego bucketa,
            //Jeżeli aktualny bucketIterator jest null albo nie ma już elementów,
            //szuka kolejnego bucketa, który nie jest null.
            private void nastepnybucket() {
                while ((bucketIterator == null || !bucketIterator.hasNext()) && bucketIndex < SIZE) {

                    //jeżeli bucket istnieje, tworzy iterator po jego elementach.
                    if (buckets[bucketIndex] != null) {
                        bucketIterator = buckets[bucketIndex].iterator();
                    }
                    //przejście do następnego indeksu bucketów
                    bucketIndex++;
                }
            }
            public boolean hasNext() {
                //upewnia się, że iterator nie stoi na pustym buckecie
                nastepnybucket();
                //return true jeżeli bucketIterator istnieje (bucketIterator != null)
                //i ma następny element(bucketIterator.hasNext())
                return bucketIterator != null && bucketIterator.hasNext();
            }

            public E next() {
                //przesuwa się do następnego bucketa z elementami
                nastepnybucket();

                //jeżeli nie ma już żadnego elementu w żadnym buckecie, next() rzuca wyjątek
                if(bucketIterator == null || !bucketIterator.hasNext())
                    throw new NoSuchElementException();
                //zwracanie kolejnego elementu z aktualnego bucketa
                return bucketIterator.next();
            }

            public void remove() {
                //jeżeli iterator nie został ustawiony na żaden bucket nie można usunąć elementu
                if (bucketIterator == null)
                    throw new IllegalStateException();
                //usuwa element zwrócony przez bucketIterator.next()
                bucketIterator.remove();
            }
        };
    }

    @Override
    public int size() {
        int z = 0;
        //pętla po bucketach
        for (int i = 0; i < SIZE; i++) {
            if (buckets[i] != null) {
                //liczy elementy w każdym buckecie z elementami w środku
                for(E pair : buckets[i])
                    z++;
            }
        }
        return z;
    }

    public static void main(String[] args) {
        Zad17_24<Integer> k = new Zad17_24<>();
        for(int i = 0; i < 10; i++) {
            k.add(i + 5);
        }
        k.add(1); // dodane 1 na końcu mimo wszystko wyprowadzone jest na początku listy
        println(k);
    }
}

