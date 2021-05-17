import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Rejestracja implements Initializable {


//deklaracja labelów, przycisków itp. z Scen Builder'a

    @FXML
    private ImageView rejestracja_logo;
    @FXML
    private TextField rejestracja_imie,rejestracja_nazwisko,rejestracja_pesel,rejestracja_login,rejestracja_email;
    @FXML
    private PasswordField rejestracja_haslo,rejestracja_haslo2;
    @FXML
    private Button btn_zarejestruj,btn_mjk,btn_wyczysc,btn_zamknij;


    // zmienna przechowywujaca informacje ktore pola nie spelniaja wymagan
private String blad="";







    //METODY



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File plik= new File("src/images/logobiale.png");
        Image zdjecie= new Image(plik.toURI().toString());
        rejestracja_logo.setImage(zdjecie);

    }
// metoda czyszczaca wszystkie pola
    public void wyczyscButton() {
        rejestracja_imie.setText("");
        rejestracja_nazwisko.setText("");
        rejestracja_pesel.setText("");
        rejestracja_login.setText("");
        rejestracja_haslo.setText("");
        rejestracja_haslo2.setText("");
        rejestracja_email.setText("");

    }

//metoda zamykajaca aplikacje
    public void zamknijButton(ActionEvent actionEvent) {
        Platform.exit();
    }

//metoda sprawdzajaca i dopisujaca do zmiennej pola ktore sa niepoprawne
public void sprawdzRejestracja()
{
    //zmienna jest czyszczona
    blad="\n";

    // pole imie
    if (rejestracja_imie.getLength()<3 || rejestracja_imie.getLength()>20)
    {
     blad+="Imie \n";
    }

    // pole nazwisko
    if (rejestracja_nazwisko.getLength()<3 || rejestracja_nazwisko.getLength()>20)
    {
        blad+="Nazwisko \n";
    }

    // pole pesel
    //TUTAJ BEDZIE VALIDATOR

   /* if (rejestracja_imie.getLength()<3 || rejestracja_imie.getLength()>20)
    {
        blad+="pesel \n";
    }
*/
    // pole login
    if (rejestracja_login.getLength()<5 || rejestracja_login.getLength()>20)
    {
        blad+="Login \n";
    }

    // pole haslo
    if (rejestracja_haslo.getLength()<5 || rejestracja_haslo.getLength()>20 ||
        rejestracja_haslo2.getLength()<5 || rejestracja_haslo2.getLength()>20)
    {
        blad+="Haslo \n";

    }
    //sprawdzenie czy hasla sa identyczne
    if(!rejestracja_haslo.getText().equals(rejestracja_haslo2.getText()))
    {

        blad+= "Hasla nie sa identyczne \n";
    }

    // pole email
    /*
    TUTAJ BEDZIE VALIDATOR
    if (rejestracja_pesel.getLength()<3 || rejestracja_pesel.getLength()>20)
    {
        blad+="Email \n";
    }
*/

}

public void komunikat()
{
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
        porazka.setContentText("Rejestracja zakonczaona niepowodzeniem. Popraw nastepujace pola: "+blad );

        porazka.showAndWait();
    }

}
    public void zarejestrujButton(ActionEvent actionEvent) {
        sprawdzRejestracja();
        komunikat();

    }
}