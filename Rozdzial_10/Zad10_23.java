import static myutils.Skrocenie_Print.*;

interface U {
	void f1();
	void f2();
	void f3();
}

class Aj {
	private int id;
	
	public Aj(int id) { //  dodanie obiektowi Aj numeru zeby bylo wiadomo ktory jest ktory
		this.id = id;
	}
	
	public U makeU() {
		return new U() { // anonimowa inner class
			@Override
			public void f1() {
				println("f1 " + id);
			}
			@Override
			public void f2() {
				println("f2 " + id);
			}
			@Override
			public void f3() {
				println("f3 " + id);
			}
		};
	}
}
/* CO ROBI Zad10_23:
Dodaje U do tablicy,
usuwa U (zmienia na null (remove()))
przechodzi i wywoluje metody f1 f2 i f3 na kazdym U
*/

public class Zad10_23 {
	private U[] arr;
	private int next = 0;
	
	//tworzenie tablicy o danym rozmiarze
	public Zad10_23(int size) {
		arr = new U[size];
	}
	
	//dodaje referencje do U do tablicy tylko jesli jest miejsce (next<arr.length)
	public void add(U u) {
		if(next < arr.length)
		arr[next++] = u;
	}
	// usuwa referencje U spod danego indexu(zmienia je na null z powrotem tak jak jest na poczatku jak tworzysz tablice)
	public void remove(int index) {
		if (index >= 0 && index < arr.length) {
			arr[index] = null;
		}
	}
	
	
	//idzie po calej tablicy i wywoluje wszystkie trzy metody U dla kazdego elementu który nie jest null
	public void callall() {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != null) {
				arr[i].f1();
				arr[i].f2();
				arr[i].f3();
			}
		}
	}
	
	public static void main(String[] args) {
		Aj[] k = new Aj[5]; // tworzenie grupe obiektow(tablicy) "Aj" robi sie grupa 5 obiektow
		for (int i = 0; i < k.length; i++) {
			k[i] = new Aj(i); // to dodaje kazdemu obiektu z Aj swoje wlasne id
		}
		Zad10_23 o = new Zad10_23(k.length); // tworzenie "B (Zad10_23)" o rozmiarze rownym liczbie Aj
		for (int i = 0; i < k.length; i++) {
			o.add(k[i].makeU()); // wypelnia tablice B(zad10_23) obiektami f1,f2,f3 z Aj
		}
		
		//B(Zad10_23) wywoluje metody f1, f2 ,f3 na wszystkich U (czyli 5 bo Aj[5])
		o.callall();
		println("usuwamy elemenet z tablicy U i odpalam jeszcze raz callall()");
		//usuwa jeden z elementow tablicy U
		o.remove(1);
		o.callall();
	}
}