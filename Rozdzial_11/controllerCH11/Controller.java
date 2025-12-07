package controllerCH11;
import java.util.*; 

public class Controller {
	// A class from java.util to hold Event objects:
	private LinkedList<Event> eventList = new LinkedList<Event>();
	public void addEvent(Event c) { 
		eventList.add(c); 
	}
	public void run() { 
		while(eventList.size() > 0) {// DOPÓKI DODANE SA EVENTY TO BEDIZEE ODPALAC
			Iterator<Event> it = new LinkedList<Event>(eventList).iterator(); 
			/* iterowanie po kopii listy bo jakbym iterowal po liscie to eventy typu bell i restart by sie dodawaly
			od nowa do listy za pomoca addevent i wywala wtedy java.util.ConcurrentModificationException dodaje rzeczy do wlasnie iterowanej listy xd*/
			while(it.hasNext()) {
				Event e = it.next();
				if(e.ready()) { // SPRAWDZA CZY NADSZEDL CZAS DO EVENTU
					System.out.println(e); 
					e.action(); // ODPALA DANE ZDARZENIE
					eventList.remove(e); // USUWA EVENT Z ORYGINALKNEJ LISTY DZIEKI CZEMU PO JAKIMS CZASIE EVENTLIST.SIZE DOJDZIE DO 0 ALBO PROGRAM SIE SKONCZY (TERMINATE) TAKI JEST WARUNEK
				}
			}
		}
	}
}

	