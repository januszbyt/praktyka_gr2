package application;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Menugl implements Initializable {
    public ImageView menugl_logo;
    public Button btn_przelew;
    public Button btn_rachunki;
    public Button btn_historia;
    public Button btn_wymiana;
    public Button btn_zgloszenia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        menugl_logo.setImage(zdjecie);
    }


    public void btn_przelew_M(ActionEvent actionEvent)  {
        try
        {
            ZmienOkno.zmienScene("przelew.fxml", 750, 483, btn_przelew);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_rachunki_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("rachunki.fxml", 750, 483, btn_przelew);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_historia_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("historia.fxml", 791, 522, btn_przelew);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_wymiana_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("wymiana.fxml", 750, 483, btn_przelew);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_zgloszenia_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("zgloszenia.fxml", 1077, 534, btn_przelew);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }
}
