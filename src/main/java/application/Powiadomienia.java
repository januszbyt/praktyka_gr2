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

    public static void alertPrzelewWeryfikacjaRachunkow2() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podaj rachunek innego uzytkownika!");
        porazka.showAndWait();
    }

    public static void alertPrzelewDlugoscPola(String tekst) {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niepoprawna dlugosc pola "+tekst+"!");
        porazka.showAndWait();
    }

    public static void alertPrzelewZnakiSpecjalne(String pole) {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Pole "+pole+" zawiera niedozwolone znaki!");
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

    public static void alertPrzelewJestPuste(String nazwa) {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Pole "+nazwa+" jest puste!");
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
        porazka.setContentText("Nieodpowiednia nazwa rachunku!");
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

    // Panel zarzadzania uzytkownikami

    public static void alertAdminWeryfikacja(String uzytkownik) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie zweryfikowano uzytkownika!\nUzytkownik: " + uzytkownik);
        sukces.showAndWait();
    }

    public static void alertAdminBlokowanie(String uzytkownik) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie zablokowano uzytkownika!\nUzytkownik: " + uzytkownik);
        sukces.showAndWait();
    }

    public static void alertAdminUsuwanie(String uzytkownik) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie usunieto uzytkownika!\nUzytkownik: " + uzytkownik);
        sukces.showAndWait();
    }

    public static void alertAdminWeryfikacjaPorazka() {
        Alert sukces = new Alert(Alert.AlertType.ERROR);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Ten uzytkownik jest juz zweryfikowany!");
        sukces.showAndWait();
    }

    public static void alertAdminBlokowaniePorazka() {
        Alert sukces = new Alert(Alert.AlertType.ERROR);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Ten uzytkownik jest juz zablokowany!");
        sukces.showAndWait();
    }

    public static void alertAdminUsuwaniePorazka() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nie mozna usunac uzytkownika!\nPrzed operacja upewnij sie ze uzytkownik nie posiada zadnych rachunkow.");
        porazka.showAndWait();
    }

    public static void alertAdminWybor() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz uzytkownika!");
        porazka.showAndWait();
    }

    // Panel zarzadzania uzytkownikami

    public static void alertAdminWalutaWybor() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz walute!");
        porazka.showAndWait();
    }

    public static void alertAdminWalutaUsuwanie(String waluta) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie usunieto walute!\nWaluta: " + waluta);
        sukces.showAndWait();
    }

    public static void alertAdminWalutaUsuwaniePorazka() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nie mozna usunac waluty!\nPrzed operacja upewnij sie ze zaden rachunek nie korzysta z tej waluty.");
        porazka.showAndWait();
    }

    public static void alertWalutaDodajSukces(String nazwa, String skrot) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie dodano walute!\nWaluta: " + nazwa + "\nSkrot: " + skrot);
        sukces.showAndWait();
    }

    public static void alertWalutaEdytujSukces(String nazwa) {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Pomyslnie zedytowano waluta!\nNowa nazwa: " + nazwa);
        sukces.showAndWait();
    }

    public static void alertWalutaNazwa() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nieodpowiednia nazwa waluty!");
        porazka.showAndWait();
    }

    public static void alertWalutaJestPuste(String nazwa) {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Pole "+nazwa+" jest puste!");
        porazka.showAndWait();
    }

    public static void alertWalutaZnakiSpecjalne(String pole) {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Pole "+pole+" zawiera niedozwolone znaki!");
        porazka.showAndWait();
    }

    public static void alertWalutaIstnieje() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Waluta juz istnieje w systemie!");
        porazka.showAndWait();
    }

    public static void alertWalutaNieIstnieje() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podana waluta nie istnieje badz nie jest obslugiwana!");
        porazka.showAndWait();
    }
    public static void adm_historia() {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Nie wybrales uzytkownika lub aktywnosci!");
        porazka.showAndWait();
    }
}