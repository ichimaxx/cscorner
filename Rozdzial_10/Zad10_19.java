import static myutils.Skrocenie_Print.*;
/*
Exercise 19: (2) Create a class containing an inner class that itself contains an inner
class. Repeat this using nested classes. Note the names of the .class files produced by the
compiler.
*/
public class Zad10_19 {
	private class IClass1 {
		private class IClass2 {
			private String f = "IClass2";
			private String istrink() {
			return f;
			}
		}
		public IClass2 iklasa2() {
			return new IClass2();
		}
	}
	private static class NClass1 {
		private static class NClass2 {
			private String g = "NClass2";
			private String nstrink() {
				return g;
			}
		}
	}
	public IClass1 iklasa1() {
		return new IClass1();
	}


	public static void main (String[] args) {;
		Zad10_19.NClass1.NClass2 op = new Zad10_19.NClass1.NClass2();
		println(op.nstrink());
		Zad10_19 k = new Zad10_19();
		Zad10_19.IClass1 kn = k.iklasa1();
		IClass1.IClass2 kno = kn.iklasa2();
		println(kno.istrink());
	}
}