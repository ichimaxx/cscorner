import static myutils.Skrocenie_Print.print;
import java.util.*;

class chuj {
float f;
}

public class zad3_2 {
public static void main(String[] args) {
	chuj a = new chuj();
	chuj b = new chuj();
	chuj c = new chuj();
	
	a.f = 30f;
	b.f = 45f;
	c.f = 60f;
	print("BEZ ZMIAN ARGUMENTUW: a.f =" + a.f + " b.f = " + b.f + " c.f = " + c.f);
	a = b;
	print("ZE ZMIANA A na B: a.f =" + a.f + " b.f = " + b.f + " c.f = " + c.f);
	
	b.f = 4f;
	print("ZE ZMIANA B NA LICZBE 4: a.f =" + a.f + " b.f = " + b.f + " c.f = " + c.f);
}
}