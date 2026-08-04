import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
import myutils.*;
import net.mindview.util.Pair;

/*
Exercise 40: (6) Following the example of ReaderWriterList.java, create a
ReaderWriterMap using a HashMap. Investigate its performance by modifying
MapComparisons.java. How does it compare to a synchronized HashMap and a
ConcurrentHashMap?
*/

//klasa pomocnicza służąca do tworzenia map wypełnionych danymi pochądzącymi z generatorów
class MapData<K,V> extends LinkedHashMap<K,V> {
    // A single Pair Generator:
    public MapData(Generator<Pair<K,V>> gen, int quantity) {
        for(int i = 0; i < quantity; i++) {
            Pair<K,V> p = gen.next();
            put(p.key, p.value);
        }
    }
    // Two separate Generators:
    public MapData(Generator<K> genK, Generator<V> genV,
                   int quantity) {
        for(int i = 0; i < quantity; i++) {
            put(genK.next(), genV.next());
        }
    }
    // A key Generator and a single value:
    public MapData(Generator<K> genK, V value, int quantity){
        for(int i = 0; i < quantity; i++) {
            put(genK.next(), value);
        }
    }
    // An Iterable and a value Generator:
    public MapData(Iterable<K> genK, Generator<V> genV) {
        for(K key : genK) {
            put(key, genV.next());
        }
    }
    // An Iterable and a single value:
    public MapData(Iterable<K> genK, V value) {
        for(K key : genK) {
            put(key, value);
        }
    }
    // Generic convenience methods:
    public static <K,V> MapData<K,V>
    map(Generator<Pair<K,V>> gen, int quantity) {
        return new MapData<K,V>(gen, quantity);
    }
    public static <K,V> MapData<K,V>
    map(Generator<K> genK, Generator<V> genV, int quantity) {
        return new MapData<K,V>(genK, genV, quantity);
    }
    public static <K,V> MapData<K,V>
    map(Generator<K> genK, V value, int quantity) {
        return new MapData<K,V>(genK, value, quantity);
    }
    public static <K,V> MapData<K,V>
    map(Iterable<K> genK, Generator<V> genV) {
        return new MapData<K,V>(genK, genV);
    }
    public static <K,V> MapData<K,V>
    map(Iterable<K> genK, V value) {
        return new MapData<K,V>(genK, value);
    }
}
//benchmark
//C oznacza typ testowanego kontenera
abstract class Testers<C> {
    //liczba powtórzeń każdego testu
    static int testReps = 10;
    //ile razy każdy wątek przejdzie przez cały kontener
    static int testCycles = 1000;
    //liczba elementów w testowanym kontenerze
    static int containerSize = 1000;
    //klasa dziedzicząca, która musi określić
    //jak uruchomić testowany kontener
    //jak uruchomić readerów i writerów
    abstract C containerInitializer();
    abstract void startReadersAndWriters();
    //aktualnie testowany kontener
    C testContainer;
    //wyświetlana nazwa testu
    String testId;
    //liczba wątków readera
    int nReaders;
    //liczba wątków writera
    int nWriters;
    volatile long readResult = 0;
    //suma czasów działania wszystkich readerów
    volatile long readTime = 0;
    //suma czasów działania wszystkich writerów
    volatile long writeTime = 0;
    //w czasie odliczania główny wątek czeka aż wszyscy readery i writery zakończą test
    CountDownLatch endLatch;
    static ExecutorService exec =
            Executors.newCachedThreadPool();
    Integer[] writeData;
    Testers(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " +
                nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        //tablica losowych danych zapisywana przez writerów
        writeData = Generated.array(Integer.class,
                new RandomGenerator.Integer(), containerSize);
        //każdy wariant benchmarku jest wykonywany kilka razy
        for(int i = 0; i < testReps; i++) {
            runTest();
            //zerowane wyniki czasowe przed kolejnym powtórzeniem
            readTime = 0;
            writeTime = 0;
        }
    }
    //jedno pełne powtórzenie testu
    void runTest() {
        //jeżeli 9 readerów i 1 writer = odlicza do 10
        endLatch = new CountDownLatch(nReaders + nWriters);
        //przed każdym powtórzeniem tworzona nowa mapa
        testContainer = containerInitializer();
        //uruchamianie wszystkich readerów i writerów
        startReadersAndWriters();
        try {
            //główny wątek czeka, dopóki wszystkie zadania nie wykonają endLatch.countDown()
            endLatch.await();
        } catch(InterruptedException ex) {
            System.out.println("endLatch interrupted");
        }
        //wyniki podawane w nanosekundach
        System.out.printf("%-27s %14d %14d\n",
                testId, readTime, writeTime);
        if(readTime != 0 && writeTime != 0)
            System.out.printf("%-27s %14d\n",
                    "readTime + writeTime =", readTime + writeTime);
    }
    //klasa pojedynczego zadania testowego
    abstract class TestTask implements Runnable {
        //właściwe operacje wykonywane przez zadanie
        abstract void test();
        //dodanie wyniku zadania do wspólnych wyników benchmarku
        abstract void putResults();
        //czas wykonywania konkretnego zadania
        long duration;
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized(Testers.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }
    public static void initMain(String[] args) {
        if(args.length > 0)
            testReps = Integer.parseInt(args[0]);
        if(args.length > 1)
            testCycles = Integer.parseInt(args[1]);
        if(args.length > 2)
            containerSize = Integer.parseInt(args[2]);
        System.out.printf("%-27s %14s %14s\n",
                "Type", "Read time", "Write time");
    }
}

//szkielet testu przeznaczony dla Map<Integer, Integer>
abstract class MapTest
        extends Testers<Map<Integer,Integer>> {
    MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }
    //wykonuje odczyty
    class Reader extends TestTask {
        long result = 0;
        void test() {
            //każdy reader testCycles razy odczytuje wszystkie wartości z mapy
            for(long i = 0; i < testCycles; i++)
                for(int index = 0; index < containerSize; index++)
                    result += testContainer.get(index);
        }
        void putResults() {
            //dodaje odczytane wartości do wspólnego wyniku
            readResult += result;
            //dodaje czas tego readera do sumy czasów odczytu
            readTime += duration;
        }
    }
    //wykonuje zapisy
    class Writer extends TestTask {
        void test() {
            for(long i = 0; i < testCycles; i++)
                for(int index = 0; index < containerSize; index++)
                    testContainer.put(index, writeData[index]);
        }
        void putResults() {
            writeTime += duration;
        }}
    //tworzy zadaną liczbę readerów i writerów i przekazuje do ExecutorService
    void startReadersAndWriters() {
        for(int i = 0; i < nReaders; i++)
            exec.execute(new Reader());
        for(int i = 0; i < nWriters; i++)
            exec.execute(new Writer());
    }
}
//test zwykłej hashmap opakowanej w synchronizedMap
class SynchronizedHashMapTest extends MapTest {
    Map<Integer,Integer> containerInitializer() {
        return Collections.synchronizedMap(
                new HashMap<Integer,Integer>(
                        MapData.map(
                                new CountingGenerator.Integer(),
                                new CountingGenerator.Integer(),
                                containerSize)));
    }
    SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("Synched HashMap", nReaders, nWriters);
    }
}
//test ConcurrentHashMap
class ConcurrentHashMapTest extends MapTest {
    Map<Integer,Integer> containerInitializer() {
        return new ConcurrentHashMap<Integer,Integer>(
                MapData.map(
                        new CountingGenerator.Integer(),
                        new CountingGenerator.Integer(), containerSize));
    }
    ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }
}
//test mapy Zad21_40
class LockedHashMapTest extends MapTest {
    Map<Integer,Integer> containerInitializer() {
        return new Zad21_40<Integer>(containerSize, new CountingGenerator.Integer());
    }
    LockedHashMapTest(int nReaders, int nWriters) {
        super("Locked HashMap", nReaders, nWriters);
    }
}

