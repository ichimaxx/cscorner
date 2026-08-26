
/*
Exercise 1: (2) Create a class containing an int and a char that are not initialized, and
print their values to verify that Java performs default initialization.
*/
public class Zad2_1 {
        static int z;
        static char g;
    public static void main(String[] args) {
        System.out.println("int = [" + z + "]");
        System.out.println("char = [" + g + "]");
    }
}

/*
Program pokazuje, że niezainicjalizowane pola klasy otrzymują
automatyczne wartości domyślne:
np int = 0, a char = "\u0000" (niewidoczny znak)
 */