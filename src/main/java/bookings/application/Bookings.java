package bookings.application;

import bookings.controllers.loading.LoadingView;
import bookings.util.Views;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Bookings extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        // Initialize controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOADING));
        Scene scene = new Scene(fxmlLoader.load());

        // Pass stage to controller
        LoadingView loadingView = fxmlLoader.getController();
        loadingView.setStage(stage);

        // Show controller
        stage.setScene(scene);
        stage.show();

        // Resizable set back to false because of intro video
        stage.setResizable(false);

    }
}

