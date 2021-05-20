package application;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Menugl implements Initializable {
    public ImageView menugl_logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        menugl_logo.setImage(zdjecie);
    }
}
