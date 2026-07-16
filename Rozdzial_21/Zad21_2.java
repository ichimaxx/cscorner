import myutils.Generator;
/*
Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that
produces a sequence of n Fibonacci numbers, where n is provided to the constructor of the
task. Create a number of these tasks and drive them using threads.
*/
public class Zad21_2 implements Generator<Integer>,Runnable {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    private int n = 0;
    public Zad21_2(int n) {
        this.n = n;
    }
    @Override
    public void run() {
        for(int i = 0; i < n; i++) {
            System.out.print(next() + " ");
            Thread.yield();
        }
    }
    public static void main(String[] args) {
        //tworzy 5 osobnych ciągów po 9 liczb, 5 wątków, 5 osobnych pól count, i obiektów Zad21_2
        for(int i = 0; i < 5; i++) {
            Zad21_2 k = new Zad21_2(9);
            Thread watek = new Thread(k);
            watek.start();
        }
    }
}

/*
Wyniki z kilku wątków mogą mieszać się w konsoli, ponieważ każdy obiekt
Zad21_2 jest wykonywany przez osobny wątek.

JVM wraz z systemem operacyjnym przydziela poszczególnym wątkom czas procesora,
dlatego nie można przewidzieć dokładnej kolejności wykonywania instrukcji i wychodzenia liczb.

Thread.yield() jedynie sugeruje, że bieżący wątek może
oddać czas wykonywania innemu wątkowi, ale nie gwarantuje przełączenia.
*/