package bookings.controllers;

import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Label msg;
    @FXML
    private Hyperlink registerHL;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;

    @FXML
    private void onLoginBtnClicked() throws IOException, SQLException, ClassNotFoundException {

        User user = UserDAO.login(usernameTF.getText(), passwordTF.getText());

        if (user == null) {
            msg.setText("Wrong Password Or Username");
            return;
        }

        // Initialize controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.MAIN));
        Scene scene = new Scene(loader.load());

        // Pass parameters to controller
        MainController mainController = loader.getController();
        mainController.setUser(user);

        // Set scene to new window
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        // Close current window
        ((Stage) usernameTF.getScene().getWindow()).close();
    }
}