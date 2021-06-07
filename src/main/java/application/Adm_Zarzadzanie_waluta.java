package application;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static classes.DBManager.select;
import static classes.Kurs.getKurs;

public class Adm_Zarzadzanie_waluta implements Initializable {
    public ImageView img_menugl;
    public ComboBox lista_waluta;
    public Label admin_daneskrot, admin_nazwawaluty, admin_danewartosc;
    public TextField waluta_skrot, waluta_nazwa;

    public static Waluta waluta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijLista();
        //wczytanie obrazu
        File plik = new File("src/images/domekbialy.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
    }

    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("adm_menugl.fxml", 1080, 540, img_menugl);
    }


    public void dodajButton() {

        if (sprawdzczyPuste()) {
            if (sprawdzczyPoprawne()) {
                if (sprawdzWalute(waluta_skrot.getText())) {
                    if(sprawdzczyIstnieje()){
                        double kurs = wczytajKurs(waluta_skrot.getText());
                        String query = "INSERT INTO waluta(nazwa, skrot, wartosc) VALUES('"+waluta_nazwa.getText()+"','"+waluta_skrot.getText()+"',"+kurs+");";
                        DBManager.update(query);
                        Powiadomienia.alertWalutaDodajSukces(waluta_nazwa.getText(), waluta_skrot.getText());
                        lista_waluta.getItems().clear();
                        wypelnijLista();
                    }
                }
            }
        }
    }


    public void edytujButton(){
        if (lista_waluta.getSelectionModel().isEmpty()) {
            Powiadomienia.alertAdminWalutaWybor();
        }
        else {
            waluta = Waluta.wczytajWaluta_skrot(waluta_skrot.getText());

            Stage anotherStage = new Stage();

            try {
                FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("adm_zarzadzaj_waluta_edytuj.fxml"));
                Parent anotherRoot = anotherLoader.load();
                Scene anotherScene = new Scene(anotherRoot, 270, 180);
                anotherStage.setScene(anotherScene);
                anotherStage.setTitle("Edycja waluty");
                anotherStage.showAndWait();
                listaWalutaAkcja2();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void usunButton() {
        if (lista_waluta.getSelectionModel().isEmpty()) {
            Powiadomienia.alertAdminWalutaWybor();
        }
        else{
                waluta = Waluta.wczytajWaluta_nazwa(String.valueOf(lista_waluta.getValue()));
                if (sprawdzRachunki(waluta.getId())) {
                    DBManager.delete("DELETE FROM waluta WHERE id = " + waluta.getId());
                    Powiadomienia.alertAdminWalutaUsuwanie(waluta.getNazwa());
                }
            }
        lista_waluta.getItems().clear();
        wypelnijLista();
    }





    public void listaWalutaAkcja(ActionEvent event)
    {
        waluta = Waluta.wczytajWaluta_skrot(String.valueOf(lista_waluta.getValue()));
        admin_daneskrot.setText(waluta.getSkrot());
        admin_nazwawaluty.setText(waluta.getNazwa());
        if(admin_daneskrot.getText().equals("PLN"))
        {
            admin_danewartosc.setText("1.0000");
        }
        else {
            try {
                admin_danewartosc.setText(String.valueOf(getKurs(waluta.getSkrot())));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void listaWalutaAkcja2()
    {
        waluta = Waluta.wczytajWaluta_skrot(String.valueOf(lista_waluta.getValue()));
        admin_daneskrot.setText(waluta.getSkrot());
        admin_nazwawaluty.setText(waluta.getNazwa());
        if(admin_daneskrot.getText().equals("PLN"))
        {
            admin_danewartosc.setText("1.0000");
        }
        else {
            try {
                admin_danewartosc.setText(String.valueOf(getKurs(waluta.getSkrot())));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sprawdzRachunki(int id){
        ResultSet result = select("SELECT * FROM rachunek where waluta = "+id);
        try {
            if (!result.isBeforeFirst() ) {
                return true;
            }
            else{
                Powiadomienia.alertAdminWalutaUsuwaniePorazka();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Powiadomienia.alertAdminWalutaUsuwaniePorazka();
            return false;
        }

    }

    public boolean sprawdzWalute(String skrot){
        ResultSet result = select("SELECT * FROM waluta where skrot = '"+skrot+"'");
        try {
            if (!result.isBeforeFirst() ) {
                return true;
            }
            else{
                Powiadomienia.alertWalutaIstnieje();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Powiadomienia.alertWalutaIstnieje();
            return false;
        }

    }

    public boolean sprawdzczyPuste(){
        if(waluta_skrot.getText().isEmpty()){
            Powiadomienia.alertWalutaJestPuste("ze skrotem");
            return false;
        }
        if(waluta_nazwa.getText().isEmpty()){
            Powiadomienia.alertWalutaJestPuste("z nazwa");
            return false;
        }
        return true;
    }

    public boolean sprawdzczyPoprawne(){
        if(!sprawdzSymbol(waluta_nazwa.getText()))
        {
            Powiadomienia.alertWalutaZnakiSpecjalne("z nazwa");
            return false;
        }
        if(!sprawdzSymbol(waluta_skrot.getText()))
        {
            Powiadomienia.alertWalutaZnakiSpecjalne("z skrotem");
            return false;
        }
       return true;
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

    public boolean sprawdzczyIstnieje(){
        try {
            double test = Kurs.getKurs(waluta_skrot.getText());
        } catch (IOException e) {
            e.printStackTrace();
            Powiadomienia.alertWalutaNieIstnieje();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Powiadomienia.alertWalutaNieIstnieje();
            return false;
        }
        return true;
    }

    public double wczytajKurs(String skrot) {
        try {
            return Kurs.getKurs(skrot);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void wypelnijLista() {
        try {
            ResultSet result = select("SELECT skrot FROM waluta");
            while (result.next()) {
                lista_waluta.getItems().add(result.getString("skrot"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}