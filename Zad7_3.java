import static myutils.Skrocenie_Print.*;

class Art { 
  Art() { println("Art constructor"); } 
} 
class Drawing extends Art { 
  Drawing() { println("Drawing constructor"); } 
} 
public class Zad7_3 extends Drawing {  
  public static void main(String[] args) { 
  Zad7_3 x = new Zad7_3(); 
  } 
}