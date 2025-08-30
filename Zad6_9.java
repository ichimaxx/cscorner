import access.local.*;
 
public class Zad6_9 { 
   public static void main(String[] args) { 
      PackagedClass pc = new PackagedClass(); 
   } 
} 

/* 
	TO NIE ODPALI BO KLASA W PAKIECIE ACCESS.LOCAL NIE JEST PUBLIC JEST BEZ MODYFIKATORA
	CZYLI NIE MOZNA MIEC DO NIEJ DOSTEPU PO ZA JEJ PAKIETEM, MUSIALBYM TO ZADNIE WRZUCIC DO PAKIETU CSCORNER\ACCESS\LOCAL

	WYGLADA ONA TAK: 
	
	
package access.local; 

class PackagedClass { 
	public PackagedClass() { 
    System.out.println("Creating a packaged class"); 
	} 
}

TRZEBA BY JA ZMIENIC NA PUBLICZNA

*/
