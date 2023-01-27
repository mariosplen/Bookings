package com.github.mariosplen.bookings.controllers.recovery;

import com.github.mariosplen.bookings.models.SettingsDAO;
import com.github.mariosplen.bookings.models.User;
import com.github.mariosplen.bookings.models.UserDAO;
import com.github.mariosplen.bookings.util.Nav;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RecoverView {

    @FXML
    private Text showPasswordTxt;
    @FXML
    private Label errorMsg;
    @FXML
    private PasswordField hotelKey;
    @FXML
    private TextField usernameTF;

    public void onBackButtonClicked() throws IOException {
        Nav.toLogin();
    }

    public void onShowBtnClicked() throws SQLException, ClassNotFoundException {
        errorMsg.setVisible(false);
        showPasswordTxt.setVisible(false);

        // Check if hotel key is correct
        if (hotelKey.getText().equals(SettingsDAO.getHotelKey())) {
            // Check if user exists
            for (User user : UserDAO.getUsers()) {
                if (user.username().equals(usernameTF.getText())) {
                    errorMsg.setVisible(false);
                    showPasswordTxt.setText(showPasswordTxt.getText() + user.password());
                    showPasswordTxt.setVisible(true);
                    return;
                }
            }
        }
        // If hotel key is incorrect or user does not exist, show error message
        errorMsg.setVisible(true);
    }
}
