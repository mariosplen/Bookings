package bookings.controllers;

import bookings.models.User;
import bookings.util.Views;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    private User user;
    @FXML
    private BorderPane mainPane;


    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));
        try {
            mainPane.setCenter(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Some delay for user to be received
        Platform.runLater(() -> {

        });
    }

    @FXML
    private void onHomeClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));
        mainPane.setCenter(loader.load());
    }

    @FXML
    private void onRoomsClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.ROOMS));
        mainPane.setCenter(loader.load());
    }

    @FXML
    private void onBooksClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.BOOKS));
        mainPane.setCenter(loader.load());
    }

    @FXML
    private void onCalendarClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.CALENDAR));
        mainPane.setCenter(loader.load());
    }
}