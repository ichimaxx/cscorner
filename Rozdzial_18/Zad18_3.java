import java.util.regex.*;
import java.io.*;
import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 3: (3) Modify DirList.java (or one of its variants) so that it sums up the file
sizes of the selected files.
*/
public class Zad18_3 {
    static long sum = 0;
    public static FilenameFilter filter(final String regex) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                File file = new File(dir, name);
                if (!pattern.matcher(name).matches())
                    return false;
                //file.isFile jest potrzebne, wydawać by się mogło, że accept(File dir) sprawdza typ File
                //jednak typ File może przyjąć ścieżkę lub adres, a nie jest gwarancją, że jest plikiem
                //więc, jak regex trafi na folder np. src, zostanie on wypisany na liście,
                //ale nie zostanie dodany do sum
                if(file.isFile())
                    sum += file.length();
                return true;
            }
        }; // End of anonymous inner class
    }
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list(filter(".*"));
        else
            list = path.list(filter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list)
            System.out.println(dirItem);
        println("\nRozmiar wszystkich plików: " + sum);
    }
}
