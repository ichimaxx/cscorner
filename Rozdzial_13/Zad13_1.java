import java.util.*; 
import static myutils.Skrocenie_Print.*;
/*Exercise 1: (2) Analyze SprinklerSystem.toString( ) in
reusing/SprinklerSystem.java to discover whether writing the toString( ) with an
explicit StringBuilder will save any StringBuilder creations. */

class WaterSource {
  private String s;
  WaterSource() {
    System.out.println("WaterSource()");
    s = "Constructed";
  }
  public String toString() { return s; }
}	

public class Zad13_1 {
  private String[] valves = new String[4];
  private WaterSource source = new WaterSource();
  private int i;
  private float f;
  /*
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("valve1 = ").append(valve1).append(' ')
      .append("valve2 = ").append(valve2).append(' ')
      .append("valve3 = ").append(valve3).append(' ')
      .append("valve4 = ").append(valve4).append('\n')
      .append("i = ").append(i).append(' ')
      .append("f = ").append(f).append(' ')
      .append("source = ").append(source);

    return sb.toString();
  }*/
  public String toString() {
	StringBuilder result = new StringBuilder();
	for(int z = 0; z < 4; z++) {
		result.append("valve").append(z + 1).append(" = ").append(valves[z]).append(" ");
	}
	result.delete(result.length()-1, result.length());
	result.append("\n");
	result.append("i = ").append(i).append(" ").append("f = ").append(f).append(" ").append("source = ").append(source);
	return result.toString();
	}

  public static void main(String[] args) {
    Zad13_1 sprinklers = new Zad13_1();
    System.out.println(sprinklers);
  }
} 
// w starszych Javach s += ... w pętli powodowało tworzenie wielu pośrednich Stringów tzw konkatenacji
// w oryginalnym SprinklerSystem.toString() (jedno wyrażenie z '+') kompilator i tak składa hurtowo
// reczny stringbuilder nie zmneijsza liczby jego utworzen, zysk jest glownie w petlach