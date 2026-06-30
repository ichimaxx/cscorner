import java.io.*;
import java.util.*;
import static myutils.Skrocenie_Print.println;
/*
Exercise 22: (5) Modify OSExecute.java so that, instead of printing the standard
output stream, it returns the results of executing the program as a List of Strings.
Demonstrate the use of this new version of the utility.
*/
class OSExecuteException extends RuntimeException {
    public OSExecuteException(String why) { super(why); }
}
class OSExecute {
    public static List<String> command(String command) {
        boolean err = false;
        //lista na linie outputu zwrócone przez uruchomiony proces
        List<String> zz = new ArrayList<>();
        try {
            Process process =
                    new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String s;
            while((s = results.readLine())!= null) {
                //dodawanie tekstu do listy, a nie print()
                zz.add(s);
            }
            BufferedReader errors = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            // Report errors and return nonzero value
            // to calling process if there are problems:
            while((s = errors.readLine())!= null) {
                System.err.println(s);
                err = true;
            }
        } catch(Exception e) {
            // Compensate for Windows 2000, which throws an
            // exception for the default command line:
            if(!command.startsWith("CMD /C"))
                return command("CMD /C " + command);
            else
                throw new RuntimeException(e);
        }
        if(err)
            throw new OSExecuteException("Errors executing " +
                    command);
        //zwracanie listy
        return zz;
    }
}
public class Zad18_22 {
    public static void main(String[] args) {
        //odpalenie programu jako listy
        List<String> result = OSExecute.command("javap Zad18_22");
        //pętla po liście, aby wyprintować poszczególne zebrane linijki kodu
        for(String line : result)
            println(line);
    }
}
/*
Program wykonuje komendę systemową javap Zad18_22
 i w środku metody command() czyta standardowy output tej komendy.
 Zapisuje każdą linię outputu do List<String> a na koniec ją zwraca.
*/