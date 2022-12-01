package bookings.controllers.login;

import bookings.controllers.MainView;
import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginView {


    private Stage stage;
    @FXML
    private Label msg;
    @FXML
    private Hyperlink registerHL;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onLoginBtnClicked() throws IOException, SQLException, ClassNotFoundException {

        User user = UserDAO.login(usernameTF.getText(), passwordTF.getText());

        if (user == null) {
            msg.setText("Wrong Password Or Username");
            return;
        }

        // Initialize controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.MAIN));
        Scene scene = new Scene(loader.load());

        // Pass parameters to controller
        MainView mainView = loader.getController();
        mainView.setUser(user);
        mainView.setStage(stage);

        // Set scene to new window
        stage.setScene(scene);


    }
}