package bookings.controllers;

import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {


    @FXML
    private void onNewReservationClicked() throws IOException {

        // Initialize controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.FORM));
        Scene scene = new Scene(loader.load());

        // Set scene to new window
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }


}
