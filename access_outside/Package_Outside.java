package access_outside;
import access.Protected_Class;
import static myutils.Skrocenie_Print.*;

public class Package_Outside extends Protected_Class{
	public Package_Outside(){
	println("Package outside constructor test");}
	protected void test_klasy(){prot_jalapeno();}
	public static void main(String[] args){
new Package_Outside().test_klasy();}}