import java.util.concurrent.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 11: (3) Create a class containing two data fields, and a method that manipulates
those fields in a multistep process so that, during the execution of that method, those fields
are in an "improper state" (according to some definition that you establish). Add methods to
read the fields, and create multiple threads to call the various methods and show that the
data is visible in its "improper state." Fix the problem using the synchronized keyword.
*/
abstract class IntGenerator {
    //volatile gwarantuje, że zmiana wartości canceled będzie widoczna dla wszystkich wątków
    private static volatile boolean canceled = false;
    public abstract int testx();
    // Allow this to be canceled:
    public static void cancel() { canceled = true; }
    public static boolean isCanceled() { return canceled; }
}
public class Zad21_11 extends IntGenerator implements Runnable {
    private static int jednaWartosc = 0;
    private static int drugaWartosc = 0;
    private final int id;
    public Zad21_11(int ident) {
        id = ident;
    }
    //metody są static synchronized,
    //dla metody statycznej synchronized używa blokady: Zad21_11.class
    //jest to ta sama blokada, której używa metoda testx()
    public static synchronized int getJednaWartosc() {
        return jednaWartosc;
    }
    public static synchronized int getDrugaWartosc() {
        return drugaWartosc;
    }
    //każdy obiekt Zad21_11 jest osobnym zadaniem uruchamianym przez ExecutorService
    @Override
    public void run() {
        //działa do momentu anulowania generatora
        while(!Zad21_11.isCanceled()) {
            testx();
            if(isImproperState()) {
                println("Nieprawidłowy stan!");
                cancel();
            }
        }
    }
    @Override
    public int testx() {
        //ze względu na to, że pola jednaWartosc i drugaWartosc są static, synchronized musi być cała klasa
        //aby zobaczyć wersję nie naprawioną, trzeba zakomentować linię 50 i 68
        synchronized(Zad21_11.class) {
            /*
            Po pierwszym zwiększeniu suma może być nieparzysta
            początek 0 + 0 = 0
            1: 1 + 0 = 1 <- nieprawidłowa
            2: 1 + 1 = 2 <- OK
            3: 2 + 1 = 3 <- nieprawidłowa
            4: 2 + 2 = 4 <- OK
             */
            ++jednaWartosc;
            Thread.yield();
            ++drugaWartosc;
            Thread.yield();
            ++jednaWartosc;
            Thread.yield();
            ++drugaWartosc;
            Thread.yield();
            return jednaWartosc + drugaWartosc;
        }
    }
    //poprawny stan to jednaWartosc + drugawWartosc jest parzysta
    public static synchronized boolean isImproperState() {
        return (jednaWartosc + drugaWartosc) % 2 != 0;
    }
    //tworzy i uruchamia podaną liczbę zadań
    public static void test(int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++)
            exec.execute(new Zad21_11(i));
        exec.shutdown();
        //odczyty odbywają się podczas działania pozostałych wątków,
        //gettery używają tej samej blokady co testx()
        for(int i = 0; i < count; i++) {
            println("PIERWSZA WARTOŚĆ: " + getJednaWartosc());
            println("DRUGA WARTOŚĆ: " + getDrugaWartosc());
        }
    }
    public static void test() {
        test(10);
    }
    public static void main(String[] args) {
        Zad21_11.test();
    }
}
/*
Program pokazuje problem współdzielenia danych pomiędzy wieloma wątkami.
Pola jednaWartosc i drugaWartosc są wspólne dla wszystkich zadań.

Metoda testx() zmienia oba pola w kilku krokach,
W trakcie wykonywania tej metody suma pól może chwilowo być nieparzysta,
co zostało przyjęte jako stan nieprawidłowy.

Bez synchronizacji inny wątek może odczytać pola w połowie ich modyfikacji
i zobaczyć nieprawidłowy stan.

Problem naprawiono przez użycie synchronized z blokadą Zad21_11.class,
dzięki temu tylko jeden wątek może jednocześnie modyfikować lub sprawdzać wspólne pola statyczne.
 */