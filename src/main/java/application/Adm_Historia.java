package application;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);

        wypelnijLista1();
        wypelnijLista2();


    }
}
