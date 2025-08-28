import java.util.*;
import static myutils.Skrocenie_Print.print;

public class zad4_8_bez_break {
	public static void main (String[] args) {
	for (int i = 0; i < 6; i++){
		int c = 'a' + i;
		switch(c){
		case 'a': print("case1");
		case 'b': print("case2");
		case 'c': print("case3");
		case 'd': print("case4");
		case 'e': print("case5");
default: print("default");}}}}