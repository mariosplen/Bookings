package bookings.controllers.loading;

import bookings.controllers.login.LoginView;
import bookings.util.Views;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingView implements Initializable {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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

            // Pass stage to controller
            LoginView loginView = fxmlLoader.getController();
            loginView.setStage(stage);

            stage.setScene(scene);

            // Resizable set back to true again
            stage.setResizable(true);

            // Set min stage dimensions
            stage.setMinHeight(600);
            stage.setMinWidth(800);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
