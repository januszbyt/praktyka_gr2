package application;

import javafx.scene.control.Alert;

public class Powiadomienia {

    //BAZA DANYCH BLAD

    public static void alertBazaDanych() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nie mozna polaczyc z baza danych!");

        porazka.showAndWait();
    }

    // Rejestracja
    public static void alertRejestracja(String blad) throws Exception {
        //sprawdzenie czy zmienna przechowywujace nieprawidlowe pola jest pusta

        if (blad.isBlank()) {//SUKCES
            Alert sukces = new Alert(Alert.AlertType.INFORMATION);
            sukces.setTitle("Powiadomienie");
            sukces.setHeaderText(null);
            sukces.setContentText("Rejestracja zakonczona sukcesem! \n Mozesz zalogowac sie na swoje konto!");

            sukces.showAndWait();

        } else {//PORAZKA
            Alert porazka = new Alert(Alert.AlertType.ERROR);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Rejestracja zakonczona niepowodzeniem. Popraw nastepujace pola: " + blad);

            porazka.showAndWait();
        }

    }

    public static void alertRejestracjaZajety() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Uzytkownik z podanym loginem lub email istnieje! ");

        porazka.showAndWait();
    }

    // Logowanie

    public static void alertLogowanie(String blad_logowania) {
        if (blad_logowania.isBlank()) { //sukces
            Alert sukces = new Alert(Alert.AlertType.INFORMATION);
            sukces.setTitle("Powiadomienie");
            sukces.setHeaderText(null);
            sukces.setContentText("Pomyslnie zalogowano!");

            sukces.showAndWait();
        } else { // porazka
            Alert porazka = new Alert(Alert.AlertType.INFORMATION);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Nie udalo sie zalogowac!" + blad_logowania);

            porazka.showAndWait();
        }
    }

    // Przelew

    public static void alertPrzelewWeryfikacjaRachunkow() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podaj rozne numery rachunkow!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWeryfikacjaRachunek() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niepoprawny numer rachunku!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWybierzRachunek() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz rachunek!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWeryfikacjaSaldo() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niewystarczajaca ilosc srodkow!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWeryfikacjaSaldo2() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niepoprawna kwota przelewu!");
        porazka.showAndWait();
    }

    public static void alertPrzelewSukces(String numer1, String numer2, String Kwota1, String Kwota2, String skrot1, String skrot2) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Przelew wykonany pomyslnie!\nNadawca: " + numer1 + "\nOdbiorca: " + numer2 + "\n" + Kwota1 + " " + skrot1 + " --> " + Kwota2 + " " + skrot2);
        sukces.showAndWait();
    }

    // Wymiana

    public static void alertWymianaWeryfikacjaRachunkow() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podano ten sam rachunek. Wybierz inny rachunek!");
        porazka.showAndWait();
    }

    public static void alertWymianaWybierzRachunek() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz odpowiednie rachunki!");
        porazka.showAndWait();
    }

    public static void alertWymianaKwota() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podaj odpowiednia kwote!");
        porazka.showAndWait();
    }

    public static void alertWymianaSukces(String numer1, String numer2, String Kwota1, String Kwota2, String skrot1, String skrot2) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Wymiana wykonana pomyslnie!\nSprzedaz: " + numer1 + "\nKupno: " + numer2 + "\n" + Kwota1 + " " + skrot1 + " --> " + Kwota2 + " " + skrot2);
        sukces.showAndWait();
    }

    // Panel rachunkow

    public static void alertWplacSukces(String rachunek, String skrot, String kwota) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Wplata wykonana pomyslnie!\nRachunek: " + rachunek + "\nWplacono: " + kwota + " " + skrot);
        sukces.showAndWait();
    }

    public static void alertWyplacSukces(String rachunek, String skrot, String kwota) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Wyplata wykonana pomyslnie!\nRachunek: " + rachunek + "\nWplacono: " + kwota + " " + skrot);
        sukces.showAndWait();
    }

    public static void alertRachunkiKwota() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podaj odpowiednia kwote!");
        porazka.showAndWait();
    }

    public static void alertRachunkiNazwa() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nieodpowiednia dlugosc nazwy rachunku!");
        porazka.showAndWait();
    }

    public static void alertRachunkiListaWalut() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz odpowiednia walute!");
        porazka.showAndWait();
    }

    public static void alertRachunkiSaldo() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nie jest mozliwe usuniecie rachunku z saldem na koncie! Oproznij konto przed usunieciem go.");
        porazka.showAndWait();
    }

    public static void alertRachunkiNumer() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz rachunek!");
        porazka.showAndWait();
    }

    public static void alertDodajSukces(String rachunek, String nazwa, String waluta) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie dodano rachunek!\nRachunek: " + rachunek + "\nNazwa: " + nazwa + "\nWaluta: "+waluta);
        sukces.showAndWait();


    }

    public static void alertEdytujSukces(String rachunek, String nazwa) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie zedytowano rachunek!\nRachunek: " + rachunek + "\nNowa nazwa: " + nazwa);
        sukces.showAndWait();


    }



    public static void alertUsunSukces(String rachunek) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie usunieto rachunek!\nRachunek: " + rachunek);
        sukces.showAndWait();
    }

}