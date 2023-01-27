package com.github.mariosplen.bookings.controllers.reservation;

import com.github.mariosplen.bookings.models.Book;
import com.github.mariosplen.bookings.models.Room;
import com.github.mariosplen.bookings.models.RoomDAO;
import com.github.mariosplen.bookings.util.Nav;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationView implements Initializable {

    public ChoiceBox<String> categoryChoiceBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Button continueButton;
    @FXML
    private DatePicker checkInDP, checkOutDP;
    private Book tempBook;

    private List<Room> availableRooms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        continueButton.setOnAction(actionEvent -> {
            try {
                Nav.toResDetails(tempBook);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        setDatePickers();

        // Populate the categoryChoiceBox with category names from the database
        fillCategoryChoiceBox();
    }

    public void checkAvailabilityClicked() throws SQLException, ClassNotFoundException {
        if (availableRooms != null) {
            availableRooms.clear();
        }

        // Hide the continue button and error label initially
        continueButton.setVisible(false);
        errorLabel.setVisible(false);

        // Check that the check-in and check-out dates are not null
        if (checkInDP.getValue() == null || checkOutDP.getValue() == null) {
            errorLabel.setVisible(true);
            errorLabel.setText("Error please enter valid parameters");
            return;
        }

        // Get the selected category name
        String cat;
        if (categoryChoiceBox.getValue().equals("Single")) {
            cat = "Single";
        } else if (categoryChoiceBox.getValue().equals("Twin")) {
            cat = "Twin";
        } else if (categoryChoiceBox.getValue().equals("Double")) {
            cat = "Double";
        } else {
            // If the selected category is not recognized, show an error message
            errorLabel.setVisible(true);
            errorLabel.setText("Error please enter valid parameters");
            return;
        }
        // Get a list of available rooms that match the selected category and date range
        availableRooms = RoomDAO.getAvailableForDate(checkInDP.getValue(), checkOutDP.getValue(), cat);

        if (availableRooms.size() > 0) {
            // If at least one room is available, show the room's ID and enable the continue button
            errorLabel.setText(String.format("Room Found with id: %s", availableRooms.get(0).id()));
            Room foundRoom = availableRooms.get(0);
            errorLabel.setVisible(true);
            tempBook = new Book(
                    -1,
                    foundRoom.id(),
                    categoryChoiceBox.getValue(),
                    null,
                    checkInDP.getValue(),
                    checkOutDP.getValue(),
                    null,
                    0
            );

            continueButton.setVisible(true);
        } else {
            // If no rooms are available, show an error message
            errorLabel.setVisible(true);
            errorLabel.setText("No rooms found!");
        }
    }

    private void fillCategoryChoiceBox() {
        categoryChoiceBox.getItems().addAll(
                "Double", "Single", "Twin"
        );
    }

    private void setDatePickers() {
        // Set the minimum date for check-in to be today
        LocalDate start = LocalDate.now();

        // Disable check-in dates that are before today or after the check-out date
        checkInDP.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                try {
                    setDisable(item.isBefore(start) || item.isAfter(checkOutDP.getValue().minusDays(1)));
                } catch (Exception e) {
                    setDisable(item.isBefore(start));
                }
            }
        });

        // Disable check-out dates that are before the check-in date
        checkOutDP.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                try {
                    setDisable(item.isBefore(checkInDP.getValue().plusDays(1)));
                } catch (Exception e) {
                    setDisable(item.isBefore(LocalDate.now().plusDays(1)));
                }
            }
        });
    }
}
