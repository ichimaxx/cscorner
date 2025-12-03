import java.util.*;
import static myutils.Skrocenie_Print.*;


class Onex {
	protected long id;
	public Onex(long id) {
		this.id = id;
	}
	@Override
    public String toString() {
        return "id= " + id; // TOSTRING W METODZIE -.-
    }
}
class Twox extends Onex { 
	public Twox() {
		super(2);
	}
}
class Threex extends Onex {
	public Threex() {
		super(3);
	}
}
class Fourx extends Onex {
	public Fourx() {
		super(4);
	}
}
public class Zad11_11 {	
	public static void stringprint(Onex f) {
		println(f);
	}
		
	public static void zwracanie(Iterator<Onex> ones) { // ITERATOROWE ZWRACANIE!!!
		while (ones.hasNext()) {
			Onex o = ones.next();
			stringprint(o);
		}
	}
	public static void main (String[] args) {
		
	List<Onex> xd = Arrays.asList(new Onex(5), new Twox(), new Threex(), new Fourx()); 
	Iterator<Onex> it = xd.iterator();
	zwracanie(it);  // ITEROWANIE ZWYKLE
	print("\n");
	HashSet<Onex> onexHS = new HashSet<Onex>(xd);
	Iterator<Onex> sd = onexHS.iterator(); // ITEROWANIE ZGODNIE Z HASHSET
	zwracanie(sd);
	print("\n");
	LinkedList<Onex> petsLL = new LinkedList<Onex>(xd);
	Iterator<Onex> of = petsLL.iterator(); // ITEROWANIE ZGODNIE Z lINKED LIST
	zwracanie(of);
	
	}
	
}