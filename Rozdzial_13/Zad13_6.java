import java.util.*;
import static myutils.Skrocenie_Print.*;
import java.math.*;

/*Exercise 6: (2) Create a class that contains int, long, float and double fields. Create a
toString( ) method for this class that uses String.format( ), and demonstrate that your
class works correctly.  */

public class Zad13_6 {
	int numers = 15;
	long numers2 = 500;
	float numers3 = 1.9534f;
	double numers4 = 1555.5304592105930196510395319045910315;
	public String toString() {
		return String.format("Int: %d Long: %d Float: %.5f Double: %e", numers, numers2, numers3, numers4);  // ciekawostka, %f DOMYŚLNIE daje 6 miejsc po przecinku, jak chce mniej lub wiecej to dodać trzeba precision typu .2 albo cos
	}
	public static void main (String[] args) {
		Zad13_6 test = new Zad13_6();
		println(test);
	}
}