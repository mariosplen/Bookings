package bookings.controllers.reservation;

import bookings.models.Category;
import bookings.models.CategoryDAO;
import bookings.models.Room;
import bookings.models.RoomDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class ReservationView implements Initializable {
    public ChoiceBox<String> categoryChoiceBox;
    public Label errorLabel;
    public Button continueButton;
    private BorderPane mainPane;
    private Room foundRoom;
    @FXML
    private DatePicker checkInDP, checkOutDP;
    @SuppressWarnings("unused")
    private List<Room> availableRooms;

    public void setMainPane(BorderPane mainPane) {

        this.mainPane = mainPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        // Populate the categoryChoiceBox with category names from the database
        try {
            categoryChoiceBox.getItems().addAll(
                    CategoryDAO.getCategories().stream().map(Category::name).toList()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void handleContinueButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.RESERVATION_DETAILS));
        mainPane.setCenter(loader.load());
        ReservationDetailsView reservationDetailsView = loader.getController();

        reservationDetailsView.setMainPane(mainPane);
        reservationDetailsView.setCategory(categoryChoiceBox.getValue());
        reservationDetailsView.setCheckInDate(checkInDP.getValue());
        reservationDetailsView.setCheckOutDate(checkOutDP.getValue());
        reservationDetailsView.setRoomId(String.valueOf(foundRoom.id()));

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
        if (categoryChoiceBox.getValue().equals("Μονόκλινο")) {
            cat = "Single";
        } else if (categoryChoiceBox.getValue().equals("Δίκλινο")) {
            cat = "Twin";
        } else if (categoryChoiceBox.getValue().equals("Φαρδύκλινο")) {
            cat = "Double";
        } else {
            // If the selected category is not recognized, show an error message
            errorLabel.setVisible(true);
            errorLabel.setText("Error please enter valid parameters");
            return;
        }
        // Get a list of available rooms that match the selected category and date range
        List<Room> availableRooms = RoomDAO.getAvailableForDate(checkInDP.getValue(), checkOutDP.getValue(), cat);

        if (availableRooms.size() > 0) {
            // If at least one room is available, show the room's ID and enable the continue button
            errorLabel.setText(String.format("Room Found with id: %s", availableRooms.get(0).id()));
            foundRoom = availableRooms.get(0);
            errorLabel.setVisible(true);
            continueButton.setVisible(true);
        } else {
            // If no rooms are available, show an error message
            errorLabel.setVisible(true);
            errorLabel.setText("No rooms found!");
        }
    }
}