package bookings.controllers.guests;

import bookings.models.Guest;
import bookings.models.GuestDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.SearchableComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GuestsView implements Initializable {
    @FXML
    private Button selectedBtn;
    @FXML
    private TextField selectedTF;
    private Guest guest;
    @FXML
    private SearchableComboBox<String> usersSearchBox;
    private String selectedInfoToEdit;
    private List<Guest> guestList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load the list of guests from the database
        try {
            guestList = GuestDAO.getGuests();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Populate the searchable combo box with the names of the guests
        usersSearchBox.setItems(
                FXCollections.observableArrayList(guestList.stream().map(Guest::name).collect(Collectors.toList()))
        );
    }

    public void clickedEditSelector(ActionEvent actionEvent) {
        // Get the text of the button that was clicked
        selectedInfoToEdit = ((Button) actionEvent.getSource()).getText();
        // Set the text of the selected button to the selected info to edit
        selectedBtn.setText(selectedInfoToEdit);
        // Find the selected guest based on the selected name in the combo box
        guest = findSelectedGuest();

        if (selectedInfoToEdit != null) {
            // Enable editing for the selected text field
            selectedTF.setEditable(true);
        }

        if (guest != null) {
            // Set the text of the selected text field to the selected info for the guest
            selectedTF.setText(getSelectedInfo(Objects.requireNonNull(selectedInfoToEdit), guest));
        }
    }

    public void selectedNewUserClicked() {
        // Find the selected guest based on the selected name in the combo box
        guest = findSelectedGuest();

        if (guest != null) {
            // Set the text of the selected text field to the selected info for the guest
            selectedTF.setText(getSelectedInfo(selectedInfoToEdit, guest));
        }
    }

    /**
     * Finds the guest in the list with the name that is currently selected in the combo box.
     *
     * @return The selected guest, or null if no matching guest was found.
     */
    private Guest findSelectedGuest() {
        return guestList.stream()
                .filter(i -> usersSearchBox.getValue().equals(i.name()))
                .findFirst().orElse(null);
    }

    /**
     * Gets the selected information for the given guest based on the specified info type.
     *
     * @param infoType The type of information to retrieve. Possible values are "Όνομα", "Τηλέφωνο", "Γκρούπ", and "email".
     * @param guest    The guest to retrieve the information for.
     * @return The selected information to edit for the guest, or null if the info type is not recognized.
     */
    private String getSelectedInfo(String infoType, Guest guest) {
        return switch (infoType) {
            case "Όνομα" -> guest.name();
            case "Τηλέφωνο" -> guest.phone();
            case "Γκρούπ" -> String.valueOf(guest.isGroup());
            case "email" -> guest.email();
            default -> null;
        };
    }

    public void onSaveClicked() throws SQLException, ClassNotFoundException {
        String databasePar = switch (selectedInfoToEdit) {
            case "Όνομα" -> "name";
            case "Τηλέφωνο" -> "phone";
            case "Γκρούπ" -> "group";
            case "email" -> "email";
            default -> null;
        };

        if (Objects.requireNonNull(databasePar).equals("group")) {
            if (selectedTF.getText().equals("true")) {
                GuestDAO.updateData(guest.id(), databasePar, "1");
            } else {
                GuestDAO.updateData(guest.id(), databasePar, "0");
            }

        }
        GuestDAO.updateData(guest.id(), databasePar, selectedTF.getText());
        try {
            guestList = GuestDAO.getGuests();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Populate the searchable combo box with the names of the guests

        usersSearchBox.setItems(
                FXCollections.observableArrayList(guestList.stream().map(Guest::name).collect(Collectors.toList()))
        );
    }
}
