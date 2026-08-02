import java.util.concurrent.*;
import java.util.*;
/*
Exercise 35: (8) Modify BankTellerSimulation.java so that it represents Web clients
making requests of a fixed number of servers. The goal is to determine the load that the
group of servers can handle.
*/
//pojedynczy klient/request z określonym czasem obsługi
class WebClient {
    //czas potrzebny serwerowi na obsługę requestu
    private final int serviceTime;
    public WebClient(int tm) { serviceTime = tm; }
    public int getServiceTime() { return serviceTime; }
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
//wspólna kolejna requestów oczekujących na obsługę przez serwery
class RequestQueue extends ArrayBlockingQueue<WebClient> {
    public RequestQueue(int maxLineSize) {
        super(maxLineSize);
    }
    //wyświetlanie requestów znajdujących się w kolejce
    public String toString() {
        if(this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for(WebClient customer : this)
            result.append(customer);
        return result.toString();
    }
}
class WebClientGenerator implements Runnable {
    //kolejka do której trafiają nowe requesty
    private RequestQueue customers;
    //stałe ziarno daje powtarzalną sekwencję wyników
    private static Random rand = new Random(47);
    public WebClientGenerator(RequestQueue cq) {
        customers = cq;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //losowy odstęp od 0 do 9ms między requestami
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(10));
                //nowy request wymaga od 0 do 999ms obsługi
                customers.put(new WebClient(rand.nextInt(1000)));
            }
        } catch(InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}
//pojedynczy serwer obsługująci i pobierający requesty
class Server implements Runnable {
    private static int counter = 0;
    //nr serwera
    private final int id = counter++;
    // Customers served during this shift:
    private RequestQueue customers;
    public Server(RequestQueue cq) { customers = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //pobiera następny request lub czeka na niego
                WebClient customer = customers.take();
                //symulacja czasu potrzebnego na obsługę requestu
                TimeUnit.MILLISECONDS.sleep(
                        customer.getServiceTime());
            }
        } catch(InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }
    public String toString() { return "Server " + id + " "; }
    //skrócona nazwa używana podczas wyświetlania stanu
    public String shortString() { return "S" + id; }
}
//nadzoruje kolejkę i wyświetla aktualne obciążenie serwerów
class ServerSupervisor implements Runnable {
    //pula uruchamiająca serwery
    private ExecutorService exec;
    //wspólna kolejka requestów
    private RequestQueue customers;
    //lista wszystkich serwerów
    private List<Server> workingTellers = new ArrayList<Server>();
    //odstęp między kolejnymi raportami
    private int adjustmentPeriod;
    public ServerSupervisor(ExecutorService e,
                         RequestQueue customers, int adjustmentPeriod) {
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        //tworzy stałą liczbę 150 serwerów
        for(int i = 0; i < 150; i++) {
            Server teller = new Server(customers);
            //każdy serwer działa jako osobne zadanie
            exec.execute(teller);
            workingTellers.add(teller);
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //raport co 1 sekunde
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                System.out.print("WebClients waiting: " + customers.size() + " { ");
                //wypisuje wsszystkie działające serwery
                for(Server teller : workingTellers)
                    System.out.print(teller.shortString() + " ");
                System.out.println("}");
            }
        } catch(InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }
    public String toString() { return "ServerSupervisor "; }
}
public class Zad21_35 {
    //maksymalna liczba requestów w kolejce
    static final int MAX_LINE_SIZE = 5000;
    //supervisor wyświetla stan co sekundę
    static final int ADJUSTMENT_PERIOD = 1000;
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        //kolejka requestów
        RequestQueue customers =
                new RequestQueue(MAX_LINE_SIZE);
        //generator requestów
        exec.execute(new WebClientGenerator(customers));
        exec.execute(new ServerSupervisor(
                exec, customers, ADJUSTMENT_PERIOD));
        //można ustawić czas działania programu
        if(args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
        else {
            System.out.println("Press ‘Enter’ to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}

/*
Przebudowano BankTellerSimulation.java na data center.

Customers > WebClients
CustomerLine > RequestQueue
CustomerGenerator > WebClientGenerator
Teller > Server
TellerManager > ServerSupervisor

WebClientGenerator
        V
Tworzy losowy request
        V
RequestQueue
        V
pierwszy wolny Server pobiera request
        V
Server obsługuje request
        V
pobiera kolejny request

Celem symulacji jest znalezienie granicy obciążenia dla stałej grupy serwerów (150).
*/