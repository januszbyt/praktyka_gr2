package application;

import classes.DBManager;
import classes.Hash;
import classes.Uzytkownik;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Ustawienia implements Initializable {


    public ImageView btn_logo;
    public ImageView img_domek;
    public Button zmien_haslo_btn;
    public PasswordField stare_haslo;
    public PasswordField nowe_haslo;
    public PasswordField potwierdz_nowe;

    String blad;
    public ResultSet result;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        btn_logo.setImage(zdjecie);

        File plik2 = new File("src/images/domekbialy.png");
        Image zdjecie2 = new Image(plik2.toURI().toString());
        img_domek.setImage(zdjecie2);

    }



    public void btn_domek(MouseEvent mouseEvent) {
        try {
            ZmienOkno.zmienSceneimg("menugl.fxml", 1080, 540, img_domek);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zmienhaslo_M(ActionEvent actionEvent) throws Exception{

        Uzytkownik sesja = Logowanie.zalogowany;
        result = DBManager.select("SELECT haslo from uzytkownik WHERE login='"+sesja.getLogin()+"'");
        result.next();

        sprawdz();
        Powiadomienia.zmiana_hasla(blad);

//        if (nowe_haslo.equals(potwierdz_nowe) && Hash.checkHash(stare_haslo.getText(),result.getString(1))){
//            DBManager.update("UPDATE `uzytkownik` SET `haslo` = '"+Hash.getHash(nowe_haslo.getText())+"' WHERE `uzytkownik`.`id` ="+sesja.getId());
//        }

//        if (Hash.checkHash(stare_haslo.getText(),result.getString(1))){
//            DBManager.update("UPDATE `uzytkownik` SET `haslo` = '"+Hash.getHash(nowe_haslo.getText())+"' WHERE `uzytkownik`.`id` ="+sesja.getId());
//        }

    }
    public void sprawdz(){

        if (stare_haslo.getText().isBlank() && nowe_haslo.getText().isBlank() && potwierdz_nowe.getText().isBlank()){
            blad = "Pola sa puste!";
        }else if (stare_haslo.getLength() > 0 && nowe_haslo.getText().isBlank() && potwierdz_nowe.getText().isBlank()){
            blad = "Nowe haslo oraz potwierdzenie hasla jest puste!";
        }else if (stare_haslo.getText().isBlank() && nowe_haslo.getLength() > 0 && potwierdz_nowe.getText().isBlank()){
            blad = "Stare haslo oraz potwierdzenie nowego jest puste!";
        }else if (stare_haslo.getText().isBlank() && nowe_haslo.getText().isBlank() && potwierdz_nowe.getLength() > 0){
            blad = "Stare haslo oraz nowe haslo jest puste!";
        }else if (stare_haslo.getLength() < 3 || stare_haslo.getLength() > 20){
            blad = "Wprowadz poprawna dlugosc hasla";
        }else if (nowe_haslo.getLength() < 3 || nowe_haslo.getLength() > 20){
            blad = "Wprowadz poprawna dlugosc nowego hasla";
        }else if (potwierdz_nowe.getLength() < 3 || potwierdz_nowe.getLength() > 20){
            blad = "Wprowadz poprawna dlugosc dla potwierdzenia nowego hasla";
        }
    }
}
