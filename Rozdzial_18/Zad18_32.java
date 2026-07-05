import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import net.mindview.util.*;
import static myutils.Skrocenie_Print.*;
import nu.xom.Element;
import nu.xom.Document;
import nu.xom.Serializer;
/*
Exercise 32: (4) Using a Map<String,Integer> and the
net.mindview.util.TextFile utility, write a program that counts the occurrence of words
in a file (use "\\W+" as the second argument to the TextFile constructor). Store the results
as an XML file.
*/
public class Zad18_32 {
    private String word;
    private Integer occurrence;
    public Zad18_32(String word, Integer occurrence) {
        this.word = word;
        this.occurrence = occurrence;
    }
    //metoda, która zapisuje cały dokument XML do wyjścia
    public static void
    format(OutputStream os, Document doc) throws Exception {
        Serializer serializer= new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }
    //zamienia obiekt na kawałek XML
    public Element getXML() {
        Element amount = new Element("amount");
        Element words = new Element("word");
        words.appendChild(word);
        Element occurrences = new Element("occurrence");
        occurrences.appendChild(String.valueOf(occurrence));
        amount.appendChild(words);
        amount.appendChild(occurrences);
        return amount;
    }
    public Zad18_32(Element amount) {
        word = amount.getFirstChildElement("word").getValue();
        occurrence = Integer.valueOf(amount.getFirstChildElement("occurrence").getValue());
    }
    public String toString() { return "Word: " +
            word + "\n" + "occurrence: " + occurrence;
    }
    public static void main(String[] args) throws Exception {
        ArrayList<String> nwords = new ArrayList<String>(new TextFile("Zad18_30.java", "\\W+"));
        Map<String, Integer> ok = new HashMap<String, Integer>();
        Iterator<String> it = nwords.iterator();
        //iteracja ArrayList zliczająca słowa, które ma dodać do mapy
        while (it.hasNext()) {
            String w = it.next();
            if (ok.containsKey(w)) {
                int i = ok.get(w);
                ok.replace(w, i, ++i);
            } else {
                ok.put(w, 1);
            }
        }
        println(ok);
        List<Zad18_32> amount = new ArrayList<Zad18_32>();
        //pętla po mapie, która szykuje poszczególne zliczenia do rozpisania w xml
        for (Map.Entry<String, Integer> entry : ok.entrySet()) {
            amount.add(new Zad18_32(entry.getKey(), entry.getValue()));
        }
        Element root = new Element("words");
        //pętla po liście amount, która buduje strukturę xml
        for(Zad18_32 p : amount)
            root.appendChild(p.getXML());
        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream(
                "Occurrence.xml")), doc);
    }
}

/*
Program wczytuje słowa z pliku za pomocą TextFile i dzieli tekst według "\\W+".
\W oznacza znak, który NIE jest znakiem słowa
+ jeden lub więcej takich znaków

Następnie zlicza wystąpienia każdego słowa w Map<String,Integer>.

Wynik jest zamieniany na XML:
<words> jest elementem głównym,
a każde słowo jest zapisane jako <amount> z elementami <word> i <occurrence>.

Plik wynikowy to Occurrence.xml
*/