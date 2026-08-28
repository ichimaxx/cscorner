import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 15: (4) Stacks are often used to evaluate expressions in programming languages.
Using net.mindview.util.Stack, evaluate the following expression, where’+’ means "push
the following letter onto the stack," and’-’ means "pop the top of the stack and print it":
"+U+n+c—+e+r+t—+a-+i-+n+t+y—+ -+r+u—+l+e+s—"
*/
public class Zad11_15 {
	public static void main(String[] args) {
		net.mindview.util.Stack<String> stack = new net.mindview.util.Stack<String>();
		String cosik = "+U+n+c—+e+r+t—+a-+i-+n+t+y—+ -+r+u—+l+e+s—";
		for (int i = 0; i < cosik.length(); i++) {
			char h = cosik.charAt(i);
			
			if (h == '+') {
				stack.push(Character.toString(cosik.charAt(++i)));
			} else if (h == '-') {
				print(stack.pop());
			}
			/* TA PETLA IDZIE PO ZNAKACH PO KOLEI I JAK NATRAFIA NA + TO WRZUCA NA STOS
			NOWE LITERY WEDLUG TEGO JAK IDA W KOLEJNOSCI PO +
			czyli po pierwszym plusie bedzie dodane U, JAK SIE TRAFI "-" TO WTEDY USUWA 
			I WYPISUJE ZNAK, KTORY BYL WRZUCONY OSTATNIO NA STOS ROZROZNIONE SA MINUSY I MYSLNIKI
			JESLI ZAMIAST MYSLNIKOW WRZUCIMY MINUSY TO HASLO WYCHODZI: ctaiyus
			a jesli zostgawimy tak jak jest w ksiazce to haslem bedzie: ai
		*/
		}
	}
}