package bookings.controllers.guests;

import bookings.models.GuestDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class FormController {
    @FXML
    private TextField nameTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private CheckBox isGroupCB;

    @FXML
    private void onSubmitClicked() throws SQLException, ClassNotFoundException {
        String name = nameTF.getText();
        String phone = phoneTF.getText();
        String email = emailTF.getText();
        Boolean isGroup = isGroupCB.isSelected();

        GuestDAO.addGuest(name,phone,email,isGroup);
    }
}
