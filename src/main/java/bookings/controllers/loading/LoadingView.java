package bookings.controllers.loading;

import bookings.controllers.login.LoginView;
import bookings.util.Views;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingView implements Initializable {

    public Text title;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Scale down
        ScaleTransition scaleDown = new ScaleTransition(
                Duration.millis(0.1),
                title
        );
        scaleDown.setToX(0);
        scaleDown.setToY(0);

        // Scale up smooth
        ScaleTransition scaleUp = new ScaleTransition(
                Duration.seconds(2),
                title
        );
        scaleUp.setByX(1f);
        scaleUp.setByY(1f);


        TranslateTransition slideUp = new TranslateTransition(
                Duration.seconds(1),
                title
        );
        slideUp.setByY(-211);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                scaleDown,
                scaleUp,
                slideUp
        );
        sequentialTransition.play();


        PauseTransition delay = new PauseTransition(Duration.seconds(3));
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
