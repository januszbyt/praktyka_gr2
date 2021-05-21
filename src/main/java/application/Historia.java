package application;

import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Historia implements Initializable
{
    @FXML
    private ComboBox historia_lista;
    @FXML
    private TextField historia_wyszukaj_text;
    @FXML
    private ListView historia_text;
    @FXML
    private  Button historia_wyszukaj_btn;

    private Uzytkownik sesja;
    private int id;
    private String wybor;
    private String query="select * from logi where uzytkownik=12";

    public void listaHistoriaAkcja(ActionEvent event)
    {
        wybor=(String) historia_lista.getValue();
        System.out.println(wybor);
        odswiezBaza(query);

    }

    public void wypelnijListe()
    {
        historia_lista.getItems().add("Wszystko");
        historia_lista.getItems().add("Logowanie");
        historia_lista.getItems().add("Przelewy przychodzace");
        historia_lista.getItems().add("Przelewy wychodzace");
    }

    public void odswiezBaza(String zapytanie)
    {

        try
        {
            DBConnection DBpolaczenie= new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();


            ResultSet result = stat.executeQuery(query);

            String data;
            String typ;
            String rachunek;
            String kwota;
            String tresc;

            while(result.next())
            {
                data = result.getString("data");
                typ = result.getString("typ");
                rachunek = result.getString("rachunek");
                kwota = result.getString("kwota");
                tresc = result.getString("tresc");
            //historia_text.getItems().add(data+"\t\t\t"+typ+"\t\t\t");
                System.out.println(data+"\t\t\t"+typ+"\t\t\t"+rachunek+"\t\t"+kwota+"\t\t"+tresc);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijListe();
       // id=sesja.getId();

    }
}
