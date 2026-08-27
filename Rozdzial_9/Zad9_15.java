import static myutils.Skrocenie_Print.*;


interface one_1 {
	void jeden();
	void dwa();	
}

interface two_1 {
	void trzy();
	void cztery();
}

interface three_1 {
	void piec();
	void szesc();
}

interface four_1 extends one_1, two_1, three_1 {
	void siedem();
}

class Klasapierwsza_1 implements two_1 {
	@Override
	public void trzy() { 
		println("Klasapierwsza.trzy");
	}
	@Override
	public void cztery() {
		println("Klasapierwsza.cztery");
	}
}
class Klasatrzecia_1 implements three_1 {
	@Override
	public void piec() {};
	@Override
	public void szesc() {}; 
	public void dziewiec() {};
}
abstract class Klasaczwartaabstrakt extends Klasapierwsza_1 {
	public abstract void dziesiec();
}
	
public class Zad9_15 extends Klasaczwartaabstrakt implements four_1 {
	static void a(one_1 e) {
		e.jeden();
		e.dwa();
	}
	static void b(two_1 f) {
		f.trzy();
		f.cztery();
	}
	static void c(three_1 g) {
		g.piec();
		g.szesc();
	}
	@Override
	public void jeden() {
		println("Zad9_14.jeden");
	}
	@Override	
	public void dwa() {
		println("Zad9_14.dwa");
	}
	@Override	
	public void piec() {
		println("Zad9_14.piec");
	}
	@Override	
	public void szesc() {
		println("Zad9_14.szesc");
	}
	@Override
	public void siedem() {
		println("Zad9_14.siedem");
		
	}
	static void d(four_1 h) {
		h.siedem(); // z interface four
	}
	public void dziesiec() {
		println("Klasaczwartaabstrakcyjna extendujaca Zad9_15 ktora jest w klasie Zad9_15");
	}
	public static void main (String[] args) {
		three_1 lol = new Klasatrzecia_1();
		c(lol);
		Zad9_15 sim = new Zad9_15();
		a(sim);
		b(sim);
		c(sim);
		d(sim);
		sim.dziesiec();
	}
}