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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Adm_Zarzadzanie_waluta_edytuj implements Initializable {

    @FXML
    private Button walutaedytuj_edytuj;

    @FXML
    private TextField walutaedytuj_nazwa;

    private static Waluta waluta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       waluta = Adm_Zarzadzanie_waluta.waluta;
       walutaedytuj_nazwa.setText(waluta.getNazwa());
    }


    public void edytujButton(ActionEvent action){

        if(walutaedytuj_nazwa.getText().length() > 0 && walutaedytuj_nazwa.getText().length() <= 20 && sprawdzSymbol(walutaedytuj_nazwa.getText()) )
        {
            String query = "UPDATE waluta set nazwa = '" + walutaedytuj_nazwa.getText() + "' WHERE id = " + waluta.getId();
            edytujWalute(query);
            Powiadomienia.alertWalutaEdytujSukces(walutaedytuj_nazwa.getText());
            Node node = (Node) action.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        }
        else Powiadomienia.alertWalutaNazwa();


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


    public void edytujWalute(String query)
    {
            DBManager.update(query);

    }


}

