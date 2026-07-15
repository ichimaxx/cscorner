import net.mindview.atunit.*;
import net.mindview.util.*;
/*
Exercise 11: (5) Add an @TestNote annotation to @Unit, so that the accompanying
note is simply displayed during testing.
*/
public class Zad20_11 {
    @Test
    @TestNote("TESTOWA NOTATKA ZAD20_11")
    boolean test() {
        return 2 + 2 == 4;
    }
    public static void main(String[] args) {
        OSExecute.command(
                "java -cp .;.. net.mindview.atunit.AtUnit Zad20_11");
    }
}



/*
Do AtUnit.java dodano 3 linijki kodu, które są odpowiedzialne za znalezienie
w kodzie adnotacji @TestNote i jej wyprintowanie:

TestNote note = m.getAnnotation(TestNote.class);
     if(note != null)
       printnb("[" + note.value() + "] ");

Wymagane było również stworzenie interfejsu TestNote w package net.mindview.atunit:

package net.mindview.atunit;
import java.lang.annotation.*;
//nowy interfejs TestNote
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestNote {
    //notatka musi zawierać String
    String value();
}

ElementType.METHOD pozwala umieszczać @TestNote nad metodami.
RetentionPolicy.RUNTIME sprawia, że adnotacja jest dostępna podczas działania programu
i może zostać odczytana z pomocą refleksji.
*/