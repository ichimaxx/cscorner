import java.util.*;
import static myutils.Skrocenie_Print.*;
import myutils.*;
/*
Exercise 10: (7) Modify class VendingMachine (only) using EnumMap so that one
program can have multiple instances of VendingMachine.
*/
//Wszystkie możliwe wejścia do automatu, monety produkty oraz komendy specjalne
enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
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
//interfejs reprezentujący akcję, wykonywaną dla danego automatu
interface CommandsVM { void next(Input m); }
//Category grupuje wartości Input na logiczne kategorie
enum Category {
    MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
    ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP),
    QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
    SHUT_DOWN(Input.STOP);
    private Input[] values;
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
    public static Category categorize(Input input) {
        return categories.get(input);
    }
}
class Zad19_10 {
    //brak pól statycznych, dzięki czemu można uruchomić kilka instancji VendingMachine,
    //każdy ma własny stan kwotę i produkt
    private State state = State.RESTING;
    private int amount = 0;
    private Input selection = null;
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
    public Zad19_10() {
        //stan początkowy: automat czeka na pieniądze lub STOP
        states.put(State.RESTING, new CommandsVM() {
            public void next(Input m) {
                switch (Category.categorize(m)) {
                    case MONEY:
                        amount += m.amount();
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
            public void next(Input m) {
                switch (Category.categorize(m)) {
                    case MONEY:
                        amount += m.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = m;
                        if(amount < selection.amount())
                            print("Insufficient money for " + selection);
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
            public void next(Input m) {
                println("here is your " + selection);
                amount -= selection.amount();
                state = State.GIVING_CHANGE;
            }
        });
        //Stan przejściowy: automat oddaje resztę i wraca do stanu RESTING
        states.put(State.GIVING_CHANGE, new CommandsVM() {
            public void next(Input m) {
                if(amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = State.RESTING;
            }
        });
        //Stan końcowy: automat zatrzymany
        states.put(State.TERMINAL, new CommandsVM() {
            public void next(Input m) {
                print("Halted");
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
    //pobiera kolejne Input z generatora i wykonuje komendę przypisaną do aktualnego stanu w EnumMap
    void run(Generator<Input> gen) {
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
        Generator<Input> gen = new RandomInputGenerator();
        if(args.length == 1)
            gen = new FileInputGenerator(args[0]);
        //Stworzono konkretną instancję automatu,
        //można stworzyć więcej instancji, ponieważ pola state/amount/selection nie są już static
        Zad19_10 vm = new Zad19_10();
        vm.run(gen);
    }
}
// For a basic sanity check:
//Generator losowych wejść do prostego testowania automatu
class RandomInputGenerator implements Generator<Input> {
    public Input next() { return Input.randomSelection(); }
}
// Create Inputs from a file of ‘;’-separated strings:
//Generator wejść z pliku tekstowego.
//Plik powinien zawierać nazwy Input oddzielone średnikami, np.
//QUARTER;QUARTER;CHIPS;STOP;
class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;
    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName , ";").iterator();
    }
    public Input next() {
        //jeżeli plik się skończy, bezpiecznie kończy działanie automatu
        if(!input.hasNext())
            return null;
        return Enum.valueOf(Input.class, input.next().trim());
    }
}

/*
Program jest przerobioną wersją przykładu VendingMachine, tak aby można było odpalić kilka instancji na raz.

Najważniejsza zmiana to usunięcie stałych(static) pól state, amount i selection.
Zachowanie stanów nie jest już zapisane bezpośrednio w enum State. Zamiast tego użyto EnumMap<State, CommandsVM>,
gdzie kluczem jest stan a wartością obiekt wykonujący logikę tego stanu.

Stany DISPENSING i GIVING_CHANGE są oznaczone jako TRANSIENT, więc wykonują się automatycznie,
bez pobierania kolejnego wejścia.
 */