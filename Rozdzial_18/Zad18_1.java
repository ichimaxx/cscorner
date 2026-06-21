import java.util.regex.*;
import java.io.*;
import java.util.*;
import myutils.*;
/*
Exercise 1: (3) Modify DirList.java (or one of its variants) so that the FilenameFilter
opens and reads each file (using the net.mindview.util.TextFile utility) and accepts the
file based on whether any of the trailing arguments on the command line exist in that file.
*/
public class Zad18_1 {
    // tablica String[] szukane, aby można było szukać za pomocą trailing arguments: czyli nie jednym słowem, tylko np:
    // java Zad18_1 "Z.*\.java" public static class
    public static FilenameFilter filter(final String regex, final String[] szukane) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                // jeżeli nazwa pliku nie matchuje z regexem nie idzie dalej, po prostu zwraca false
                if(!pattern.matcher(name).matches())
                    return false;
                Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
                words.addAll(new TextFile(name, "\\W+"));
                // szukanie for po String[] szukane, który jest array argumentów z main
                for (String s : szukane)
                    if (words.contains(s))
                        return true;
                return false;
            }
        }; // End of anonymous inner class
    }
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else {
            String[] szukane = Arrays.copyOfRange(args, 1, args.length);
            //test
            list = path.list(filter(args[0], szukane));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        //for po liście z zaakceptowanymi nazwami plików
        for(String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}