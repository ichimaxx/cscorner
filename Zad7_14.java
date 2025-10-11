import static myutils.Skrocenie_Print.*;

class Engine { 
  public String status = "OK";
  
  public void start() { 
	println("start() silnika");
  } 
  public void rev() {
	println("rev() silnika");
  } 
  public void stop() {
	println("stop() silnika");  
  } 
  public String service(String info) {
	status = info;
	return "Status silnika: " + status;
  }
  public String status() {
	return status;
  }
} 
class Wheel { 
  public int psi;
  public String inflate(int psi) {
	this.psi = psi;
	return "Kola napompowane do  " + this.psi + " psi";
  } 
} 
class Window { 
  public String rollup() {
	return "Window.rollup()";
  } 
  public String rolldown() {
	return "Window.rolldown()";
  } 
} 
class Door { 
  public Window window = new Window(); 
  public String open() {
	return "Door open()";
  } 
  public String close() {
	return "Door close()";
  } 
} 
public class Zad7_14 { 
  public Engine engine = new Engine(); 
  public Wheel[] wheel = new Wheel[4]; 
  public Door 
    left = new Door(), 
    right = new Door(); // dwie pary drzwi
  public Zad7_14() { 
    for(int i = 0; i < 4; i++) 
      wheel[i] = new Wheel(); 
  } 
  public static void main(String[] args) { 
    Zad7_14 car = new Zad7_14(); 
    println(car.left.window.rollup()); 
    println(car.wheel[0].inflate(72)); 
	println(car.engine.service("SERWISIK AUTA"));
  } 
} 