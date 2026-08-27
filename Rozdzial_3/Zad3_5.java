import static myutils.Skrocenie_Print.print;
/*
Exercise 5: (2) Create a class called Dog containing two Strings: name and says. In
main( ), create two dog objects with names “spot” (who says, “Ruff!”) and “scruffy” (who
says, “Wurf!”). Then display their names and what they say.
*/

class Dog
{
	String name;
String says;}

public class Zad3_5 {
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

