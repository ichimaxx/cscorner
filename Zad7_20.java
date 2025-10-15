//: reusing/FinalOverridingIllusion.java 
// It only looks like you can override 
// a private or private final method. 
import static myutils.Skrocenie_Print.*;


class WithFinals { 
  // Identical to "private" alone: 
  private final void f() { print("WithFinals.f()"); } 
  // Also automatically "final": 
  private void g() { print("WithFinals.g()"); } 
} 
class OverridingPrivate extends WithFinals { 
@Override
  private final void f() { 
    print("OverridingPrivate.f()"); 
  } 
@Override
  private void g() { 
    print("OverridingPrivate.g()"); 
  } 
} 

class OverridingPrivate2 extends OverridingPrivate { 
@Override
  public final void f() { 
    print("OverridingPrivate2.f()"); 
  } 
@Override
  public void g() { 
    print("OverridingPrivate2.g()"); 
  } 
} 
public class Zad7_20 { 
  public static void main(String[] args) { 
    OverridingPrivate2 op2 = new OverridingPrivate2(); 
    op2.f(); 
    op2.g(); 
    // You can upcast: 
    OverridingPrivate op = op2; 
    // But you can’t call the methods: 
    //op.f(); 
    //op.g(); 
    // Same here: 
    WithFinals wf = op2; 
    //! wf.f(); 
    //! wf.g(); 
  } 
}

/*
BŁĄD PRZY KOMPILACJI, CZYLI DOBRZE WYRZUCA BO TAK JAK BYLO MOWIONE JAK JEST PRIVATE TO NIEWAZNE CO ZROBISZ TO I TAK NIE ZROBISZ OVERRIDE (PRIVATE MA TEZ FUNKCJE FINAL)
 Zad7_20.java:14: error: method does not override or implement a method from a supertype
@Override
^
Zad7_20.java:18: error: method does not override or implement a method from a supertype
@Override
^
Zad7_20.java:25: error: method does not override or implement a method from a supertype
@Override
^
Zad7_20.java:29: error: method does not override or implement a method from a supertype
@Override
^ 

*/