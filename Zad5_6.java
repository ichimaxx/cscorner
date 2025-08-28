
import java.util.*;
import static myutils.Skrocenie_Print.print;

public class Zad5_6 {
public void szczeki(){
	print("SZCZEK NORMALNY = HAL!!!!!");
}
public void szczeki(String a){
print("SZCZEK STRING = HAUUUU!!!");}
public void szczeki(int a, double b){
print("SZCZEK INT A POTEM DOUBLE = H4444L!! H4.4.4.4L!!!");}
public void szczeki(double a, int b){
print("SZCZEK DOUBLE A POTEM INT = H4.4.4.4L!!! H4444L!!");}


public static void main (String[] args){
Zad5_6 dog = new Zad5_6();
dog.szczeki();
dog.szczeki("szczek");
dog.szczeki(1, 1.1);
dog.szczeki(1.1, 1);}}
