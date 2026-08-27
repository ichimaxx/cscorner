import static myutils.Skrocenie_Print.print;
/*
Exercise 5: (2) Create a class called Dog with an overloaded bark( ) method. This
method should be overloaded based on various primitive data types, and print different types
of barking, howling, etc., depending on which overloaded version is called. Write a main( )
that calls all the different versions.
*/
public class Zad5_5 {
public void szczeki(){
	print("SZCZEK NORMALNY = HAL!!!!!");
}
public void szczeki(String a){
print("SZCZEK STRING = HAUUUU!!!");}
public void szczeki(int a){
print("SZCZEK INT = H4444L!!");}
public void szczeki(double a){
print("SZCZEK DOUBLE = H4.4.4.4L!!!");}


public static void main (String[] args){
Zad5_5 dog = new Zad5_5();
dog.szczeki();
dog.szczeki("szczek");
dog.szczeki(1);
dog.szczeki(1.1);}}
