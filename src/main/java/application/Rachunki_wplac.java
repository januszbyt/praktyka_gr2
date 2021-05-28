package application;

import classes.Rachunek;
import classes.Uzytkownik;
import classes.Waluta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static classes.DBManager.select;

public class Rachunki_wplac implements Initializable{

    @FXML
    private Button rachunkiwplac_wplac;
    @FXML
    private ComboBox listarachunek;
    @FXML
    private TextField rachunkiwplac_kwota;

    private String login;
    private Uzytkownik sesja;
    private Rachunek rachunek;
    private float kwota;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Rachunki.sesja;
        wypelnijListaRachunek(sesja.getId(), listarachunek);

    }




    public void wplacButton(ActionEvent event) {
        rachunek = Rachunek.wczytajRachunek_numer(String.valueOf(listarachunek.getValue()));
        if (rachunek.getNumer().isEmpty()) {
            Powiadomienia.alertWymianaWybierzRachunek();
        } else if (rachunkiwplac_kwota.getText().isEmpty()) {
            Powiadomienia.alertWymianaKwota();

        } else if(jestLiczba(rachunkiwplac_kwota.getText())) {
            kwota = Float.parseFloat(rachunkiwplac_kwota.getText());
            if(kwota > 0)
            {
                DecimalFormat df = new DecimalFormat("###.##");
                Rachunek.dodajSaldo(rachunek, kwota);
                Powiadomienia.alertWplacSukces(rachunek.getNumer(), Waluta.wczytajWaluta_id(rachunek.getWaluta()).getSkrot(), df.format(kwota));
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
            }
            else Powiadomienia.alertRachunkiKwota();
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

