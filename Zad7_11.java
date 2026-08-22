import static myutils.Skrocenie_Print.*;
  
  
 class Cleanser {
  private String s = "Cleanser: \n";
  public void append(String a) {
	s += " " + a; 
  }
  public void dilute() {
	append("dilute() \n"); 
  }
  public void apply()  {
    append("apply() \n");
  }
  public void scrub()  {
  append("scrub() \n");
  }
  @Override public String toString() {
  return s;
  }
}

public class Zad7_11 { 
  private static Cleanser supplies = new Cleanser();
  public void dilute(){
	  supplies.dilute();
  }
  public void apply(){
	  supplies.apply();
  }
  public void scrub(){
	 supplies.scrub();
	 supplies.append("Detergent.scrub() \n"); 
  }
  
 
  // nowa metodka
  public void foam() {
	  supplies.append("foam() \n"); 
  } 
  @Override public String toString() { return supplies.toString(); }
  
  // tescik 
  public static void main(String[] args) { 
    Zad7_11 x = new Zad7_11(); 
    x.dilute(); 
    x.apply(); 
    x.scrub(); 
    x.foam();  
	println(x);
  }  
}