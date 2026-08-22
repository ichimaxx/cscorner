package Rozdzial_22;
import javax.swing.*;

public class Zad22_36 {
    private JPanel panel1;
}
/*
Celem Zad22_36 jest utworzenie JAR zawierającego Frog i BangBean
oraz załadowanie ich obu do Beans-enabled GUI Builder.

Klasy znajdują się w
    package Rozdzial_22.manifest_35;

Manifest Zad22_36.mf
     Manifest-Version: 1.0

    Name: Rozdzial_22/manifest_35/Frog.class
    Java-Bean: True

    Name: Rozdzial_22/manifest_35/BangBean2.class
    Java-Bean: True

W Name znajduje się pełna ścieżka package wewnątrz JAR.

tworzenie jar:
jar cfm Zad22_36.jar Zad22_36.mf -C .. Rozdzial_22\manifest_35

do pliku JAR z Zad22_36 trafiają klasy pomocnicze wygenerowane przez kompilator:
BangBean2$ML.class itd.

W IntelliJ Zad22_36.jar musiał zostać dodany do classpath MODUŁU,
do którego rzeczywiście należał kod (w tym przypadku to "cscorner").
Folder Rozdzial_22 i moduł IntelliJ Rozdzial_22 to nie koniecznie to samo.

Do palette wybierać ścieżki poprzedzone kropką:
Rozdzial_22.manifest_35.Frog

Oryginalna klasa Frog jest niewidzialnym JavaBean.
Stary BeanBuilder używany w książce potrafił takie obsługiwać.
IntelliJ Swing UI Designer oczekuje komponentu Swing.
Zamieniono na potrzeby nowego GUI Designer
    public class Frog extends JPanel
zamiast:
    public class Frog

True oraz Sun Bean Builder to rozwiązania stare.
Jedynym atutem tego ćwiczenia jest mechanizm pakowania JavaBeans opisany w książce.
*/