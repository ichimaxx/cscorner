package inner_2;
import inner_1.*;
import static myutils.Skrocenie_Print.*;

public class Inner2 {
	protected class Inner2p implements Inner1 {
		@Override
		public void inner1() {
			println("Tekst z package inner_2 protected class Inner2p");
		}
	}
	public Inner1 inner1() {
		return new Inner2p();
	}
}
		