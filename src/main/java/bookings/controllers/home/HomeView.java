package bookings.controllers.home;

import bookings.controllers.login.LoginView;
import bookings.controllers.reservation.ReservationView;
import bookings.util.ShowButton;
import bookings.util.Views;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeView implements Initializable {
    private BorderPane mainPane;

    private ShowButton showButton;

    public void setShowButton(ShowButton showButton) {
        this.showButton = showButton;
    }

    public void navToUsers() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.USERS));
        mainPane.setCenter(loader.load());
        showButton.showBtn();
    }

    public void navToReservation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.RESERVATION));
        mainPane.setCenter(loader.load());
        ReservationView reservationView = loader.getController();

        reservationView.setMainPane(mainPane);

        showButton.showBtn();

    }

    public void navToBooks() {
    }

    public void navToGuests() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.GUESTS));
        mainPane.setCenter(loader.load());
        showButton.showBtn();
    }

    public void navToCalendar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.CALENDAR));
        mainPane.setCenter(loader.load());
        showButton.showBtn();
    }

    public void navToRooms() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.NEWGUEST));
        mainPane.setCenter(loader.load());
        showButton.showBtn();
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onLogoutClicked(MouseEvent mouseEvent) throws IOException {
        // Load the login view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOGIN));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the login view controller and pass the stage to it
        LoginView loginView = fxmlLoader.getController();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        loginView.setStage(stage);

        // Set the scene to the login view
        stage.setScene(scene);


    }
}
