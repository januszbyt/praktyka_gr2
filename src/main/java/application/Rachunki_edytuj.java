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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static classes.DBManager.select;

public class Rachunki_edytuj implements Initializable {

    @FXML
    private Button rachunkiedytuj_edytuj;

    @FXML
    private TextField rachunkiedytuj_nazwa;

    private Uzytkownik sesja;
    private Rachunek rachunek;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Rachunki.sesja;
        rachunek = Rachunki.rachunek2;
        rachunkiedytuj_nazwa.setText(rachunek.getNazwa());
    }




    public void edytujButton(ActionEvent action){

        if(rachunkiedytuj_nazwa.getText().length() > 0 && rachunkiedytuj_nazwa.getText().length() <= 20 && sprawdzSymbol(rachunkiedytuj_nazwa.getText()) )
        {
            String query = "UPDATE rachunek set nazwa = '" + rachunkiedytuj_nazwa.getText() + "' WHERE id = " + rachunek.getId();
            edytujRachunek(query);
            Powiadomienia.alertEdytujSukces(rachunek.getNumer(), rachunkiedytuj_nazwa.getText());
            Node node = (Node) action.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        }
        else Powiadomienia.alertRachunkiNazwa();


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


    public void edytujRachunek(String query)
    {
            DBManager.update(query);

    }


}

