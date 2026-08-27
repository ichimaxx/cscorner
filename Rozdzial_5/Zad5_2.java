import static myutils.Skrocenie_Print.print;

/*
Exercise 2: (2) Create a class with a String field that is initialized at the point of
definition, and another one that is initialized by the constructor. What is the difference
between the two approaches?
*/
public class Zad5_2{
String s1 = "z poziomu definicji";
String s2;
public Zad5_2(){
s2 = "z poziomu konstruktora";}

public static void main (String[] args){
Zad5_2 sri = new Zad5_2();
print(sri.s1 + " | " + sri.s2);}}

