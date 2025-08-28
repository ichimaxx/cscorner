import static myutils.Skrocenie_Print.print;

class Dog
{
	String name;
String says;}

public class zad3_5 {
public static void main (String[] args){
Dog spot = new Dog();
Dog scruffy = new Dog();

spot.name = "Spot" ; 
spot.says = "Hau";

scruffy.name = "Scruffy";
scruffy.says = "Wrrr";

print(spot.name + " says " + spot.says);
print(scruffy.name + " says " + scruffy.says);
}
}

