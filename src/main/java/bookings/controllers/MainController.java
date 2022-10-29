package bookings.controllers;

import bookings.models.User;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    private User user;
    private Scene scene;
    private Stage stage;
    private BorderPane borderPane;


    public void setRoot(BorderPane root) {
        this.borderPane = root;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();
        stage.setMinHeight(700);
        stage.setMinWidth(1200);

        Platform.runLater(() -> {
            scene = new Scene(borderPane);
            stage.setScene(scene);
            stage.show();
        });

    }

}