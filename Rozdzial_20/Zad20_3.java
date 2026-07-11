package Rozdzial_20;
import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.util.*;
import java.util.*;
import static com.sun.mirror.util.DeclarationVisitors.*;
/*
Exercise 3: (2) Add support for more SQL types to
TableCreationProcessorFactory.java (Zad20_3).
*/
@DBTable(name = "Zad20_3")
public class Zad20_3
        implements AnnotationProcessorFactory {
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
    public AnnotationProcessor getProcessorFor(
            Set<AnnotationTypeDeclaration> atds,
            AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList(
                "Rozdzial_20.DBTable",
                "Rozdzial_20.Constraints",
                "Rozdzial_20.SQLString",
                "Rozdzial_20.SQLInteger",
                "Rozdzial_20.SQLLong",
                "Rozdzial_20.SQLShort",
                "Rozdzial_20.SQLDouble");
    }
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }
    private static class TableCreationProcessor
            implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        private String sql = "";
        public TableCreationProcessor(
                AnnotationProcessorEnvironment env) {
            this.env = env;
        }
        public void process() {
            for(TypeDeclaration typeDecl :
                    env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(getDeclarationScanner(
                        new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }
        private class TableCreationVisitor
                extends SimpleDeclarationVisitor {
            public void visitClassDeclaration(
                    ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if(dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length() < 1)
                            ? d.getSimpleName().toUpperCase()
                            : dbTable.name();
                    sql += " (";
                }
            }
            public void visitFieldDeclaration(
                    FieldDeclaration d) {
                String columnName = "";
                if(d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(
                            SQLInteger.class);
                    // Use field name if name not specified
                    if(sInt.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sInt.name();
                    sql += "\n " + columnName + " INT" +
                            getConstraints(sInt.constraints()) + ",";
                }
                if(d.getAnnotation(SQLString.class) != null) {
                    SQLString sString = d.getAnnotation(
                            SQLString.class);
                    // Use field name if name not specified.
                    if(sString.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sString.name();
                    sql += "\n " + columnName + " VARCHAR(" +
                            sString.value() + ")" +
                            getConstraints(sString.constraints()) + ",";
                }
                if(d.getAnnotation(SQLLong.class) != null) {
                    SQLLong sLong = d.getAnnotation(
                            SQLLong.class);
                    // Use field name if name not specified
                    if(sLong.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sLong.name();
                    sql += "\n " + columnName + " LONG" +
                            getConstraints(sLong.constraints()) + ",";
                }
                if(d.getAnnotation(SQLShort.class) != null) {
                    SQLShort sShort = d.getAnnotation(
                            SQLShort.class);
                    // Use field name if name not specified
                    if(sShort.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sShort.name();
                    sql += "\n " + columnName + " SHORT" +
                            getConstraints(sShort.constraints()) + ",";
                }
                if(d.getAnnotation(SQLDouble.class) != null) {
                    SQLDouble sDouble = d.getAnnotation(
                            SQLDouble.class);
                    // Use field name if name not specified
                    if(sDouble.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sDouble.name();
                    sql += "\n " + columnName + " DOUBLE" +
                            getConstraints(sDouble.constraints()) + ",";
                }
            }
            private String getConstraints(Constraints con) {
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
    }
}
/*
Dodano obsługę nowych typów SQL:
SQLLong
SQLShort
SQLDouble
Rozszerzono również metodę supportedAnnotationTypes() o nowe adnotacje
Dzięki temu procesor potrafi generować definicje kolumn dla większej liczby typów danych niż z przykładu z książki.

Uwaga:
Przykład wykorzystuje stare API apt oraz pakiety com.sun.mirror.*, które zostały usunięte z nowoczesnych
wersji JDK. Kod działa wyłącznie na starszych wersjach Javy wspierających apt.
*/