//mapa zabezpieczona ReentrantReadWriteLock
//klucze i wartości typu Integer tak jak inne testowane mapy
public class Zad21_40<V> extends AbstractMap<Integer, V> {
    //właściwe dane przechowywane w zwykłej HashMap
    //ta mapa nie jest bezpieczna wątkowo, dostęp do niej jest chroniony blokadami
    private HashMap<Integer, V> lockedMap;
    //true oznacza sprawiedliwy typ blokady, wątki oczekujące na blokadę są obsługiwane
    //w kolejności zbliżonej do kolejności zgłoszeń
    private ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock(true);
    //tworzenie mapy o określonym rozmiarze
    public Zad21_40(int size, Generator<V> initialValue) {
        lockedMap = new HashMap<Integer, V>();
        for(int i = 0; i < size; i++)
            lockedMap.put(i, initialValue.next());
    }
    //zapis wymaga writeLock
    @Override
    public V put(Integer key, V value) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return lockedMap.put(key, value);
        } finally {
            wlock.unlock();
        }
    }
    //odczyt wymaga readLock
    //wiele wątków może jednocześnie posiadać readLock,
    //pod warunkiem że żaden Writer nie posiada writeLock
    @Override
    public V get(Object key) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            return lockedMap.get(key);
        } finally {
            rlock.unlock();
        }
    }
    //AbstractMap wymaga implementacji entrySet()
    @Override
    public Set<Entry<Integer, V>> entrySet() {
        Lock rlock = lock.readLock();
        rlock.lock();

        try {
            Set<Entry<Integer, V>> set = new HashSet<Entry<Integer,V>>();
            for(Entry<Integer, V> entry : lockedMap.entrySet()) {
                set.add(entry);
            }
            return set;
        } finally {
            rlock.unlock();
        }
    }
    //uruchomienie benchmarku
    public static void main(String[] args) {
        Testers.initMain(args);
        //10 readerów 0 writerów itd...
        new SynchronizedHashMapTest(10, 0);
        new SynchronizedHashMapTest(9, 1);
        new SynchronizedHashMapTest(5, 5);
        new ConcurrentHashMapTest(10, 0);
        new ConcurrentHashMapTest(9, 1);
        new ConcurrentHashMapTest(5, 5);
        new LockedHashMapTest(10, 0);
        new LockedHashMapTest(9, 1);
        new LockedHashMapTest(5, 5);
        Testers.exec.shutdown();
    }
}

/*
Najlepiej wypadła ConcurrentHashMap - była zdecydowanie szybsza zarówno w samych odczytach,
jak i przy jednoczesnym odczycie i zapisie.

Collection.synchronizedMap() była wolniejsza,
ponieważ używa jednego wspólnego zamka i wątki blokują się nawzajem.

Najgorzej wypada LockedHashMap z ReentrantReadWriteLock(true).
Sprawiedliwa blokada tworzy kolejkę wątków, przez co przy pojawieniu się Writera
powstaje bardzo duży narzut.
*/