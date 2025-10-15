import static myutils.Skrocenie_Print.*;

class ZFinal {
	public final void fin() {
		println("publiczna with final bez override");
	}
}
class OverrideMetody extends ZFinal {
	@Override
	public final void fin() {
		println("publiczna with final po override");
	}
}

public class Zad7_21 {
	public static void main (String[] args) {
		OverrideMetody over = new OverrideMetody();
		over.fin();
	}
}