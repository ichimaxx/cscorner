import java.io.*;
import java.util.*;
/*
Exercise 18: (1) Modify TextFile.java(Zad18_18) so that it passes IOExceptions out to the caller.
*/
public class Zad18_18 extends ArrayList<String> {
    // Read a file as a single string:
    public static String read(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        in.close();
        return sb.toString();
    }

    // Write a single file in one method call:
    public static void write(String fileName, String text) throws IOException {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            out.print(text);
            out.close();
    }

    // Read a file, split by any regular expression:
    public Zad18_18(String fileName, String splitter) throws IOException {
        super(Arrays.asList(read(fileName).split(splitter)));
        // Regular expression split() often leaves an empty
        // String at the first position:
        if (get(0).equals("")) remove(0);
    }

    // Normally read by lines:
    public Zad18_18(String fileName) throws IOException {
        this(fileName, "\n");
    }

    public void write(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        for (String item : this)
            out.println(item);
        out.close();
    }
}

/*
modyfikacja polega na tym, że IOException nie jest łapany wewnątrz klasy,
tylko przekazywany dalej do kodu, który wywołuje metody tej klasy.
Dlatego caller(kod który wywołuje metodę) musi albo obsłużyć wyjątek przez try/catch,
 albo sam dopisać throws IOException.
Różnicą jest, że jeżeli wyjątek jest obsługiwany bezpośrednio w metodzie, to decyzja co z nim zrobić
jest narzucona wewnątrz tej metody.
Jeśli wypuścimy wyjątek dalej, możemy np. w momencie złapania go, wywołać tę samą metodę ponownie, z inną nazwą pliku.
*/