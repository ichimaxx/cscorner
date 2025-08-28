import static myutils.Skrocenie_Print.print;

public class zad3_10 {
	static int l1 = 0xaaaaaa;
	static int  l2 = 0x555555;
public static void main (String[] args){
	
	print("l1 =" +  Integer.toBinaryString(l1));
	print("l2 =" +  Integer.toBinaryString(l2));
	print("l1& =" + Integer.toBinaryString(l1 & l1));
	print("l1| =" + Integer.toBinaryString(l1 | l1));
	print("l1~ =" + Integer.toBinaryString(~l1));
	print("l1^ =" + Integer.toBinaryString(l1 ^ l1));
	print("l2& =" + Integer.toBinaryString(l2 & l2));
	print("l2| =" + Integer.toBinaryString(l2 | l2));
	print("l2~ =" + Integer.toBinaryString(~l2));
	print("l2^ =" + Integer.toBinaryString(l2 ^ l2));
}}