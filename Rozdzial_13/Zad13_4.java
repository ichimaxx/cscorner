//: strings/Receipt.java
import java.util.*;

/*Exercise 4: (3) Modify Receipt.java so that the widths are all controlled by a single set of
constant values. The goal is to allow you to easily change a width by changing a single value
in one place. */


/*
%[argument_index$][flags][width][.precision]conversion 
tak wyglada caly syntax Formattera
*/

public class Zad13_4 {
	private double total = 0;
	private final int ITEM_W = 30;
	private final int QTY_W = 5;
	private final int PRICE_W = 10;
	
	private Formatter f = new Formatter(System.out);
	public void printTitle() {
		f.format("%-" + ITEM_W + "s %" /* s oznacza String w formatterze % to rozpoczecie placeholdera w tym przypadku szerokosci i jest oznazczona tu np jako QTY_W i to będzie liczba*/ + QTY_W + "s %"/*i dopiero tu na s jest koniec tego placeholdera i on oznacza ze w tym przypadku bedziemy wpisywac tu String*/ + PRICE_W + "s%n"/*%n to placeholder nowej linii*/, "Item", "Qty", "Price");
		f.format("%-" + ITEM_W + "s %" + QTY_W + "s %" + PRICE_W + "s%n", "----", "---", "-----");
	}
	public void print(String name, int qty, double price) {
		f.format("%-" + ITEM_W + "." + ITEM_W + "s %" + QTY_W + "d %"/* d to placeholder od liczby calkowitej, bo bedzie ilosc produktow czyli quantity*/ + PRICE_W + ".2f%n", name, qty, price);
		total += price;
	}
	public void printTotal() {
		f.format("%-" + ITEM_W + "s %" + QTY_W + "s %" + PRICE_W + ".2f%n"/*to .2f oznacza ile cyfr po przecinku a f to jest liczba zmiennoprzecinkowa np double float  */, "Tax", "", total * 0.06);
		f.format("%-" + ITEM_W + "s %" + QTY_W + "s %" + PRICE_W + "s%n", "", "", "-----");
		f.format("%-" + ITEM_W + "s %" + QTY_W + "s %" + PRICE_W + ".2f%n", "Total", "", total * 1.06);
	}
	public static void main(String[] args) {
		Zad13_4 receipt = new Zad13_4();
		receipt.printTitle();
		receipt.print("Jack's Magic Beans", 4, 4.25);
		receipt.print("Princess Peas", 3, 5.1);
		receipt.print("Three Bears Porridge", 1, 14.29);
		receipt.printTotal();
	}
}

/* 
ogolnie wytlumaczenie formattera
%-30.30s to String wyrównany do lewej (bo minus "-") szerokość pola 30 i maksymalnie 30 znaków (ta.30 po kropce to precyzja dla stringa czyli ucinanie znaków tak jak w ksiazce)
%5d to int(liczba calkowita) szerokosc(width) 5 domyslnie wyrownane do prawej (mie pisze sie plusa)
%10.2f to double/float szerokosc 10 i .2 to dwie cyfry po przecinku
%n to nowa linia 
*/