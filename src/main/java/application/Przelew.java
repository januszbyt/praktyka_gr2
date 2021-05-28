package application;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static classes.DBManager.select;

public class Przelew implements Initializable {
    public ImageView img_menugl;

    //deklaracja zmiennych z Scen Buildera

    @FXML
    private TextField przelew_dane, przelew_numer, przelew_kwota, przelew_tytul;
    @FXML
    private Button btn_potwierdz, btn_wyczysc;
    @FXML
    private ComboBox przelew_listarachunek;
    @FXML
    private Label przelew_numerrachunku, przelew_danenazwa1, przelew_danewaluta1, przelew_danesaldo1;

    public Uzytkownik sesja;
    public Rachunek rachunek1, rachunek2;
    public Waluta waluta1, waluta2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Logowanie.zalogowany;
        wypelnijListaRachunek(sesja.getId());
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
    }

    public void listarachunekAkcja(ActionEvent event) {
        rachunek1 = Rachunek.wczytajRachunek_numer(String.valueOf(przelew_listarachunek.getValue()));
        waluta1 = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        przelew_numerrachunku.setText(rachunek1.getNumer());
        przelew_danenazwa1.setText(rachunek1.getNazwa());
        przelew_danewaluta1.setText(waluta1.getSkrot());
        przelew_danesaldo1.setText(String.valueOf(rachunek1.getSaldo()));

    }

    public void listarachunekAkcja2() {
        rachunek1 = Rachunek.wczytajRachunek_numer(String.valueOf(przelew_listarachunek.getValue()));
        waluta1 = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        przelew_numerrachunku.setText(rachunek1.getNumer());
        przelew_danenazwa1.setText(rachunek1.getNazwa());
        przelew_danewaluta1.setText(waluta1.getSkrot());
        przelew_danesaldo1.setText(String.valueOf(rachunek1.getSaldo()));

    }

    public void potwierdzButton() {

        rachunek2 = Rachunek.wczytajRachunek_numer(przelew_numer.getText());
        if (czyistniejeRachunek(przelew_numer.getText()) == false) {
            Powiadomienia.alertPrzelewWeryfikacjaRachunek();

        } else if (weryfikacjaRachunkow(rachunek1, rachunek2) == true) {
            if (Rachunek.weryfikacjaSaldo(rachunek1, Float.parseFloat(przelew_kwota.getText()))) {
                if (rachunek1.getWaluta() == rachunek2.getWaluta()) {
                    przelejSamaWaluta(rachunek1, rachunek2, Float.parseFloat(przelew_kwota.getText()));
                    wyczyscButton();
                    listarachunekAkcja2();
                } else {
                    przelejInnaWaluta(rachunek1, rachunek2, Float.parseFloat(przelew_kwota.getText()));
                    wyczyscButton();
                    listarachunekAkcja2();

                }
            }

        }

    }

    public void wyczyscButton()   // Guzik wyczyszczenia pola
    {
        przelew_dane.setText("");
        przelew_numer.setText("");
        przelew_kwota.setText("");
        przelew_tytul.setText("");
    }

    public void przelejSamaWaluta(Rachunek rachunek1, Rachunek rachunek2, float kwota) {
        Rachunek.usunSaldo(rachunek1, kwota);
        Rachunek.dodajSaldo(rachunek2, kwota);
        Waluta waluta = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        DecimalFormat df = new DecimalFormat("###.##");

        DBManager.update(Logi.przelewWykonujacyLog(Integer.toString(rachunek2.getId()),Float.toString(kwota),przelew_tytul.getText(),Integer.toString(sesja.getId())));
        DBManager.update(Logi.przelewPrzyjmujacyLog(Integer.toString(rachunek1.getId()),Integer.toString(rachunek2.getId()),Float.toString(kwota),przelew_tytul.getText(),Integer.toString(rachunek2.getUzytkownik())));

        System.out.println(Logi.przelewPrzyjmujacyLog(Integer.toString(rachunek1.getId()),Integer.toString(rachunek2.getId()),Float.toString(kwota),przelew_tytul.getText(),Integer.toString(rachunek2.getUzytkownik())));
        Powiadomienia.alertPrzelewSukces(rachunek1.getNumer(), rachunek2.getNumer(), df.format(kwota), df.format(kwota), waluta.getSkrot(), waluta.getSkrot());
    }

    public void przelejInnaWaluta(Rachunek rachunek1, Rachunek rachunek2, float kwota) {
        double kurs1 = 0;
        double kurs2 = 0;
        Waluta waluta1 = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        Waluta waluta2 = Waluta.wczytajWaluta_id(rachunek2.getWaluta());
        if (!waluta1.getSkrot().equals("PLN")) {
            kurs1 = sprawdzWaluta(waluta1);
        } else kurs1 = 1.0;

        if (!waluta2.getSkrot().equals("PLN")) {
            kurs2 = sprawdzWaluta(waluta2);
        } else kurs2 = 1.0;

        Double przelicznik = kurs1 / kurs2;
        float kwota2 = kwota;
        kwota2 = kwota2 * przelicznik.floatValue();

        Rachunek.usunSaldo(rachunek1, kwota);
        Rachunek.dodajSaldo(rachunek2, kwota2);
        DecimalFormat df = new DecimalFormat("###.##");

        //metody dodajace logi przelewow
        DBManager.update(Logi.przelewWykonujacyLog(Integer.toString(rachunek2.getId()),Float.toString(kwota),przelew_tytul.getText(),Integer.toString(sesja.getId())));
        DBManager.update(Logi.przelewPrzyjmujacyLog(Integer.toString(rachunek1.getId()),Integer.toString(rachunek2.getId()),Float.toString(kwota2),przelew_tytul.getText(),Integer.toString(rachunek2.getUzytkownik())));
        System.out.println(Logi.przelewPrzyjmujacyLog(Integer.toString(rachunek1.getId()),Integer.toString(rachunek2.getId()),Float.toString(kwota2),przelew_tytul.getText(),Integer.toString(rachunek2.getUzytkownik())));
        Powiadomienia.alertPrzelewSukces(rachunek1.getNumer(), rachunek2.getNumer(), df.format(kwota), df.format(kwota2), waluta1.getSkrot(), waluta2.getSkrot());
    }

    public double sprawdzWaluta(Waluta waluta) {
        try {
            return Kurs.getKurs(waluta.getSkrot());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean weryfikacjaRachunkow(Rachunek rachunek1, Rachunek rachunek2) {

        if (rachunek1 == null) {
            Powiadomienia.alertPrzelewWybierzRachunek();
        }
        if (rachunek1.getId() == rachunek2.getId()) {
            Powiadomienia.alertPrzelewWeryfikacjaRachunkow();
            return false;
        } else return true;

    }

    public static boolean czyistniejeRachunek(String numer) {

        try {

            ResultSet result = select("SELECT * FROM rachunek WHERE numer = '" + numer + "';");

            if (result.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void wypelnijListaRachunek(int uzytkownik) {
        try {
            ResultSet result = select("SELECT numer FROM rachunek WHERE uzytkownik = " + uzytkownik + ";");
            String numer;
            while (result.next()) {
                numer = result.getString("numer");
                przelew_listarachunek.getItems().add(numer);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("menugl.fxml", 1077, 534, img_menugl);
    }
}
