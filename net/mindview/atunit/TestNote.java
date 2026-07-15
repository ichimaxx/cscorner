package net.mindview.atunit;
import java.lang.annotation.*;
//nowy interfejs TestNote
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestNote {
    //notatka musi zawierać String
    String value();
}
