import java.util.*;
import net.mindview.util.*;
/*
Exercise 19: (2) Following the form of Store.java, build a model of a containerized
cargo ship.
*/

class Product {
    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
    List<String> kg = Arrays.asList("China", "Venezuela", "USA", "Russia","Poland", "Japan");
    private final int id;
    private String country;
    private String description;
    private double price;
    public Product(int IDnumber, String descr, double price){
        country = kg.get(rand.nextInt(kg.size()));
        id = IDnumber;
        description = descr;
        this.price = price;
        System.out.println(toString());
    }
    private Random rand = new Random();
    public String toString() {
        return id + ": " + description + ", country of origin: " + country + "\nPRICE: " + price;
    }
    public void priceChange(double change) {
        price += change;
    }
    public static Generator<Product> generator =
            new Generator<Product>() {
                private Random rand = new Random(47);
                public Product next() {
                    return new Product(rand.nextInt(1000), "Product",
                            Math.round(rand.nextDouble() * 1000.0) + 0.99);
                }
            };
}
class Container extends ArrayList<Product> { // Zamiast shelves jest Container
    public Container(int nProducts) {
        Product.fill(this, Product.generator, nProducts);
    }
}
class Bay extends ArrayList<Container> { // zamiast Aisles jest Bay
    public Bay(int nContainers, int nProducts) {
        for(int i = 0; i < nContainers; i++)
            add(new Container(nProducts));
    }
}

public class Zad15_19 extends ArrayList<Bay> {
    public Zad15_19(int nBays, int nContainers, int nProducts) {
        for(int i = 0; i < nBays; i++)
            add(new Bay(nContainers, nProducts));
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Bay a : this)
            for(Container s : a)
                for(Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Zad15_19(14, 5, 15));
    }
}

// adaptacja Store.java do modelu Ship.java(Zad15_19) zamiast Aisles i Shelves wprowadzono Bays i Containers, dodano zmienna country