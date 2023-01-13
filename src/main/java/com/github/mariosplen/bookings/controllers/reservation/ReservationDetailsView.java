package com.github.mariosplen.bookings.controllers.reservation;


import com.github.mariosplen.bookings.models.Book;
import com.github.mariosplen.bookings.models.BookDAO;
import com.github.mariosplen.bookings.models.Guest;
import com.github.mariosplen.bookings.models.GuestDAO;
import com.github.mariosplen.bookings.util.Nav;
import com.github.mariosplen.bookings.util.Views;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDetailsView {
    private final int numberOfNights;
    private final int roomId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    @FXML
    private Text roomIDText, categoryText, numberOfNightsText, finalPriceText, errorMsg;
    @FXML
    private TextField doorPrice;

    @FXML
    private SearchableComboBox<String> usersSearchBox;
    private int totalPrice;


    public ReservationDetailsView(Book tempBook) throws SQLException, ClassNotFoundException {
        roomId = tempBook.roomId();
        checkInDate = tempBook.checkIn();
        checkOutDate = tempBook.checkOut();

        numberOfNights = checkInDate.until(checkOutDate).getDays();


        Platform.runLater(() -> {

            try {
                setUsersInBox();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            numberOfNightsText.setText("Number of nights: " + numberOfNights);
            roomIDText.setText("room ID: " + roomId);

            categoryText.setText(tempBook.roomCategory());


        });
    }

    @FXML
    private void refresh() {
        totalPrice = numberOfNights * Integer.parseInt(doorPrice.getText());
        finalPriceText.setText("$ " + totalPrice);
    }

    private void setUsersInBox() throws SQLException, ClassNotFoundException {
        List<Guest> guestList = GuestDAO.getGuests();
        usersSearchBox.setItems(
                FXCollections.observableArrayList(guestList.stream().map(Guest::name).collect(Collectors.toList()))
        );
    }


    public void onAddGuestBtnClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.NEW_GUEST));
        Stage popUpStage = new Stage();
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        popUpStage.setScene(scene);
        popUpStage.show();
        popUpStage.setOnCloseRequest(windowEvent -> {
            try {
                setUsersInBox();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void onSubmitClicked() throws IOException, SQLException, ClassNotFoundException {
        errorMsg.setVisible(false);
        if (usersSearchBox.getValue() == null || totalPrice == 0) {
            errorMsg.setText("Please enter correct parameters!");
            errorMsg.setVisible(true);
            return;
        }

        BookDAO.addBook(
                roomId,
                categoryText.getText(),
                usersSearchBox.getValue(),
                checkInDate,
                checkOutDate,
                LocalDate.now(),
                totalPrice
        );

        Nav.toHome();
    }
}




