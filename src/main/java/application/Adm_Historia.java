package application;

import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static classes.DBManager.select;

public class Adm_Historia implements Initializable {

public ImageView img_menugl;
public ComboBox adm_listaHistoriaUzytkownik,adm_listaHistoriaAkcja;
public Button adm_historia_wyszukaj_btn,adm_historia_wyczysc_btn;
public TableView table_view;

private String wybor1,wybor2;

    public void wypelnijLista1()
    {
        try
        {
            ResultSet result = select("SELECT login FROM uzytkownik order by login ");

            while(result.next())
            {
                adm_listaHistoriaUzytkownik.getItems().add(result.getString("login"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void wypelnijLista2(){
        adm_listaHistoriaAkcja.getItems().add("Przelewy przychodzace");
        adm_listaHistoriaAkcja.getItems().add("Przelewy wychodzace");
        adm_listaHistoriaAkcja.getItems().add("Wplaty");
        adm_listaHistoriaAkcja.getItems().add("Wyplaty");
        adm_listaHistoriaAkcja.getItems().add("Przewalutowanie");
        adm_listaHistoriaAkcja.getItems().add("Transfer srodkow");
        adm_listaHistoriaAkcja.getItems().add("Logowanie");
    }

    public void odswiezTableView(String wyborUzytkownika, String wyborHistorii, TableView tabelka) throws SQLException {

    int id= Uzytkownik.wczytajUzytkownik_login(wyborUzytkownika).getId();
        switch (wyborHistorii) {
            case "Przelewy przychodzace":

                Historia.xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek nadawcy'," +
                        "(SELECT numer from rachunek where id=rachunek2) AS 'Rachunek odbiorcy',kwota," +
                        "tresc from logi where uzytkownik="+id+" and typ ='Przelew przychodzacy'",tabelka);
                break;
            case "Przelewy wychodzace":

                Historia.xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek docelowy'" +
                        ",kwota,tresc from logi where uzytkownik="+id+" and typ ='Przelew wychodzacy'",tabelka);
                break;
            case "Wplaty":

                Historia.xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek'" +
                        ",tresc from logi where uzytkownik="+id+" and typ='Wplata'",tabelka);
                break;
            case "Wyplaty":

                Historia.xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek'," +
                        "tresc from logi where uzytkownik="+id+" and typ='Wyplata'",tabelka);
                break;
            case "Przewalutowanie":

                Historia.xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) " +
                        "AS 'Rachunek nadawcy',(SELECT numer from rachunek where id=rachunek2) AS 'Rachunek odbiorcy'," +
                        "kwota,tresc from logi where uzytkownik="+id+" and typ='Przewalutowanie'",tabelka);
                break;
            case "Transfer srodkow":

                Historia.xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek nadawcy'," +
                        "(SELECT numer from rachunek where id=rachunek2) AS 'Rachunek odbiorcy'," +
                        "kwota,tresc from logi where uzytkownik="+id+" and typ ='Transfer srodkow'", tabelka);
                break;
            case "Logowanie":

                Historia.xyz("select data,typ,tresc from logi where uzytkownik="+id+" and typ='Logowanie'",tabelka);
                break;

        }
    }


    public void wyszukaj(ActionEvent event) throws Exception {

            wybor1 = (String) adm_listaHistoriaUzytkownik.getValue();
            wybor2 = (String) adm_listaHistoriaAkcja.getValue();


            if (wybor1==null||wybor1.length()==0||wybor2==null||wybor2.length()==0)
            {
               Powiadomienia.adm_historia();
            }
            else {
                odswiezTableView(wybor1,wybor2,table_view);
            }


    }
    public void adm_wyczysc_historia(ActionEvent event) {

        adm_listaHistoriaUzytkownik.getSelectionModel().clearSelection();
        adm_listaHistoriaAkcja.getSelectionModel().clearSelection();

        table_view.getColumns().clear();
        table_view.getItems().clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);

        wypelnijLista1();
        wypelnijLista2();


    }

    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("adm_menugl.fxml", 1077, 534, img_menugl);
    }
}
