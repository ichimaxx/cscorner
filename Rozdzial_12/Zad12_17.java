import static net.mindview.util.Print.*;
import java.util.*; 
/*Exercise 17: (3) Modify polymorphism/Frog.java so that it uses try-finally to
guarantee proper cleanup, and show that this works even if you return from the middle of
the try-finally. */
class Characteristics {
	private String s;
	Characteristics(String s) {
		this.s = s;
		print("Creating Characteristics " + s);
	}
	protected void dispose() {
		print("disposing Characteristics " + s);
	}
}
class Descriptions {
	private String s;
	Descriptions(String s) {
		this.s = s;
		print("Creating Descriptions " + s);
	}
	protected void dispose() {
		print("disposing Descriptions " + s);
	}
} 
class LivingCreatures {
	private Characteristics p = new Characteristics("is alive");
	private Descriptions t = new Descriptions("Basic Living Creature");
	LivingCreatures() {
		print("LivingCreatures()");
	}
	protected void dispose() {
		print("LivingCreatures dispose");
		t.dispose();
		p.dispose();
	}
}
class Animals extends LivingCreatures {
	private Characteristics p = new Characteristics("has heart");
	private Descriptions t = new Descriptions("Animals not Vegetable");
	Animals() { 
		print("Animals()"); 
	}
	protected void dispose() {
		print("Animals dispose");
		t.dispose();
		p.dispose();
		super.dispose();
	}
}
class Amphibians_2 extends Animals {
	private Characteristics p = new Characteristics("can live in water");
	private Descriptions t = new Descriptions("Both water and land");
	Amphibians_2() {
		print("Amphibians()");
	}
	protected void dispose() {
		print("Amphibians dispose");
		t.dispose();
		p.dispose();
		super.dispose();
	}
}
public class Zad12_17 extends Amphibians_2 {
	private Characteristics p = new Characteristics("Croaks");
	private Descriptions t = new Descriptions("Eats Bugs");
	public Zad12_17() { 
		print("Zad12_17()"); 
	}
	protected void dispose() {
		print("Zad12_17 dispose");
		t.dispose();
		p.dispose();
		super.dispose();
	}
	public static void main(String[] args) {
		Zad12_17 frog = new Zad12_17();
		try {
			print("Bye!");
			return;
		} finally {
			print("czyszczenie");
			frog.dispose();
		}
	}
}

// to samo co z poprzednim cwiczeniem, jak wrzucisz return w try to i tak odpali finally i zrobi jak w tym przypadku dispose()
// przypominajac super.dispose() cofa sie do derived klasy czyli dziedziczacej to np do Zad12_17 super.dispose() odpali dispose() z Amphibians bo jej robi extends