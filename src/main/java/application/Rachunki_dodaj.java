package application;

import classes.DBManager;
import classes.Rachunek;
import classes.Uzytkownik;
import classes.Waluta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import static classes.DBManager.select;

public class Rachunki_dodaj implements Initializable {

    @FXML
    private Button rachunkidodaj_dodaj;
    @FXML
    private ComboBox listawalut;
    @FXML
    private TextField rachunkidodaj_nazwa;

    private Uzytkownik sesja;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Rachunki.sesja;
        wypelnijListaWaluta(listawalut);

    }




    public void dodajButton(ActionEvent action){

        if(listawalut.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertRachunkiListaWalut();
        }
        else if(rachunkidodaj_nazwa.getText().length() > 0 && rachunkidodaj_nazwa.getText().length() <= 20)
        {
            boolean znaleziono = false;
            while(znaleziono == false)
            {
                String numer = generujRachunek(26);
                if(!czyistniejeRachunek(numer))
                {
                    int waluta = Waluta.wczytajWaluta_nazwa(listawalut.getSelectionModel().getSelectedItem().toString()).getId();
                    Rachunek rachunek = new Rachunek(0,rachunkidodaj_nazwa.getText(), numer, 0, sesja.getId(), waluta);;
                    String query = "INSERT INTO rachunek(nazwa, numer, saldo, uzytkownik, waluta) VALUES('"+rachunek.getNazwa()+"', '"+rachunek.getNumer()+"', "+rachunek.getSaldo()+", "+rachunek.getUzytkownik()+", "+rachunek.getWaluta()+");";
                    dodajRachunek(query);
                    znaleziono = true;
                    Powiadomienia.alertDodajSukces(rachunek.getNumer(), rachunek.getNazwa(), listawalut.getSelectionModel().getSelectedItem().toString());
                    Node node = (Node) action.getSource();
                    Stage thisStage = (Stage) node.getScene().getWindow();
                    thisStage.hide();
                }
            }
        }
        else Powiadomienia.alertRachunkiNazwa();


    }


    public void dodajRachunek(String query)
    {
            DBManager.update(query);

    }


    public static boolean czyistniejeRachunek(String numer){

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

    private String generujRachunek(int cyfry) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < cyfry; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }




    public void wypelnijListaWaluta( ComboBox listawalut) {
        try {
            ResultSet result = select("SELECT nazwa FROM waluta;");
            String nazwa;
            while (result.next()) {
                nazwa = result.getString("nazwa");
                listawalut.getItems().add(nazwa);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

