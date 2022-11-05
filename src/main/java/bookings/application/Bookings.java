package bookings.application;

import bookings.util.Views;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

public class Bookings extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        // Initialize controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LAUNCHER));
        Scene scene = new Scene(fxmlLoader.load());

        // Applying styling for jMetro ProgressBar
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        // Show controller
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }
}

