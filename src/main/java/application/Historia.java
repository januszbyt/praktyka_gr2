package application;

import classes.DBManager;
import classes.Uzytkownik;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import application.Logowanie;

import javax.xml.transform.Result;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Historia implements Initializable {
    public TextField historia_wyszukaj_text;
    public TableView table_view;
    public ComboBox historia_lista;
    public Uzytkownik sesja = Logowanie.zalogowany;
    public ImageView img_menugl;

    private ObservableList<ObservableList> lista = FXCollections.observableArrayList();

    String wybor;
    public void listaHistoriaAkcja(ActionEvent actionEvent) throws Exception{
        odswiezTableView();

    }
    public static void xyz(String zapytanko,TableView tabelka) throws SQLException {
        ResultSet result;
        ObservableList<ObservableList> lista = FXCollections.observableArrayList();
        result = DBManager.select(zapytanko);

        tabelka.getColumns().clear();
        tabelka.getItems().clear();

        for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {

            final int j = i;
            TableColumn col = new TableColumn(result.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tabelka.getColumns().addAll(col);

        }

        while (result.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                row.add(result.getString(i));
            }

            lista.add(row);
            tabelka.setItems(lista);

        }
    }
    public void odswiezTableView() throws SQLException {
        wybor = (String) historia_lista.getValue();

        switch (wybor) {
            case "Przelewy przychodzace":

                xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek nadawcy'," +
                        "(SELECT numer from rachunek where id=rachunek2) AS 'Rachunek odbiorcy',kwota," +
                        "tresc from logi where uzytkownik="+sesja.getId()+" and typ ='Przelew przychodzacy'",table_view);
                break;
            case "Przelewy wychodzace":

                xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek docelowy'" +
                        ",kwota,tresc from logi where uzytkownik="+sesja.getId()+" and typ ='Przelew wychodzacy'",table_view);
                break;
            case "Wplaty":

                xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek'" +
                        ",tresc from logi where uzytkownik="+sesja.getId()+" and typ='Wplata'",table_view);
                break;
            case "Wyplaty":

                xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek'," +
                        "tresc from logi where uzytkownik="+sesja.getId()+" and typ='Wyplata'",table_view);
                break;
            case "Przewalutowanie":

                xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) " +
                        "AS 'Rachunek nadawcy',(SELECT numer from rachunek where id=rachunek2) AS 'Rachunek odbiorcy'," +
                        "kwota,tresc from logi where uzytkownik="+sesja.getId()+" and typ='Przewalutowanie'",table_view);
                break;
            case "Transfer srodkow":

                xyz("select data,typ,(SELECT numer from rachunek where id=rachunek) AS 'Rachunek nadawcy'," +
                        "(SELECT numer from rachunek where id=rachunek2) AS 'Rachunek odbiorcy'," +
                        "kwota,tresc from logi where uzytkownik="+sesja.getId()+" and typ ='Transfer srodkow'", table_view);
                break;
            case "Logowanie":

                xyz("select data,typ,tresc from logi where uzytkownik="+sesja.getId()+" and typ='Logowanie'",table_view);
                break;

        }
    }
    public void wypelnijListe(){
        historia_lista.getItems().add("Przelewy przychodzace");
        historia_lista.getItems().add("Przelewy wychodzace");
        historia_lista.getItems().add("Wplaty");
        historia_lista.getItems().add("Wyplaty");
        historia_lista.getItems().add("Przewalutowanie");
        historia_lista.getItems().add("Transfer srodkow");
        historia_lista.getItems().add("Logowanie");
    }


    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("menugl.fxml", 1080, 540, img_menugl);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijListe();
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
    }
}
