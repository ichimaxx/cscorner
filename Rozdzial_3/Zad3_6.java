import static myutils.Skrocenie_Print.print;
/*
Exercise 6: (3) Following Exercise 5, create a new Dog reference and assign it to spot’s
object. Test for comparison using == and equals( ) for all references.
*/
class Dog2
{
	String name;
	String says;}

public class Zad3_6 {
  static void compare(Dog2 spot, Dog2 scruffy)
  { 
    print("== na przodzie: " + (spot == scruffy));
	print("funkcja equals: " + spot.equals(scruffy));
	print("== na przodzie imienia: " + (spot.name == scruffy.name));
	print("funkcja equals na imieniu: " + spot.name.equals(scruffy.name));
	print("== na przodzie tego co robi pies: " + (spot.says == scruffy.says));
	print("funkcja equals tego co robi pies: " + spot.says.equals(scruffy.says));
  }
public static void main (String[] args){
	Dog2 spot = new Dog2();
	Dog2 scruffy = new Dog2();
	Dog2 nowy = spot;  // nowy dodany dog (PIESIO)
	 spot.name = "Spot";
	 spot.says = "HAU";
	 scruffy.name = "Scruffy";
	 scruffy.says = "WRRRRRRRR";
	 print("PORÓWNANIE PSA SPOT I SCRUFFY:");
	 compare(spot, scruffy);
	 print("PORÓWNANIE PSA SPOT I NOWY");
	 compare(spot, nowy);
	 print("PORÓWNANIE PSA SCRUFY I NOWY");
	 compare(scruffy, nowy);
}}
