//: reusing/FinalOverridingIllusion.java
// It only looks like you can override 
// a private or private final method. 
import static myutils.Skrocenie_Print.*;
/*
Exercise 20: (1) Show that @Override annotation solves the problem in this section.
*/

class WithFinals { 
  // Identical to "private" alone: 
  private final void f() { print("Rozdzial_7.WithFinals.f()"); }
  // Also automatically "final": 
  private void g() { print("Rozdzial_7.WithFinals.g()"); }
} 
class OverridingPrivate extends WithFinals {
  private final void f() { 
    print("Rozdzial_7.OverridingPrivate.f()");
  }
  private void g() { 
    print("Rozdzial_7.OverridingPrivate.g()");
  } 
} 

class OverridingPrivate2 extends OverridingPrivate {
  public final void f() { 
    print("Rozdzial_7.OverridingPrivate2.f()");
  }
  public void g() { 
    print("Rozdzial_7.OverridingPrivate2.g()");
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
 Rozdzial_7.Zad7_20.java:14: error: method does not override or implement a method from a supertype
@Override
^
Rozdzial_7.Zad7_20.java:18: error: method does not override or implement a method from a supertype
@Override
^
Rozdzial_7.Zad7_20.java:25: error: method does not override or implement a method from a supertype
@Override
^
Rozdzial_7.Zad7_20.java:29: error: method does not override or implement a method from a supertype
@Override
^ 

*/