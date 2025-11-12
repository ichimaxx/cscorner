import static myutils.Skrocenie_Print.*;

interface CanFight {
	void fight();
}
interface CanSwim {
	void swim();
}
interface CanFly {
	void fly();
}
interface CanClimb {
	void climb();
}
class ActionCharacter {
	public void fight() {}
}
class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly, CanClimb {
	@Override
	public void swim() {}
	@Override
	public void fly() {}
	@Override
	public void climb() {}
}
public class Zad9_12 {
	public static void t(CanFight x) {
		x.fight(); 
	}
	public static void u(CanSwim x) { 
		x.swim(); 
	}
	public static void v(CanFly x) { 
		x.fly(); 
	}
	public static void f(CanClimb x) { 
		x.climb(); 
	}	
	public static void w(ActionCharacter x) { 
		x.fight(); 
	}
	public static void main(String[] args) {
		Hero h = new Hero();
		t(h); // Treat it as a CanFight
		u(h); // Treat it as a CanSwim
		v(h); // Treat it as a CanFly
		w(h); // Treat it as an ActionCharacter
		f(h); // to jako a CanClimb
	}
}