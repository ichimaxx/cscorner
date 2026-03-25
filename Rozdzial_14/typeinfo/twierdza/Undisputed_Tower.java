package typeinfo.twierdza;
import static myutils.Skrocenie_Print.*;

public class Undisputed_Tower {
	void one() {
		println("METHOD ONE JAKO SCHOWANA METODA");
	}
	public void two() {
		println("METHOD TWO JAKO PUBLICZNA METODA");
	}
	protected void three() {
		println("METHOD THREE JAKO PROTECTED METODA");
	}
	private void four() {
		println("METHOD FOUR JAKO PRIVATE METODA");
	}
	private final void five() {
		println("METHOD FIVE JAKO PRIVATE FINAL METODA");
	}
	private void six() {
		Packac z = new Packac();
		z.packacs();
	}
} // metody stworzone na potzreby zadania Zad14_25.java