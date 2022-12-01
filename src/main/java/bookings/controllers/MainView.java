package bookings.controllers;

import bookings.controllers.home.HomeView;
import bookings.models.User;
import bookings.util.Views;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    @FXML
    private BorderPane mainPane;
    private Stage stage;
    private User user;


    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            navToHome();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Some delay for user to be received
        Platform.runLater(() -> {

        });
    }

    @FXML
    private void navToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));

        // Show HomeView
        mainPane.setCenter(loader.load());

        // Pass mainPane to controller
        HomeView homeView = loader.getController();
        homeView.setMainPane(mainPane);

    }

}