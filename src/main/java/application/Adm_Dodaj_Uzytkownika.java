package application;

import classes.DBManager;
import classes.Hash;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Adm_Dodaj_Uzytkownika implements Initializable {
    //deklaracja labelów, przycisków itp. z Scen Builder'a

    @FXML
    private ImageView rejestracja_logo;

    @FXML
    private TextField rejestracja_imie, rejestracja_nazwisko, rejestracja_pesel, rejestracja_login, rejestracja_email;
    @FXML
    private PasswordField rejestracja_haslo, rejestracja_haslo2;
    @FXML
    private Button btn_zarejestruj, btn_wyczysc, btn_wroc;

    // zmienna przechowywujaca informacje ktore pola nie spelniaja wymagan
    private String blad = "";

    private static final EmailValidator validator = EmailValidator.getInstance();

//METODY

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        rejestracja_logo.setImage(zdjecie);

    }

    // metoda czyszczaca wszystkie pola
    public void wyczyscButton() {
        rejestracja_imie.setText("");
        rejestracja_nazwisko.setText("");
        rejestracja_pesel.setText("");
        rejestracja_login.setText("");
        rejestracja_haslo.setText("");
        rejestracja_haslo2.setText("");
        rejestracja_email.setText("");

    }



    //metoda sprawdzajaca i dopisujaca do zmiennej pola ktore sa niepoprawne
    public void sprawdzRejestracja() {
        //zmienna jest czyszczona
        blad = "\n";

        // pole imie
        if (rejestracja_imie.getLength() < 3 || rejestracja_imie.getLength() > 20) {
            blad += "Imie \n";
        }

        // pole nazwisko
        if (rejestracja_nazwisko.getLength() < 3 || rejestracja_nazwisko.getLength() > 20) {
            blad += "Nazwisko \n";
        }

        if (weryfikujPesel(rejestracja_pesel.getText()) == false) {
            blad += "Pesel \n";
        }

        // pole login
        if (rejestracja_login.getLength() < 5 || rejestracja_login.getLength() > 20) {
            blad += "Login \n";
        }

        // pole haslo
        if (rejestracja_haslo.getLength() < 5 || rejestracja_haslo.getLength() > 20 ||
                rejestracja_haslo2.getLength() < 5 || rejestracja_haslo2.getLength() > 20) {
            blad += "Haslo \n";

        }
        //sprawdzenie czy hasla sa identyczne
        if (!rejestracja_haslo.getText().equals(rejestracja_haslo2.getText())) {

            blad += "Hasla nie sa identyczne \n";
        }

        // pole email
        if (weryfikujEmail(rejestracja_email.getText()) == false || rejestracja_email.getLength() > 50) {
            blad += "Email\n";
        }

    }

    // metoda weryfikujaca pesel
    public boolean weryfikujPesel(String pesel) {
        if (pesel.length() == 11) {

            int pierwszaCyfra = Integer.parseInt(pesel.substring(0, 1));
            int drugaCyfra = Integer.parseInt(pesel.substring(1, 2));
            int trzeciaCyfra = Integer.parseInt(pesel.substring(2, 3));
            int czwartaCyfra = Integer.parseInt(pesel.substring(3, 4));
            int piataCyfra = Integer.parseInt(pesel.substring(4, 5));
            int szostaCyfra = Integer.parseInt(pesel.substring(5, 6));
            int siodmaCyfra = Integer.parseInt(pesel.substring(6, 7));
            int osmaCyfra = Integer.parseInt(pesel.substring(7, 8));
            int dziewiataCyfra = Integer.parseInt(pesel.substring(8, 9));
            int dziesiataCyfra = Integer.parseInt(pesel.substring(9, 10));
            int jedenastaCyfra = Integer.parseInt(pesel.substring(10, 11));

            int check = 1 * pierwszaCyfra + 3 * drugaCyfra + 7 * trzeciaCyfra
                    + 9 * czwartaCyfra + 1 * piataCyfra + 3 * szostaCyfra + 7
                    * siodmaCyfra + 9 * osmaCyfra + 1 * dziewiataCyfra + 3
                    * dziesiataCyfra;
            int lastNumber = check % 10;
            int controlNumber = 10 - lastNumber;

            if (controlNumber == jedenastaCyfra || (controlNumber == 10 && jedenastaCyfra == 0)) {
                return true;
            }

        }
        return false;
    }

//metoda weryfikujaca email

    public static boolean weryfikujEmail(final String email) {
        return validator.isValid(email);
    }

    public void rejestracjaUzytkownik() throws Exception {
        String imie = rejestracja_imie.getText();
        String nazwisko = rejestracja_nazwisko.getText();
        String pesel = rejestracja_pesel.getText();
        String login = rejestracja_login.getText();
        String haslo = rejestracja_haslo.getText();
        String email = rejestracja_email.getText();

        // zapytanie sprawdzajace czy login lub email jest zajety
        String query = "SELECT COUNT(id) from uzytkownik where login='" + rejestracja_login.getText() + "' OR mail = '" + rejestracja_email.getText() + " ' ";

        String bazaSql = "INSERT INTO uzytkownik(imie,nazwisko,pesel,login,haslo,mail,rola,weryfikacja) VALUES('";
        String wpisaneSql = imie + "','" + nazwisko + "','" + pesel + "','" + login + "','" + Hash.getHash(haslo) + "','" + email + "','" + "U" + "','" + "0" + "')";
        String kodSql = bazaSql + wpisaneSql;

        ResultSet queryResult = DBManager.select(query);

        queryResult.next();

        if (blad.isBlank()) {
            if (queryResult.getInt(1) == 0) {
                DBManager.update(kodSql);
                Powiadomienia.alertRejestracja(blad);
                wyczyscButton();
                ZmienOkno.zmienScene("adm_zarzadzanie_uzytkownikami.fxml", 650, 552, btn_wroc);
            } else {
                Powiadomienia.alertRejestracjaZajety();
            }
        } else {
            Powiadomienia.alertRejestracja(blad);
        }

    }

    public void zarejestrujButton(ActionEvent actionEvent) throws Exception {
        sprawdzRejestracja();
        rejestracjaUzytkownik();

    }

    public void powrot(ActionEvent actionEvent) throws Exception {
        ZmienOkno.zmienScene("adm_zarzadzanie_uzytkownikami.fxml", 750, 483, btn_wroc);
    }

}
