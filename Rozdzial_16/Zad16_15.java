import myutils.*;
import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 15: (2) Modify ContainerComparison.java by creating a Generator for
BerylliumSphere, and change main( ) to use that Generator with Generated.array().
*/
class BerylliumSphere6 {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Sphere " + id; }
}
public class Zad16_15 {
    public static class BerylliumSphere6Gen implements myutils.Generator<BerylliumSphere6> {
        public BerylliumSphere6 next() {
            return new BerylliumSphere6(); }
    } // generator tworzący obiekty BerylliumSphere6
    public static void main(String[] args) {
        int size = 5;
        BerylliumSphere6[] spheres = new BerylliumSphere6[size];
        spheres = Generated.array(spheres, new BerylliumSphere6Gen()); // wypełnienie array za pomocą Generated.array() w tej sytuacji wypełniana jest istniejąca tabela
    println(Arrays.toString(spheres));
    println(spheres[4]);
    List<BerylliumSphere6> sphereList =
            new ArrayList<BerylliumSphere6>();
    for(int i = 0; i < 5; i++)
        sphereList.add(new BerylliumSphere6());
    println(sphereList);
    println(sphereList.get(4));
    int[] integers = { 0, 1, 2, 3, 4, 5 };
    println(Arrays.toString(integers));
    println(integers[4]);
    List<Integer> intList = new ArrayList<Integer>(
            Arrays.asList(0, 1, 2, 3, 4, 5));
    intList.add(97);
    println(intList);
    println(intList.get(4));
    }
}