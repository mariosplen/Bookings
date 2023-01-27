package com.github.mariosplen.bookings.controllers.login;

import com.github.mariosplen.bookings.models.User;
import com.github.mariosplen.bookings.models.UserDAO;
import com.github.mariosplen.bookings.util.Nav;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class LoginView {

    @FXML
    private PasswordField passwordTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private Text errorMsg;

    @FXML
    private void onLoginBtnClicked() throws SQLException, ClassNotFoundException, IOException {

        // Try to log the user in
        User user = UserDAO.login(usernameTF.getText(), passwordTF.getText());

        // If the login fails, display an error message
        if (user == null) {
            errorMsg.setVisible(true);
            return;
        }
        Nav.user = user;
        Nav.toHome();

    }

    @FXML
    private void onForgotPassHLClicked() throws IOException {
        Nav.toRecovery();
    }

    @FXML
    private void onRegisterHLClicked() throws IOException {
        Nav.toRegister();
    }

}
