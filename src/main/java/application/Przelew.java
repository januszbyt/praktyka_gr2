package application;

import classes.Rachunek;
import classes.Uzytkownik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Przelew
{

    //deklaracja zmiennych z Scen Buildera

    @FXML
    private TextField przelew_dane, przelew_numer, przelew_kwota, przelew_tytul;
    @FXML
    private Button btn_potwierdz, btn_wyczysc;



    public void potwierdzButton()
    {
        tempSesja();
        sprawdzRachunek();




    }



    public void wyczyscButton()   // Guzik wyczyszczenia pola
    {
        przelew_dane.setText("");
        przelew_numer.setText("");
        przelew_kwota.setText("");
        przelew_tytul.setText("");

    }



    public void tempSesja()
    {
        Uzytkownik sesja = new Uzytkownik(4, "Piotr", "Kozak", "95080941574", "pkazako", "", "kazakop2010@wp.pl", "u", 1);



    }



    public void sprawdzRachunek()
    {


    }


}
