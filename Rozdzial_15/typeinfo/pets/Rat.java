//: typeinfo/pets/Rat.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Rat extends Rodent {
  public Rat(String name) { super(name); }
  public Rat() { super(); }
  public void speak() { println("Rat mówi: PIII PIPII"); }
} ///:~
