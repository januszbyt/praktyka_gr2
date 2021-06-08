package application;

import classes.DBManager;
import classes.Rachunek;
import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static classes.DBManager.select;
import static classes.DBManager.update;

public class Adm_Zarzadzanie_uzytkownikami implements Initializable {
    public ImageView img_menugl;
    public Button adm_zarejestruj, adm_zweryfikuj, adm_zablokuj, adm_usun;
    public ComboBox lista_uzytkownik1, lista_uzytkownik2, lista_uzytkownik3;
    public Uzytkownik uzytkownik;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        //wczytanie obrazu
        File plik = new File("src/images/domekbialy.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
        wypelnijLista1();
        wypelnijLista2();
    }
    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("adm_menugl.fxml", 1080, 540, img_menugl);
    }


    public void rejestracjaButton(){ {
        try {
            ZmienOkno.zmienScene("adm_dodaj_uzytkownika.fxml", 650, 776, adm_zarejestruj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }

    public void zweryfikujButton(){
        if(lista_uzytkownik1.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertAdminWybor();
        }
        else {
            uzytkownik = Uzytkownik.wczytajUzytkownik_login(lista_uzytkownik1.getSelectionModel().getSelectedItem().toString());
            if (uzytkownik.getWeryfikacja() == 0) {
                update("UPDATE uzytkownik SET weryfikacja = 1 WHERE id = " + uzytkownik.getId());
                Powiadomienia.alertAdminWeryfikacja(uzytkownik.getLogin());
            } else {
                Powiadomienia.alertAdminWeryfikacjaPorazka();
            }
            lista_uzytkownik1.getItems().clear();
            lista_uzytkownik2.getItems().clear();
            lista_uzytkownik3.getItems().clear();
            wypelnijLista1();
            wypelnijLista2();
        }
    }

    public void zablokujButton(){
        if(lista_uzytkownik2.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertAdminWybor();
        }
        else {
            uzytkownik = Uzytkownik.wczytajUzytkownik_login(lista_uzytkownik2.getSelectionModel().getSelectedItem().toString());
            if (uzytkownik.getWeryfikacja() == 1) {
                update("UPDATE uzytkownik SET weryfikacja = 0 WHERE id = " + uzytkownik.getId());
                Powiadomienia.alertAdminBlokowanie(uzytkownik.getLogin());
            } else {
                Powiadomienia.alertAdminBlokowaniePorazka();
            }
            lista_uzytkownik1.getItems().clear();
            lista_uzytkownik2.getItems().clear();
            lista_uzytkownik3.getItems().clear();
            wypelnijLista1();
            wypelnijLista2();
        }
    }

    public void usunButton(){
        if(lista_uzytkownik3.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertAdminWybor();
        }
        else {
            uzytkownik = Uzytkownik.wczytajUzytkownik_login(lista_uzytkownik3.getSelectionModel().getSelectedItem().toString());
            if (sprawdzRachunki(uzytkownik.getId())) {
                DBManager.delete("DELETE FROM uzytkownik WHERE id = "+uzytkownik.getId());
                Powiadomienia.alertAdminUsuwanie(uzytkownik.getLogin());
            }
        }
        lista_uzytkownik1.getItems().clear();
        lista_uzytkownik2.getItems().clear();
        lista_uzytkownik3.getItems().clear();
        wypelnijLista1();
        wypelnijLista2();
    }


    public boolean sprawdzRachunki(int id){
        ResultSet result = select("SELECT * FROM rachunek where uzytkownik = "+id);
        try {
            if (!result.isBeforeFirst() ) {
                return true;
            }
            else{
                Powiadomienia.alertAdminUsuwaniePorazka();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Powiadomienia.alertAdminUsuwaniePorazka();
            return false;
        }

    }


    public void wypelnijLista1()
    {
        try
        {
            ResultSet result = select("SELECT login FROM uzytkownik WHERE weryfikacja = 0 AND rola = 'U'");
            String numer;
            while(result.next())
            {
                lista_uzytkownik1.getItems().add(result.getString("login"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void wypelnijLista2()
    {
        try
        {
            ResultSet result = select("SELECT login FROM uzytkownik WHERE weryfikacja = 1 AND rola = 'U'");
            String numer;
            while(result.next())
            {
                lista_uzytkownik2.getItems().add(result.getString("login"));
                lista_uzytkownik3.getItems().add(result.getString("login"));

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }




}
