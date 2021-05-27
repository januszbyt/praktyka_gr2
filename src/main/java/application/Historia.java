package application;

import classes.DBManager;
import classes.Rachunek;
import classes.Uzytkownik;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Historia implements Initializable {
    @FXML
    private ComboBox historia_lista;
    @FXML
    private ListView<String> historia_text;
    @FXML
    private Button historia_wyszukaj_btn,historia_wyczysc_btn;
    @FXML
    private Label historia_rachunek,historia_rachunek2,historia_kwota,historia_opis;

    private Uzytkownik sesja;
    private int id;
    private String wybor;
    private String query = "select * from logi where uzytkownik=4";
    private ObservableList<String> lista = FXCollections.observableArrayList();
    public ImageView img_menugl;

    private  String t2="\t\t";
    private  String t3="\t\t\t";
    private String t4="\t\t\t\t";

    public void listaHistoriaAkcja(ActionEvent event) {
        wybor = (String) historia_lista.getValue();
        System.out.println(wybor);
        odswiezBaza(query);

    }

    public void wypelnijListe() {
        historia_lista.getItems().add("Wszystko");
        historia_lista.getItems().add("Przelewy przychodzace");
        historia_lista.getItems().add("Przelewy wychodzace");
        historia_lista.getItems().add("Przewalutowanie");
        historia_lista.getItems().add("Transfer srodkow");
        historia_lista.getItems().add("Logowanie");
    }

    public void odswiezBaza(String query) {

        try {
            ResultSet result = DBManager.select(query);

            String data;
            String typ;
            String rachunek;
            String rachunek2;
            String kwota;
            String tresc;

            switch (wybor) { // Rozpoczęcie switch case
                case "Wszystko":
                    historia_text.getItems().clear();
                    while (result.next()) {
                        data = result.getString("data");
                        typ = result.getString("typ");
                        rachunek = result.getString("rachunek");
                        rachunek2 = result.getString("rachunek2");
                        kwota = result.getString("kwota");
                        tresc = result.getString("tresc");

                        historia_text.getItems().add(data + "\t\t\t" + typ + "\t\t\t" + rachunek + "\t\t" + kwota + "\t\t" + tresc);
                        System.out.println(data + "\t\t\t" + typ + "\t\t\t" + rachunek + "\t\t" + kwota + "\t\t" + tresc);
                    }
                    break;
                case "Logowanie":
                    historia_text.getItems().clear();
                    result= DBManager.select("select * from logi where uzytkownik=4 and typ='Logowanie'");
                    while (result.next()) {
                        data = result.getString("data");
                        typ = result.getString("typ");
                        tresc = result.getString("tresc");

                        historia_opis.setLayoutX(385);
                        historia_opis.setLayoutY(20);
                        historia_rachunek.setVisible(false);
                        historia_kwota.setVisible(false);

                        historia_text.getItems().add(data + "\t\t\t" + typ + "\t\t\t\t\t\t" + tresc);
                    }
                    break;
                case "Przelewy przychodzace":
                    historia_text.getItems().clear();

                    historia_rachunek.setVisible(true);
                    historia_rachunek2.setVisible(true);
                    historia_kwota.setVisible(true);

                    historia_rachunek.setLayoutX(278);
                    historia_rachunek.setLayoutY(20);
                    historia_rachunek2.setLayoutX(501);
                    historia_rachunek2.setLayoutY(20);
                    historia_kwota.setLayoutX(714);
                    historia_kwota.setLayoutY(20);

                    result= DBManager.select("select * from logi where uzytkownik=5 and typ='Przelew przychodzacy'");
                    while (result.next()) {
                        data = result.getString("data");
                        typ = result.getString("typ");
                        rachunek = result.getString("rachunek");
                        rachunek2 = result.getString("rachunek2");
                        kwota = result.getString("kwota");
                        tresc = result.getString("tresc");

                        if (kwota.length()>=5)
                        {
                            historia_text.getItems().add(data + "\t" + typ + t2 + Rachunek.wczytajRachunek_id(Integer.parseInt(rachunek)).getNumer()
                                    + t2 + Rachunek.wczytajRachunek_id(Integer.parseInt(rachunek2)).getNumer() +t2 + kwota + t3 + tresc);
                        }
                        else
                        {
                            historia_text.getItems().add(data + "\t" + typ + "\t\t" + Rachunek.wczytajRachunek_id(Integer.parseInt(rachunek)).getNumer()
                                    + t2 + Rachunek.wczytajRachunek_id(Integer.parseInt(rachunek2)).getNumer() +t2 + kwota + t4 + tresc);
                        }

                    }
                    break;
                case "Przelewy wychodzace":
                    historia_text.getItems().clear();

                    historia_rachunek.setVisible(false);
                    historia_rachunek2.setVisible(true);
                    historia_kwota.setVisible(true);

                    historia_rachunek2.setLayoutX(278);
                    historia_rachunek2.setLayoutY(20);
                    historia_kwota.setLayoutX(540);
                    historia_kwota.setLayoutY(20);
                    historia_opis.setLayoutX(640);
                    historia_opis.setLayoutY(20);

                    break;

                case "Transfer srodkow":
                    historia_text.getItems().clear();
                    //kod
                    break;

                case "Przewalutowanie":
                    historia_text.getItems().clear();
                    //kod
                    break;

                default:
                    break;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijListe();
        // id=sesja.getId();
        historia_text.setItems(lista);
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
    }

    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("menugl.fxml", 1077, 534, img_menugl);
    }

}
