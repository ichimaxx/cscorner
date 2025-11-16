import static myutils.Skrocenie_Print.*;
import java.util.*; 



interface Game1 { 
	boolean rzut(); 
	Random RAND = new Random(); 
	
	/* static boolean randBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }
    static int randInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
	
	wedlug ksiazki powinno byc random w interfejsie ale tak naprawde przez to ze tak jest interfejs odpala sie wiecej razy niz potrzeba z threadlocalrandom.current odpala sie raz*/
	
}
interface GameFactory1 { 
	Game1 getGame1(); 
}

class Cointoss1 implements Game1 {
	private int moves = 0;
	private String checker() { 
		int RANDC = Game1.RAND.nextInt(2);
		return (RANDC == 0) ? "ORZEL" : "RESZKA"; 
	}
	private static final int MOVES = 1;
	public boolean rzut() {
		String wynik = checker();
		println("Coin toss result: " + wynik);
		return ++moves != MOVES;
	}
	public static GameFactory1 factory = new GameFactory1() {
		public Game1 getGame1() {
			return new Cointoss1();
		}
	};
}

class Dietoss1 implements Game1 {
	private int moves = 0;
	private int rzuters() {
		return Game1.RAND.nextInt(6) + 1;
	}

	private static final int MOVES = 1;
	public boolean rzut() {
		int wyniks = rzuters();
		println("Die toss result: " + wyniks);
		return ++moves != MOVES;
	}
	public static GameFactory1 factory = new GameFactory1() {
		public Game1 getGame1() {
			return new Dietoss1();
		}
	};
}
public class Zad10_17 {
	public static void playGame(GameFactory1 factory) {
		Game1 s = factory.getGame1();
		while(s.rzut())
		;
	}
	public static void main(String[] args) {
		playGame(Cointoss1.factory);
		playGame(Dietoss1.factory);
	}
}