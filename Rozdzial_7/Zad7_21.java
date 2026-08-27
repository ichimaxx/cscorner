import static myutils.Skrocenie_Print.*;
/*
Exercise 21: (1) Create a class with a final method. Inherit from that class and attempt
to overwrite that method.
*/
class ZFinal {
	public final void fin() {
		println("publiczna with final bez override");
	}
}
class OverrideMetody extends ZFinal {
	//@Override
	//public final void fin() {
	//	println("publiczna with final po override");
	//}
}

public class Zad7_21 {
	public static void main (String[] args) {
		OverrideMetody over = new OverrideMetody();
		over.fin();
	}
}
/*
Przy odkomentowaniu metody zadanie się nie skompiluje:
Zad7_21.java:13: error: fin() in OverrideMetody cannot override fin() in ZFinal
        public final void fin() {
                          ^
  overridden method is final
1 error
*/