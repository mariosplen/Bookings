package com.github.mariosplen.bookings.controllers.home;


import com.github.mariosplen.bookings.util.Nav;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeView implements Initializable {


    @FXML
    private Button newGuestBtn, calendarBtn, guestsButton, usersButton, checkInBtn, booksBtn;
    @FXML
    private HBox logoutButton;


    public void refresh() {
        if (Nav.user.canManageGuests()) {
            guestsButton.setDisable(false);
        }
        if (Nav.user.canManageUsers()) {
            usersButton.setDisable(false);
        }
        if (Nav.user.canDoBasic()) {
            checkInBtn.setDisable(false);
            newGuestBtn.setDisable(false);
            calendarBtn.setDisable(false);
            booksBtn.setDisable(false);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();

        newGuestBtn.setOnAction(actionEvent -> {
            try {
                Nav.toNewGuest();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        calendarBtn.setOnAction(actionEvent -> {
            try {
                Nav.toCalendar();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        guestsButton.setOnAction(actionEvent -> {
            try {
                Nav.toGuests();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        usersButton.setOnAction(actionEvent -> {
            try {
                Nav.toUsers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        logoutButton.setOnMouseClicked(mouseEvent -> {
            try {
                Nav.toLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        checkInBtn.setOnAction(actionEvent -> {
            try {
                Nav.toReservation();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        booksBtn.setOnAction(actionEvent -> {
            try {

                Nav.toBooks();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
