import static myutils.Skrocenie_Print.print;
/*
Exercise 4: (1) Add an overloaded constructor to the previous exercise that takes a
String argument and prints it along with your message.
*/
public class Zad5_4{
Zad5_4(){
print("Domyslny Konstruktor");}
Zad5_4(String s){
print(s + " i Domyslny Konstruktor");}

public static void main (String[] args){
if (args.length > 0) {
new Zad5_4(args[0]);}
else {
new Zad5_4();}
}}
