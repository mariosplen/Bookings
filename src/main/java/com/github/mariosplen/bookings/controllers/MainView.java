package com.github.mariosplen.bookings.controllers;

import com.github.mariosplen.bookings.models.User;
import com.github.mariosplen.bookings.util.Nav;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private final User user;
    public HBox mainViewUsernameHBox;
    public Text mainViewUsername;
    public BorderPane contentPane;

    @FXML
    private Button backBtn;

    public MainView(User user, Node content) {
        this.user = user;
        Platform.runLater(() -> contentPane.setCenter(content));

    }

    public Button getBackBtn() {
        return backBtn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (user != null) {
            mainViewUsername.setText(user.username().toUpperCase());
            backBtn.setOnAction(event -> {
                try {
                    Nav.toHome();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            mainViewUsernameHBox.setVisible(false);
            backBtn.setOnAction(event -> {
                try {
                    Nav.user = null;
                    Nav.toLogin();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
