import java.util.regex.*;
import java.io.*;
import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 6: (5) Use ProcessFiles(Zad18_6) to find all the Java source-code files in a particular
directory subtree that have been modified after a particular date.
*/

public class Zad18_6 {
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy;
    private String regex;
    private long uni;
    public Zad18_6(Strategy strategy, String regex, long uni) {
        this.strategy = strategy;
        this.regex = regex;
        this.uni = uni;
    }
    public void start(String[] args) {
        try {
            if(args.length == 0)
                processDirectoryTree(new File("C:\\Users\\ichim\\Desktop\\cscorner\\Rozdzial_18"));
            else
                for(String arg : args) {File fileArg = new File(arg);
                    if(fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        // fileArg.lastModified() wyznacza czas w milisekundach
                        if(fileArg.getName().matches(regex) && fileArg.lastModified() > uni)
                            strategy.process(new File(arg).getCanonicalFile());
                    }
                }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void
    processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(
                root.getAbsolutePath(), regex))
            if (file.lastModified() > uni)
                strategy.process(file.getCanonicalFile());
    }
    // Demonstration of how to use it:
    public static void main(String[] args) {
        new Zad18_6(new Zad18_6.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, ".*\\.java",
                //program znajdzie wszystkie pliki, które były zmodyfikowane po północy 22 czerwca 2026
                new GregorianCalendar(2026, Calendar.JUNE, 22).getTimeInMillis()).start(args);
    }
}
