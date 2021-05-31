package application;


import classes.DBManager;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
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
    public ImageView img_domek;

    public ImageView img_menugl;


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
    public static Rachunek rachunek2;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sesja = Logowanie.zalogowany;
        wypelnijListaRachunek(sesja.getId(), rachunki_listarachunek);
        //wczytanie obrazu
        File plik = new File("src/images/domekbialy.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_domek.setImage(zdjecie);

        File plik2 = new File("src/images/logobiale.png");
        Image zdjecie2 = new Image(plik2.toURI().toString());
        img_menugl.setImage(zdjecie2);
    }



    public void usunButton(){
        rachunek = Rachunek.wczytajRachunek_numer(String.valueOf(rachunki_listarachunek.getValue()));
        if(rachunki_listarachunek.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertRachunkiNumer();
        }else if(sprawdzSaldo(rachunek)){
            String query = "DELETE FROM rachunek WHERE ID = "+rachunek.getId();
            usunRachunek(query);
            Powiadomienia.alertUsunSukces(rachunek.getNumer());
            rachunki_listarachunek.getItems().clear();
            wypelnijListaRachunek(sesja.getId(), rachunki_listarachunek);
            listarachunekAkcja2();
        }
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
            listarachunekAkcja2();

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
            listarachunekAkcja2();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void edytujButton(){

        rachunek2 = Rachunek.wczytajRachunek_numer(String.valueOf(rachunki_listarachunek.getValue()));
        if(rachunki_listarachunek.getSelectionModel().isEmpty())
        {
            Powiadomienia.alertRachunkiNumer();
        }else {

            Stage anotherStage = new Stage();

            try {
                FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("rachunki_edytuj.fxml"));
                Parent anotherRoot = anotherLoader.load();
                Scene anotherScene = new Scene(anotherRoot, 270, 180);
                anotherStage.setScene(anotherScene);
                anotherStage.setTitle("Edycja rachunku");
                anotherStage.showAndWait();
                listarachunekAkcja2();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
            rachunki_listarachunek.getItems().clear();
            wypelnijListaRachunek(sesja.getId(), rachunki_listarachunek);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void usunRachunek(String query)
    {
        DBManager.delete(query);

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

    public static boolean sprawdzSaldo(Rachunek rachunek){

        if(rachunek.getSaldo() != 0){
            Powiadomienia.alertRachunkiSaldo();
            return false;
        } else return true;

    }


    public void listarachunekAkcja2()
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

    public void img_menugl_M(MouseEvent mouseEvent) throws Exception{
        ZmienOkno.zmienSceneimg("menugl.fxml", 1077, 534, img_domek);
    }
}
