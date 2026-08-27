import java.util.*;
import static myutils.Skrocenie_Print.*;
import myutils.*;
/*
Exercise 10: (7) Modify class VendingMachine (only) using EnumMap so that one
program can have multiple instances of VendingMachine.
*/
//Wszystkie możliwe wejścia do automatu, monety produkty oraz komendy specjalne
enum Input_1 {
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
    Input_1(int value) { this.value = value; }
    Input_1() {}
    int amount() { return value; }; // In cents
    static Random rand = new Random(47);
    //Losuje wejście do testów automatu
    //Stop jest pomijany, dlatego losowy generator sam z siebie nie zakończy programu
    public static Input_1 randomSelection() {
        // Don’t include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}
//interfejs reprezentujący akcję, wykonywaną dla danego automatu
interface CommandsVM_1 { void next(Input_1 m); }
//Category grupuje wartości Input na logiczne kategorie
enum Category_1 {
    MONEY(Input_1.NICKEL, Input_1.DIME, Input_1.QUARTER, Input_1.DOLLAR),
    ITEM_SELECTION(Input_1.TOOTHPASTE, Input_1.CHIPS, Input_1.SODA, Input_1.SOAP),
    QUIT_TRANSACTION(Input_1.ABORT_TRANSACTION),
    SHUT_DOWN(Input_1.STOP);
    private Input_1[] values;
    Category_1(Input_1... types) { values = types; }
    //EnumMap pozwala szybko znaleźć kategorię dla konkretnego Input,
    //kluczem jest Input a wartością odpowiadającą mu Category
    private static EnumMap<Input_1,Category_1> categories =
            new EnumMap<Input_1,Category_1>(Input_1.class);
    //wypełnia mapę categories, każdy input zostaje przypisany do swojej kategorii
    static {
        for(Category_1 c : Category_1.class.getEnumConstants())
            for(Input_1 type : c.values)
                categories.put(type, c);
    }
    //zwraca kategorię dla podanego wejścia, np Quarter -> Money, Toothpaste -> item selection
    public static Category_1 categorize(Input_1 input) {
        return categories.get(input);
    }
}
class Zad19_10 {
    //brak pól statycznych, dzięki czemu można uruchomić kilka instancji VendingMachine,
    //każdy ma własny stan kwotę i produkt
    private State state = State.RESTING;
    private int amount = 0;
    private Input_1 selection = null;
    //mapa stanów automatu, klczem jest State, a wartością komenda opisująca zachowanie tego stanu
    private EnumMap<State, CommandsVM_1> states =
            new EnumMap<State, CommandsVM_1>(State.class);
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
        states.put(State.RESTING, new CommandsVM_1() {
            public void next(Input_1 m) {
                switch (Category_1.categorize(m)) {
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
        states.put(State.ADDING_MONEY, new CommandsVM_1() {
            public void next(Input_1 m) {
                switch (Category_1.categorize(m)) {
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
        states.put(State.DISPENSING, new CommandsVM_1() {
            public void next(Input_1 m) {
                println("here is your " + selection);
                amount -= selection.amount();
                state = State.GIVING_CHANGE;
            }
        });
        //Stan przejściowy: automat oddaje resztę i wraca do stanu RESTING
        states.put(State.GIVING_CHANGE, new CommandsVM_1() {
            public void next(Input_1 m) {
                if(amount > 0) {
                    println("Your change: " + amount);
                    amount = 0;
                }
                state = State.RESTING;
            }
        });
        //Stan końcowy: automat zatrzymany
        states.put(State.TERMINAL, new CommandsVM_1() {
            public void next(Input_1 m) {
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
    //pobiera kolejne Input z generatora i wykonuje komendę przypisaną do aktualnego stanu w EnumMap
    void run(Generator<Input_1> gen) {
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
        Generator<Input_1> gen = new RandomInputGenerator_1();
        if(args.length == 1)
            gen = new FileInputGenerator_1(args[0]);
        //Stworzono konkretną instancję automatu,
        //można stworzyć więcej instancji, ponieważ pola state/amount/selection nie są już static
        Zad19_10 vm = new Zad19_10();
        vm.run(gen);
    }
}
// For a basic sanity check:
//Generator losowych wejść do prostego testowania automatu
class RandomInputGenerator_1 implements Generator<Input_1> {
    public Input_1 next() { return Input_1.randomSelection(); }
}
// Create Inputs from a file of ‘;’-separated strings:
//Generator wejść z pliku tekstowego.
//Plik powinien zawierać nazwy Input oddzielone średnikami, np.
//QUARTER;QUARTER;CHIPS;STOP;
class FileInputGenerator_1 implements Generator<Input_1> {
    private Iterator<String> input;
    public FileInputGenerator_1(String fileName) {
        input = new TextFile(fileName , ";").iterator();
    }
    public Input_1 next() {
        //jeżeli plik się skończy, bezpiecznie kończy działanie automatu
        if(!input.hasNext())
            return null;
        return Enum.valueOf(Input_1.class, input.next().trim());
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