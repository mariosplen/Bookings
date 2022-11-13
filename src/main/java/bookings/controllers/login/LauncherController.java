package bookings.controllers.login;

import bookings.util.Views;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LauncherController implements Initializable {

    @FXML
    private ProgressBar progressBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.seconds(3.5));
        delay.setOnFinished((ActionEvent) -> goToSignIn());
        delay.play();
    }


    private void goToSignIn() {
        try {
            // Initialize controller
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOGIN));
            Scene scene = new Scene(fxmlLoader.load());

            // Set scene to new window
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Close old window
            ((Stage) progressBar.getScene().getWindow()).close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
