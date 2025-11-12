import static myutils.Skrocenie_Print.*;


interface one {
	void jeden();
	void dwa();	
}

interface two {
	void trzy();
	void cztery();
}

interface three {
	void piec();
	void szesc();
}

interface four extends one, two, three {
	void siedem();
}

class Klasapierwsza implements two {
	@Override
	public void trzy() { 
		println("Klasapierwsza.trzy");
	}
	@Override
	public void cztery() {
		println("Klasapierwsza.cztery");
	}
}
class Klasatrzecia implements three {
	@Override
	public void piec() {};
	@Override
	public void szesc() {}; 
	public void dziewiec() {};
}
abstract class Klasaczwartaabstrakt extends Klasapierwsza {
	public abstract void dziesiec();
}
	
public class Zad9_15 extends Klasaczwartaabstrakt implements four {
	static void a(one e) {
		e.jeden();
		e.dwa();
	}
	static void b(two f) {
		f.trzy();
		f.cztery();
	}
	static void c(three g) {
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
	static void d(four h) {
		h.siedem(); // z interface four
	}
	public void dziesiec() {
		println("Klasaczwartaabstrakcyjna extendujaca Zad9_15 ktora jest w klasie Zad9_15");
	}
	public static void main (String[] args) {
		three lol = new Klasatrzecia();
		c(lol);
		Zad9_15 sim = new Zad9_15();
		a(sim);
		b(sim);
		c(sim);
		d(sim);
		sim.dziesiec();
	}
}