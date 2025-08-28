package access;
import static myutils.Skrocenie_Print.*;

public class Widget {
int x = 1;
public static int a;
	static {
		Widget c = new Widget();
	    a = c.x + 1;
println(a);
}}