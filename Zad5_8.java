import java.util.*;
import static myutils.Skrocenie_Print.print;

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
	

