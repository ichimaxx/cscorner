import access.Protected_Class;
/*
Exercise 15: (2) Create a class inside a package. Your class should contain a protected
method. Outside of the package, try to call the protected method and explain the results.
Now inherit from your class and call the protected method from inside a method of your
derived class.
*/

public class Zad7_15 extends Protected_Class {
	public static void main(String[] args) {
		Zad7_15 k = new Zad7_15();
		k.prot_jalapeno();
	}
}