import java.util.*;

/*Exercise 3: (2) Add Rhomboid to Shapes.java. Create a Rhomboid, upcast it to a
Shape, then downcast it back to a Rhomboid. Try downcasting to a Circle and see what
happens. 
*/

abstract class Shapex_3 {
	void draw() { 
		System.out.println(this + ".draw()"); 
	}
	abstract public String toString();
}
class Circlex_3 extends Shapex_3 {
	public String toString() { 
		return "Circlex"; 
	}
}
class Squarex_3 extends Shapex_3 {
	public String toString() {
		return "Squarex"; 
	}
}
class Trianglex_3 extends Shapex_3 {
	public String toString() { 
		return "Trianglex"; 
	}
}
class Rhomboid_3 extends Shapex_3 {
	public String toString() { 
		return "Rhomboid"; 
	}
}
public class Zad14_3 {
	public static void main(String[] args) {
		List<Shapex_3> shapeList = Arrays.asList(new Circlex_3(), new Squarex_3(), new Trianglex_3(), new Rhomboid_3());
		for(Shapex_3 shape : shapeList)
			shape.draw();
		Rhomboid_3 romb = new Rhomboid_3();
		Shapex_3 shape = romb; // upcasting
		Rhomboid_3 rs = (Rhomboid_3) shape; // downcasting do rombu
		rs.draw(); // draw
		Circlex_3 cl = (Circlex_3) shape; // downcasting rombu do circle, BLEDNIE WYKONANY
		cl.draw(); // nie wykona się bo wyjdzie wyjątek ClassCastException, czyli to co oczekiwało cwiczenie, w javie kazdy cast jest sprawdzany w runtime i to jest wlasnie RTTI
	}
}

/* RTTI(runtime type identification) generalnie odpowiada za sprawdzenie jaki obiekt jest naprawde w środku referencji.
Dla przykladu jesli odniesiemy się do Circle z Rhomboid to tak naprawde nie robimy downcastu tylko jego niepoprawną próbę, poniewaz i Circle i Rhomboid w hierarchii sa na tym samym poziomie, Downcasting jest zrobiony poprawnie wiec program się skompiluje, ale typy są złe co wylapuje RTTI sprawdzając typ klasy


jest kartka z napisem:

„zwierzę” - Shapex

Pod spodem stoi naprawdę

„pies” - Rhomboid

A zapisane jest:

„to na pewno kot” - (Circlex) shape

Czy to jest zejście do bardziej konkretnego typu?
- Tak, formalnie to downcast.

Czy poprawne?
- Nie, bo w środku jest pies, a nie kot

I właśnie to sprawdzenie „czy to kot czy pies” to odpowiednik RTTI.

Jeśli jest psem, a próbujesz go potraktować jako kota, to mamy błąd - ClassCastException.
*/