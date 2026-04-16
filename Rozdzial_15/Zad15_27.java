import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 27: (2) Show that covariance doesn’t work with Lists, using Numbers and
Integers, then introduce wildcards.
 */

public class Zad15_27 {
    public static void main(String[] args) {
        // List<Number> nums1 = new ArrayList<Integer>(); //ArrayList<Integer> cannot be converted to List<Number> czyli nie działa przypisanie List<integer> do List<Number> cos podobnego do upcast(NIE MYLIĆ Z UPCAST!) ale z ograniczeniami
        List<? extends Number> nums = new ArrayList<Integer>(); // tzw. wildcard daje kowariantny widok na typ, czyli pozwala na to żeby zrobić coś co wyglada na "upcast"
        Float f = 0.4f;
        int a = 1;
        int b = 4;
        //nums.add(b); // error: incompatible types: int cannot be converted to CAP#1
        //nums.add(a); // error: incompatible types: int cannot be converted to CAP#1
        //nums.add(f); // error: incompatible types: Float cannot be converted to CAP#1
        nums.add(null); // null można dodać
        Number g = nums.get(0); // get index 0 czyli w naszym przypadku null
        println(nums); // printed [null]

    }
}

// WAŻNE, dla List<? extends Number> nie można dodawać żadnych konkretnych elementów ale można czytać z listy co najmniej Number