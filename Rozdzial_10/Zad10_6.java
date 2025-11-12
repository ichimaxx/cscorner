import inner_2.*;
import inner_1.*;
import static myutils.Skrocenie_Print.*;

public class Zad10_6 extends Inner2 {
	public static void main(String[] args) {
		Inner2 o = new Inner2();
		Inner1 op = o.inner1();
		op.inner1();
	}
}
		