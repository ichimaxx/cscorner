import static myutils.Skrocenie_Print.*;
import java.util.*; 

interface Processor {
	String name();
	Object process(Object input);
}


class Apply {
	public static void process(Processor p, Object s) {
		println("Using Processor " + p.name());
		println(p.process(s));
	}
}

public abstract class Zad9_11 implements Processor{
	public String name() {
		return getClass().getSimpleName();
	}
	public abstract String process(Object input);
	public static String s = "If she weighs the same as a duck, she's made of wood";
	public static void main(String[] args) {
		Apply.process(new Upcase(), s);
		Apply.process(new Downcase(), s);
		Apply.process(new Splitter(), s);
		Apply.process(new SplitterByChar(), s);
	}
} 

class Upcase extends Zad9_11 {
	public String process(Object input) { // Covariant return
		return ((String)input).toUpperCase();
	}
}
class Downcase extends Zad9_11 {
	public String process(Object input) {
		return ((String)input).toLowerCase();
	}
}
class Splitter extends Zad9_11 {
	public String process(Object input) {
		return Arrays.toString(((String)input).split(" "));
	}
} 

class SplitterByChar extends Zad9_11 {
	public String process(Object input) {
		char temp;
		char arr[] = ((String)input).toCharArray();
		for(int i=0;i<arr.length-1; i +=2){
            temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
        }
		return new String(arr);
	}
	
}