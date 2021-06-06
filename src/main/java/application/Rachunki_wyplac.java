package application;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
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


    public void wyplacButton(ActionEvent actionEvent) {

        if (listarachunek.getSelectionModel().isEmpty()) {
            Powiadomienia.alertWymianaWybierzRachunek();
        } else if (rachunkiwyplac_kwota.getText().isEmpty()) {
            Powiadomienia.alertWymianaKwota();
        } else if (jestLiczba(rachunkiwyplac_kwota.getText())) {
            rachunek = Rachunek.wczytajRachunek_numer(String.valueOf(listarachunek.getValue()));
            kwota = Float.parseFloat(rachunkiwyplac_kwota.getText());
            if (Rachunek.weryfikacjaSaldo(rachunek, kwota)) {
                DecimalFormat df = new DecimalFormat("###.##");
                Rachunek.usunSaldo(rachunek, kwota);

                DBManager.update(Logi.logWyplata(Integer.toString(sesja.getId()),Integer.toString(rachunek.getId()),Float.toString(kwota),
                        Waluta.wczytajWaluta_id(rachunek.getWaluta()).getSkrot()));

                Powiadomienia.alertWyplacSukces(rachunek.getNumer(), Waluta.wczytajWaluta_id(rachunek.getWaluta()).getSkrot(), df.format(kwota));
                Node node = (Node) actionEvent.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
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
            Powiadomienia.alertRachunkiKwota();
            return false;
        }

    }

}


