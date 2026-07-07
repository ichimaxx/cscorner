//dzięki temu importowi można użyć typu SignalsEnum
import myutils.SignalsEnum;
//a dzięki importowi static, można użyć nazw kolorów bez podawania SignalsEnum.GREEN itd. za każdym razem
import static myutils.SignalsEnum.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 1: (2) Use a static import to modify TrafficLight.java so you don’t have to
qualify the enum instances.
*/
public class Zad19_1 {
    SignalsEnum color = GREEN;

    public void change() {
        switch (color) {
            case RED:
                color = GREEN;
                break;
            case GREEN:
                color = YELLOW;
                break;
            case YELLOW:
                color = RED;
                break;
        }
    }
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        Zad19_1 t = new Zad19_1();
        for (int i = 0; i < 7; i++) {
            println(t);
            t.change();
        }

    }
}
