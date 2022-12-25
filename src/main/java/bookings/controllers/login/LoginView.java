package bookings.controllers.login;

import bookings.controllers.MainView;
import bookings.controllers.recovery.RecoverView;
import bookings.controllers.register.RegisterView;
import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginView {
    @FXML
    private PasswordField passwordTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private Text errorMsg;
    private Stage stage;

    @FXML
    private void onForgotPassHLClicked() throws IOException {
        // Load the recovery view
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.RECOVERY));
        Scene scene = new Scene(loader.load());

        // Get the recovery view controller and pass the stage to it
        RecoverView recoverView = loader.getController();
        recoverView.setStage(stage);

        // Set the scene to the recovery view
        stage.setScene(scene);
    }

    @FXML
    private void onLoginBtnClicked() throws SQLException, ClassNotFoundException, IOException {

        // Try to log the user in
        User user = UserDAO.login(usernameTF.getText(), passwordTF.getText());

        // If the login fails, display an error message
        if (user == null) {
            errorMsg.setVisible(true);
            return;
        }

        // Load the main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.MAIN));
        Scene scene = new Scene(loader.load());

        // Get the main view controller and pass the user and stage to it
        MainView mainView = loader.getController();
        mainView.setUser(user);
        mainView.setStage(stage);

        // Set the scene to the main view
        stage.setScene(scene);
    }

    @FXML
    private void onRegisterHLClicked() throws IOException {

        // Load the register view
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.REGISTER));
        Scene scene = new Scene(loader.load());

        // Get the register view controller and pass the stage to it
        RegisterView registerView = loader.getController();

        registerView.setStage(stage);

        // Set the scene to the register view
        stage.setScene(scene);
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
