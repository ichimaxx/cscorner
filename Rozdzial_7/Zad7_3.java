import static myutils.Skrocenie_Print.*;
/*
Exercise 3: (2) Prove the previous sentence.
*/
class Art { 
  Art() { println("Rozdzial_7.Art constructor"); }
} 
class Drawing extends Art { 
  Drawing() { println("Rozdzial_7.Drawing constructor"); }
} 
public class Zad7_3 extends Drawing {  
  public static void main(String[] args) { 
  Zad7_3 x = new Zad7_3(); 
  } 
}