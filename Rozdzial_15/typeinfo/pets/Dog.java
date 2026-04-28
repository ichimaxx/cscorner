//: typeinfo/pets/Dog.java
package Rozdzial_15.typeinfo.pets;
import static myutils.Skrocenie_Print.*;

public class Dog extends Pet {
  public Dog(String name) { super(name); }
  public Dog() { super(); }
  public void speak() { println("Dog mówi: wooof"); }
} ///:~
