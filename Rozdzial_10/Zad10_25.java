import controller.*;
/*
Exercise 25: (3) Inherit from GreenhouseControls in GreenhouseControls.java
to add Event inner classes that turn water mist generators on and off. Write a new version of
GreenhouseController.java to use these new Event objects.
*/
public class Zad10_25 {
	public static void main(String[] args) {
		GreenhouseControls2 ab = new GreenhouseControls2();
		// Instead of hard-wiring, you could parse
		// configuration information from a text file here:
		ab.addEvent(ab.new Bell(900));
		Event[] eventList = {
			ab.new ThermostatNight(0),
			ab.new LightOn(200),
			ab.new LightOff(400),
			ab.new FanOn(400),
			ab.new FanOff(730),
			ab.new MistOn(100),
			ab.new MistOff(830),
			ab.new WaterOn(600),
			ab.new WaterOff(800),
			ab.new ThermostatDay(1400)
		};
		ab.addEvent(ab.new Restart(2000, eventList));
		if(args.length == 1)
			ab.addEvent(
				new GreenhouseControls2.Terminate(
					Integer.parseInt(args[0])));
		ab.run();
	}
}