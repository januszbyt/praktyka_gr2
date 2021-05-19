package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ZmienOkno {

    public static void zmienScene( String fxml,int x, int y, Button button  ) throws Exception {
        Parent root = FXMLLoader.load(ZmienOkno.class.getResource(fxml));
        Stage window = (Stage)button.getScene().getWindow();
        window.setScene(new Scene(root, x, y));
    }
}
