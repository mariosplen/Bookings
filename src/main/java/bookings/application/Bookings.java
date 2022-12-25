package bookings.application;

import bookings.controllers.loading.LoadingView;
import bookings.util.Views;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Bookings extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        // Load the FXML file for the loading view and initialize the controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOADING));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the controller and set the stage on it
        LoadingView loadingView = fxmlLoader.getController();
        loadingView.setStage(stage);

        // Set the scene on the stage
        stage.setScene(scene);

        // Set the icon of the stage
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/icons/icon.png")).toExternalForm()));

        // Set the title of the stage
        stage.setTitle("Hotel Reservation Management");

        // Make the stage non-resizable, because of intro video
        stage.setResizable(false);


        // Show the stage
        stage.show();
    }
}

