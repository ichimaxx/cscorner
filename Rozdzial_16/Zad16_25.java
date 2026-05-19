import java.util.*;
import static myutils.Skrocenie_Print.*;
/*
Exercise 25: (3) Rewrite PythonLists.py in Java.
*/
public class Zad16_25{
    public Integer[] revers(Integer[] k) {
        Integer[] revers = Arrays.copyOf(k, k.length); // reversed = self[:] kopia tablicy z main
        Collections.reverse(Arrays.asList(revers)); // reversed.reverse() tablica revers[] skonwertowana do widoku List, aby można było użyć metody Collections.reverse()
        return revers;
        /*
        def getReversed(self):
            reversed = self[:] # Copy list using slices
            reversed.reverse() # Built-in list method
            return reversed
        */
    }
    public static void main(String[] args) {
        Integer[] zk = new Integer[]{1, 2, 3, 4, 5};
        Integer[] z = new Integer[]{1, 2, 3, 4, 5}; // aList = [1, 2, 3, 4, 5]
        println("\nArray z[]: \n" + Arrays.toString(z)); // print aList # [1, 2, 3, 4, 5]
        println("\nTYP array zk[]:" + zk.getClass()+ "\n"); // print type(list2) / print type(aList)
        println("Array zk[] użyta metoda revers():\n" + Arrays.toString(new Zad16_25().revers(zk))); // print list2.getReversed()
        println("\nz[4] = " + z[4]); // print aList[4] #
        Integer[] resize = Arrays.copyOf(z, z.length + 3); // aList.append(6) aList += [7, 8] array nie może być resized w javie więc trzeba skopiować tablicę z pomocą Arrays,copyOf() i zrobić nową z dodanym rozmiarem
        resize[5] = 6;
        resize[6] = 7;
        resize[7] = 8;
        println("\nPowiększona tablica z[]: \n" + Arrays.toString(resize));
        z = Arrays.copyOfRange(resize, 2, 4); // aSlice = aList[2:4]
        println("\nTablica z[] od 2 do 4 indeksu: \n" + Arrays.toString(z)); // print aSlice # [3, 4]
    }
}
