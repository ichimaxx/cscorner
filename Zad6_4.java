import access.Protected_Class;
import static myutils.Skrocenie_Print.*;

public class Zad6_4 extends Protected_Class{
	public Zad6_4(){
	println("Package outside constructor test");}
	protected void test_klasy(){prot_jalapeno();}
	public static void main(String[] args){
new Zad6_4().test_klasy();}}