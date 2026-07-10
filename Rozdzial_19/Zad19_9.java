import java.util.*;
import static myutils.Skrocenie_Print.*;
import myutils.Enums;
/*
Exercise 9: (5) Modify class PostOffice so that it uses an EnumMap.
*/
//enum stworzony do pobierania enumów przez EnumMaps
enum MailsEnum {
    GENERAL_DELIVERY,
    SCANNABILITY,
    READABILITY,
    ADDRESS,
    RETURN_ADDRESS;
}
//interface commands do wyprowadzania komend co stanie się listami
interface Commands { boolean action(Mails m); }
class Mails {
    // The NO’s lower the probability of random selection:
    enum GeneralDelivery {YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability {UNSCANNABLE,YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE,YES1,YES2,YES3,YES4}
    enum Address {INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
    enum ReturnAddress {MISSING,OK1,OK2,OK3,OK4,OK5}
    GeneralDelivery generalDelivery() {
        return(GeneralDelivery) em.get(MailsEnum.GENERAL_DELIVERY);
    }
    Scannability scannability() {
        return(Scannability) em.get(MailsEnum.SCANNABILITY);
    }
    Readability readability() {
        return(Readability) em.get(MailsEnum.READABILITY);
    }
    Address address() {
        return(Address) em.get(MailsEnum.ADDRESS);
    }
    ReturnAddress returnAddress() {
        return(ReturnAddress) em.get(MailsEnum.RETURN_ADDRESS);
    }
    static long counter = 0;
    long id = counter++;
    public String toString() { return "Mail " + id; }
    public String details() {
        return toString() +
                ", General Delivery: " + generalDelivery() +
                ", Address Scanability: " + scannability() +
                ", Address Readability: " + readability() +
                ", Address Address: " + address() +
                ", Return address: " + returnAddress();
    }
    // EnumMap użyte do metody randomMail()
    EnumMap<MailsEnum,Enum<?>> em =
            new EnumMap<MailsEnum,Enum<?>>(MailsEnum.class);
    public static Mails randomMail() {
        Mails m = new Mails();
        m.em.put(MailsEnum.GENERAL_DELIVERY,
                Enums.random(GeneralDelivery.class));
        m.em.put(MailsEnum.SCANNABILITY,
                Enums.random(Scannability.class));
        m.em.put(MailsEnum.READABILITY,
                Enums.random(Readability.class));
        m.em.put(MailsEnum.ADDRESS,
                Enums.random(Address.class));
        m.em.put(MailsEnum.RETURN_ADDRESS,
                Enums.random(ReturnAddress.class));
        return m;
    }
    public static Iterable<Mails> generator(final int count) {
        return new Iterable<Mails>() {

            int n = count;
            public Iterator<Mails> iterator() {
                return new Iterator<Mails>() {
                    public boolean hasNext() { return n-- > 0; }
                    public Mails next() {
                        return Mails.randomMail();
                    }
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
public class Zad19_9 {
    //enumMap do wybierania Chain of responsibility
    static  EnumMap<MailsEnum,Commands> ems =
            new EnumMap<MailsEnum,Commands>(MailsEnum.class);
    //anonimowe klasy biorące udział w chain
    static {
        ems.put(MailsEnum.GENERAL_DELIVERY, new Commands() {
            public boolean action(Mails m) {
                switch (m.generalDelivery()) {
                    case YES:
                        println("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        });
        ems.put(MailsEnum.SCANNABILITY, new Commands() {
            public boolean action(Mails m) {
                switch (m.scannability()) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address()) {
                            case INCORRECT:
                                return false;
                            default:
                                println("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        });
        ems.put(MailsEnum.READABILITY, new Commands() {
            public boolean action(Mails m) {
                switch (m.readability()) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address()) {
                            case INCORRECT:
                                return false;
                            default:
                                println("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        });
        ems.put(MailsEnum.RETURN_ADDRESS, new Commands() {
            public boolean action(Mails m) {
                switch (m.returnAddress()) {
                    case MISSING:
                        return false;
                    default:
                        println("Returning " + m + " to sender");
                        return true;
                }
            }
        });
    }
    static void action(Mails m) {
        for (Commands handler : ems.values())
            if (handler.action(m))
                return;
        println(m + " is a dead letter");
    }
    public static void main(String[] args) {
        for (Mails mail : Mails.generator(10)) {
            println(mail.details());
            action(mail);
            println("*****");
        }
    }
}
/* Zadanie modyfikuje klasę PostOffice, zamiast zwykłych enum, do chain of responsibility(strategia) użyto EnumMap
MailsEnum służy jako klucz w mapie.
W klasie Mails EnumMap przechowuje losowo wybrane wartości listu, a w klasie
Zad19_9 druga EnumMap, przechowuje strategie.

Każda anonimowa klasa Commands próbuje obsłużyć list i zwraca true, jeżeli się udało.
Jeżeli żadna strategia nie obsłuży listu, trafia jako dead letter.
*/