import static myutils.Skrocenie_Print.*;

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