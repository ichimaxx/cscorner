import static myutils.Skrocenie_Print.print;
/*
Exercise 12: (4) Create a class called Tank that can be filled and emptied, and has a
termination condition that it must be empty when the object is cleaned up. Write a
finalize( ) that verifies this termination condition. In main( ), test the possible scenarios
that can occur when your Tank is used.
*/
class Tank {
    private boolean full = false;          // stan zbiornika

    void fill()  { 
	full = true; 	}          // napełnij
    void empty() {
	full = false; 	}         // opróżnij

 @Override
    protected void finalize() {
        if (full) 
            print("Błąd: tank nie został opróżniony!");
			else
				print("OK, zbiornik pusty");
        
    }
}

public class Zad5_12 {
    public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++)   // 1 roibimy dużo obiektów
            new Tank();
        // Scenariusz 1 – poprawne użycie
        Tank t1 = new Tank();
        t1.fill();
        t1.empty();      // warunek spełniony
        t1 = null;

        // Scenariusz 2 – zapomniane opróżnienie
        Tank t2 = new Tank();
        t2.fill();       // brak empty()
        t2 = null;

        // Wymuszenie GC, aby pokazać finalize()
        System.gc();
        System.runFinalization();
        Thread.sleep(100);  // dajemy czas wątkowi finalizera
    }
}