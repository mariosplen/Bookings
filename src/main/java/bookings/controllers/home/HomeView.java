package bookings.controllers.home;

import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeView {


    private BorderPane mainPane;

    @FXML
    private void newReservation() throws IOException {

        // Initialize controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.BOOK_FORM));
        Scene scene = new Scene(loader.load());

        // Set scene to new window
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }

    @FXML
    private void navToRooms() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.ROOMS));
        mainPane.setCenter(loader.load());
    }

    @FXML
    private void navToBooks() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.BOOKS));
        mainPane.setCenter(loader.load());
    }

    @FXML
    private void navToCalendar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.CALENDAR));
        mainPane.setCenter(loader.load());
    }

    public void navToGuests() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.GUESTS));
        mainPane.setCenter(loader.load());
    }

    public void navToUsers() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.GUESTS));
        mainPane.setCenter(loader.load());
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
}
