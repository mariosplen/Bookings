package bookings.controllers;

import bookings.util.Views;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LauncherController implements Initializable {


    public ProgressBar progressBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PauseTransition delay = new PauseTransition(Duration.seconds(3.5));
        delay.setOnFinished((ActionEvent) -> goToSignIn());
        delay.play();
    }

    private void goToSignIn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOGIN));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

//            JMetro jMetro = new JMetro(Style.LIGHT);
//            jMetro.setScene(scene);

            stage.setScene(scene);
            stage.show();
            stage.requestFocus();
            ((Stage) progressBar.getScene().getWindow()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
