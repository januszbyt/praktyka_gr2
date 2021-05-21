package application;

import classes.Rachunek;
import classes.Uzytkownik;
import classes.Waluta;
import classes.Kurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.beanutils.converters.StringArrayConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Wymiana implements Initializable {

    //deklaracja zmiennych z Scen Buildera

    @FXML
    private TextField wymiana_kwota;
    @FXML
    private Button btn_potwierdz, btn_wyczysc;
    @FXML
    private ComboBox wymiana_listarachunek1, wymiana_listarachunek2;
    @FXML
    private Label wymiana_numerrachunku, wymiana_danenazwa, wymiana_danewaluta, wymiana_danesaldo;
    @FXML
    private Label wymiana_numerrachunku2, wymiana_danenazwa2, wymiana_danewaluta2, wymiana_danesaldo2, wymiana_podglad;

    public Uzytkownik sesja;
    public Rachunek rachunek1, rachunek2;
    public Waluta waluta1, waluta2;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Uzytkownik.zaloguj("pkazako");
        wypelnijListaRachunek(sesja.getId(), wymiana_listarachunek1);
        wypelnijListaRachunek(sesja.getId(), wymiana_listarachunek2);
    }

    public void potwierdzButton(){
        if(wymiana_numerrachunku.getText().isEmpty() || wymiana_numerrachunku2.getText().isEmpty() )
        {
            Powiadomienia.alertWymianaWybierzRachunek();
        }
        else if(wymiana_kwota.getText().isEmpty())
        {
           Powiadomienia.alertWymianaKwota();

        }
        else if(weryfikacjaRachunkow(rachunek1, rachunek2) == true)
            {
                if(Rachunek.weryfikacjaSaldo(rachunek1, Float.parseFloat(wymiana_kwota.getText()))) {
                    if (rachunek1.getWaluta() == rachunek2.getWaluta())
                    {
                        wymienSamaWaluta(rachunek1, rachunek2, Float.parseFloat(wymiana_kwota.getText()));
                        wyczyscButton();
                    }
                    else {
                        wymienInnaWaluta(rachunek1, rachunek2, Float.parseFloat(wymiana_kwota.getText()));
                        wyczyscButton();

                    }
                }

            }


    }


    public void wyczyscButton()   // Guzik wyczyszczenia pola
    {
        wymiana_kwota.setText("");
        wymiana_podglad.setText("");
    }


    public boolean weryfikacjaRachunkow(Rachunek rachunek1, Rachunek rachunek2){

        if(rachunek1.getId() == rachunek2.getId())
        {
            Powiadomienia.alertWymianaWeryfikacjaRachunkow();
            return false;
        }
        else return true;

    }


    public void listarachunek1Akcja(ActionEvent event)
    {
        rachunek1 = Rachunek.wczytajRachunek_numer(String.valueOf(wymiana_listarachunek1.getValue()));
        waluta1 = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        wymiana_numerrachunku.setText(rachunek1.getNumer());
        wymiana_danenazwa.setText(rachunek1.getNazwa());
        wymiana_danewaluta.setText(waluta1.getSkrot());
        wymiana_danesaldo.setText(String.valueOf(rachunek1.getSaldo()));
        aktualizujPodglad();

    }

    public void listarachunek2Akcja(ActionEvent event)
    {
        rachunek2 = Rachunek.wczytajRachunek_numer(String.valueOf(wymiana_listarachunek2.getValue()));
        waluta2 = Waluta.wczytajWaluta_id(rachunek2.getWaluta());
        wymiana_numerrachunku2.setText(rachunek2.getNumer());
        wymiana_danenazwa2.setText(rachunek2.getNazwa());
        wymiana_danewaluta2.setText(waluta2.getSkrot());
        wymiana_danesaldo2.setText(String.valueOf(rachunek2.getSaldo()));
        aktualizujPodglad();

    }


    public void aktualizujPodglad() {

        if (!wymiana_danewaluta.getText().isEmpty() && !wymiana_danewaluta2.getText().isEmpty() && !wymiana_kwota.getText().isEmpty())
        {
            DecimalFormat df = new DecimalFormat("###.##");
            String skrot1 = wymiana_danewaluta.getText();
            String skrot2 = wymiana_danewaluta2.getText();
        double kurs1, kurs2;

        if(jestLiczba(wymiana_kwota.getText()) == true)
        {
            double kwota = Double.parseDouble(wymiana_kwota.getText());
            if (kwota >= 0) {
                if (!skrot1.equals("PLN")) {
                    kurs1 = sprawdzWaluta2(skrot1);
                } else kurs1 = 1.0;

                if (!skrot2.equals("PLN")) {
                    kurs2 = sprawdzWaluta2(skrot2);
                } else kurs2 = 1.0;

                Double przelicznik = kurs1 / kurs2;
                kwota = kwota * przelicznik;

                wymiana_podglad.setText(df.format(kwota) + " " + skrot2);

            }
        }
        }
        }


    public void wypelnijListaRachunek(int uzytkownik, ComboBox listarachunek)
    {
        try
        {
            DBConnection DBpolaczenie= new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();


            ResultSet result = stat.executeQuery("SELECT numer FROM rachunek WHERE uzytkownik = "+uzytkownik+";");
            String numer;
            while(result.next())
            {
                numer = result.getString("numer");
                listarachunek.getItems().add(numer);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void wymienSamaWaluta(Rachunek rachunek1, Rachunek rachunek2, float kwota){
        Rachunek.usunSaldo(rachunek1, kwota);
        Rachunek.dodajSaldo(rachunek2, kwota);
        Waluta waluta = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        DecimalFormat df = new DecimalFormat("###.##");
        Powiadomienia.alertWymianaSukces(rachunek1.getNumer(), rachunek2.getNumer(), df.format(kwota), df.format(kwota), waluta.getSkrot(), waluta.getSkrot());
    }

    public void wymienInnaWaluta(Rachunek rachunek1, Rachunek rachunek2, float kwota)
    {
        double kurs1 = 0;
        double kurs2 = 0;
        Waluta waluta1 = Waluta.wczytajWaluta_id(rachunek1.getWaluta());
        Waluta waluta2 = Waluta.wczytajWaluta_id(rachunek2.getWaluta());
        if(!waluta1.getSkrot().equals("PLN"))
        {
            kurs1 = sprawdzWaluta(waluta1);
        }else kurs1 = 1.0;

        if(!waluta2.getSkrot().equals("PLN"))
        {
            kurs2 = sprawdzWaluta(waluta2);
        }else kurs2 = 1.0;

        Double przelicznik = kurs1 / kurs2;
        float kwota2 = kwota;
        kwota2 = kwota2 * przelicznik.floatValue();

        Rachunek.usunSaldo(rachunek1, kwota);
        Rachunek.dodajSaldo(rachunek2, kwota2);
        DecimalFormat df = new DecimalFormat("###.##");

        Powiadomienia.alertWymianaSukces(rachunek1.getNumer(), rachunek2.getNumer(), df.format(kwota), df.format(kwota2), waluta1.getSkrot(), waluta2.getSkrot());
    }

    public boolean jestLiczba(String tekst)
    {
        try {
            int wartosc = Integer.parseInt(tekst);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public double sprawdzWaluta(Waluta waluta)
    {
        try {
            return Kurs.getKurs(waluta.getSkrot());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double sprawdzWaluta2(String skrot)
    {
        try {
            return Kurs.getKurs(skrot);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

}