import static myutils.Skrocenie_Print.*;

class AlertStatus {
	public void alertStatus() {
		println("no status at all yet");
	}
}

class Flying extends AlertStatus {
	@Override
	public void alertStatus() {
		println("Starship Status: F L Y I N G");
	}
}

class Driving extends AlertStatus {
	@Override
	public void alertStatus() {
		println("Starship Status: D R I V I N G");
	}
}

class Landed extends AlertStatus {
	@Override
	public void alertStatus() {
		println("Starship Status: L A N D E D");
	}
}

class Starship {
	private AlertStatus alertStatus = new AlertStatus();
	public void drivingstatus() {
		alertStatus = new Driving();
	}
	public void landedstatus() {
		alertStatus = new Landed();
	}
	public void flyingstatus() {
		alertStatus = new Flying();
	}
	public void statusofalert() {
		alertStatus.alertStatus();
	}
}
public class Zad8_16 {
	public static void main (String[] args) {
		Starship starship = new Starship();
		starship.statusofalert(); // lpierwszy status
		starship.flyingstatus(); // zmiana statusu na wylot
		starship.statusofalert(); // check statusu drugi
		starship.drivingstatus(); // zmiana statusu na "lot" nazwałem jazda driving
		starship.statusofalert(); // check statusu trzeci
		starship.landedstatus(); // zmiana statusu na lądowanie 
		starship.statusofalert(); // check statusu czwarty
	}
}