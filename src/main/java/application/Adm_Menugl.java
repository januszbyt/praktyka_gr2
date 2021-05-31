package application;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Adm_Menugl implements Initializable {
    public ImageView menugl_logo;
    public Button btn_adm_zarzadzaj_uz;
    public Button btn_adm_zarzadzaj_wal;
    public Button btn_adm_zgloszenia;
    public Button btn_adm_historia;
    public Button btn_wyloguj;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        menugl_logo.setImage(zdjecie);
    }


    public void btn_adm_zarzadzaj_uz_M(ActionEvent actionEvent)  {
        try
        {
            ZmienOkno.zmienScene("adm_zarzadzanie_uzytkownikami.fxml", 750, 483, btn_adm_zarzadzaj_uz);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_adm_zarzadzaj_wal_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("adm_zarzadaj_wal.fxml", 750, 483, btn_adm_zarzadzaj_wal);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_adm_zgloszenia_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("adm_zgloszenia.fxml", 791, 522, btn_adm_zgloszenia);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_adm_historia_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("adm_historia.fxml", 1077, 534, btn_adm_historia);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_wyloguj_M(ActionEvent actionEvent) {
        try {
            ZmienOkno.zmienScene("logowanie.fxml", 650, 552, btn_wyloguj);
        }catch (Exception e){
            System.out.println("Błąd poczas wylogowania");
        }
    }
}
