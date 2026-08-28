import static myutils.Skrocenie_Print.*;
/*
Exercise 20: (1) Create an interface containing a nested class. Implement this interface
and create an instance of the nested class.
*/
public interface Zad10_20 {
	public void g();
	class ClassInterface implements Zad10_20 {
		public void g() {
			println("g w klasie ClassInterface ktora implementuje Zad10_20 czyl interfejs");
		}
	}
	public static void main (String[] args) {
		Zad10_20 f = new ClassInterface();
		f.g();
	}
}