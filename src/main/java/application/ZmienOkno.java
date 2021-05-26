package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ZmienOkno {

    /*metoda przyjmuje jako parametry :
    -nazwe fxml ktory ma sie otworzyc
    -rozmiar nowego okna
    -nazwe dowolnego przycisku z aktualnego okna
     */

    public static void zmienScene(String fxml, int x, int y, Button button) throws Exception {
        Parent root = FXMLLoader.load(ZmienOkno.class.getResource(fxml));
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root, x, y));
    }
    public static void zmienSceneimg(String fxml, int x, int y, ImageView button) throws Exception {
        Parent root = FXMLLoader.load(ZmienOkno.class.getResource(fxml));
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root, x, y));
    }
}
