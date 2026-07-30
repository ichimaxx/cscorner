import java.util.concurrent.*;
import java.io.*;
import java.util.*;
import static net.mindview.util.Print.*;
/*
Exercise 30: (1) Modify PipedIO.java to use a BlockingQueue instead of a pipe.
 */


class Sender implements Runnable {
    private Random rand = new Random(47);
    private BlockingQueue<Character> out = new LinkedBlockingQueue<Character>();
    public BlockingQueue<Character> getQueue() { return out; }
    public void run() {
        try {
            while(true)
                for(char c = 'A'; c <= 'z'; c++) {
                    //wkłada znak do kolejki
                out.put(c);
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
            }
        } catch(InterruptedException e) {
            print(e + " Sender interrupted");
        }
    }
}
//consumer znaków
class Receiver implements Runnable {
    private BlockingQueue<Character> in;
    public Receiver(Sender sender)  {
        //receiver korzysta z tej samej kolejki co sender
        in = sender.getQueue();
    }
    public void run() {
        try {
            while(true) {
                // Blocks until characters are there:
                //take(), czeka, gdy kolejka jest pusta
                printnb("Read: " + (char)in.take() + ", ");
            }
        }catch (InterruptedException e) {
            print(e + " Receiver read exception");
        }
    }
}
public class Zad21_30 {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}

/*
Zadanie polega na zastąpieniu
PipedWrited + PipedReader
jedną współdzieloną
BlockingQueue<Character>

Sender.out i Receiver.in muszą wskazywać na dokładnie ten sam obiekt kolejki.
Jeżeli by były w innych, to Sender wkładałby znaki do jednej, a Receiver czekałby na elementy w innej.
*/