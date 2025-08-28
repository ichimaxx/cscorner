
import java.util.*;
import static myutils.Skrocenie_Print.print;

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
