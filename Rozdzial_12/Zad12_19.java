import static net.mindview.util.Print.*;
import java.util.*;
/*Exercise 19: (2) Repair the problem in LostMessage.java by guarding the call in the
finally clause.*/

class VeryImportantException_2 extends Exception {
	public String toString() { 
		return "A very important exception!";
	}
}
class HoHumException_2 extends Exception {
	public String toString() {
		return "A trivial exception";
	}
}

public class Zad12_19 {
	void f() throws VeryImportantException_2 {
		throw new VeryImportantException_2();
	}
	void dispose() throws HoHumException_2 {
		throw new HoHumException_2();
	}
	public static void main(String[] args) {
		try {
			Zad12_19 lm = new Zad12_19();
			try {
				lm.f();	
			} finally {
				try{
					lm.dispose();
				} catch(Exception e) {
					System.out.println(e);
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

// wniosek jest taki ze jak rzucasz wyjatek w finally a nie chcesz stracić wyjątku z wcześniejszego try to zawsze musisz go zlapac(catch) w nowym try zagniezdzonym w finally
