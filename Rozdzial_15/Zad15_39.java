import java.lang.reflect.*;
import java.util.*;
import net.mindview.util.*;

class TwoTuple3<A,B> {
    public final A first;
    public final B second;
    public TwoTuple3(A a, B b) { first = a; second = b; }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
interface TimeStampeds { long getStamp(); }
class TimeStampedImps implements TimeStampeds {
    private final long timeStamp;
    public TimeStampedImps() {
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}
interface SerialNumbereds { long getSerialNumber(); }
class SerialNumberedImps implements SerialNumbereds {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber() { return serialNumber; }
}
interface Basics {
    public void set(String val);
    public String get();
}
interface Coloreds { // dodany interfejs Colored
    public boolean check();
}
class ColoredImps implements Coloreds { // dodana ColoredImp class
    Random rand = new Random();
    private final boolean i;
    public ColoredImps() {
        i = rand.nextBoolean(); // random boolean czy true czy false
    }
    public boolean check() {
        return i;
    }
}
class BasicImp implements Basics {
    private String value;
    public void set(String val) { value = val; }
    public String get() { return value; }
}
class Mixins extends BasicImp implements TimeStampeds, SerialNumbereds, Coloreds {
    private TimeStampeds timeStamp = new TimeStampedImps();
    private Coloreds colored = new ColoredImps();
    private SerialNumbereds serialNumber = new SerialNumberedImps(); public long getStamp() { return timeStamp.getStamp(); }
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }
    public boolean check() {
        return colored.check();
    }
}

class MixinProxy implements InvocationHandler {
    Map<String,Object> delegatesByMethod;
    public MixinProxy(TwoTuple3<Object,Class<?>>... pairs) {
        delegatesByMethod = new HashMap<String,Object>();
        for(TwoTuple3<Object,Class<?>> pair : pairs) {
            for(Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                // The first interface in the map
                // implements the method.
                if (!delegatesByMethod.containsKey(methodName))
                    delegatesByMethod.put(methodName, pair.first);
            }
        }
    }
    public Object invoke(Object proxy, Method method,
                         Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }
    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple3... pairs) {
        Class[] interfaces = new Class[pairs.length];
        for(int i = 0; i < pairs.length; i++) {
            interfaces[i] = (Class)pairs[i].second;
        }
        ClassLoader cl =
                pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(
                cl, interfaces, new MixinProxy(pairs));
    }
}
public class Zad15_39 {
    public static <A,B> TwoTuple3<A,B> tuple(A a, B b) {
        return new TwoTuple3<A,B>(a, b); // ze względu na nie używanie importu tak jak jest w książce z import static net.mindview.util.Tuple.*;  trzeba zrobić metodę statyczną tuple w klasie z zadaniem, ewentualnie można pisać: new TwoTuple3<Object,Class<?>>(new BasicImp(), Basics.class)
    }
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImp(), Basics.class),
                tuple(new TimeStampedImps(), TimeStampeds.class),
                tuple(new SerialNumberedImps(),SerialNumbereds.class),
                tuple(new ColoredImps(),Coloreds.class));
        Basics b = (Basics)mixin;
        TimeStampeds t = (TimeStampeds)mixin;
        SerialNumbereds s = (SerialNumbereds)mixin;
        Coloreds f = (Coloreds)mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
        System.out.println(f.check());
    }
}

/*
Co robi kod z zadania w skrócie:
Z kilku zwykłych obiektów tworzy jeden "składany" obiekt.
Coś jak jeden pilot, który jest uniwersalny do różnych przedmiotów.
Proxy  (class MixinProxy implements InvocationHandler) natomiast przechwytuje wszystkie wywołania metod i decyduje do którego obiektu je wysłać.

Dodano nowy mixin Colored i wmieszano go jako dodatkowy obiekt.
Przy dynamic proxy każda domieszana klasa musi być implementacją interfejsu, bo proxy działa przez interfejsy.
*/