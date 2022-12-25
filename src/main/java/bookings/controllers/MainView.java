package bookings.controllers;

import bookings.controllers.home.HomeView;
import bookings.models.User;
import bookings.util.Views;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    public Text usernameTxt;
    public ImageView imageForInvisibleTopBarSizing;
    @FXML
    private Button backBtn;
    @FXML
    private BorderPane mainPane;
    private User user;
    private Stage stage;

    @FXML
    private void onBackButtonClicked() throws IOException {
        navToHome();
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            navToHome();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(() -> usernameTxt.setText(user.username().toUpperCase()));
    }

    @FXML
    private void navToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));

        // Show HomeView
        mainPane.setCenter(loader.load());

        // Pass mainPane to controller
        HomeView homeView = loader.getController();
        homeView.setMainPane(mainPane);
        // Pass showBackBtn() to controller
        homeView.setShowButton(this::showBackBtn);
        hideBackBtn();


    }

    public void hideBackBtn() {
        backBtn.setVisible(false);
        // hide the image so that the top bar shrinks
        imageForInvisibleTopBarSizing.setFitHeight(1);
        imageForInvisibleTopBarSizing.setFitWidth(1);
    }

    public void showBackBtn() {
        backBtn.setVisible(true);
        // make the Image normal size
        imageForInvisibleTopBarSizing.setFitHeight(75);
        imageForInvisibleTopBarSizing.setFitWidth(93);
    }

}

