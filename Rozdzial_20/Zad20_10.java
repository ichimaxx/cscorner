import java.io.*;
import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 10: (2) Select an example from elsewhere in the book and add @Unit tests.
*/
public class Zad20_10 {
    private static void usage() {
        System.err.println(
                "Usage:Zad20_10 path1 ...\n" +
                        "Creates each path\n" +
                        "Usage:Zad20_10 -d path1 ...\n" +
                        "Deletes each path\n" +
                        "Usage:Zad20_10 -r path1 path2\n" +
                        "Renames from path1 to path2");
        System.exit(1);
    }
    private static void fileData(File f) {
        System.out.printf(
                "Absolute path: %s%n" +
                " Can read: %s%n" +
                " Can write: %s%n" +
                " getName: %s%n" +
                " getParent: %s%n" +
                " getPath: %s%n" +
                " length: %s%n" +
                " lastModified: %s%n",
                f.getAbsolutePath(),
                f.canRead(),
                f.canWrite(),
                f.getName(),
                f.getParent(),
                f.getPath(),
                f.length(),
                f.lastModified()
        );
        if(f.isFile())
            System.out.println("It's a file");
        else if(f.isDirectory())
            System.out.println("It's a directory");
    }
    @Test void _fileData() {
        File kk = new File("Zad20_10.java"); //tworzy obiekt reprezentujący ścieżkę do Zad20_10.java
        assert kk.exists(); //sprawdza, czy plik istnieje
        assert kk.isFile(); //sprawdza, czy plik jest plikiem
        fileData(kk);
    }
    public static void main(String[] args) {
        //jeżeli brak argumentów wywołuje tylko test
        if (args.length == 0) {
            OSExecute.command(
                    "java -cp .;.. net.mindview.atunit.AtUnit Zad20_10");
            return;
        }
        if(args[0].equals("-r")) {
            if(args.length != 3) usage();
            File
                    old = new File(args[1]),
                    rname = new File(args[2]);
            old.renameTo(rname);
            fileData(old);
            fileData(rname);
            return; // Exit main
        }
        int count = 0;
        boolean del = false;
        if(args[0].equals("-d")) {
            count++;
            del = true;
        }
        count--;
        while(++count < args.length) {
            File f = new File(args[count]);
            if(f.exists()) {
                System.out.printf("%s exists%n", f);
                if(del) {
                    System.out.printf("deleting... %s%n", f);
                    f.delete();
                }
            }
            else { // Doesn’t exist
                if(!del) {
                    f.mkdirs();
                    System.out.printf("created %s%n", f);
                }
            }
            fileData(f);
        }
    }
}

/*
Do zadania wykorzystano przykład MakeDirectories i dodano test @Unit prywatnej metody fileData()

Framework @Unit z książki korzysta ze starej klasy ClassNameFinder,
która samodzielnie analizuje zawartość plików .class. Nie rozpoznaje
ona wpisu CONSTANT_InvokeDynamic o numerze 18, który może pojawić się w plikach
generowanych przez współczesny kompilator javac.

Aby nie modyfikować frameworka atUnit, łączenie napisów przez + zastąpiono metodą printf().
*/