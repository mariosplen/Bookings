package bookings.controllers;

import bookings.models.User;
import bookings.util.Views;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Pane pane;

    private User user;
    private Scene scene;
    private Stage stage;
    private BorderPane root;


    public void setRoot(Pane root) {
        this.root = (BorderPane) root;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();
        stage.setResizable(false);

        // Some delay for the parameters from LoginController to be received
        Platform.runLater(() -> {
            scene = new Scene(root);
            stage.setScene(scene);
            try {
                // Load the home-view's root Pane to main-view's BorderPane Center
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));
                root.setCenter(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
        });
    }

    @FXML
    public void onHomeClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));
        root.setCenter(loader.load());
    }

    @FXML
    public void onRoomsClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.ROOMS));
        root.setCenter(loader.load());
    }

    @FXML
    public void onBooksClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));
        root.setCenter(loader.load());
    }

    @FXML
    public void onCalendarClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.CALENDAR));
        root.setCenter(loader.load());
    }
}