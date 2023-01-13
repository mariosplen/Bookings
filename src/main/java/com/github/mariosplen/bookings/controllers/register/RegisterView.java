package com.github.mariosplen.bookings.controllers.register;

import com.github.mariosplen.bookings.models.SettingsDAO;
import com.github.mariosplen.bookings.models.User;
import com.github.mariosplen.bookings.models.UserDAO;
import com.github.mariosplen.bookings.util.Nav;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterView {
    @FXML
    private Text errorMsg;
    @FXML
    private PasswordField hotelKeyTF, passwordTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private CheckBox editUsersCB;
    @FXML
    private CheckBox editGuestsCB;
    @FXML
    private CheckBox basicCB;


    @FXML
    private void onRegisterBtnClicked() throws IOException, SQLException, ClassNotFoundException {

        errorMsg.setVisible(false);

        // Check if username, password, and hotel key are not blank
        // Check if hotel key is correct
        if (usernameTF.getText().isBlank() || passwordTF.getText().isBlank() || !hotelKeyTF.getText().equals(SettingsDAO.getHotelKey())) {
            errorMsg.setText("Please enter a username, password, and hotel key (ask the owner for the hotel key)");
            errorMsg.setVisible(true);
            return;
        }

        // Check if username is already taken
        for (User user : UserDAO.getUsers()) {
            if (user.username().equals(usernameTF.getText())) {
                errorMsg.setText("A user with this username already exists");
                errorMsg.setVisible(true);
                return;
            }
        }

        // If no errors, add new user to the database
        if (!errorMsg.isVisible()) {
            UserDAO.addUser(
                    usernameTF.getText(),
                    passwordTF.getText(),
                    basicCB.isSelected(),
                    editGuestsCB.isSelected(),
                    editUsersCB.isSelected()
            );
            Nav.toLogin();
        }
    }
}
