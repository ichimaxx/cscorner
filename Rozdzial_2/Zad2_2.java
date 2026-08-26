/*
Exercise 2: (1) Following the HelloDate.java example in this chapter, create a “hello,
world” program that simply displays that statement. You need only a single method in your
class (the “main” one that gets executed when the program starts). Remember to make it
static and to include the argument list, even though you don’t use the argument list. Compile
the program with javac and run it using java. If you are using a different development
environment than the JDK, learn how to compile and run programs in that environment.
*/
public class Zad2_2
{
	String c = "Witaj,";
	String a = "świecie!";
	public Zad2_2(){
	System.out.println(c + a);}
	public static void main (String[]args) {
	new Zad2_2();}
}
	