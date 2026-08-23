/*
Exercise 38: (3) Build the "simple example of data binding syntax" shown above.
*/

public class Zad22_38 {
}

/*
Rozwiązanie znajduje się w pliku Zad22_39.mxml

MXML (Macromedia XML) służył we Flexie do opisywania interfejsu użytkownika.
Zamiast tworzyć komponenty instrukcjami kodu, opisuje się je tagami XML np mx:Slider.

Data Binding:
text = "{mySlider.value}"

mySlider.value jest źródłem danych, a Text.text jest z nim powiązany.
Zmiana wartości Slidera automatycznie zmienia tekst.

Data binding to automatyczne powiązanie jednej wartości z drugą.
Gdy wartość źródłowa się zmienia, powiązana właściwość jest automatycznie aktualizowana
bez ręcznego przepisywania danych lub obsługi listenera.

Slider.value
    V  data binding
Text.text

Do edycji pliku .mxml w IntelliJ użyto pluginu Flex,
który zapewnia obsługę składni i struktury plików Flex/MXML.

MXML był częścią technologii Adobe/Macromedia Flex i był kompilowany
razem z ActionScriptem do pliku SWF uruchamianego przez FlashPlayer.

Flex/Flash jest obecnie bardzo przestarzały.
Jednak idea deklaratywnego GUI i data bindingu jest nadal aktualna.
*/