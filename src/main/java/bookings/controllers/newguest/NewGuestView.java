package bookings.controllers.newguest;

import bookings.models.GuestDAO;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class NewGuestView {

    public TextField nameTF;
    public TextField phoneTF;
    public TextField emailTF;
    public CheckBox checkbox;
    public Text errormsg;

    public void onSaveClicked() throws SQLException, ClassNotFoundException {
        errormsg.setText("");
        if (nameTF.getText().isBlank() || phoneTF.getText().isBlank() || emailTF.getText().isBlank()) {
            errormsg.setText("Λάθος στα στοιχεία");
            return;
        }


        GuestDAO.addGuest(nameTF.getText(), phoneTF.getText(), emailTF.getText(), checkbox.isSelected());
        errormsg.setText("Προστέθηκε νέος πελάτης!");
        nameTF.setText("");
        phoneTF.setText("");
        emailTF.setText("");
    }
}
