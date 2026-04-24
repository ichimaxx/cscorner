import java.util.*;

/*
Exercise 37: (2) Add a new mixin class Colored to Mixins.java, mix it into Mixin,
and show that it works.
*/
interface TimeStamped { long getStamp(); }
class TimeStampedImp implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImp() {
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}
interface SerialNumbered { long getSerialNumber(); }
class SerialNumberedImp implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber() { return serialNumber; }
}
interface Basic { 
    public void set(String val);
    public String get();
}
interface Colored { // dodany interfejs Colored
    public boolean check();
}
class ColoredImp implements Colored { // dodana ColoredImp class
    Random rand = new Random();
    private final boolean i;
    public ColoredImp() {
        i = rand.nextBoolean(); // random boolean czy true czy false
    }
    public boolean check() {
        return i;
    }
}
class BasicImp implements Basic {
    private String value;
    public void set(String val) { value = val; }
    public String get() { return value; }
}
class Mixin extends BasicImp implements TimeStamped, SerialNumbered, Colored {
    private TimeStamped timeStamp = new TimeStampedImp();
    private Colored colored = new ColoredImp();
    private SerialNumbered serialNumber = new SerialNumberedImp(); public long getStamp() { return timeStamp.getStamp(); }
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }
    public boolean check() {
        return colored.check();
    }
}
public class Zad15_37 {
    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " +
                mixin1.getStamp() + " " + mixin1.getSerialNumber() + " "  + mixin1.check());
        System.out.println(mixin2.get() + " " +
                mixin2.getStamp() + " " + mixin2.getSerialNumber() + " "  + mixin2.check());
    }
}
/*
Mixin to generalnie sposób domieszania do klasy jakiejś dodatkowej cechy albo zachowania, bez robienia hierarchii klas.
W C++ mixin robi się wygodniej przez szablony(templates) ale w javie przez erasure nie można zrobić tego tak samo,  dlatego używa się do tego np interfejsów lub klasy implementującej.
Dodano nowy mixin Colored i wrzucono do klasy Mixin.
Mixin przechouje obiekt ColoredImp i wysyła metodę check().
Dzięki temu Mixin ma funkcjonalność jednocześnie Basic, TimeStamped, SerialNumbered i Colored.
*/
