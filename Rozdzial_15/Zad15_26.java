/*
Exercise 26: (2) Demonstrate array covariance using Numbers (Number class) and Integers(Integer class).
 */

public class Zad15_26 {
    public static void main(String[] args) {
        Number[] nums = new Integer[5]; // tzw. covariance
        Float f = 0.4f;
        int a = 1;
        int b = 4;
        nums[0] = b; // OK
        nums[1] = a; // OK
        try {
            nums[0] = f; //wywola się ArrayStoreException bo f to obiekt float a nums[0] to runtime type Integer[]
        } catch(Exception e) { System.out.println(e); }
    }
}