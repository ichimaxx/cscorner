import static myutils.Skrocenie_Print.print;
/*
Exercise 8: (1) Create a class with two methods. Within the first method, call the second
method twice: the first time without using this, and the second time using this—just to see it
working; you should not use this form in practice.
*/
public class Zad5_8 {
public void metoda1(){
	metoda2();
	this.metoda2();
}
public void metoda2(){
print("metoda2() metodka 2 o tak o");}
public static void main(String args[]){
	new Zad5_8().metoda1();
}
}
	

