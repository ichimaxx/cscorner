package Rozdzial_22;
import javax.swing.*;
/*
Exercise 37: (5) Create your own JavaBean called Valve that contains two properties: a
boolean called "on" and an int called "level." Create a manifest file, use jar to package your
Bean, then load it into the Bean Builder or into a Beans-enabled program builder tool so that
you can test it.
*/
public class Zad22_37 {
    private JPanel panel1;
}

/*
Do zadania wymagany jest plugin Swing UI Designer
Valve posiada dwie properties JavaBean:
level -> getLevel()/setLevel()
on -> isOn()/setOn()

Utworzono manifest z:
Name: Rozdzial_22/Valve/Valve.class
Java-Bean: True

Bean został spakowany do Zad22_37.jar i JAR dodano
do Dependencies modułu "cscorner", ponieważ do tego modułu faktycznie
należy kod Rozdzial_22.

Valve rozszerza JPanel tylko dlatego, że używany współcześnie IntelliJ
Swing UI Designer wymaga wizualnego komponentu Swing;
oryginalny Bean nie musiałby dziedziczyć po JPanel.

Po załadowaniu Valve z Jar do Palette GUI Designer przez introspekcję
poprawnie wykrył properties "level" i "on".
*/