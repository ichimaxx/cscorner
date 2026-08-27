import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 10: (2) Create a class with two methods, f( ) and g( ). In g( ), throw an
exception of a new type that you define. In f( ), call g( ), catch its exception and, in the catch
clause, throw a different exception (of a second type that you define). Test your code in
main( ).
*/
class Exception1_1 extends Exception {
	public Exception1_1(String msgs) {
		super(msgs);
	}
}
class Exception2 extends Exception {
	public Exception2(String msg, Throwable cause) {
		super(msg, cause); /* jak robisz rethrow tego samego exception w catch to nie ma problemu bo printstacktrace zapisze ta informacje, ale w ksiazce jest informacja ze zeby robic rethrow roznych exception po sobie,
		to trzeba bylo recznie zachowywac informacje w rozne inne sposoby tak jak w ksiazce, teraz wystarczy to zrobic przez argument tak jak tutaj
		wtedy bez problemu wyrzuci tez wczesniejszy exception do logow.
		*/
	}
}
public class Zad12_10 {
	public static void g() throws Exception1_1 {
		throw new Exception1_1("exception 1 z g()");
		} 
	public static void f() throws Exception2 {
		try {
			g();
		} catch(Exception1_1 e) {
		throw new Exception2("exeption2 ze srodka f() first try i throw", e); // po prostu do twojego exception ktory chcesz zapisac, wypisujesz e jako ktory bedzie ten co "caused" exception
		}	
	}
	public static void main(String[] args) {
		try {
			f();
		} catch(Exception2 e) { 
			println("zlapany exception2 w main()");
			e.printStackTrace(System.out); // to dalej jak w ksiazce printujesz stackTrace, czyli kolejnosc wywolan wyjatków, w sumie o tym byl ten paragraf ale w zadaniu nie trzeba tego wykonywac...
		}
	}
}