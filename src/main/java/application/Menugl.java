package application;

import classes.DBManager;
import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import classes.Kurs;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import application.Logowanie;
import application.Menugl;
import classes.Uzytkownik;
public class Menugl implements Initializable {
    public ImageView menugl_logo;
    public Button btn_przelew;
    public Button btn_rachunki;
    public Button btn_historia;
    public Button btn_wymiana;
    public Button btn_zgloszenia;
    public Button btn_wyloguj;
    public Button btn_kurs;
    @FXML
    public Label zalogowany_jako, ostatnio_zalogowany;
    public TableView tabela_rachunki;
    public Label kurs_eur,kurs_usd,kurs_gbp,kurs_uah;

    public static Uzytkownik sesja;
    ResultSet result;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sesja=Logowanie.zalogowany;
        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        menugl_logo.setImage(zdjecie);
        try{
            odswiez_Dane();
            }
        catch (SQLException | IOException | InterruptedException e)
        {
            System.out.println("Coś poszło nie tak!");
        }
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
            ZmienOkno.zmienScene("historia.fxml", 1080, 540, btn_przelew);
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
            ZmienOkno.zmienScene("zgloszenia.fxml", 1080, 540, btn_przelew);
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

    public void odswiez_Dane () throws SQLException, IOException, InterruptedException {

            result=DBManager.select("select imie,nazwisko,data from logi inner join uzytkownik on uzytkownik.id=logi.uzytkownik where logi.uzytkownik="+sesja.getId()+
                    " and typ='Logowanie' order by data desc limit 2");
            result.next();
            result.next();
            zalogowany_jako.setText("Zalogowano jako: " + result.getString(1) + " " + result.getString(2));
            ostatnio_zalogowany.setText("Ostatnia data logowania: "+result.getString(3));

            odswiez_Kurs();
            Historia.xyz("select rachunek.nazwa,numer,saldo,(Select skrot from waluta where waluta.id=rachunek.waluta) as waluta " +
                    "from rachunek WHERE uzytkownik="+sesja.getId()+" ORDER by waluta ",tabela_rachunki);
    }

    public void odswiez_Kurs() throws IOException, InterruptedException {

        kurs_eur.setText(Double.toString(Kurs.getKurs("EUR")));
        kurs_usd.setText(Double.toString(Kurs.getKurs("USD")));
        kurs_gbp.setText(Double.toString(Kurs.getKurs("GBP")));
        kurs_uah.setText(Double.toString(Kurs.getKurs("UAH")));

    }
}
