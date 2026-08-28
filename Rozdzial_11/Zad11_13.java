import controllerCH11.*;
import java.util.*; 
/*
Exercise 13: (3) In the innerclasses/GreenhouseController.java example, the
class Controller uses an ArrayList. Change the code to use a LinkedList instead, and use
an Iterator to cycle through the set of events.
*/
// PEŁNE ROZWIAZANIE W Controller.JAVA ROZDZIAL_11
public class Zad11_13 {
	public static void main(String[] args) {
		GreenhouseControlss gc = new GreenhouseControlss();
		
		gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
            gc.new ThermostatNight(0),
            gc.new LightOn(200),
            gc.new LightOff(400),
            gc.new FanOn(400),
            gc.new FanOff(730),
            gc.new WaterOn(600),
            gc.new WaterOff(800),
            gc.new ThermostatDay(1400),
        };
        gc.addEvent(gc.new Restart(2000, eventList));

        if (args.length == 1)
            gc.addEvent(
                new GreenhouseControlss.Terminate(
                    Integer.parseInt(args[0])));

        gc.run(); 
    }
}