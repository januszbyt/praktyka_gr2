package application;

import classes.Rachunek;
import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Przelew implements Initializable
{

    //deklaracja zmiennych z Scen Buildera

    @FXML
    private TextField przelew_dane, przelew_numer, przelew_kwota, przelew_tytul;
    @FXML
    private Button btn_potwierdz, btn_wyczysc;
    @FXML
    private ComboBox przelew_listarachunek;
    @FXML
    private Label przelew_danenazwa, przelew_danewaluta;

    public Uzytkownik sesja;
    public Rachunek rachunek;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Uzytkownik.zaloguj("pkazako");
        WypelnijListaRachunek(sesja.getId());
    }


    public void listarachunekAkcja(ActionEvent event)
    {
        rachunek = Rachunek.wczytajRachunek_numer(String.valueOf(przelew_listarachunek.getValue()));
        przelew_danenazwa.setText("Nazwa: "+rachunek.getNazwa());

    }



    public void potwierdzButton()
    {





    }



    public void wyczyscButton()   // Guzik wyczyszczenia pola
    {
        przelew_dane.setText("");
        przelew_numer.setText("");
        przelew_kwota.setText("");
        przelew_tytul.setText("");
    }


    public void WypelnijDaneRachunek(Rachunek rachunek){
        przelew_danenazwa.setText(rachunek.getNazwa());
        przelew_danewaluta.setText(rachunek.getNazwa());

    }


    public void WypelnijListaRachunek(int uzytkownik)
    {
        try
        {
            DBConnection DBpolaczenie= new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();


            ResultSet result = stat.executeQuery("SELECT numer FROM rachunek WHERE uzytkownik = "+uzytkownik+";");
            String numer;
            while(result.next())
            {
                numer = result.getString("numer");
                przelew_listarachunek.getItems().add(numer);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




    public void sprawdzRachunek()
    {


    }


}
