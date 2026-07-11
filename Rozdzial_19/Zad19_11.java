import java.util.*;
import static myutils.Skrocenie_Print.*;
import myutils.*;
/*
Exercise 11: (7) In a real vending machine you will want to easily add and change the
type of vended items, so the limits imposed by an enum on Input are impractical
(remember that enums are for a restricted set of types). Modify VendingMachine.java so
that the vended items are represented by a class instead of being part of Input, and
initialize an Array List of these objects from a text file (using
net.mindview.util.TextFile).
*/
enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    ABORT_TRANSACTION {
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // This must be the last instance.
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value; // In cents
    Input(int value) { this.value = value; }
    Input() {}
    int amount() { return value; }; // In cents
    static Random rand = new Random(47);
    //Losuje wejście do testów automatu
    //Stop jest pomijany, dlatego losowy generator sam z siebie nie zakończy programu
    public static Input randomSelection() {
        // Don’t include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}
class VendItem {
    private String name;
    //lista wszystkich produktów w automacie, już nie jest enumami tylko obiektami klasy VendItem
    static ArrayList<VendItem> items = new ArrayList<VendItem>();
    private int price;
    public VendItem(String name, int price){
        this.name = name;
        this.price = price;
    }
    public String name() {
        return name;
    }
    public int amount() {
        return price;
    }
    public String toString() {
        return name;
    }
    //wczytuje produkty z pliku tekstowego
    //format pliku:
    //TOOTHPASTE,200;CHIPS,100;SODA,150;
    //każdy produkt jest oddzielony średnikiem, a nazwa i cena produktu oddzielone przecinkiem
    public static void loadItems(String fileName) {
        for (String s : new TextFile(fileName, ";")) {
            s = s.trim();
            if(s.length() == 0)
                continue;
            String[] parts = s.split(",");
            //jeśli po podziale nie ma dokładnie dwóch części,
            // czyli nazwy i ceny, to format pliku jest niepoprawny
            if(parts.length != 2)
                throw new IllegalArgumentException(
                        "Zły format produktu w " + fileName + ": " + s +
                                ". Powinno być np. TOOTHPASTE,200;CHIPS,100;SODA,150;SOAP,50;GREENS,10;COKE,100000;");
            items.add(new VendItem(parts[0].trim(), Integer.parseInt(parts[1].trim())));
        }
    }
    //szuka produktu po nazwie w ArrayList<VendItem>
    //np. jeśli z pliku wejściowego przyjdzie "CHIPS",
    //metoda szuka obiektu VendItem o nazwie CHIPS
    public static VendItem findItem (String name) {
        for(VendItem item : items)
            if(item.name().equals(name))
                return item;
        //jeżeli produktu nie znaleziono, zwraca null
        return null;
    }
}
//interfejs reprezentujący akcję, wykonywaną dla danego automatu
//przyjmuje tutaj Object(szerzej), ponieważ wejściem może być albo Input, albo VendItem
interface CommandsVM { void next(Object  m); }
//Category grupuje wartości Input na logiczne kategorie
enum Category {
    MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
    ITEM_SELECTION(),
    QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
    SHUT_DOWN(Input.STOP);
    private Input[] values;
    Input command = null;

    Category(Input... types) { values = types; }
    //EnumMap pozwala szybko znaleźć kategorię dla konkretnego Input,
    //kluczem jest Input a wartością odpowiadającą mu Category
    private static EnumMap<Input,Category> categories =
            new EnumMap<Input,Category>(Input.class);
    //wypełnia mapę categories, każdy input zostaje przypisany do swojej kategorii
    static {
        for(Category c : Category.class.getEnumConstants())
            for(Input type : c.values)
                categories.put(type, c);
    }
    //zwraca kategorię dla podanego wejścia, np Quarter -> Money, Toothpaste -> item selection
    public static Category categorize(Object input) {
        if(input instanceof VendItem)
            return ITEM_SELECTION;
        //rzutowanie na Input, ponieważ input jest Object, czyli szerzej niż Input
        return categories.get((Input)input);
    }
}
class Zad19_11 {
    //brak pól statycznych, dzięki czemu można uruchomić kilka instancji VendingMachine,
    //każdy ma własny stan kwotę i produkt
    private State state = State.RESTING;
    private int amount = 0;
    private VendItem selection = null;
    //mapa stanów automatu, klczem jest State, a wartością komenda opisująca zachowanie tego stanu
    private EnumMap<State, CommandsVM> states =
            new EnumMap<State, CommandsVM>(State.class);
    //oznaczenie stanów przejściowych, które mają wykonać się automatycznie,
    //bez pobierania kolejnego Input od użytkownika
    enum StateDuration { TRANSIENT } // Tagging enum
    //Stany automatu
    //DISPENSING i GIVING_CHANGE przejściowe, bo po wyborze produktu
    //automat powinien od razu wydać produkt i resztę
    enum State {
        RESTING,ADDING_MONEY,DISPENSING(StateDuration.TRANSIENT),GIVING_CHANGE(StateDuration.TRANSIENT),TERMINAL;
        //true oznacza, że stan ma zostać wykonany automatycznie w pętli run()
        private boolean isTransient = false;
        State() {}
        State(StateDuration trans) {
            isTransient = true;
        }
    }
    //Konstruktor wypełnia EnumMap zachowaniami dla każdego stanu,
    //Dzięki temu logika stanów jest przypisana do konkretnej instancji automatu
    public Zad19_11() {
        //stan początkowy: automat czeka na pieniądze lub STOP
        states.put(State.RESTING, new CommandsVM() {
            public void next(Object  m) {
                switch (Category.categorize(m)) {
                    case MONEY:
                        //rzutowanie na Input, ponieważ m jest Object, czyli szerzej niż VendItem/Input
                        amount += ((Input)m).amount();
                        state = State.ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = State.TERMINAL;
                    default:
                }
            }
        });
        //Stan dodawania pieniędzy, można dorzucać monety, wybrać produkt
        //anulować transakcje albo zatrzymać automat
        states.put(State.ADDING_MONEY, new CommandsVM() {
            public void next(Object  m) {
                switch (Category.categorize(m)) {
                    case MONEY:
                        amount += ((Input)m).amount();
                        break;
                    case ITEM_SELECTION:
                        //rzutowanie na VendItem, ponieważ m jest Object, czyli szerzej niż VendItem/Input
                        selection = (VendItem)m;
                        if(amount < selection.amount())
                            print("Insufficient money for " + selection + " ");
                        else state = State.DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = State.GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = State.TERMINAL;
                    default:
                }
            }
        });
        //Stan przejściowy: automat wydaje wydany produkt i przechodzi do wydawania reszty
        states.put(State.DISPENSING, new CommandsVM() {
            public void next(Object  m) {
                println("here is your " + selection);
                amount -= selection.amount();
                state = State.GIVING_CHANGE;
            }
        });
        //Stan przejściowy: automat oddaje resztę i wraca do stanu RESTING
        states.put(State.GIVING_CHANGE, new CommandsVM() {
            public void next(Object  m) {
                if(amount > 0) {
                    println("Your change: " + amount);
                    amount = 0;
                }
                state = State.RESTING;
            }
        });
        //Stan końcowy: automat zatrzymany
        states.put(State.TERMINAL, new CommandsVM() {
            public void next(Object  m) {
                println("Halted");
            }
        });
    }
    //wyświetla aktualną kwotę w automacie albo informacje o zatrzymaniu
    void output() {
        if(state == State.TERMINAL)
            println("Halted");
        else
            println(amount);
    }
    //Główna pętla automatu
    //pobiera kolejne wejście z generatora. Wejściem może być Input lub VendItem
    void run(Generator<Object> gen) {
        while(state != State.TERMINAL) {
            //Zamiast state.next(..) szuka zachowania aktualnego stanu w mapie
            states.get(state).next(gen.next());
            //Stany przejściowe wykonują się automatycznie, bez pobierania kolejnego wejścia z generatora
            while(state.isTransient)
                states.get(state).next(null);

            output();
        }
    }
    //tworzy generator wejść, losowy albo z pliku, jeżeli podano nazwę pliku w args[0]
    public static void main(String[] args) {
        VendItem.loadItems("Rzeczy.txt");
        Generator<Object> gen = new RandomInputGenerator();
        if(args.length == 1)
            gen = new FileInputGenerator(args[0]);
        //Stworzono konkretną instancję automatu,
        //można stworzyć więcej instancji, ponieważ pola state/amount/selection nie są już static
        Zad19_11 vm = new Zad19_11();
        vm.run(gen);
    }
}
// For a basic sanity check:
//Generator losowych wejść do prostego testowania automatu
class RandomInputGenerator implements Generator<Object> {
    public Input next() { return Input.randomSelection(); }
}
// Create Inputs from a file of ‘;’-separated strings:
//Generator wejść z pliku tekstowego.
//Plik powinien zawierać nazwy Input oddzielone średnikami, np.
//QUARTER;QUARTER;CHIPS;STOP;
class FileInputGenerator implements Generator<Object> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    public Object next() {
        //jeżeli plik się skończy, bezpiecznie kończy działanie automatu
        if (!input.hasNext())
            return Input.STOP;
        String token = input.next().trim();
        try {
            return Enum.valueOf(Input.class, token);
        } catch(IllegalArgumentException e) {
            VendItem item = VendItem.findItem(token);
            if(item != null)
                return item;
            throw new IllegalArgumentException("Unknown input: " + token);
        }
    }
}

/*
Przerobiono VendingMachines, tak aby produktu nie były już częścią enuma Input.
Produkty są teraz obiektami klasy VendItem i są wczytywane z pliku tekstowego Rzeczy.txt do ArrayList<VendItem>.
*/