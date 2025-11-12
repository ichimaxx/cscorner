import static myutils.Skrocenie_Print.*;
import java.util.*; 



interface Game { 
	boolean rzut(); 
	Random RAND = new Random(); 
	
	/* static boolean randBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }
    static int randInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
	
	wedlug ksiazki powinno byc random w interfejsie ale tak naprawde przez to ze tak jest interfejs odpala sie wiecej razy niz potrzeba z threadlocalrandomcurrent odpala sie raz*/
	
}
interface GameFactory { 
	Game getGame(); 
}

class Cointoss implements Game {
	private int moves = 0;
	private String checker() { 
		int RANDC = Game.RAND.nextInt(2);
		return (RANDC == 0) ? "ORZEL" : "RESZKA"; 
	}
	private static final int MOVES = 1;
	public boolean rzut() {
		String wynik = checker();
		println("Coin toss result: " + wynik);
		return ++moves != MOVES;
	}
}
class CointossFactory implements GameFactory {
	public Game getGame() { 
		return new Cointoss(); 
	}
}
class Dietoss implements Game {
	private int moves = 0;
	private int rzuters() {
		return Game.RAND.nextInt(6) + 1;
	}

	private static final int MOVES = 1;
	public boolean rzut() {
		int wyniks = rzuters();
		println("Die toss result: " + wyniks);
		return ++moves != MOVES;
	}
}
class DietossFactory implements GameFactory {
	public Game getGame() { 
		return new Dietoss(); 
	}
}
public class Zad9_19 {
	public static void playGame(GameFactory factory) {
		Game s = factory.getGame();
		while(s.rzut())
		;
	}
	public static void main(String[] args) {
		playGame(new CointossFactory());
		playGame(new DietossFactory());
	}
}