import static myutils.Skrocenie_Print.*;
class Game { 
  Game(int i) { 
	println("Game constructor"); 
  } 
} 
class BoardGame extends Game { 
  BoardGame(int i) { 
	super(i);
    println("BoardGame constructor"); // jak wykomentujesz te dwie linie to wlasnie udowodnisz ze program wgl sie nie skompiluje
  } 
}  
public class Zad7_6 extends BoardGame { 
  Zad7_6() { 
    super(11); 
    print("Chess constructor"); 
  } 
  public static void main(String[] args) { 
    Zad7_6 x = new Zad7_6(); 
  } 
}