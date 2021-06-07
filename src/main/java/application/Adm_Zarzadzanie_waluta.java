package application;

import classes.Kurs;
import classes.Rachunek;
import classes.Waluta;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static classes.DBManager.select;
import static classes.Kurs.getKurs;

public class Adm_Zarzadzanie_waluta implements Initializable {
    public ImageView img_menugl;
    public ComboBox lista_waluta;
    public Waluta waluta;
    public Label admin_daneskrot, admin_nazwawaluty, admin_danewartosc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijLista();
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
    }

    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("adm_menugl.fxml", 1077, 534, img_menugl);
    }

    public void listaWalutaAkcja(ActionEvent event)
    {
        waluta = Waluta.wczytajWaluta_nazwa(lista_waluta.getSelectionModel().getSelectedItem().toString());
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



    public void wypelnijLista() {
        try {
            ResultSet result = select("SELECT nazwa FROM waluta");
            while (result.next()) {
                lista_waluta.getItems().add(result.getString("nazwa"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}