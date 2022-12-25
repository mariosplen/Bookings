package bookings.controllers.loading;

import bookings.controllers.login.LoginView;
import bookings.util.Views;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoadingView implements Initializable {


    @FXML
    private Text shadow1, shadow2, title;
    @FXML
    private MediaView intro;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Instant Scale down
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(0.1), title);
        scaleDown.setToX(0);
        scaleDown.setToY(0);

        // Scale up
        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(2), title);
        scaleUp.setByX(1f);
        scaleUp.setByY(1f);

        // Slide up
        TranslateTransition slideUp = new TranslateTransition(Duration.seconds(1), title);
        slideUp.setByY(-211);

        // Fade in the shadows
        TranslateTransition firstShadow = new TranslateTransition(Duration.seconds(0.1), shadow1);
        firstShadow.setByY(+25);
        TranslateTransition secondShadow = new TranslateTransition(Duration.seconds(0.1), shadow2);
        secondShadow.setByY(+50);

        // Play the transitions in sequence
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(scaleDown, scaleUp, slideUp);
        sequentialTransition.setOnFinished(actionEvent -> {
            shadow1.setVisible(true);
            shadow2.setVisible(true);
            firstShadow.play();
            secondShadow.play();
        });

        // Set up the intro video
        MediaPlayer player = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/intro/intro.mp4")).toExternalForm()));
        player.setAutoPlay(true);
        intro.setMediaPlayer(player);

        // Show the title when the video ends
        player.setOnEndOfMedia(() -> {
            title.setVisible(true);
            sequentialTransition.play();
        });

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished((ActionEvent) -> goToSignIn());
        delay.play();


    }

    private void goToSignIn() {
        try {
            // Load the login view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOGIN));
            Scene scene = new Scene(fxmlLoader.load());

            // Get the login view controller and pass the stage to it
            LoginView loginView = fxmlLoader.getController();
            loginView.setStage(stage);

            // Set the scene to the login view
            stage.setScene(scene);

            // Set the stage to be resizable again
            stage.setResizable(true);

            // Set the minimum dimensions for the stage
            stage.setMinHeight(600);
            stage.setMinWidth(800);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
