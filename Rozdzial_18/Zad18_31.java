import java.io.*;
import java.util.*;
import nu.xom.Element;
import nu.xom.Document;
import nu.xom.Serializer;
import nu.xom.Elements;
import nu.xom.Builder;
/*
Exercise 31: (2) Add appropriate address information to Person.java and
People.java.
*/
public class Zad18_31 {
    private String first, last, address, zip, state;
    public Zad18_31(String first, String last, String address, String zip, String state) {
        this.first = first;
        this.last = last;
        this.address = address;
        this.zip = zip;
        this.state = state;
    }
    // Produce an XML Element from this Person object:
    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        Element addressName = new Element("address");
        addressName.appendChild(address);
        Element zipName = new Element("zip");
        zipName.appendChild(zip);
        Element stateName = new Element("state");
        stateName.appendChild(state);
        person.appendChild(firstName);
        person.appendChild(lastName);
        person.appendChild(addressName);
        person.appendChild(zipName);
        person.appendChild(stateName);
        return person;
    }
    // Constructor to restore a Person from an XML Element:
    public Zad18_31(Element person) {
        first= person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
        address= person.getFirstChildElement("address").getValue();
        zip = person.getFirstChildElement("zip").getValue();
        state= person.getFirstChildElement("state").getValue();
    }
    public String toString() { return first + " " + last + "\n" + "Address: " +
            address + "\n" + "Zip code: " + zip + "\n" + "State: " + state;
    }
    // Make it human-readable:
    public static void
    format(OutputStream os, Document doc) throws Exception {
        Serializer serializer= new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }
    public static void main(String[] args) throws Exception {
        List<Zad18_31> people = Arrays.asList(
                new Zad18_31("Dr. Bunsen", "Honeydew", "400 Age Rd", "41445", "TX" ),
                new Zad18_31("Gonzo", "The Great", "14141 Boo Cr", "54222", "CA" ),
                new Zad18_31("Phillip J.", "Fry", "111 Hot Rd", "15111", "AR" ));
        System.out.println(people);
        Element root = new Element("people");
        for(Zad18_31 p : people)
            root.appendChild(p.getXML());
        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream(
                "People.xml")), doc);
    }
}
class People extends ArrayList<Zad18_31> {
    public People(String fileName) throws Exception {
        Document doc = new Builder().build(fileName);
        Elements elements =
                doc.getRootElement().getChildElements();
        for(int i = 0; i < elements.size(); i++)
            add(new Zad18_31(elements.get(i)));
    }
    public static void main(String[] args) throws Exception {
        People p = new People("People.xml");
        System.out.println(p);
    }
}
/*
Przypisano adresy do ludzi.
Program przekształca dane obiektów javy na XML.
Jest to pewien rodzaj serializacji danych, tylko do pliku, który w łatwy sposób będzie w stanie odczytać inny język programowania
taki jak: C#, C++ lub np. python
 */