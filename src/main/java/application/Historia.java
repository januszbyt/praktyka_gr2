package application;

import classes.DBManager;
import classes.Uzytkownik;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import application.Logowanie;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Historia implements Initializable {
    public TextField historia_wyszukaj_text;
    public TableView table_view;
    public ComboBox historia_lista;
    public Uzytkownik sesja = Logowanie.zalogowany;
    public static ResultSet result,result_wyszukiwanie;

    public static ObservableList<ObservableList> lista = FXCollections.observableArrayList();
//    public static ObservableList<String> row = FXCollections.observableArrayList();

    String wybor;
    public void listaHistoriaAkcja(ActionEvent actionEvent) throws Exception{
        odswiezTableView();
    }
    public static void xyz(String zapytanko,TableView tabelka) throws SQLException {

//        ObservableList<ObservableList> lista = FXCollections.observableArrayList();
        result = DBManager.select(zapytanko);
        result_wyszukiwanie = DBManager.select(zapytanko);

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

    public void wyszukaj(TableView tabelka) throws Exception{
        String text = historia_wyszukaj_text.getText();
//        ObservableList<ObservableList> lista = FXCollections.observableArrayList();

        tabelka.getItems().clear();

        ObservableList<String> row = FXCollections.observableArrayList();
        while (result_wyszukiwanie.next()) {


            if (result_wyszukiwanie.getString("tresc").contains(text)){
                for (int i = 1; i <= result_wyszukiwanie.getMetaData().getColumnCount(); i++) {
                    row.add(result.getString(i));
                }
                System.out.println("Udalo sie");
                }else {
                    System.out.println("Nie udalo sie");
                }

            lista.add(row);
            tabelka.setItems(lista);

            }



        }


    public void img_menugl_M(MouseEvent mouseEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypelnijListe();
    }

    public void search(ActionEvent actionEvent) throws Exception{
        wyszukaj(table_view);
    }
}
