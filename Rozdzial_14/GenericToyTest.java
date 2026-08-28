//: typeinfo/toys/GenericToyTest.java
// Testing class Class.

interface HasBatteries_4 {}
interface Waterproof_4 {}
interface Shoots_4 {}

class Toy_4 {
  Toy_4() {}
  Toy_4(int i) {}
}

class FancyToy_4 extends Toy_4
        implements HasBatteries_4, Waterproof_4, Shoots_4 {
  FancyToy_4() {
    super(1);
  }
}
public class GenericToyTest {
  public static void main(String[] args) throws Exception {
    Class<FancyToy_4> ftClass = FancyToy_4.class;
    // Produces exact type:
    FancyToy_4 fancyToy = ftClass.newInstance();
    Class<? super FancyToy_4> up = ftClass.getSuperclass();
    // This won't compile:
    // Class<Toy> up2 = ftClass.getSuperclass();
    // Only produces Object:
    Object obj = up.newInstance();
  }
} ///:~
