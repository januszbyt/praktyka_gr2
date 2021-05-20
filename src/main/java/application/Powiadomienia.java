package application;
import javafx.scene.control.Alert;

public class Powiadomienia {



    //BAZA DANYCH BLAD

    public static void alertBazaDanych()
    {
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
            sukces.setContentText("Rejestracja zakonczona sukcesem! \n Mozesz zalogowac się na swoje konto!");

            sukces.showAndWait();


        } else {//PORAZKA
            Alert porazka = new Alert(Alert.AlertType.ERROR);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Rejestracja zakonczona niepowodzeniem. Popraw nastepujace pola: " + blad);

            porazka.showAndWait();
        }

    }

    public static void alertRejestracjaZajety ()
    {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Uzytkownik z podanym loginem lub email istnieje! " );

        porazka.showAndWait();
    }


    // Logowanie

    public static void alertLogowanie(String blad_logowania){
        if(blad_logowania.isBlank()){ //sukces
            Alert sukces = new Alert(Alert.AlertType.INFORMATION);
            sukces.setTitle("Powiadomienie");
            sukces.setHeaderText(null);
            sukces.setContentText("Pomyslnie zalogowano!");

            sukces.showAndWait();
        }else { // porazka
            Alert porazka = new Alert(Alert.AlertType.INFORMATION);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Nie udalo sie zalogowac!" + blad_logowania);

            porazka.showAndWait();
        }
    }

    // Przelew

    public static void alertPrzelewWeryfikacjaRachunkow()
    {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Podaj rozne numery rachunkow!");
        porazka.showAndWait();
    }


    public static void alertPrzelewWeryfikacjaRachunek()
    {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niepoprawny numer rachunku!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWybierzRachunek()
    {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Wybierz rachunek!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWeryfikacjaSaldo()
    {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niewystarczajaca ilosc srodkow!");
        porazka.showAndWait();
    }

    public static void alertPrzelewWeryfikacjaSaldo2()
    {
        Alert porazka = new Alert(Alert.AlertType.ERROR);
        porazka.setTitle("Powiadomienie");
        porazka.setHeaderText(null);
        porazka.setContentText("Niepoprawna kwota przelewu!");
        porazka.showAndWait();
    }

    public static void alertPrzelewSukces()
    {
        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
        sukces.setTitle("Powiadomienie");
        sukces.setHeaderText(null);
        sukces.setContentText("Przelew wykonano pomyslnie!");
        sukces.showAndWait();
    }

}