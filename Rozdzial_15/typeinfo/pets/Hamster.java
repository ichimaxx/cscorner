//: typeinfo/pets/Hamster.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Hamster extends Rodent {
  public Hamster(String name) { super(name); }
  public Hamster() { super(); }
  public void speak() { println("Hamster mówi: KRkrkrkr"); }
} ///:~
