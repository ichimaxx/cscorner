import static myutils.Skrocenie_Print.*;

interface Monster {
	void menace();
}
interface DangerousMonster extends Monster {
	void destroy();
}
interface Lethal {
	void kill();
}
class DragonZilla implements DangerousMonster {
	public void menace() {}
	public void destroy() {}
}
interface Vampire extends DangerousMonster, Lethal {
	void drinkBlood();
}
class VeryBadVampire implements Vampire {
	public void menace() {}
	public void destroy() {}
	public void kill() {}
	public void drinkBlood() {}
}
public class Zad10_14 {
	
	static void u(Monster b) { 
		b.menace(); 
	}
	static void v(DangerousMonster d) {
		d.destroy();
	} 
	static void s(Vampire k) {
		k.drinkBlood();
	} 
	static DangerousMonster dangermon() {
		return new DangerousMonster() {
			public void menace() {
				println("DangerousMonster.menace()");
			}
			public void destroy() {
				println("DangerousMonster.destroy()");
			}
		};
	}
	static Vampire dangervamp() {
		return new Vampire() {
			public void menace() {
				println("Vampire.menace()");
			}
			public void destroy() {
				println("Vampire.destroy()");
			}
			public void kill() {
				println("Vampire.kill()");
			}
			public void drinkBlood() {
				println("Vampire.drinkBlood()");
			}
		};
	}
	static void w(Lethal l) { 
		l.kill(); 
	}
	public static void main(String[] args) {
		DangerousMonster barney = dangermon(); // teraz jak juz sa dorobione klasy vampire i dangerousmonster w glownej klasie to wystarczy odpalic ta metode i ona zajmie sie implementacja metod abstrakcyjnych
		u(barney);
		v(barney);
		Vampire vlad = dangervamp(); // teraz jak juz sa dorobione klasy vampire i dangerousmonster w glownej klasie to wystarczy odpalic ta metode i ona zajmie sie implementacja metod abstrakcyjnych
		u(vlad);
		s(vlad);
		w(vlad);
	}
}