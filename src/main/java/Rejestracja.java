import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    private Label rejestracja_haslo_alert,rejestracja_alert;

    // zmienna przechowywujaca informacje ktore pola nie spelniaja wymagan
private String blad;







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
    // pole imie
    if (rejestracja_imie.getLength()<3 || rejestracja_imie.getLength()>20)
    {
     blad+="Imie ";
    }

    // pole nazwisko
    if (rejestracja_nazwisko.getLength()<3 || rejestracja_nazwisko.getLength()>20)
    {
        blad+="Nazwisko ";
    }

    // pole pesel
    //TUTAJ BEDZIE VALIDATOR

   /* if (rejestracja_imie.getLength()<3 || rejestracja_imie.getLength()>20)
    {
        blad+="pesel ";
    }
*/
    // pole login
    if (rejestracja_login.getLength()<5 || rejestracja_login.getLength()>20)
    {
        blad+="Login ";
    }

    // pole haslo
    if (rejestracja_haslo.getLength()<5 || rejestracja_haslo.getLength()>20 ||
        rejestracja_haslo2.getLength()<5 || rejestracja_haslo2.getLength()>20)
    {
        blad+="Haslo ";

        //sprawdzenie czy hasla sa identyczne
        if(rejestracja_haslo.getText()!=rejestracja_haslo2.getText())
        {
            rejestracja_haslo_alert.setText("Hasla sa niepoprawne !");
        }
    }

    // pole email
    /*
    TUTAJ BEDZIE VALIDATOR
    if (rejestracja_pesel.getLength()<3 || rejestracja_pesel.getLength()>20)
    {
        blad+="Email ";
    }
*/

}

}