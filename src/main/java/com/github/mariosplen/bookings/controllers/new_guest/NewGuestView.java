package com.github.mariosplen.bookings.controllers.new_guest;

import com.github.mariosplen.bookings.models.GuestDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class NewGuestView {
    @FXML
    private TextField nameTF, phoneTF, emailTF;
    @FXML
    private Text errorMsg;

    public void onSaveClicked() throws SQLException, ClassNotFoundException {
        errorMsg.setText("");
        if (nameTF.getText().isBlank() || phoneTF.getText().isBlank() || emailTF.getText().isBlank()) {
            errorMsg.setText("Wrong Parameters");
            return;
        }


        GuestDAO.addGuest(nameTF.getText(), phoneTF.getText(), emailTF.getText());
        errorMsg.setText("New Guest Added!");
        nameTF.setText("");
        phoneTF.setText("");
        emailTF.setText("");
    }
}
