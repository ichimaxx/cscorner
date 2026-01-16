import static net.mindview.util.Print.*;
import java.util.*;
/*Exercise 18: (3) Add a second level of exception loss to LostMessage.java so that the
HoHumException is itself replaced by a third exception. */

class VeryImportantException extends Exception {
	public String toString() { 
		return "A very important exception!";
	}
}
class HoHumException extends Exception {
	public String toString() {
		return "A trivial exception";
	}
}
class LolException extends Exception {
	public String toString() {
		return "A second trivial exception";
	}
}
public class Zad12_18 {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	void dispose() throws HoHumException {
		throw new HoHumException();
	}
	void newexc() throws LolException {
		throw new LolException();
	}
	public static void main(String[] args) {
		try {
			Zad12_18 lm = new Zad12_18();
			try {
				lm.f();
				try {
					lm.f();
				} finally {
				lm.dispose();
				}	
			} finally {
				lm.newexc();
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

// wywolany jest tylko i wylacznie wyjatek ostatni z finally, dwa exception z try sa pomijane w runtime(przy odpaleniu kodu)