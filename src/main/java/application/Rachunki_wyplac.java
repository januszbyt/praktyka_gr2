package application;

import classes.Rachunek;
import classes.Uzytkownik;
import classes.Waluta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static classes.DBManager.select;

public class Rachunki_wyplac implements Initializable {

    @FXML
    private Button rachunkiwyplac_wyplac;
    @FXML
    private ComboBox listarachunek;
    @FXML
    private TextField rachunkiwyplac_kwota;

    private String login;
    private Uzytkownik sesja;
    private Rachunek rachunek;
    private float kwota;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Rachunki.sesja;
        wypelnijListaRachunek(sesja.getId(), listarachunek);

    }




    public void wyplacButton() {
        rachunek = Rachunek.wczytajRachunek_numer(String.valueOf(listarachunek.getValue()));
        if (rachunek.getNumer().isEmpty()) {
            Powiadomienia.alertWymianaWybierzRachunek();
        } else if (rachunkiwyplac_kwota.getText().isEmpty()) {
            Powiadomienia.alertWymianaKwota();

        } else if(jestLiczba(rachunkiwyplac_kwota.getText())) {
            kwota = Float.parseFloat(rachunkiwyplac_kwota.getText());
            if(Rachunek.weryfikacjaSaldo(rachunek, kwota))
            {
                DecimalFormat df = new DecimalFormat("###.##");
                Rachunek.usunSaldo(rachunek, kwota);
                Powiadomienia.alertWyplacSukces(rachunek.getNumer(), Waluta.wczytajWaluta_id(rachunek.getWaluta()).getSkrot(), df.format(kwota));
            }
        }
    }



    public void wypelnijListaRachunek(int uzytkownik, ComboBox listarachunek) {
        try {
            ResultSet result = select("SELECT numer FROM rachunek WHERE uzytkownik = " + uzytkownik + ";");
            String numer;
            while (result.next()) {
                numer = result.getString("numer");
                listarachunek.getItems().add(numer);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean jestLiczba(String tekst) {
        try {
            Float wartosc = Float.parseFloat(tekst);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }



}
