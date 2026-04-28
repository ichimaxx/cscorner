//: typeinfo/pets/Rodent.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Rodent extends Pet {
  public Rodent(String name) { super(name); }
  public Rodent() { super(); }
  public void speak() { println("Rodent mówi: krkarra"); }
} ///:~
