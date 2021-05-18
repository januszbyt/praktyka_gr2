import javafx.scene.control.Alert;

public class Powiadomienia {

    // Rejestracja
    public static void alertRejestracja(String blad) {
        //sprawdzenie czy zmienna przechowywujace nieprawidlowe pola jest pusta

        if (blad.isBlank()) {//SUKCES
            Alert sukces = new Alert(Alert.AlertType.INFORMATION);
            sukces.setTitle("Powiadomienie");
            sukces.setHeaderText(null);
            sukces.setContentText("Rejestracja zakonczona sukcesem!");

            sukces.showAndWait();
        } else {//PORAZKA
            Alert porazka = new Alert(Alert.AlertType.ERROR);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Rejestracja zakonczona niepowodzeniem. Popraw nastepujace pola: " + blad);

            porazka.showAndWait();
        }

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


}