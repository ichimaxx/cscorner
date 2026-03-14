import Rozdzial_14.typeinfo.*; 
import static myutils.Skrocenie_Print.*;

/*Exercise 11: (2) Add Gerbil to the typeinfo.pets library and modify all the examples in
this chapter to adapt to this new class. */

public class Zad14_11 {
	public static void main (String[] args) {
		println("\nSTART PETCOUNT1\n" + "....\n");
		PetCount.main(args); // start programu PetCount.java 
		println("\nSTART PETCOUNT2\n" + "....\n");
		PetCount2.main(args); // start programu PetCount2.java 
		println("\nSTART PETCOUNT3\n" + "....\n");
		PetCount3.main(args); // start programu PetCount3.java 
		println("\nSTART PETCOUNT4\n" + "....\n");
		PetCount4.main(args); // start programu PetCount4.java 
	}
}

/* 
stworzono nowy package osobny dla Rozdzialu 14 nazwany Rozdzial_14.typeinfo.*; zeby byl osobnym pakietiem klas niż do rozdzialu 11, gdzie juz uzyto typeinfo/pets
Dodano do package Gerbil, zadanie udało się wykonać, program się kompiluje.
Ze wzgledu na to, zeby mieć wszysstkie zadania w jednym folderze, przerobiłem trochę idee tak, aby odpalać wszystko z pozycji folderu Rozdzial_x

OUTPUT:
C:\Users\ichim\Desktop\cscorner\Rozdzial_14>java Zad14_11

START PETCOUNT1
....

EgyptianMau Gerbil Cymric EgyptianMau Cymric EgyptianMau Pug Rat Mutt Cymric Manx Manx Manx Cymric EgyptianMau Pug Hamster Cymric Gerbil Pug
{EgyptianMau=8, Pug=3, Cymric=8, Rat=1, Cat=12, Manx=8, Rodent=4, Mutt=1, Gerbil=2, Dog=4, Pet=20, Hamster=1}

START PETCOUNT2
....

EgyptianMau Gerbil Cymric EgyptianMau Cymric EgyptianMau Pug Rat Mutt Cymric Manx Manx Manx Cymric EgyptianMau Pug Hamster Cymric Gerbil Pug
{EgyptianMau=8, Pug=3, Cymric=8, Rat=1, Cat=12, Manx=8, Rodent=4, Mutt=1, Gerbil=2, Dog=4, Pet=20, Hamster=1}

START PETCOUNT3
....

Pug Manx Pug Mouse Rat Mutt Rat Pug Cymric Rat Mutt Hamster Hamster Mouse Pug Manx Pug Manx Cymric Mutt
{Pet=20, Dog=8, Cat=5, Rodent=7, Mutt=3, Pug=5, EgyptianMau=0, Manx=5, Cymric=2, Rat=3, Mouse=2, Hamster=2, Gerbil=0}

START PETCOUNT4
....

Manx Gerbil Mutt Pug Manx Pug Hamster Rat Cymric Hamster Manx Cymric Pug Rat Pug EgyptianMau Manx Manx Manx Hamster
{Gerbil=1, Manx=8, Cat=9, Rodent=6, Pet=20, Mutt=1, Dog=5, EgyptianMau=1, Pug=4, Rat=2, Hamster=3, Cymric=2}


*/