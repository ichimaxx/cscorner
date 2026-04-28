//: typeinfo/pets/Mouse.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Mouse extends Rodent {
  public Mouse(String name) { super(name); }
  public Mouse() { super(); }
  public void speak() { println("Mouse mówi: pipipi"); }
} ///:~
