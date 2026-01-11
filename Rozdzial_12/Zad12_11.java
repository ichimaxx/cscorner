import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 11: (1) Repeat the previous exercise, but inside the catch clause, wrap g( )’s
exception in a RuntimeException. 
*/
class Exception1 extends Exception {
	public Exception1(String msgs) {
		super(msgs);
	}
}
public class Zad12_11 {
	public static void g() throws Exception1 { 
		throw new Exception1("exception 1 z g()");
		} 
	public static void f() { // dzieki temu ze RuntimeException jest UNCHECKED nie trzeba pisac throws :)
		try {
			g();
		} catch(Exception1 e) {
		throw new RuntimeException("zamiast Exception2 jak bylo w poprzednim cwiczeniu to RuntimeException ze srodka f() first try i throw", e); 
		
		// e jest "cause" (przyczyną) — dzięki temu nie gubimy stack trace Exception1
		}	
	}
	public static void main(String[] args) {
		try {
			f();
		} catch(RuntimeException e) { 
			println("zlapany RuntimeException w main()");
			e.printStackTrace(System.out); // to dalej jak w ksiazce printujesz stackTrace, czyli kolejnosc wywolan wyjatków, w sumie o tym byl ten paragraf ale w zadaniu nie trzeba tego wykonywac...
		}
	}
}