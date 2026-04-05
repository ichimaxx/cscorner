import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 17: (4) Study the JDK documentation for EnumSet. You’ll see that there’s a
clone( ) method defined. However, you cannot clone( ) from the reference to the Set
interface passed in Sets.java. Can you modify Sets.java to handle both the general case of a
Set interface as shown, and the special case of an EnumSet, using clone( ) instead of
creating a new HashSet?
 */
enum Watercolors {
    ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW, ORANGE,
    BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET,
    CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
    COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
    SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
    BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
}
class Sets {
    private static <T> Set<T> copy(Set<T> source) {
        if (source instanceof EnumSet) { // jesli source to instanceof EnumSet to: v
            return (Set<T>) ((EnumSet) source).clone(); //  zwroci metode clone()
        }
        return new HashSet<T>(source); // dopiero potem kopia zwykłego zbioru w HashSet
    }
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = copy(a); // tworzy kopię zbioru: przez clone() dla EnumSet albo przez HashSet dla zwykłego Set, zależnie od rzeczywistego typu przekazanego do copy() z main
        result.addAll(b);
        return result;
    }
    public static <T>
    Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = copy(a); // tworzy kopię zbioru: przez clone() dla EnumSet albo przez HashSet dla zwykłego Set, zależnie od rzeczywistego typu przekazanego do copy() z main
        result.retainAll(b);
        return result;
    }
    // Subtract subset from superset:
    public static <T> Set<T>
    difference(Set<T> superset, Set<T> subset) {
        Set<T> result = copy(superset); // tworzy kopię zbioru: przez clone() dla EnumSet albo przez HashSet dla zwykłego Set, zależnie od rzeczywistego typu przekazanego do copy() z main
        result.removeAll(subset);
        return result;
    }
    // Reflexive--everything not in the intersection:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}

public class Zad15_17 extends Sets {
    public static void main(String[] args) {

        Set<Watercolors> set1 =
                EnumSet.range(Watercolors.BRILLIANT_RED, Watercolors.VIRIDIAN_HUE);
        Set<Watercolors> set2 =
                EnumSet.range(Watercolors.CERULEAN_BLUE_HUE, Watercolors.BURNT_UMBER);
        println("set1: " + set1);
        println("set2: " + set2);
        println("union(set1, set2): " + union(set1, set2));
        Set<Watercolors> subset = intersection(set1, set2);
        println("intersection(set1, set2): " + subset);
        println("difference(set1, subset): " +
                difference(set1, subset));
        println("difference(set2, subset): " +
                difference(set2, subset));
        println("complement(set1, set2): " +
                complement(set1, set2));
    }
}

// dodanie opcji z EnumSet jest po to, aby kopie startowa dla EnumSet robic naturalnym mechanizmem tego typu czyli clone() zamiast zawsze uzywac HashSet