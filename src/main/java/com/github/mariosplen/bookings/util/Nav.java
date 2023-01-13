package com.github.mariosplen.bookings.util;


import com.github.mariosplen.bookings.controllers.MainView;
import com.github.mariosplen.bookings.controllers.reservation.ReservationDetailsView;
import com.github.mariosplen.bookings.models.Book;
import com.github.mariosplen.bookings.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Nav {
    private static Stage stage;
    public static BorderPane content;
    public static Button backBtn;
    public static User user;

    public static void toLoading(Stage stage) throws IOException {
        Nav.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Nav.class.getResource(Views.LOADING));
        Nav.stage.setScene(new Scene(fxmlLoader.load()));
        // Set the icon of the stage
        stage.getIcons().add(new Image(Objects.requireNonNull(Nav.class.getResource("/com/github/mariosplen/bookings/media/icons/icon.png")).toExternalForm()));

        // Set the title of the stage
        stage.setTitle("Hotel Reservation Management");

        // Make the stage non-resizable, because of intro video
        stage.setResizable(false);

        // Show the stage
        stage.show();
    }

    public static void toLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Nav.class.getResource(Views.LOGIN));
        stage.setScene(new Scene(fxmlLoader.load()));

        // Set the stage to be resizable again
        stage.setResizable(true);

        // Set the minimum dimensions for the stage
        stage.setMinHeight(600);
        stage.setMinWidth(800);
    }

    public static void toRegister() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.REGISTER));
        Node content = contentLoader.load();

        FXMLLoader fxmlLoader = new FXMLLoader(Nav.class.getResource(Views.MAIN));
        fxmlLoader.setControllerFactory(aClass -> new MainView(null, content));
        stage.setScene(new Scene(fxmlLoader.load()));


    }

    public static void toRecovery() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.RECOVERY));

        stage.setScene(new Scene(contentLoader.load()));
    }

    public static void toHome() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.HOME));

        FXMLLoader loader = new FXMLLoader(Nav.class.getResource(Views.MAIN));

        loader.setControllerFactory(controllerClass -> {
            try {
                return new MainView(user, contentLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        stage.setScene(new Scene(loader.load()));
        MainView mainViewController = loader.getController();
        backBtn =  mainViewController.getBackBtn();
        backBtn.setVisible(false);
        Nav.content = mainViewController.contentPane;
    }

    public static void toCalendar() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.CALENDAR));
        content.setCenter(contentLoader.load());
        backBtn.setVisible(true);
    }


    public static void toNewGuest() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.NEW_GUEST));
        content.setCenter(contentLoader.load());
        backBtn.setVisible(true);
    }

    public static void toUsers() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.USERS));
        content.setCenter(contentLoader.load());
        backBtn.setVisible(true);
    }

    public static void toGuests() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.GUESTS));
        content.setCenter(contentLoader.load());
        backBtn.setVisible(true);
    }

    public static void toReservation() throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(Nav.class.getResource(Views.RESERVATION));
        content.setCenter(contentLoader.load());
        backBtn.setVisible(true);
    }

    public static void toResDetails(Book tempBook) throws IOException {
        FXMLLoader loader = new FXMLLoader(Nav.class.getResource(Views.RESERVATION_DETAILS));
        loader.setControllerFactory(controllerClass -> {
            try {
                return new ReservationDetailsView(tempBook);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        content.setCenter(loader.load());
        backBtn.setVisible(true);
    }


    public static void toBooks() throws IOException {
        FXMLLoader loader = new FXMLLoader(Nav.class.getResource(Views.BOOKS));
        content.setCenter(loader.load());
        backBtn.setVisible(true);
    }



}
