package bookings.controllers.register;

import bookings.controllers.login.LoginView;
import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterView {
    public BorderPane mainPane;
    @FXML
    private Text errorMsg;
    @FXML
    private PasswordField hotelKeyTF, passwordTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private CheckBox editUsersCB, editGuestsCB, statsCB, basicCB;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onRegisterBtnClicked() throws IOException, SQLException, ClassNotFoundException {

        errorMsg.setVisible(false);

        // Check if username, password, and hotel key are not blank
        // Check if hotel key is correct
        if (usernameTF.getText().isBlank() || passwordTF.getText().isBlank() || !hotelKeyTF.getText().equals("secrethotelkey123")) {
            errorMsg.setText("Please enter a username, password, and hotel key (ask the owner for the hotel key)");
            errorMsg.setVisible(true);
            return;
        }

        // Check if username is already taken
        for (User user : UserDAO.getUsers()) {
            if (user.username().equals(usernameTF.getText())) {
                errorMsg.setText("A user with this username already exists");
                errorMsg.setVisible(true);
                return;
            }
        }

        // If no errors, add new user to the database
        if (!errorMsg.isVisible()) {
            UserDAO.addUser(
                    usernameTF.getText(),
                    passwordTF.getText(),
                    basicCB.isSelected(),
                    statsCB.isSelected(),
                    editGuestsCB.isSelected(),
                    editUsersCB.isSelected()
            );
            onBackButtonClicked();
        }
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
}
