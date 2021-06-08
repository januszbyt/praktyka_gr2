package application;

import classes.DBManager;
import classes.Kurs;
import classes.Uzytkownik;
import com.mysql.cj.log.Log;
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

public class Adm_Menugl implements Initializable {
    public ImageView menugl_logo;
    public Button btn_adm_zarzadzaj_uz;
    public Button btn_adm_zarzadzaj_wal;
    public Button btn_adm_zgloszenia;
    public Button btn_adm_historia;
    public Button btn_wyloguj;

    public static Uzytkownik sesja;
    public Label zalogowany_jako;
    public Label ostatnio_zalogowany;
    public Label kurs_eur;
    public Label kurs_usd;
    public Label kurs_gbp;
    public Label kurs_uah;
    public TableView tabela_niepotwierdzeni;
    ResultSet result;
    public static int liczba_osob_niepotwierdzonych;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sesja = Logowanie.zalogowany;

        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        menugl_logo.setImage(zdjecie);

        //Dodanie danych na temat logowania
        try {
            odswiez_dane();
        }catch (Exception e){
            System.out.println("Cos poszło nie tak");
        }

    }


    public void btn_adm_zarzadzaj_uz_M(ActionEvent actionEvent)  {
        try
        {
            ZmienOkno.zmienScene("adm_zarzadzanie_uzytkownikami.fxml", 1080, 540, btn_adm_zarzadzaj_uz);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_adm_zarzadzaj_wal_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("adm_zarzadzaj_waluta.fxml", 1080, 540, btn_adm_zarzadzaj_wal);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_adm_zgloszenia_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("adm_zgloszenia.fxml", 1080, 540, btn_adm_zgloszenia);
        }
        catch (Exception e){
            System.out.println("Błąd w wczytaniu okna");
        }
    }

    public void btn_adm_historia_M(ActionEvent actionEvent) {
        try
        {
            ZmienOkno.zmienScene("adm_historia.fxml", 1080, 540, btn_adm_historia);
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
            System.out.println("Błąd poczas wylogowania");
        }
    }

    public void odswiez_dane() throws Exception{
        result = DBManager.select("select imie,nazwisko,data from logi inner join uzytkownik on uzytkownik.id=logi.uzytkownik where logi.uzytkownik="+sesja.getId()+
                " and typ='Logowanie' order by data desc limit 2");

        result.next();
        result.next();

        zalogowany_jako.setText("Zalogowany jako " + result.getString(1) + " " + result.getString(2));
        ostatnio_zalogowany.setText("Ostatnia data logowania: " + result.getString(3));

        odswiez_kurs();
        Historia.xyz("SELECT imie,nazwisko,login FROM uzytkownik WHERE weryfikacja = 0",tabela_niepotwierdzeni,liczba_osob_niepotwierdzonych);
        System.out.println(liczba_osob_niepotwierdzonych);
    }
    public void odswiez_kurs() throws Exception{
        kurs_eur.setText(Double.toString(Kurs.getKurs("EUR")));
        kurs_usd.setText(Double.toString(Kurs.getKurs("USD")));
        kurs_gbp.setText(Double.toString(Kurs.getKurs("GBP")));
        kurs_uah.setText(Double.toString(Kurs.getKurs("UAH")));

    }
}
