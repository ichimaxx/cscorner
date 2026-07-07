import java.util.*;
import net.mindview.util.*;
/*
Exercise 2: (2) Instead of implementing an interface, make next( ) a static method.
What are the benefits and drawbacks of this approach?
*/
enum CartoonCharacter {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private static Random rand = new Random(47);
    public static CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}
public class Zad19_2 {
    //po modyfikacji nie potrzeba argumentu w printNext()
    public static void printNext() {
        System.out.print(CartoonCharacter.next() + ", ");
    }
    public static void main(String[] args) {
        //usunięcie CartoonCharacter.BOB, teraz można wywołać samą metodę printNext()
        for(int i = 0; i < 10; i++)
            printNext();
    }
}

/*
Benefit jest taki, że można teraz wywołać samą metodę printNext() bez definiowania obiektu
CartoonCharacter cc = CartoonCharacter.BOB

Drawback jest taki, że nie da się już przekazać CartoonCharacter do generatora, ponieważ enum przestał
implementować interfejs Generator<CartoonCharacter>.
*/