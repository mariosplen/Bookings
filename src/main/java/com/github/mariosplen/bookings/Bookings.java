package com.github.mariosplen.bookings;

import com.github.mariosplen.bookings.util.Nav;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Bookings extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Nav.toLoading(stage);
    }
}
