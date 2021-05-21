package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Historia implements Initializable
{
    @FXML
    private ComboBox historia_lista;
    @FXML
    private TextField historia_wyszukaj_text;
    @FXML
    private TextArea historia_text;
    @FXML
    private  Button historia_wyszukaj_btn;




    public void wypelnijListe()
    {
        historia_lista.getItems().add("Wszystko");
        historia_lista.getItems().add("Logowanie");
        historia_lista.getItems().add("Przelewy przychodzace");
        historia_lista.getItems().add("Przelewy wychodzace");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijListe();
    }
}
