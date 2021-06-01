package application;

import classes.DBManager;
import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Menugl implements Initializable {
    public ImageView menugl_logo;
    public Button btn_przelew;
    public Button btn_rachunki;
    public Button btn_historia;
    public Button btn_wymiana;
    public Button btn_zgloszenia;
    public Button btn_wyloguj;
    public Button btn_kurs;
    public Label zalogowany_jako, ostatnio_zalogowany;
    //public TableView tabela_rachunki;
    public Label kurs_eur,kurs_usd,kurs_gbp,kurs_uah;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        menugl_logo.setImage(zdjecie);

        Uzytkownik sesja= Logowanie.zalogowany;

        ResultSet result;
        try {
            result = DBManager.select("SELECT imie,nazwisko from uzytkownik where id=" + sesja.getId());

            zalogowany_jako.setText("Zalogowany jako: " + result.getString("imie") + " " + result.getString("nazwisko"));

            System.out.println(result.getString("imie")+result.getString("nazwisko"));
        }
        catch(Exception e)
        {}
    }


    public void btn_przelew_M(ActionEvent actionEvent) throws Exception  {

            ZmienOkno.zmienScene("przelew.fxml", 1080, 540, btn_przelew);


    }

    public void btn_rachunki_M(ActionEvent actionEvent) throws Exception {

            ZmienOkno.zmienScene("rachunki.fxml", 1080, 540, btn_rachunki);

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
            ZmienOkno.zmienScene("wymiana.fxml", 1080, 540, btn_przelew);
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

    public void btn_wyloguj_M(ActionEvent actionEvent) {
        try {
            Logowanie.zalogowany = null;
            ZmienOkno.zmienScene("logowanie.fxml", 650, 552, btn_wyloguj);
        }catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }

    }
}
