import java.io.*;
import java.util.*;

/*Exercise 3: (1) Modify Turtle.java so that it sends all output to System.err. */

public class Zad13_3 {
	private String name;
	private Formatter f;
	public Zad13_3(String name, Formatter f) {
		this.name = name;
		this.f = f;
	}
	public void move(int x, int y) {
		f.format("%s The Turtle is at (%d,%d)\n", name, x, y);
	}
	public static void main(String[] args) {
		PrintStream outAlias = System.err;
		Zad13_3 tommy = new Zad13_3("Tommy", new Formatter(System.err));
		Zad13_3 terry = new Zad13_3("Terry", new Formatter(outAlias));
		tommy.move(0,0);
		terry.move(4,8);
		tommy.move(3,4);
		terry.move(2,5);
		tommy.move(3,3);
		terry.move(3,3);
	}
} 

/*
System.out jest tak zwanym "stdout" zwykły komunikat programu
System.err jest drugim strumieniem wyjścia nazywanym standard error (stderr), ogolnie lecą tam błedy/logi, ale mimo wszystko oba wypiszą to samo w konsoli choć są rozdzielone technicznie
dodatkowo pokiazane jest w zadaniu jak można łatwo stworzyć dwa formattery ktore beda obslugiwac ten sam output
*/