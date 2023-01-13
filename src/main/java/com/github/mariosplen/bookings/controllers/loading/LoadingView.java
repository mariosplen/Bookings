package com.github.mariosplen.bookings.controllers.loading;

import com.github.mariosplen.bookings.util.Nav;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Start the animations
        playAnimations();

        // Animations end at approximately 7.5 seconds, then continue
        PauseTransition delay = new PauseTransition(Duration.seconds(7.5));
        delay.setOnFinished((ActionEvent) -> {
            try {
                goToSignIn();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        delay.play();
    }

    private void goToSignIn() throws IOException {

        Nav.toLogin();

    }

    private void playAnimations() {
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
        MediaPlayer player = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/github/mariosplen/bookings/media/intro/intro.mp4")).toExternalForm()));

        player.setAutoPlay(true);


        intro.setMediaPlayer(player);

        // Show the title when the video ends
        player.setOnEndOfMedia(() -> {
            title.setVisible(true);
            sequentialTransition.play();
        });
    }
}
