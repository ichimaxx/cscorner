/*
Exercise 39: (4) The code download for this book does not include the MP3S or JPGs
shown in SongService.java. Find some MP3S and JPGs, modify SongService.java to
include their file names, download the Flex trial and build the application.
*/

public class Zad22_39 {
}

/*
Pliki zadania znajdują się w katalogu:
Rozdzial_22/Zad22_39/

Zadanie pokazuje połączenie aplikacji Flex z usługą napisaną w Javie.
SongService udostępnia dane o utworach, a Flex pobiera je przez RemoteObject
i wykorzystuje do wyświetlania informacji, obrazów JPG oraz odtwarzania MP3.

Java dostarcza dane, a klient Flex wykorzystuje je do prezentacji oraz obsługi
multimediów.

Technologia użyta w książce jest obecnie przestarzała. Flex tworzył interfejs
uruchamiany przez FlashPlayer, którego support zakończono w 2020r.

Nie oznacza to jednak, że sposób działania pokazany w zadaniu jest nieaktualny.
Współczesne aplikacje webowe bardzo często mają podobny podział.

Flex/MXML to dziś HTML + CSS + JavaScript/TypeScript,
    często z frameworkiem takim jak React, Angular lub Vue.

ActionScript to dziś JavaScript lub TypeScript

SongService.java nadal może byc kodem Java po stronie servera,
    np. usługą lub kontrolerem aplikacji Spring Boot.

<mx:RemoteObject> i wywołanie songService.getSongs() to dziś najczęściej
    żądanie HTTP do backendu, np. GET /songs wykonane przez fetch(),
    a dane są zwykle przesyłane jako JSON

Obiekty Song przesyłane z Javy do Flex
    Dziś najczęściej obiekty Java są zamieniane na JSON, który
    frontend odczytuje jako obiekty JavaScript.

JRun - server aplikacji z książki
    współcześnie aplikacja Java może być uruchamiana np. przez Spring Boot
    z wbudowanym serwerem HTTP

Flex nie ma jednego bezpośredniego współczesnego odpowiednika.
Został zastąpiony przez zestaw standardowych technologii webowych,
ale architektura klient-serwer pokazana w zadaniu jest nadal aktualna.

Flex > RemoteObject > SongService.java > List<Song>

Flex < dane o utworach < +

Współcześnie:

React/Vue/Angular > HTTP GET /songs > Spring Boot

frontend < JSON z listą utworów < ----- +

Zależności są bardzo podobne:
frontend odpowiada za prezentację i interakcję z użytkownikiem,
backend Java dostarcza dane i wykonuje logikę aplikacji.

Zmienił się przede wszystkim sposób komunikacji.
Flex używał RemoteObject i technologii związanycvh z Flash,
a współczesne aplikacje komunikują się przez HTTP i wymieniają
dane w formacie JSON.
*/