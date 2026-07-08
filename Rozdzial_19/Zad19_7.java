import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 7: (3) Find the source code for EnumSet and explain how it works.
*/

public enum Zad19_7 {
        STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,
        OFFICE4, BATHROOM, UTILITY, KITCHEN;
    public static void main(String[] args) {
        EnumSet<Zad19_7> points = EnumSet.noneOf(Zad19_7.class); // Empty set
        points.add(BATHROOM);
        println(points);
        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        println(points);
        points = EnumSet.allOf(Zad19_7.class);
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        println(points);
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        println(points);
        points = EnumSet.complementOf(points);
        println(points);
    }


}
/*
public abstract sealed class EnumSet<E extends Enum<E>> extends AbstractSet<E>
    implements Cloneable, java.io.Serializable permits JumboEnumSet, RegularEnumSet
{
 declare EnumSet.class serialization compatibility with JDK 8
    @java.io.Serial
    private static final long serialVersionUID = 1009687484059888093L;
    final transient Class<E> elementType;
    All of the values comprising E.  (Cached for performance.)
    final transient Enum<?>[] universe;
    EnumSet(Class<E>elementType, Enum<?>[] universe) {
        this.elementType = elementType;
        this.universe    = universe;
    }
}

EnumSet dziedziczy po AbstractSet<E>, typ EnumSet musi byc extends Enum, czyli przechowywać tylko elementy typu enum.
Rozdziela a informacje na elementType i universe i na podstawie ich wykonuje metody
używając dziedziczących po AbstractSet.

elementType - klasa enuma np. Zad19_7
universe - wszystkie wartości enuma np. STAIR1, STAIR2, LOBBY

public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClassCastException(elementType + " not an enum");
        if (universe.length <= 64)
            return new RegularEnumSet<>(elementType, universe);

        else
            return new JumboEnumSet<>(elementType, universe);
    }

Metoda noneOf tworzy pusty EnumSet dla podanego typu enum.
Najpierw pobiera wszystkie wartości enum do tablicy universe.

Jeżeli enum ma mniej niż 65 elementy zwraca zwykły EnumSet (long ma 64 bity, 1 bit na jeden element)
Jeśli bit jest 1, element jest w secie
Natomiast gdy bit jest 0 elementu nie ma

Ale gdy enum ma więcej niż 64 elementy tworzy JumboEnumSet który używa tablicy long[]
long[0] wartości 0-63
long[1] wartości 64-127 itd...

public static <E extends Enum<E>> EnumSet<E> allOf(Class<E> elementType) {
        EnumSet<E> result = noneOf(elementType);
        result.addAll();
        return result;
    }
Ta metoda tworzy EnumSet zawierający wszystkie wartości enum.


*/