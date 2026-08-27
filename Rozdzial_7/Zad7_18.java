import static myutils.Skrocenie_Print.*;
import java.util.*;
/*
Exercise 18: (2) Create a class with a static final field and a final field and
demonstrate the difference between the two.
*/
class Value {
	int i;
	@Override public String toString(){
		return String.valueOf(i);  // TO POWODUJE ZE WARTOSCI SA ZAPISANE W STRING
	}
	public Value(int i) {
		this.i = i;
	}
}

public class Zad7_18 {
	String id;
	public final Value v1 = new Value(25);
	public final static Value v2 = new Value(300);
	public Value v3 = new Value(251);
	public Zad7_18(String id) {
		this.id = id;
	}
	
static class ValueChange{
	private static Random rand = new Random(47);
	static void change(Zad7_18 z){ 
		z.v1.i = 912;
		z.v3 = new Value(rand.nextInt(300));
		Zad7_18.v2.i = 149;
	}
}


	public String toString() {
		return id + ": " + "v1(final) = " + v1 + " // v2(final static) = " + v2 + " // v3(nic) = " + v3; 
	}

	public static void main(String[] args){
		Zad7_18 fa = new Zad7_18("PIERWSZA INSTANCJA");
		println(fa);
		ValueChange.change(fa);
		println("po zmianie values pierwszej instancji \n" + fa);
		Zad7_18 fa2 = new Zad7_18("DRUGA INSTANCJA");
		println("DODANIE DO ZADANIA DRUGIEJ INSTANCJI");
		println(fa2);
		ValueChange.change(fa2);
		println("ponizej print z nowej instancji po change");
		println(fa);
		println(fa2);
	}
}
	
	
/* 
v1 – final (instancyjne)
MOGE
	zmienić zawartość obiektu: z.v1.i = 912;

NIE MOGE
	podmienić referencji: z.v1 = new Rozdzial_7.Value(...); (błąd kompilacji)
	
		Zmiany dotyczą tylko tej jednej instancji Rozdzial_7.Zad7_18.

v2 – static final (klasowe)
MOGE
	zmienić zawartość wspólnego obiektu: Rozdzial_7.Zad7_18.v2.i = 149 (lepiej przez nazwę klasy, ale fa.v2.i = ... też zadziała)

NIE MOGE
	podmienić referencji: Rozdzial_7.Zad7_18.v2 = new Rozdzial_7.Value(...); (błąd kompilacji)
	
		Zmiana jest wspólna dla wszystkich instancji (widać ją w fa i fa2).

v3 – zwykłe (instancyjne, bez final)
MOGE
zmienić zawartość: z.v3.i = 123;
też podmienić referencję: z.v3 = new Rozdzial_7.Value(rand.nextInt(...));

*/