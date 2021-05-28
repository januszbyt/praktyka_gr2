package application;

import classes.Zgloszenie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.text.TabableView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Zgloszenia implements Initializable {
    public ImageView img_menugl;
    public TableView<TabZgloszenia> tabelka;
    public TableColumn<TabZgloszenia, String> tabelatemat;
    public TableColumn<TabZgloszenia, Integer> tabelaid;

    ObservableList<TabZgloszenia> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);

        tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabelatemat.setCellValueFactory(new PropertyValueFactory<>("tytul"));


    }
    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        //ZmienOkno.zmienSceneimg("menugl.fxml", 1077, 534, img_menugl);
        xd();
    }

    public void xd() {
        oblist.add(new TabZgloszenia("dUsadsadaPA", "XDDDD", "SADAD", "AWDA", 12, 2));
        tabelka.setItems(oblist);

    }

}
