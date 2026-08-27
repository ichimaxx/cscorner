import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (1) Using Chess.java, prove the statements in the previous paragraph.
*/
class Game { 
  Game(int i) { 
	println("Rozdzial_7.Game constructor");
  } 
} 
class BoardGame extends Game { 
  BoardGame(int i) { 
	super(i);
    println("Rozdzial_7.BoardGame constructor"); // jak wykomentujesz te dwie linie to wlasnie udowodnisz ze program wgl sie nie skompiluje
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