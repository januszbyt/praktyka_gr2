package application;

import classes.DBManager;
import classes.Uzytkownik;
import classes.Zgloszenie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.*;

public class Adm_Zgloszenia implements Initializable {
    Uzytkownik sesja = Logowanie.zalogowany;
    public ImageView img_menugl;
    public TableView<Zgloszenie> zgloszeniaTabelka;

    public TableColumn<Zgloszenie, Integer> zgloszeniaId;
    public TableColumn<Zgloszenie, Date> zgloszeniaData;
    public TableColumn<Zgloszenie, String> zgloszeniaTytul;
    //public TableColumn<Zgloszenie, String> zgloszeniaTuser;
    //public TableColumn<Zgloszenie, String> zgloszeniaTadmin;
    public TableColumn<Zgloszenie, String> zgloszeniaStatus;
    public TableColumn<Zgloszenie, Integer> zgloszeniaUzytkownik;

    public TextField TrescTextFiled;
    public TextField TytulTextFiled;
    public Button NoweZgloszenie_btn;


    ObservableList<classes.Zgloszenie> oblist = observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);

        zgloszeniaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        zgloszeniaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        zgloszeniaTytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        //zgloszeniaTuser.setCellValueFactory(new PropertyValueFactory<>("tresc_user"));
        //zgloszeniaTadmin.setCellValueFactory(new PropertyValueFactory<>("tresc_admin"));
        zgloszeniaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        zgloszeniaUzytkownik.setCellValueFactory(new PropertyValueFactory<>("uzytkownik"));

        try {
            showZgloszenia();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void showZgloszenia() throws SQLException {
        zgloszeniaTabelka.getItems().clear();
        ResultSet result = DBManager.select("SELECT * FROM `zgloszenie`");
        while (result.next()) {

            oblist.add(new Zgloszenie(result.getInt("id"), result.getDate("data"), result.getString("tytul"), result.getString("tresc_user"), result.getString("tresc_admin"), result.getString("status"), result.getInt("uzytkownik")));

            zgloszeniaTabelka.setItems(oblist);
        }

    }

    public void utworzZgloszenie(){
        int id = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getId();
        DBManager.update("UPDATE `zgloszenie` SET `tresc_admin` = '"+TrescTextFiled.getText()+"', `status` = 'Odpowiedziano' WHERE `zgloszenie`.`id` = "+id+";");

    }


    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("menugl.fxml", 1080, 540, img_menugl);
    }

    public void NoweZgloszenie_click(ActionEvent actionEvent)  throws  Exception{

        utworzZgloszenie();
        showZgloszenia();
    }

    public void Zgloszenie_click(MouseEvent mouseEvent) {

        String status = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getStatus();
        int id = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getId();
        Date data = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getData();
        String tytul = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getTytul();
        String zapytanie = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getTresc_user();
        String odpowiedz = zgloszeniaTabelka.getSelectionModel().getSelectedItem().getTresc_admin();

        if (mouseEvent.getClickCount() == 2)
        {

            Powiadomienia.alertZgloszenieSzczegoly(status,id,data,tytul,zapytanie,odpowiedz);
        }
        TytulTextFiled.setText(tytul);
    }
}

