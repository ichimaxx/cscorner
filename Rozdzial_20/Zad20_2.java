import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import java.io.*;
import java.util.*;
import java.lang.annotation.*;
/*
Exercise 2: (3) Add support for division to the interface extractor.
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface ExtractInterface {
    public String value();
}
@ExtractInterface("IMultiplier")
class Multiplier {
    public int multiply(int x, int y) {
        int total = 0;
        for(int i = 0; i < x; i++)
            total = add(total, y);
        return total;
    }
    public int division(int x, int y) {
        int result = 0;
        int total = x;
        if (y == 0)
            throw new ArithmeticException("Division by zero");
        while (total >= y) {
                total = total - y;
                result++;
            }
        return result;
    }
    private int add(int x, int y) { return x + y; }
    public static void main(String[] args) {
        Multiplier m = new Multiplier();
        System.out.println("11*16 = " + m.multiply(11, 16));
        System.out.println("4555/30 = " + m.division(4555, 30));
    }
}
public class Zad20_2 implements AnnotationProcessor {
    private final AnnotationProcessorEnvironment env;
    private ArrayList<MethodDeclaration> interfaceMethods =
            new ArrayList<MethodDeclaration>();
    public Zad20_2(
            AnnotationProcessorEnvironment env) { this.env = env; }
    public void process() {
        for(TypeDeclaration typeDecl :
                env.getSpecifiedTypeDeclarations()) {
            ExtractInterface annot =
                    typeDecl.getAnnotation(ExtractInterface.class);
            if(annot == null)
                break;
            for(MethodDeclaration m : typeDecl.getMethods())
                if(m.getModifiers().contains(Modifier.PUBLIC) &&
                        !(m.getModifiers().contains(Modifier.STATIC)))
                    interfaceMethods.add(m);
            if(interfaceMethods.size() > 0) {

                try {
                    PrintWriter writer =
                            env.getFiler().createSourceFile(annot.value());
                    writer.println("package " +
                            typeDecl.getPackage().getQualifiedName() +";");
                    writer.println("public interface " +
                            annot.value() + " {");
                    for(MethodDeclaration m : interfaceMethods) {
                        writer.print(" public ");
                        writer.print(m.getReturnType() + " ");
                        writer.print(m.getSimpleName() + " (");
                        int i = 0;
                        for(ParameterDeclaration parm :
                                m.getParameters()) {
                            writer.print(parm.getType() + " " +
                                    parm.getSimpleName());
                            if(++i < m.getParameters().size())
                                writer.print(", ");
                        }
                        writer.println(");");
                    }
                    writer.println("}");
                    writer.close();
                } catch(IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        }
    }
}

/*
Dodano metodę division() do zadania.
Metoda procesowania adnotacji jest przestarzała.
Kodu nie uda się odpalić na nowym JDK, ponieważ com.sun.mirror.* zostało usunięte.

Kod książki                                    Współczesna java

AnnotationProcessor                            AbstractProcessor
AnnotationProcessorEnvironment                 ProcessingEnvironment
TypeDeclaration                                TypeElement
MethodDeclaration                              ExecutableElement
ParameterDeclaration                           VariableElement
env.getFiler()                                 processingEnv.getFiler()
process()                                      process(Set<? extends TypeElement>, RoundEnvironment)
com.sun.mirror.apt.*                           javax.annotation.processing.*
com.sun.mirror.declaration.*                   javax.lang.model.element.*

*/