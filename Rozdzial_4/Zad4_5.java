import static myutils.Skrocenie_Print.print;
/*
Exercise 5: (4) Repeat Exercise 10 from the previous chapter, using the ternary operator
and a bitwise test to display the ones and zeroes, instead of Integer.toBinaryString( ).
*/
public class Zad4_5 {
		 private static String toBin24(int n) {
        char[] buf = new char[24];          // 24 bity → 24 znaki '0'/'1'

        for (int i = 23; i >= 0; i--) {     // indeksy 23…0 (MSB → LSB)
            buf[23 - i] = ( (n & (1 << i)) == 0 ) ? '0' : '1';
            // (1 << i) ustawia pojedynczy bit masy → AND sprawdza czy w n jest 1
        }
        return new String(buf);
    }
	public static void main (String[] args){
				int max = 0xFFFFFF;
				for (int a = 0x1; a < max; a++){ //  
				
					if (a == 0xaaaaaa || a ==0x555555)
						print(toBin24(a));
				}}}