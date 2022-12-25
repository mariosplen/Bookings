package bookings.controllers.recovery;

import bookings.controllers.login.LoginView;
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

public class RecoverView {
    public Text showPasswordTxt;
    public Text errorMsg;
    public PasswordField hotelKey;
    public TextField usernameTF;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onBackButtonClicked() throws IOException {
        // Initialize login view controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.LOGIN));
        Scene scene = new Scene(loader.load());

        // Pass stage to the login view the controller
        LoginView loginView = loader.getController();
        loginView.setStage(stage);

        // Set the scene as the current scene
        stage.setScene(scene);

    }

    @FXML
    private void onShowBtnClicked() throws SQLException, ClassNotFoundException {
        errorMsg.setVisible(false);
        showPasswordTxt.setVisible(false);

        // Check if hotel key is correct
        if (hotelKey.getText().equals("secrethotelkey123")) {
            // Check if user exists
            for (User user : UserDAO.getUsers()) {
                if (user.username().equals(usernameTF.getText())) {
                    errorMsg.setVisible(false);
                    showPasswordTxt.setText(showPasswordTxt.getText() + user.password());
                    showPasswordTxt.setVisible(true);
                    return;
                }
            }
        }
        // If hotel key is incorrect or user does not exist, show error message
        errorMsg.setVisible(true);
    }
}
