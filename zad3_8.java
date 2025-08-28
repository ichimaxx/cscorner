import static myutils.Skrocenie_Print.print;

public class zad3_8 {
	public static void main(String[] args) {
	long l1 = 0x2f; // z malymi literami
print( "l1: " + Long.toBinaryString(l1));	
long l2 = 0177; // literal osemkowy
print("l2: " + Long.toBinaryString(l2));
long l3 = 0x2F; // z duzyymi literami
print("l3: " + Long.toBinaryString(l3));
}}