import interfejs.Interfacek;
import static myutils.Skrocenie_Print.*;

public class Zad9_5 implements Interfacek {
	@Override
	public void metoda1(){
		println("metoda1 printek");
	}
	@Override	
	public void metoda2(){
		println("metoda2 printek");
	}
	@Override	
	public void metoda3(){
		println("metoda3 printek");
	}
	public static void main(String[] args) {
		Interfacek ek = new Zad9_5();
		ek.metoda1();
		ek.metoda2();
		ek.metoda3();
	}
}
