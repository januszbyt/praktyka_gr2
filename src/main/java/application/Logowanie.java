package application;

import classes.Uzytkownik;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Logowanie implements Initializable {

    // Zmienne ----------------

    public String login,haslo,login_field,haslo_field,blad_logowanie;

    //--------------------------
    public TextField logowanie_login;
    public PasswordField logowanie_haslo;
    public Button btn_zaloguj;
    public Button btn_wyczysc;
    public  Button btn_nmk;
    public Button btn_zamknij;
    public Label logowanie_haslo_alert;
    public Label logowanie_alert;
    @FXML
    private ImageView logowanie_logo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        logowanie_logo.setImage(zdjecie);
    }

    public void zaloguj_btn_M(ActionEvent actionEvent) throws Exception{

        // Login kaziks
        // Haslo kaziks
        login = "0";
        haslo = "0";
        zaloguj();
        sprawdz_logowanie();

        if (login.equals(login_field) && haslo.equals(haslo_field)) {
            Uzytkownik sesja = Uzytkownik.zaloguj(login);
            if (sesja.getWeryfikacja()==1){

                zmien_okno();
            }else {
                blad_logowanie = "\nNie zostales zweryfikowany przez Administratora!";
            }

        }
        Powiadomienia.alertLogowanie(blad_logowanie);
    }

    public void zaloguj(){
        login_field = logowanie_login.getText(); //Przypisanie loginu i hasla z textfield do zmiennych
        haslo_field = logowanie_haslo.getText();
        try{

            String query = "SELECT login,haslo FROM uzytkownik WHERE login='"+login_field+"' AND haslo='"+haslo_field+"'";


            DBConnection polaczenie = new DBConnection();
            Connection polacz = polaczenie.getConnection();

            Statement statement = polacz.createStatement();
            ResultSet result = statement.executeQuery(query);
            result.next();

            login = result.getString(1);
            haslo = result.getString(2);

            query="SELECT COUNT(id) from uzytkownik where login='"+login+"' and haslo='"+haslo+"'";
            result=statement.executeQuery(query);
            result.next();
            if(result.getInt(1)==1)
            {
                String id;
                query="SELECT id FROM uzytkownik where login ='"+login+"'";
                result=statement.executeQuery(query);
                result.next();
                id=result.getString(1);
                query="INSERT INTO logi (typ,tresc,uzytkownik) VALUES ('Logowanie','Udane logowanie','"+id+"')";
                statement.executeUpdate(query);
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void sprawdz_logowanie(){
        blad_logowanie = "\n"; //czyszczenie zmiennej


        if (login_field.isBlank() && haslo_field.isBlank()){ //Sprawdzam czy pola sa puste
            blad_logowanie += "Nie wypelniono zadnego pola!";
            //System.out.println(blad_logowanie); //Test
        }
        else if (login_field.isBlank() || haslo_field.isBlank()){ //Sprawdzam czy login lub haslo sa puste
            blad_logowanie += "Login lub haslo sa puste";
            //System.out.println(blad_logowanie); //Test
        }else if (!login.equals(login_field) || !haslo.equals(haslo_field)){

            blad_logowanie += "Wprowadzono nieprawidlowe dane!";
            //System.out.println(blad_logowanie); //Test
        }
    }

    public void zmien_okno() throws Exception{
        ZmienOkno.zmienScene("menugl.fxml",1077,534,btn_zaloguj);
    }

    public void wyczysc_btn_M(ActionEvent actionEvent) {
        wyczysc();
    }
    public void wyczysc(){
        logowanie_login.setText("");
        logowanie_haslo.setText("");
    }

    public void zamknij_btn_M(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void zmienOkno(ActionEvent actionEvent) throws Exception {
        ZmienOkno.zmienScene("rejestracja.fxml",650,776,btn_nmk);
    }
}