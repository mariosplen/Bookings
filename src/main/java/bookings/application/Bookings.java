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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LAUNCHER));
        Scene scene = new Scene(fxmlLoader.load());
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }
}

