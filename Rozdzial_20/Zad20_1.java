package Rozdzial_20;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
// kompilacja przez: java Rozdzial_20/Zad20_1 Rozdzial_20.Zad20_1
/*
Exercise 1: (2) Implement more SQL types in the database example.
*/
//Adnotacja z ograniczeniami dla kolumny SQL
//moze oznaczać np klucz główny, brak null albo unikalność
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
}
//adnotacja dla pola, które ma być kolumną tekstową SQL
//value oznacza długość varchar np. @SQLString(30) -> VARCHAR(30)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLString {
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
}
//adnotacja dla pola, które ma byc kolumną typu INT i tak dalej z następnymi typami
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLInteger {
    String name() default "";
    Constraints constraints() default @Constraints;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLLong {
    String name() default "";
    Constraints constraints() default @Constraints;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLDouble {
    String name() default "";
    Constraints constraints() default @Constraints;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLShort{
    String name() default "";
    Constraints constraints() default @Constraints;
}
//Adnotacja dla całej klasy
//oznacza, że dana klasa ma zostać potraktowana jak tabela w bazie danych.
@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
@interface DBTable {
    public String name() default "";
}
//Klasa, z której ma wygenerować CREATE TABLE
//nazwa tabeli to Zad20_1
@DBTable(name = "Zad20_1")
public class Zad20_1 {
    //pole firstName będzie kolumną VARCHAR(30)
    @SQLString(30) String firstName;
    //pole lastName będzie kolumną VARCHAR(50)
    @SQLString(50) String lastName;
    //pole age będzie kolumną INT i tak dalej...
    @SQLInteger Integer age;
    @SQLLong Long longtimenosee;
    @SQLShort Short shorts;
    @SQLDouble Double doubles;
    @SQLString(value = 30,
            constraints = @Constraints(primaryKey = true))
    String handle;
    //to pole nie ma adnotacji SQL więc nie będzie dodane w tabeli
    static int memberCount;
    public String getHandle() { return handle; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String toString() { return handle; }
    public Integer getAge() { return age; }
    public Long getSee() { return longtimenosee; }
    public Double getDouble() { return doubles; }
    public Short getShort() { return shorts; }
    public static void main(String[] args) throws Exception {
        //program wymaga podania nazwy klasy jako argumentu
        //np.
        //java Rozdzial_20.Zad20_1 Rozdzial_20.Zad20_1
        if(args.length < 1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        //przechodzi po args, ponieważ może dostać kilka nazw klas
        for(String className : args) {
            //Class.forname() ładuje klasę po nazwie tekstowej, dzięki czemu może analizować klasę przez refleksje
            Class<?> cl = Class.forName(className);
            //pobiera adnotacje @DBTable z klasy
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            //jeżeli klasa nie ma @DBTable, to nie traktujemy jej jako tabeli
            if(dbTable == null) {
                System.out.println(
                        "No DBTable annotations in class " + className);
                continue;
            }
            //pobiera nazwę tabeli z adnotacji @DBTable
            String tableName = dbTable.name();
            // If the name is empty, use the Class name:
            if(tableName.length() < 1)
                tableName = cl.getName().toUpperCase();
            //Lista definicji kolumn np.
            //FIRSTNAME VARCHAR(30)
            //AGE INT
            //itd....
            List<String> columnDefs = new ArrayList<String>();
            //przechodzi po wszystkich polach klasy, refleksja sprawdza pola nawet wtedy,
            //kiedy program zna klasę dopiero po nazwie tekstowej
            for(Field field : cl.getDeclaredFields()) {
                String columnName = null;
                //pobiera adnotacje z konkretnego pola
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1)
                    continue; // Not a db table column
                //obsługi pól....
                if(anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                    if(sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" +
                            getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLLong) {
                    SQLLong sLong = (SQLLong) anns[0];
                    // Use field name if name not specified
                    if(sLong.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sLong.name();
                    columnDefs.add(columnName + " LONG" +
                            getConstraints(sLong.constraints()));
                }
                if(anns[0] instanceof SQLShort) {
                    SQLShort sShort = (SQLShort) anns[0];
                    // Use field name if name not specified
                    if(sShort.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sShort.name();
                    columnDefs.add(columnName + " SHORT" +
                            getConstraints(sShort.constraints()));
                }
                if(anns[0] instanceof SQLDouble) {
                    SQLDouble sDouble = (SQLDouble) anns[0];
                    // Use field name if name not specified
                    if(sDouble.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sDouble.name();
                    columnDefs.add(columnName + " DOUBLE" +
                            getConstraints(sDouble.constraints()));
                }
                if(anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified.
                    if(sString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR(" +
                            sString.value() + ")" +
                            getConstraints(sString.constraints()));
                }
                }
            //przeniesiono za pętle, bo bezsensownie po każdym for dodawał stringa
            StringBuilder createCommand = new StringBuilder(
                    "CREATE TABLE " + tableName + "(");
            //dodaje każdą kolumnę do komenty CREATE TABLE
            for(String columnDef : columnDefs)
                createCommand.append("\n " + columnDef + ",");
            // Remove trailing comma
            //usuwa ostatni przecinek i dodaje zakończenie );
            String tableCreate = createCommand.substring(
                    0, createCommand.length() - 1) + ");";
            System.out.println("Table Creation SQL for " +
                    className + " is :\n" + tableCreate);
        }
    }
    //metoda zamienia adnotacje @constraints na tekst SQL
    //np. primaryKey = true daje " PRIMARY KEY"
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if(!con.allowNull())
            constraints += " NOT NULL";
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += " UNIQUE";
        return constraints;
    }
}
/*
ćwiczenie rozbudowuje przykład z książki.
W przykładzie były adnotacje typu SQLString i SQLInteger, tutaj zostały dodane SQLLong, SQLDouble i SQLShort.
Program czyta klasę podaną w argumencie programu.
Sprawdza, czy klasa ma adnotację @DBTable.
Przechodzi po polach klasy za pomocą refleksji.
Sprawdza adnotacje pól, np @SQLString, @SQLInteger
Na tej podstawie buduje tekst komendy SQL CREATE TABLE
*/