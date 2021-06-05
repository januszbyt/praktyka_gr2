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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static classes.DBManager.select;

public class Przelew implements Initializable {
    public ImageView img_domek;
    public ImageView menugl_logo;

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
        File plik = new File("src/images/domekbialy.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_domek.setImage(zdjecie);

        File plik2 = new File("src/images/logobiale.png");
        Image zdjecie2 = new Image(plik2.toURI().toString());
        menugl_logo.setImage(zdjecie2);
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

        if(sprawdzCzyPuste()) {
            rachunek2 = Rachunek.wczytajRachunek_numer(przelew_numer.getText());
            if(sprawdzCzyPoprawne())
            {
                if (Rachunek.weryfikacjaSaldo(rachunek1, Float.parseFloat(przelew_kwota.getText()))) {
                    if (rachunek1.getWaluta() == rachunek2.getWaluta()) {
                        przelejSamaWaluta(rachunek1, rachunek2, Float.parseFloat(przelew_kwota.getText()));
                        wyczyscButton();
                    } else {
                        przelejInnaWaluta(rachunek1, rachunek2, Float.parseFloat(przelew_kwota.getText()));
                        wyczyscButton();
                    }
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
        listarachunekAkcja2();
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
        listarachunekAkcja2();
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


    public boolean sprawdzSymbol(String tekst){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tekst);
        boolean b = m.find();
        if (!b) return true;
        else {
            return false;
        }
    }

    public boolean sprawdzKwota(String tekst){
        try {
            float wartosc = Float.parseFloat(tekst);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean sprawdzCzyPoprawne(){
        if(!sprawdzSymbol(przelew_dane.getText()))
        {
            Powiadomienia.alertPrzelewZnakiSpecjalne("z danymi");
            return false;
        }
        if(!sprawdzSymbol(przelew_tytul.getText()))
        {
            Powiadomienia.alertPrzelewZnakiSpecjalne("z tytulem");
            return false;
        }
        if(!sprawdzSymbol(przelew_numer.getText()))
        {
            Powiadomienia.alertPrzelewZnakiSpecjalne("z numerem rachunku");
            return false;
        }
        if (przelew_numer.getText().length() > 26)
        {
            Powiadomienia.alertPrzelewDlugoscPola("z numerem rachunku");
            return false;
        }
        if (przelew_dane.getText().length() > 30)
        {
            Powiadomienia.alertPrzelewDlugoscPola("z danymi");
            return false;
        }
        if (przelew_tytul.getText().length() > 20)
        {
            Powiadomienia.alertPrzelewDlugoscPola("z tytulem");
            return false;
        }
        if (przelew_kwota.getText().length() > 10)
        {
            Powiadomienia.alertPrzelewDlugoscPola("z kwota");
            return false;
        }
        if (czyistniejeRachunek(przelew_numer.getText()) == false) {
            Powiadomienia.alertPrzelewWeryfikacjaRachunek();
            return false;
        }
        if (rachunek1.getId() == rachunek2.getId())
        {
            Powiadomienia.alertPrzelewWeryfikacjaRachunkow();
            return false;
        }
        return true;
    }

    public boolean sprawdzCzyPuste(){
        if(przelew_listarachunek.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertPrzelewWybierzRachunek();
            return false;
        }
        if(przelew_dane.getText().isEmpty())
        {
            Powiadomienia.alertPrzelewJestPuste("z danymi");
            return false;
        }
        if(przelew_tytul.getText().isEmpty())
        {
            Powiadomienia.alertPrzelewJestPuste("z tytulem");
            return false;
        }
        if(przelew_numer.getText().isEmpty())
        {
            Powiadomienia.alertPrzelewJestPuste("z numerem rachunku");
            return false;
        }
        if(przelew_kwota.getText().isEmpty())
        {
            Powiadomienia.alertPrzelewJestPuste("z kwota");
            return false;
        }
        if (przelew_numer.getText() == przelew_numerrachunku.getText()) {
            Powiadomienia.alertPrzelewWeryfikacjaRachunkow();
            return false;
        }
        if(!sprawdzKwota(przelew_kwota.getText())) {
            Powiadomienia.alertPrzelewZnakiSpecjalne("z kwota");
            return false;
        } else {
            float kwota = Float.parseFloat(przelew_kwota.getText());
            if (kwota <= 0) {
                Powiadomienia.alertPrzelewWeryfikacjaSaldo2();
                return false;
            }
        }
        return true;
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


    public void img_menugl_M(MouseEvent mouseEvent) throws Exception{
        ZmienOkno.zmienSceneimg("menugl.fxml", 1077, 534, img_domek);
    }
}

