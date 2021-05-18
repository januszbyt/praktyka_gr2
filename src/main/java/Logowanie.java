import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Logowanie implements Initializable {

    @FXML
    private ImageView logowanie_logo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        logowanie_logo.setImage(zdjecie);
    }
}