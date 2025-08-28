import static myutils.Skrocenie_Print.print;


public class zad3_6 { 
  static void compare(Dog spot, Dog scruffy)
  { 
    print("== na przodzie: " + (spot == scruffy));
	print("funkcja equals: " + spot.equals(scruffy));
	print("== na przodzie imienia: " + (spot.name == scruffy.name));
	print("funkcja equals na imieniu: " + spot.name.equals(scruffy.name));
	print("== na przodzie tego co pierdoli pies: " + (spot.says == scruffy.says));
	print("funkcja equals tego co pierdoli pies: " + spot.says.equals(scruffy.says));
  }
public static void main (String[] args){
	 Dog spot = new Dog();
	 Dog scruffy = new Dog();
	 Dog nowy = spot;  // nowy dodany dog (PIESIO)
	 spot.name = "Spot";
	 spot.says = "HAU";
	 scruffy.name = "Scruffy";
	 scruffy.says = "WRRRRRRRR";
	 print("PORUWNANIE PSA SPOT I SCRUFY:");
	 compare(spot, scruffy);
	 print("PORUWNANIE PSA SPOT I NOWY");
	 compare(spot, nowy);
	 print("PORUWNANIE PSA SCRUFY I NOWY");
	 compare(scruffy, nowy);
}}
