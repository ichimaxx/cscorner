//: typeinfo/pets/Cat.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Cat extends Pet {
  public Cat(String name) { super(name); }
  public Cat() { super(); }
  public void speak() { println("Kot mówi: meow"); }
} ///:~
