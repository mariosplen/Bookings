package com.github.mariosplen.bookings.controllers.guests;

import com.github.mariosplen.bookings.models.Guest;
import com.github.mariosplen.bookings.models.GuestDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GuestsView implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private Button selectedBtn;
    @FXML
    private TextField selectedTF;
    private Guest guest;
    @FXML
    private ComboBox<String> usersSearchBox;
    private String selectedInfoToEdit;
    private Map<String, Guest> guestMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load the list of guests from the database
        try {
            List<Guest> guestList = GuestDAO.getGuests();
            guestMap = guestList.stream().collect(Collectors.toMap(Guest::name, Function.identity()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Populate the searchable combo box with the names of the guests
        usersSearchBox.setItems(FXCollections.observableArrayList(guestMap.keySet()));
    }

    public void clickedEditSelector(ActionEvent actionEvent) {
        msg.setVisible(false);
        // Get the text of the button that was clicked
        selectedInfoToEdit = ((Button) actionEvent.getSource()).getText();
        // Set the text of the selected button to the selected info to edit
        selectedBtn.setText(selectedInfoToEdit);
        // Find the selected guest based on the selected name in the combo box
        guest = guestMap.get(usersSearchBox.getValue());

        if (selectedInfoToEdit != null) {
            // Enable editing for the selected text field
            selectedTF.setEditable(true);
        }

        if (guest != null) {
            // Set the text of the selected text field to the selected info for the guest
            assert selectedInfoToEdit != null;

            selectedTF.setText(fromInfoToValue(selectedInfoToEdit));
        }
    }

    private String fromInfoToValue(String selectedInfoToEdit) {
        String text;
        switch (selectedInfoToEdit) {
            case "Name":
                text = guest.name();
                break;
            case "Phone":
                text = guest.phone();
                break;
            case "email":
                text = guest.email();
                break;
            default:
                text = selectedInfoToEdit;
                break;
        }
        return text;
    }

    public void selectedNewUserClicked() {
        msg.setVisible(false);
        // Find the selected guest based on the selected name in the combo box
        guest = guestMap.get(usersSearchBox.getValue());

        if (guest != null && selectedInfoToEdit != null) {
            // Set the text of the selected text field to the selected info for the guest

            selectedTF.setText(fromInfoToValue(selectedInfoToEdit));
        }
    }

    public void onSaveClicked() throws SQLException, ClassNotFoundException {
        String databasePar = selectedInfoToEdit;
        msg.setVisible(false);
        if (databasePar != null) {

            GuestDAO.updateData(guest.name(), databasePar, selectedTF.getText());

            // Refresh the list of guests from the database
            guestMap = GuestDAO.getGuests().stream().collect(Collectors.toMap(Guest::name, Function.identity()));

            // Update the items in the combo box to reflect the updated list of guests
            usersSearchBox.setItems(FXCollections.observableArrayList(guestMap.keySet()));
            msg.setVisible(true);
        }

    }
}
