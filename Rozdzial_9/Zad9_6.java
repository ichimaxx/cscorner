import interfejs.Interfacek_private;
import static myutils.Skrocenie_Print.*;
/*
Exercise 6: (2) Prove that all the methods in an interface are automatically public.
*/
public class Zad9_6 implements Interfacek_private {
	@Override
	public // usuń, aby zobaczyć rozwiązanie zadania
	void metoda1(){ //nie przejdzie bo interface jest domyślnie public
		println("metoda1 printek");
	}
	@Override
	public // usuń, aby zobaczyć rozwiązanie zadania
	void metoda2(){ //nie przejdzie bo interface jest domyślnie public
		println("metoda2 printek");
	}
	@Override
	public // usuń, aby zobaczyć rozwiązanie zadania
	void metoda3(){ //nie przejdzie bo interface jest domyślnie public
		println("metoda3 printek");
	}
	public static void main(String[] args) {
		Interfacek_private ek = new Zad9_6();
		ek.metoda1();
		ek.metoda2();
		ek.metoda3();
	}
}
/*
C:\Users\ichim\Desktop\cscorner>javac Zad9_6.java
Zad9_6.java:14: error: metoda3() in Zad9_6 cannot implement metoda3() in Interfacek_private
        void metoda3(){
             ^
  attempting to assign weaker access privileges; was public
Zad9_6.java:10: error: metoda2() in Zad9_6 cannot implement metoda2() in Interfacek_private
        void metoda2(){
             ^
  attempting to assign weaker access privileges; was public
Zad9_6.java:6: error: metoda1() in Zad9_6 cannot implement metoda1() in Interfacek_private
        void metoda1(){
             ^
  attempting to assign weaker access privileges; was public
3 errors

nie skompiluje sie bo bo chce zrobic override na domyslny dostep bo metody interface maja 
ustawiony modyfikator public
*/