package application;


import classes.Rachunek;
import classes.Uzytkownik;
import classes.Waluta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static classes.DBManager.select;

public class Rachunki implements Initializable {


    @FXML
    private Button rachunki_dodaj, rachunki_edytuj, rachunki_usun, rachunki_wplac, rachunki_wyplac;
    @FXML
    private ComboBox rachunki_listarachunek;
    @FXML
    private Label rachunki_numerrachunku, rachunki_danenazwa, rachunki_danewaluta, rachunki_danesaldo;

    public static Uzytkownik sesja;
    public Rachunek rachunek;
    public Waluta waluta;
    public static String parameters;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Uzytkownik.zaloguj("pkazako");
        wypelnijListaRachunek(sesja.getId(), rachunki_listarachunek);

    }



    public void usunButton(){


    }


    public void wplacButton(){
        Stage anotherStage = new Stage();

        try {
            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("rachunki_wplac.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot, 270, 180);
            anotherStage.setScene(anotherScene);
            anotherStage.setTitle("Wplacanie");
            anotherStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void wyplacButton(){
        Stage anotherStage = new Stage();

        try {
            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("rachunki_wyplac.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot, 270, 180);
            anotherStage.setScene(anotherScene);
            anotherStage.setTitle("Wyplacanie");
            anotherStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void edytujButton(){



    }

    public void dodajButton(){
        Stage anotherStage = new Stage();

        try {
            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("rachunki_dodaj.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot, 270, 180);
            anotherStage.setScene(anotherScene);
            anotherStage.setTitle("Dodawanie rachunku");
            anotherStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void listarachunekAkcja(ActionEvent event)
    {
        rachunek = Rachunek.wczytajRachunek_numer(String.valueOf(rachunki_listarachunek.getValue()));
        waluta = Waluta.wczytajWaluta_id(rachunek.getWaluta());
        rachunki_numerrachunku.setText(rachunek.getNumer());
        rachunki_danenazwa.setText(rachunek.getNazwa());
        rachunki_danewaluta.setText(waluta.getSkrot());
        rachunki_danesaldo.setText(String.valueOf(rachunek.getSaldo()));

    }


    public void wypelnijListaRachunek(int uzytkownik, ComboBox listarachunek)
    {
        try
        {
            ResultSet result = select("SELECT numer FROM rachunek WHERE uzytkownik = "+uzytkownik+";");
            String numer;
            while(result.next())
            {
                numer = result.getString("numer");
                listarachunek.getItems().add(numer);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }








}
