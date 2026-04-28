//: typeinfo/pets/Mutt.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Mutt extends Dog {
  public Mutt(String name) { super(name); }
  public Mutt() { super(); }
  public void speak() { println("Mutt mówi: mumumu"); }
} ///:~
