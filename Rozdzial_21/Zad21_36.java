import net.mindview.util.Enums;
import java.util.concurrent.*;
import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 36: (10) Modify RestaurantWithQueues.java so there’s one OrderTicket
object per table. Change order to orderTicket, and add a Table class, with multiple
Customers per table.
*/

//wspólny typ dla wszystkich rodzajów jedzenia
interface Foods {
    enum Appetizer implements Foods {
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Foods {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Foods {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }
    enum Coffee implements Foods {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
}
//Kolejne etapy całego posiłku klienta
enum Courses {
    APPETIZER(Foods.Appetizer.class),
    MAINCOURSE(Foods.MainCourse.class),
    DESSERT(Foods.Dessert.class),
    COFFEE(Foods.Coffee.class);
    //produkty należące do danego etapu posiłku
    private Foods[] values;
    private Courses(Class<? extends Foods> kind) {
        values = kind.getEnumConstants();
    }
    //losuje jedzenie z danej kategorii
    public Foods randomSelection() {
        return Enums.random(values);
    }
}
//test losowania całego posiłku
class Meals {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            for(Courses course : Courses.values()) {
                Foods food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}
//reprezentuje jeden stolik w restauracji
class Table {
    //każdy stolik ma jeden OrderTicket
    //to wspólna kartka zamówień dla całego stolika
    private final OrderTicket orderTicket;
    //każdy element tablicy reprezentuje jedno siedzenie
    //null miejsce wolne
    //Customer miejsce zajete przez danego klienta
    private final Customer[] seats;
    //kelner przypisany do stolika
    private final WaitPerson waitPerson;
    //numer stolika
    private final int id;
    public Table(int id, int numberOfseats, WaitPerson waitPerson) {
        this.id = id;
        seats = new Customer[numberOfseats];
        orderTicket = new OrderTicket(this, waitPerson);
        this.waitPerson = waitPerson;
    }
    public int getId() {
        return id;
    }
    public OrderTicket getOrderTicket() {
        return orderTicket;
    }
    public WaitPerson getWaitPerson() {
        return waitPerson;
    }
    //szuka pierwszego wolnego siedzenia
    //metoda jest synchronized, aby dwa wątki klientów nie zajęły jednocześnie tego samego miejsca
    public synchronized int usadzenieKlienta(Customer customer) {
        for(int i = 0; i < seats.length; i++) {
            if(seats[i] == null) {
                seats[i] = customer;
                //zwraca numer przydzielonego siedzenia
                return i;
            }
        }
        //zwraca to, jeśli brak wolnych miejsc
        return -1;
    }
    //zwalnia to samo siedzenie, które wcześniej otrzymał Customer
    public synchronized void wysadzenieKlienta(Customer customer, int seatNumber) {
        //jeżeli na tym siedzeniu siedzi ten klient, to go wysadza z niego i robi null
        if(seats[seatNumber] == customer) {
            seats[seatNumber] = null;
        } else {
            throw new IllegalStateException(
                    customer + " nie zajmuje miejsca " + seatNumber + " przy " + this);
        }
    }
    public String toString() {
        return "Table " + id + ", Seat:" + Arrays.toString(seats);
    }
}
//zamówienie przekazywane od kelnera do kuchni
class OrderTicket { // (A data-transfer object)
    private static int counter = 0;
    //numer zamówienia
    private final int id = counter++;
    //stolik do którego należy ten ticket
    private final Table table;
    //kelner obsługujący zamówienia
    private final WaitPerson waitPerson;
    //kolejka wszystkich pozycji zamówionych przez klientów siedzących przy danym stoliku
    //jeden order ticket może wiec zawierać zamówienia kilku różnych klientów
    private final BlockingQueue<OrderItem> items =
            new LinkedBlockingQueue<OrderItem>();
    class OrderItem {
        private final Customer customer;
        private final Foods food;
        //jedna pozycja na wspólnej karcie zamówień
        //przechowuje klienta i jedzenie, na które złożył zamówienie
        public OrderItem(Customer customer, Foods food) {
            this.customer = customer;
            this.food = food;
        }
        public Customer getCustomer() {
            return customer;
        }
        public Foods getFood() {
            return food;
        }
    }
    //dopisuje nową pozycję do wspólnego ticketu stolika
    public void addItem(Customer customer, Foods food) {
        items.add(new OrderItem(customer, food));
    }

    //kucharz pobiera jedną oczekującą pozycję.
    //jeżeli nie ma żadnej pozycji, metoda czeka

    public OrderItem takeItem() throws InterruptedException {
        return items.take();
    }
    public OrderTicket(Table cust, WaitPerson wp) {
        table = cust;
        waitPerson = wp;
    }
    public Table getTable() { return table; }
    public WaitPerson getWaitPerson() { return waitPerson; }
    public String toString() {
        return "OrderTicket: " + id +
                " for: " + table +
                " served by: " + waitPerson;
    }
}

//gotowe danie zwracane przez kucharza
// This is what comes back from the chef:
class Plate {
    //zamówienie któego dotyczy talerz
    private final OrderTicket order;
    //przygotowane jedzenie
    private final Foods food;
    private final OrderTicket.OrderItem orderItem;
    public Plate(OrderTicket ord, Foods f, OrderTicket.OrderItem orderItem) {
        order = ord;
        food = f;
        this.orderItem = orderItem;
    }
    //konkretna pozycja zamówienia
    //dzięki niej kelner wie, który klient zamówił to danie
    public OrderTicket.OrderItem getOrderItem() {
        return orderItem;
    }
    public OrderTicket getOrder() { return order; }
    public Foods getFood() { return food; }
    public String toString() { return food.toString(); }
}
//klient składający zamówienia i oczekujący na dania
class Customer implements Runnable {
    //prywatne miejsce na talerz konkretnego klienta
    //SynchronousQueue nie przechowuje więcej niż jednego elementu,
    //kelner wykonujący put() czeka aż klient wykona take()
    //dzięki temu talerz trafia zawsze do właściwego klienta
    private final SynchronousQueue<Plate> placeSetting =
            new SynchronousQueue<Plate>();
    //numer siedzenia przy stoliku -1 oznacza brak miejsca
    private int seatNumber = -1;
    //stolik przy którym siedzi klient
    private Table table;
    private static int counter = 0;
    //numer klienta
    private final int id = counter++;
    //kelner przypisany do klienta
    // przekazuje jeden talerz bezpośrednio do klienta

    public Customer() {
    }
    public Table getTable() {
        return table;
    }
    //klient zapamiętuje przydzielony stolik i numer siedzenia
    public synchronized void usadzenieKlienta(Table table, int seatNumber) {
        this.seatNumber = seatNumber;
        this.table = table;
    }
    //kelner przekazuje klientowi gotowy talerz
    public void deliver(Plate p) throws InterruptedException {
        // Only blocks if customer is still
        // eating the previous course:
        placeSetting.put(p);
    }
    public void run() {
        try {
        //klient zamawia po jednej pozycji z każdej kategorii
            for(Courses course : Courses.values()) {
                try{
                Foods food = course.randomSelection();
                //przekazuje zamówienie kelnerowi przypisanego do jego stolika
                table.getWaitPerson().placeOrder(this, food);
                // czeka na dostarczenie gotowego dania
                println(this + "eating " + placeSetting.take());
                }catch (InterruptedException e) {
                    println(this + "waiting for " +
                            course + " interrupted");
                    break;
                }
            }
        } finally {
            //miejsce zwalniane tylko raz, po zakończeniu całego posiłku albo przerwaniu zadania
                synchronized(this) {
                    if (table != null && seatNumber != -1)
                        table.wysadzenieKlienta(this, seatNumber);
                }
            println(this + "finished meal, leaving");
            }
        }
    public String toString() {
        if(table == null)
            return "Customer " + id + " bez stolika";
        return "Customer " + id + " Stolik: " + table.getId() + " ";
    }
}
//kelner odbiera zamówienie i dostarcza gotowe dania
class WaitPerson implements Runnable {
    private static int counter = 0;
    //numer kelnera
    private final int id = counter++;
    //restauracja, w której pracuje kelner
    private final Restaurant restaurant;
    //kolejka gotowych dań dla tego kelnera
    //kucharz wkłada tutaj przygotowany talerz, a kelner później dostarcza go klientowi
    BlockingQueue<Plate> filledOrders =
            new LinkedBlockingQueue<Plate>();
    public WaitPerson(Restaurant rest) { restaurant = rest; }
    //dopisuje zamówienie do ticketu z danego stolika i przekazuje je do kuchni
    public void placeOrder(Customer cust, Foods food) {
        try {
            //pobiera jeden wspólny ticket należący do stolika
            OrderTicket ticket = cust.getTable().getOrderTicket();
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            ticket.addItem(cust, food);
            //wysyła istniejący ticket pobrany z OrderTicket
            //ten sam obiekt OrderTicket może pojawić się w kolejce wiele razy,
            //ale nadal jest to jeden ticket należący do stolika,
            //każde umieszczenie go w kolejce sygnalizuje kucharzowi,
            //że czeka następna pozycja OrderItem
            restaurant.orders.put(ticket);
        } catch(InterruptedException e) {
            println(this + " placeOrder interrupted");
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czeka na gotowe danie
                // Blocks until a course is ready
                Plate plate = filledOrders.take();
                println(this + "received " + plate +
                        " delivering to " +
                        plate.getOrderItem().getCustomer());
                //dostarcza danie właściwemu klientowi
                plate.getOrderItem().getCustomer().deliver(plate);
            }
        } catch(InterruptedException e) {
            println(this + " interrupted");
        }
        println(this + " off duty");
    }
    public String toString() {
        return "WaitPerson " + id + " ";
    }
}
//kucharz pobiera zamówienia i przygotowuje dania
class Chef implements Runnable {
    private static int counter = 0;
    //numer kucharza
    private final int id = counter++;
    private final Restaurant restaurant;
    //generator losowego czasu przygotowania
    private static Random rand = new Random(47);
    public Chef(Restaurant rest) { restaurant = rest; }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                //czeka na nowe zamówienie
                // Blocks until an order appears:
                OrderTicket order = restaurant.orders.take();
                //pobiera teraz jedzenie z OrderItem a nie z Order Ticket
                OrderTicket.OrderItem item = order.takeItem();
                Foods requestedItem = item.getFood();
                //symuluje przygotowanie dania
                // Time to prepare order:
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                //tworzy gotowy talerz
                Plate plate = new Plate(order, requestedItem, item);
                //przekazuje talerz odpowiedniemu kelnerowi
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch(InterruptedException e) {
            println(this + " interrupted");
        }
        println(this + " off duty");
    }
    public String toString() { return "Chef " + id + " "; }
}
//zarządza kelnerami kucharzami i tworzeniem klientów
class Restaurant implements Runnable {
    //lista stolików tworzonych w restauracji
    private int liczbaStolikow = 10;
    //wszyscy kelnerzy pracujący w restauracji
    private List<WaitPerson> waitPersons =
            new ArrayList<WaitPerson>();
    //lista stolików
    private List<Table> tables =
            new ArrayList<Table>();
    //lista kucharzy
    private List<Chef> chefs = new ArrayList<Chef>();
    private ExecutorService exec;
    private static Random rand = new Random(47);
    //wspólna kolejka zamówień dla kucharzy
    BlockingQueue<OrderTicket>
            orders = new LinkedBlockingQueue<OrderTicket>();
    public Restaurant(ExecutorService e, int nWaitPersons,
                      int nChefs) {
        exec = e;
        //tworzy i uruchamia kelnerów
        for(int i = 0; i < nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        //tworzy i uruchamia kucharzy
        for(int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
        //tworzy określoną ilość stolików o losowej ilości miejsc (maksymalnie 6)
        //kelnerzy przypisywani są do stolików po kolei.
        // % pozwala ponownie zacząć od pierwszego kelnera gdy stolików jest więcej niż kelnerów
        for(int i = 0; i < liczbaStolikow; i++) {
            WaitPerson wp = waitPersons.get(i % waitPersons.size());
            Table table = new Table(i, rand.nextInt(6)+ 1, wp);
            tables.add(table);
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //do restauracji przychodzi nowy klient
                Customer c = new Customer();
                //szuka stolika, przy którym znajduje się wolne siedzenie
                for (Table table : tables) {
                    int seatNumber = table.usadzenieKlienta(c);
                    if(seatNumber != -1) {
                        c.usadzenieKlienta(table, seatNumber);
                        exec.execute(c);
                        break;
                    }
                }
                //odstęp między pojawianiem się klientów
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            println("Restaurant interrupted");
        }
        println("Restaurant closing");
    }
}

public class Zad21_36 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        //restauracja z 5 kelnerami i 2 kucharzami
        Restaurant restaurant = new Restaurant(exec, 5, 2);
        exec.execute(restaurant);
        //opcjonalnie można ustawić czas działania programu
        if(args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
        else {
            println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
/*
ćwiczenie polegało na przebudowaniu symulacji restauracji tak, aby przy jednym stoliku
mogło siedzieć wielu klientów, ale każdy stolik miał tylko jeden wspólny OrderTicket.

OrderTicket przechowuje OrderItem, a każdy OrderItem zawiera
klienta i zamówione jedzenie

Przebieg zamówienia:
1. Restaurant tworzy klienta
2. Table przydziela klientowi konkretne wolne siedzenie
3. Customer wybiera potrawę
4. WaitPerson pobiera wspólny OrderTicket stolika
5. Do ticketu dodawany jest OrderItem zawierający klienta i potrawę
6. Referencja do ticketu trafia do kolejki kuchni
7. Chef pobiera z ticketu jedną pozycję OrderItem
8. Chef przygotowuje Plate
9. Plate zachowuje informację o OrderItem
10. WaitPerson odczytuje z OrderItem właściwego klienta
11. Talerz trafia do prywatnej SynchronousQueue tego klienta
12. Po zakończeniu całego posiłku klient zwalnia dokładnie swój stolik i swoje siedzenie
*/