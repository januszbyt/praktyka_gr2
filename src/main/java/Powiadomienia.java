import javafx.scene.control.Alert;

public class Powiadomienia

{

    // Rejestracja
    public void alertRejestracja(String blad)
    {
        //sprawdzenie czy zmienna przechowywujace nieprawidlowe pola jest pusta

        if(blad.isBlank())
        {//SUKCES
            Alert sukces = new Alert(Alert.AlertType.INFORMATION);
            sukces.setTitle("Powiadomienie");
            sukces.setHeaderText(null);
            sukces.setContentText("Rejestracja zakończona sukcesem!");

            sukces.showAndWait();
        }
        else
        {//PORAZKA
            Alert porazka = new Alert(Alert.AlertType.ERROR);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Rejestracja zakończona niepowodzeniem. Popraw następujące pola: "+blad );

            porazka.showAndWait();
        }

    }



    // Logowanie



    // Przelew



}