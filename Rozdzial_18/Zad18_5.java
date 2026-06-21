import java.util.regex.*;
import java.io.*;
import java.util.*;
import myutils.*;
import static myutils.Skrocenie_Print.println;

/*
Exercise 5: (1) Modify ProcessFiles.java so that it matches a regular expression rather
than a fixed extension.
*/
public class Zad18_5 {
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy;
    private String regex;
    public Zad18_5(Strategy strategy, String regex) {
        this.strategy = strategy;
        this.regex = regex; //zmiana ext na regex, ponieważ bardziej pasuje do zadania
    }
    public void start(String[] args) {
        try {
            if(args.length == 0)
                processDirectoryTree(new File("."));
            else
                for(String arg : args) {File fileArg = new File(arg);
                    if(fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        //zamiana arg.endsWith(ext) na wersję która tworzy logikę,
                        //która pyta, czy nazwa pliku pasuje do regexa
                        if(fileArg.getName().matches(regex))
                            strategy.process(new File(arg).getCanonicalFile());
                    }
                }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void
    processDirectoryTree(File root) throws IOException {
        for(File file : Directory.walk(
                root.getAbsolutePath(), regex))
            strategy.process(file.getCanonicalFile());
    }
    // Demonstration of how to use it:
    public static void main(String[] args) {
        new Zad18_5(new Zad18_5.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
            //teraz podaje się pełny regex, a nie samo rozszerzenie ".java"
        }, ".*\\.java").start(args);
    }
}
/*
Cwiczenie pokazuje różnicę między szukaniem po stałym rozszerzeniu a po wyrażeniu regularnym(REGEX).
W starej wersji program sam tworzył wzór na podstawie rozszerzenia .java. Po zmianie podaje się cały regex.
*/