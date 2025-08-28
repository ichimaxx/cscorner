
import java.util.*;
import static myutils.Skrocenie_Print.print;

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
