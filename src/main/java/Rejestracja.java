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







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File plik= new File("src/images/logobiale.png");
        Image zdjecie= new Image(plik.toURI().toString());
        rejestracja_logo.setImage(zdjecie);
    }
}